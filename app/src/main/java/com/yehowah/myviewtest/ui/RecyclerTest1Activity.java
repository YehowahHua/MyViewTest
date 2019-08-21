package com.yehowah.myviewtest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yehowah.myviewtest.MyRecyclerAdapter;
import com.yehowah.myviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTest1Activity extends AppCompatActivity {
    private static final String TAG = "RecyclerTest1Activity";
    private RecyclerView mRecyclerView;
    private List<String> mDatas;//List<Integer>
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test1);
        context = this;
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
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyView_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;//super.onCreateOptionsMenu(menu)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//和之前设置的默认的是一样的
                Toast.makeText(context, "设置列表式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));//重新设置布局，网格3列
                Toast.makeText(context, "设置3列", Toast.LENGTH_SHORT).show();//需要注意的是分割线加深了，主要是画了3次
                break;
            case R.id.action_hor_gridview:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));//5列
                Toast.makeText(context, "设置水平", Toast.LENGTH_SHORT).show();//注意字母之间的间隔
                break;
            case R.id.action_staggered:
                Toast.makeText(context, "clicked remove", Toast.LENGTH_SHORT).show();
                break;
            default:

        }
        return true;//super.onOptionsItemSelected(item)
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
