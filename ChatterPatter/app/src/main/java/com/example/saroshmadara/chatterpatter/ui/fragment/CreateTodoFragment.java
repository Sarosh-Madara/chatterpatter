package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

                obj.setTitle(title.getText().toString());
                obj.setTag(tag.getText().toString());
                obj.setDesc(desc.getText().toString());


                TodoService.writeTodo(obj.getTitle().toString() + "~" + System.currentTimeMillis(), obj);
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
