package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.saroshmadara.chatterpatter.ChatterPatterApp;
import com.example.saroshmadara.chatterpatter.MainActivity;
import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.User;
import com.example.saroshmadara.chatterpatter.services.core.AuthenticationService;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSignUpFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {


    private static final String TAG = "SignupFragment";
    private Button nextBtn,backBtn,backBtn2,signUpBtn;
    private View view;
    private static User user;
//    Firebase ref = new Firebase("https://chatterapplication.firebaseio.com/");
    private LinearLayout signUpStep1;
    private LinearLayout signUpStep2;
    private EditText email;
    private EditText firstname;
    private EditText lastname;
    private EditText password;
    private EditText confirmPassword;
    private EditText date;


    private String temp = "";
    private DatePickerDialog picker;
    private int year;
    private int month;
    private int day;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnSignUpFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        user = new User();


        signUpStep1 = (LinearLayout) view.findViewById(R.id.linear1);
        signUpStep2 = (LinearLayout) view.findViewById(R.id.linear2);
        password    = (EditText)     view.findViewById(R.id.userPassword);
        confirmPassword = (EditText) view.findViewById(R.id.userConfirmPassword);

        nextBtn = (Button) view.findViewById(R.id.nextBtn);
        backBtn = (Button) view.findViewById(R.id.backbtn);
        backBtn2  = (Button) view.findViewById(R.id.userBackBtn2);
        signUpBtn = (Button) view.findViewById(R.id.userSignupBtn);

        initStep1Views(signUpStep1);
        initStep1Listeners();
        initCalander();
        initStep2Views(signUpStep2);
        initStep2Listeners();
        return view;
    }

    private void initStep2Listeners() {
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstname.getText().equals("") && !password.getText().equals("") && !confirmPassword.getText().equals("") && !lastname.getText().equals("")){
                    if(password.getText().toString().equals(confirmPassword.getText().toString())){
                        user.setFirstName(firstname.getText().toString());
                        user.setLastName(lastname.getText().toString());
                        final String userkey = user.getEmail().replace(".", ">");
                        user.setUserID(userkey);
                        user.setLastLogin(String.valueOf(System.currentTimeMillis()));
                        user.setStatus("offline");

                        FirebaseHandler.getInstance().getFirebaseRoot().createUser(user.getEmail(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {
                                Toast.makeText(getActivity(), "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_SHORT).show();
                                AuthenticationService.signUp(userkey, user);
                                ((MainActivity)mListener).onBackPressed();
                                saveToInternal(user);
                                ChatterPatterApp.setApplicationUser(user);
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Toast.makeText(getActivity(), "Error while signing up", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(getActivity(),"Password should be same!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Missing First name",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void saveToInternal(User user) {

        SharedPreferences mPrefs = MainActivity.getChatPrefs();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(ChatterPatterApp.appuser,json);
        prefsEditor.apply();

    }

    private void changeView() {
        signUpStep2.setVisibility(View.INVISIBLE);
        signUpStep1.setVisibility(View.VISIBLE);
    }

    private void initStep2Views(View signUpStep2) {
        firstname = (EditText) signUpStep2.findViewById(R.id.userFirstName);
        lastname  = (EditText) signUpStep2.findViewById(R.id.userLastName);
        password  = (EditText) signUpStep2.findViewById(R.id.userPassword);
        confirmPassword = (EditText) signUpStep2.findViewById(R.id.userConfirmPassword);
    }

    private void initStep1Listeners() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractFields();
            }

            private void extractFields() {
                if (email.getText().length() < 1)
                    Toast.makeText(getActivity(), "Email Required", Toast.LENGTH_SHORT).show();
                else
                    user.setEmail(getEmail());
            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true)
                {
                    picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            monthOfYear++;
                            temp = "" + dayOfMonth + "/" + monthOfYear + "/" + year;

                            date.setText(temp);
                        }
                    }, year, month, day);
                    picker.show();
                }
            }
        });

        date.setOnKeyListener(new View.OnKeyListener() {
              @Override
              public boolean onKey(View v, int keyCode, KeyEvent event) {
                  date.setText(temp);
                  return false;
              }
          }
        );

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mListener).onBackPressed();
            }
        });
    }

    private String getEmail() {
        if(email.getText().toString().contains("@") && email.getText().toString().contains(".")){
            nextStep();
            return email.getText().toString();
        }else{
            Toast.makeText(getActivity(),"Invalid Email Address",Toast.LENGTH_SHORT).show();
        }
        return email.getText().toString();
    }

    private void nextStep() {
        signUpStep1.setVisibility(View.INVISIBLE);
        signUpStep2.setVisibility(View.VISIBLE);
    }

    private void initStep1Views(View view) {
        email = (EditText) view.findViewById(R.id.signupEmail);
        date  = (EditText) view.findViewById(R.id.dateText);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSignupFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSignUpFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSignUpFragmentInteractionListener");
        }
    }
    public  void initCalander ()
    {
        Calendar c  = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);

        Log.d("Date : ", " " + day + " " + month + " " + year);
        date.setText("" + day + "/" + month + "/" + year);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSignUpFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onSignupFragmentInteraction(Uri uri);
    }

    private void signUpUser() {
        Toast.makeText(getActivity(), "signUpUser clicked", Toast.LENGTH_SHORT).show();
    }
}
