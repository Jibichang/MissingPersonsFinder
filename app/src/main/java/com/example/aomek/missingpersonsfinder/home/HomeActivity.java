package com.example.aomek.missingpersonsfinder.home;

import android.app.ActionBar;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.ui.findandselect.FindAndSelectFragment;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private TextView mTextMessage;
    private ActionBar toolbar;
    private ViewPager mViewPager;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: Started.");

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new FindAndSelectFragment());

//        mViewPager = (ViewPager) findViewById(R.id.frame_contrain);


//        navigation.setItemBackgroundResource(R.color.bback);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new FindAndSelectFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_home);
//                    FindAndSelectFragment fragment = new FindAndSelectFragment();
//                    fragmentTransaction.add(R.id.fragment_container, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_found:
                    fragment = new FindAndSelectFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_found);
                    return true;
                case R.id.navigation_add:
                    fragment = new FindAndSelectFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_add);
                    return true;
                case R.id.navigation_profile:
//                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_contrain, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
