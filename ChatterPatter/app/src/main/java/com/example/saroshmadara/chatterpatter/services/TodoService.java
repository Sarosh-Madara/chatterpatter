package com.example.saroshmadara.chatterpatter.services;

import android.util.Log;

import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.Todo;
import com.example.saroshmadara.chatterpatter.ui.fragment.TodoFragment;
import com.firebase.client.ChildEventListener;
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

        todoRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Todo obj = dataSnapshot.getValue(Todo.class);
                Log.d("obj from firebase: ",obj.getDesc()+obj.getTag()+obj.getTitle());
                todoListAdapter.add(obj);
                Log.d("key is:"+dataSnapshot.getKey(),"value is: "+dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
