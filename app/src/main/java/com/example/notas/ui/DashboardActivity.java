package com.example.notas.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.notas.R;
import com.example.notas.databinding.ActivityNotasBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity  {

    private ActivityNotasBinding binding;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {
        Fragment f = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                f = new NotaFragment();
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        if (f != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, f)
                    .commit();
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new NotaFragment())
                .commit();
    }

}
//
    //@Override
   // protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_notas);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
            //    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
          //      .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_notas);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(binding.navView, navController);

       // getSupportFragmentManager()
                //.beginTransaction()
                //.add(com.google.android.material.R.id.container, new NotaFragment())
                //.commit();//
    //



