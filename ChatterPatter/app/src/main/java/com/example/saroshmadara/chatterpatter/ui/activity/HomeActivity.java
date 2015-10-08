package com.example.saroshmadara.chatterpatter.ui.activity;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.models.DrawerItem;
import com.example.saroshmadara.chatterpatter.ui.fragment.CustomDrawerAdapter;
import com.example.saroshmadara.chatterpatter.ui.fragment.HomeFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.TodoFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.UserProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initializing
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

//        mDrawerLayout.setDrawerShadow(R.drawable.backshade,
//                GravityCompat.START);

        // Add Drawer Item to dataList
        dataList.add(new DrawerItem(true));

        dataList.add(new DrawerItem("Connections"));
        dataList.add(new DrawerItem("My Profile", R.drawable.ic_action_message));
        dataList.add(new DrawerItem("Following", R.drawable.ic_action_following));
        dataList.add(new DrawerItem("Followers", R.drawable.ic_action_follower));
        dataList.add(new DrawerItem("Group Chat", R.drawable.groupchat));

        dataList.add(new DrawerItem("Main Options"));
        dataList.add(new DrawerItem("Tasks", R.drawable.todo_icon));
        dataList.add(new DrawerItem("Cloud", R.drawable.ic_action_message));
        dataList.add(new DrawerItem("Camara", R.drawable.ic_action_camera));
        dataList.add(new DrawerItem("Video", R.drawable.ic_action_following));
        dataList.add(new DrawerItem("Groups", R.drawable.groupchat));
        dataList.add(new DrawerItem("Import & Export",R.drawable.ic_action_help));

        dataList.add(new DrawerItem("Other Options"));
        dataList.add(new DrawerItem("About", R.drawable.ic_action_about));
        dataList.add(new DrawerItem("Settings", R.drawable.ic_action_camera));
        dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));

        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
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
            } else {
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
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 4:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 5:
                fragment = new TodoFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 7:
                fragment = new TodoFragment();
                break;
            case 8:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 9:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 10:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 11:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 12:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 14:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 15:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 16:
                fragment = new HomeFragment();
                args.putString(HomeFragment.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(HomeFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getSupportFragmentManager();
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

    public class LoginTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
}