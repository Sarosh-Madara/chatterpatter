package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.Todo;
import com.example.saroshmadara.chatterpatter.services.TodoService;

public class CreateTodoFragment extends Fragment {

    private EditText title;
    private EditText tag;
    private EditText deadline;
    private EditText desc;
    private Button create,cancel;
    private View view;

    private String temp = "";
    private int month;
    private int date;
    private int year;
    private DatePickerDialog datePickerDialog;

    private boolean[] fields = new boolean[3];

    private OnFragmentInteractionListener mListener;


    public CreateTodoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_create_todo, container, false);

        initViews(view);
        initListeners();

        return view;
    }

    private void initListeners() {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo obj = new Todo();

                if(title.getText().toString().equals("") || title.getText().toString() == null)     fields[0] = false;
                else fields[0] = true;

                if(tag.getText().toString().equals("") || tag.getText().toString() == null)   fields[1] = false;
                else fields[1] = true;

                if(desc.getText().toString().equals("") || desc.getText().toString() == null) fields[2] = false;
                else fields[2] = true;

                if(fields[0] == false && fields[1] == false){
                    title.setError(null, ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_about));
                    tag.setError(null,ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_about));
                }
//                else if(fields[0] && fields[1] && fields[2]) {
//                    obj.setTitle(title.getText().toString());
//                    obj.setTag(tag.getText().toString());
//                    obj.setDesc(desc.getText().toString());
//                }else if(fields[0] && fields[1]){
//                    obj.setTitle(title.getText().toString());
//                    obj.setTag(tag.getText().toString());
//                    obj.setDesc("null");
//                }
//
//
//                TodoService.writeTodo(obj.getTitle().toString() + "~" + System.currentTimeMillis(), obj);
            }
        });


        deadline.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            monthOfYear++;
                            temp = dayOfMonth+"/"+monthOfYear+"/"+year;
                            deadline.setText(temp);
                        }
                    },year,month,date);
                }
            }
        });
    }

    private void initViews(View view) {
         title = (EditText) view.findViewById(R.id.todoTitleEt);
         tag = (EditText) view.findViewById(R.id.todoTagEt);
         deadline = (EditText) view.findViewById(R.id.todoDeadlineEt);
         desc = (EditText) view.findViewById(R.id.todoDescEt);
         create = (Button) view.findViewById(R.id.todoCreate);
        cancel = (Button) view.findViewById(R.id.todoCancel);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
