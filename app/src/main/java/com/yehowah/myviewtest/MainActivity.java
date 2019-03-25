package com.yehowah.myviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yehowah.myviewtest.ui.MyViewActivity;
import com.yehowah.myviewtest.ui.RecyclerTest1Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Button recyclerViewTestBt;
    private Button rxjavaBt;
    private Button viewBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerViewTestBt = (Button) findViewById(R.id.recyclerViewTestBt);
        viewBt = (Button) findViewById(R.id.viewBt);
        rxjavaBt = (Button) findViewById(R.id.rxjavaBt);
        recyclerViewTestBt.setOnClickListener(this);
        viewBt.setOnClickListener(this);
        rxjavaBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recyclerViewTestBt:
                Log.i(TAG, "onClick: recyclerViewTestBt");
                Intent recyclerIntent = new Intent(this, RecyclerTest1Activity.class);
                startActivity(recyclerIntent);
                break;
            case R.id.viewBt:
                Intent viewIntent = new Intent(this, MyViewActivity.class);
                startActivity(viewIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
