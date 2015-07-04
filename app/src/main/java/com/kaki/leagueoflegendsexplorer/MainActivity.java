package com.kaki.leagueoflegendsexplorer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.kaki.leagueoflegendsexplorer.fragments.champions.ListChampionsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragment_container, new ListChampionsFragment())
                .commit();
    }
}
