package com.krishna.wrokmanagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStartWork = findViewById(R.id.buttonStartWork);


        buttonStartWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = new Data.Builder()
                        .putString(WorkManagers.EXTRA_TITLE, "Work Done!")
                        .putString(WorkManagers.EXTRA_TEXT, "Hi! I have come from activity.")
                        .build();

                final OneTimeWorkRequest simpleRequest = new OneTimeWorkRequest.Builder(WorkManagers.class)
                        .setInputData(data)
                        .build();
                WorkManager.getInstance().enqueue(simpleRequest);
            }
        });
    }
}
