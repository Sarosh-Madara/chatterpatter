package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.firebase.FirebaseArrayListAdapter;
import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.Todo;
import com.example.saroshmadara.chatterpatter.services.TodoService;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;


public class TodoFragment extends Fragment {

    private TodoListAdapter adapter;

    private ImageView addTodoBtn;
    private ListView todoList;

    public static final String TAG = "TodoFragment";
    private LayoutInflater inflater;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_todo,container,false);
        intiView(view);
        initListeners();
        adapter = new TodoListAdapter(getActivity(),R.layout.todo_item);
        return view;
    }

    private void initListeners() {
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("add todo clicked", "TodoFragment");
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new CreateTodoFragment(), "Home").commit();
                getActivity().onPrepareOptionsMenu(null);
                addTodoBtn.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void intiView(View view) {
        todoList = (ListView) view.findViewById(R.id.todoList);
        addTodoBtn = (ImageView) view.findViewById(R.id.create_todo_button);
    }












    public class TodoListAdapter extends FirebaseArrayListAdapter<Todo>{

        private TextView title,datetime,tag,desc;
        private ImageView privacy_img;

        private LayoutInflater inflater;


        public TodoListAdapter(Context context,int res){
            super(FirebaseHandler.getInstance().getUser_todos(),Todo.class,res,(Activity)context);
            inflater = ((Activity)context).getLayoutInflater();
        }


        @Override
        public void addEventListener() {
            TodoService.fetchTodo(this);
        }

        private void initView(View view) {
            title = (TextView) view.findViewById(R.id.title);
            datetime = (TextView) view.findViewById(R.id.datetime);
            tag = (TextView) view.findViewById(R.id.tag);
            desc = (TextView) view.findViewById(R.id.desc);
            privacy_img = (ImageView) view.findViewById(R.id.privacy_img);
        }

        @Override
        protected void populateView(View v, Todo model) {
            initView(v);
            title.setText(model.getTitle());
            datetime.setText(model.getDeadline().toString());
            tag.setText(model.getTag());
            desc.setText(model.getDesc());
        }
    }

}

