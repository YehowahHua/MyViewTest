package com.yehowah.myviewtest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yehowah.myviewtest.MyRecyclerAdapter;
import com.yehowah.myviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTest1Activity extends AppCompatActivity {
    private static final String TAG = "RecyclerTest1Activity";
    private RecyclerView mRecyclerView;
    private List<String> mDatas;//List<Integer>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test1);

        initData();
        initView();
        //设置布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//horizontal

        //第二个参数就是用于指定方向是竖直还是水平，第三个参数用于指定是否从右到左布局，基本都是false，我们的习惯都是左到右的排列方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        Log.i(TAG, "onCreate: ");
        //设置适配器
        mRecyclerView.setAdapter(new MyRecyclerAdapter(this,mDatas));

        //设置item间的分隔线,使用自带的DividerItemDecoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyView_content);
    }


    private  void initData(){
//        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
//                R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground
//                ,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground));

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <='z' ; i++) {
            mDatas.add(""+(char)i);
        }
    }



}
