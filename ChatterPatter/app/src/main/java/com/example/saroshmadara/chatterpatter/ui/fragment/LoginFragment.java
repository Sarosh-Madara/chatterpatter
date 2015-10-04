package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saroshmadara.chatterpatter.ChatterPatterApp;
import com.example.saroshmadara.chatterpatter.MainActivity;
import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.services.core.AuthenticationService;
import com.example.saroshmadara.chatterpatter.ui.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private TextView registerButton;
    private Button signInBtn;
    private View view;
    private EditText userEmail,userPassword;

    public static boolean isAuthenticated;

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        initListeners();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void init(View view){
        registerButton = (TextView) view.findViewById(R.id.registerButton);
        signInBtn = (Button) view.findViewById(R.id.loginButton);
        userEmail = (EditText) view.findViewById(R.id.signInEmailEt);
        userPassword = (EditText) view.findViewById(R.id.signInPasswordEt);
    }

    private void initListeners(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(1);
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationService.signIn(userEmail.getText().toString(),userPassword.getText().toString());
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
//                if(isAuthenticated){

//                }
//                Log.d("password: " + userPassword.getText().toString(),"  email: "+ userEmail.getText().toString());
//                Toast.makeText(getActivity(),"Login >> password: "+userPassword.getText().toString()+"  email: "+ userEmail.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSignUpFragmentInteractionListener");
        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
