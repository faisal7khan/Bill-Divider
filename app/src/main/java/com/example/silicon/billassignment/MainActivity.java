package com.example.silicon.billassignment;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.silicon.billassignment.Fragment.StartFragment;

public class MainActivity extends AppCompatActivity implements StartFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            StartFragment startFragment = new StartFragment();
            FragmentTransaction fragmentTransaction =  this.getSupportFragmentManager().beginTransaction();
            if(fragmentTransaction == null){
                return;
            }

            fragmentTransaction.add(R.id.fragment_container_content_view,startFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }catch (Exception ex){
                ex.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
