package com.example.jianhu.animationdamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jianhu.animations.Complete;

public class MainActivity extends AppCompatActivity {

    private Complete complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        complete = findViewById(R.id.complete);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                complete.startAnimation();
            }
        });

    }
}
