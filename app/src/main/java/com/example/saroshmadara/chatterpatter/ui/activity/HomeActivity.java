package com.example.saroshmadara.chatterpatter.ui.activity;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.saroshmadara.chatterpatter.ChatterPatterApp;
import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.model.DrawerItem;
import com.example.saroshmadara.chatterpatter.ui.fragment.AddFriendFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.ChatFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.CreateTodoFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.CustomDrawerAdapter;
import com.example.saroshmadara.chatterpatter.ui.fragment.FriendsFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.HomeFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.TodoFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.UserProfileFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.saroshmadara.chatterpatter.R.drawable.ic_drawer;

public class HomeActivity extends AppCompatActivity implements CreateTodoFragment.OnFragmentInteractionListener {
    private static final String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar( toolbar );

        // Initializing
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


//        mDrawerLayout.setDrawerShadow(R.drawable.backshade,
//                GravityCompat.START);

        // Add Drawer Item to dataList
        if(ChatterPatterApp.getApplicationUser() != null)
            dataList.add(new DrawerItem(ChatterPatterApp.getApplicationUser()));

        dataList.add(new DrawerItem("Connections"));
        dataList.add(new DrawerItem("My Profile", R.drawable.ic_action_profile));
        dataList.add(new DrawerItem("Add Friend",R.drawable.ic_action_add_friend));
        dataList.add(new DrawerItem("Friends",R.drawable.ic_action_friends));

        dataList.add(new DrawerItem("Main Options"));
        dataList.add(new DrawerItem("Tasks", R.drawable.ic_action_todo));
        dataList.add(new DrawerItem("Chat",R.drawable.ic_action_chat));

        dataList.add(new DrawerItem("Other Options"));
        dataList.add(new DrawerItem("About", R.drawable.ic_action_about));
        dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));

        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar , R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            if (dataList.get(0).isSpinner()
                    & dataList.get(1).getTitle() != null) {
                SelectItem(2);
            } else if (dataList.get(0).getTitle() != null) {
                SelectItem(1);
            } else if(dataList.get(0).hasUser()){
                SelectItem(2);
            }
            else {
                SelectItem(0);
            }
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(menu == null){

        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {

            case 2:
                fragment = new UserProfileFragment();
                break;
            case 3:
                fragment = new AddFriendFragment();
                break;
            case 4:
                fragment = new FriendsFragment();
                break;
            case 6:
                fragment = new TodoFragment();
                break;
            case 7:
                fragment = new ChatFragment();
                break;
            case 9:
                fragment = new HomeFragment();
                break;
            case 10:
                fragment = new HomeFragment();
                break;
            default:
                break;
        }


        FragmentManager frgManager = getSupportFragmentManager();
        if(fragment != null)
            frgManager.beginTransaction().replace(R.id.content_frame, fragment,"Home")
                    .commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    public void onTodoCreateFragmentInteraction() {
        Log.d(TAG,"onTodoCreateFragmentInteraction");
        SelectItem(2);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if(dataList.get(position).getTitle() == null) {
                SelectItem(position);
                Log.d("onclick", "drawer Clicked");
            }

        }
    }

}
