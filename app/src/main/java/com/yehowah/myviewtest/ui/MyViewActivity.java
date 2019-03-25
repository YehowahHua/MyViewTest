package com.yehowah.myviewtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yehowah.myviewtest.R;

public class MyViewActivity extends AppCompatActivity implements View .OnClickListener{
    private Button paintBt;
    private Button pathBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        initView();
    }

    private void initView() {
        paintBt = (Button) findViewById(R.id.paintBt);
        pathBt = (Button) findViewById(R.id.pathBt);
        paintBt.setOnClickListener(this);
        pathBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paintBt:
                Intent paintIntent = new Intent(this,ViewPaintTestActivity.class);
                startActivity(paintIntent);
                break;
            case R.id.pathBt:
                Intent pathIntent = new Intent(this,ViewPathTestActivity.class);
                startActivity(pathIntent);
                break;

        }
    }
}
