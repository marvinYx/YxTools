package com.marvin.yxtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marvin.jetpack.DataLiveData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataLiveData dataLiveData = new DataLiveData();
    }
}
