package com.example.saroshmadara.chatterpatter.services;

import android.util.Log;

import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.Todo;
import com.example.saroshmadara.chatterpatter.ui.fragment.TodoFragment;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Sarosh Madara on 07-10-2015.
 */
public class TodoService {

    static Firebase todoRef = FirebaseHandler.getInstance().getUser_todos();
    public static void writeTodo(String key,Todo obj){
        todoRef.child(key).setValue(obj);
    }

    public static void fetchTodo(final TodoFragment.TodoListAdapter todoListAdapter){
        todoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Todo obj = dataSnapshot.getValue(Todo.class);
                Log.d(dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());
                Log.d(dataSnapshot.getChildren().toString(),String.valueOf(dataSnapshot.getChildrenCount()));
//                todoListAdapter.add(obj);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
