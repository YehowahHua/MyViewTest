package com.yehowah.myviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


//https://blog.csdn.net/shaochen2015821426/article/details/80239645
//https://blog.csdn.net/mouzhai/article/details/53760813
//https://blog.csdn.net/u012124438/article/details/53495951
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder> {
    private static final String TAG = "MyRecyclerAdapter";
    private Context mContext;

    private LayoutInflater mInflater;//
    private List<String> mDatas;//Integer
    public MyRecyclerAdapter(Context context, List<String> datas){
        super();
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }


    //重写onCreateViewHolder方法，创建一个自定义的ViewHolder
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //填充布局
//        View view  = LayoutInflater.from(mContext).inflate(R.layout.view_item,null);
        View view = mInflater.inflate(R.layout.item_single_textview,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        Log.i(TAG, "onCreateViewHolder: i--"+i);
        return myHolder;
    }

    //填充onCreateViewHolder方法返回的holder中的控件 将数据绑定到viewHolder的item中
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        Log.i(TAG, "onBindViewHolder: position--"+position);
        myHolder.tv.setText(mDatas.get(position));
//        myHolder.imageView.setImageResource(mDatas.get(position));
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //1.定义内部类继承ViewHolder
    class MyHolder extends RecyclerView.ViewHolder{
//        private ImageView imageView;
        TextView tv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            Log.i(TAG, "MyHolder: ");
//            imageView = (ImageView)itemView.findViewById(R.id.iv_item);\
            tv = (TextView) itemView.findViewById(R.id.id_tv);
        }
    }
}
