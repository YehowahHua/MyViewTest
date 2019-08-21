package com.yehowah.myviewtest.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yehowah.myviewtest.R;

import java.io.File;
import java.io.IOException;


//https://blog.csdn.net/zyf994318935/article/details/80545359
//
public class GlideTestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "GlideTestActivity";

    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";


    private ImageView pictureIv;
    private int resId = R.drawable.ic_launcher_foreground;
    private File picFile;
    private Button urlBt;
    private Button resBt;
    private Button uriBt;
    private Button cameraFileBt;
    private Button albumFileBt;

    private Uri cameraImageUri;
    private Uri picUri;
    private Context context;

    String pictureUrl = "https://images.pexels.com/photos/2341290/pexels-photo-2341290.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        context = this;
        initView();

    }

    private void initView() {
        pictureIv = (ImageView) findViewById(R.id.PictureIv);
        urlBt = (Button) findViewById(R.id.urlBt);
        resBt = (Button) findViewById(R.id.resBt);
        uriBt = (Button) findViewById(R.id.uriBt);
        cameraFileBt = (Button) findViewById(R.id.cameraFileBt);
        albumFileBt = (Button) findViewById(R.id.albumFileBt);
        pictureIv.setOnClickListener(this);
        urlBt.setOnClickListener(this);
        resBt.setOnClickListener(this);
        uriBt.setOnClickListener(this);
        cameraFileBt.setOnClickListener(this);
        albumFileBt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.urlBt:
                showUrlView();
                break;
            case R.id.resBt:
                showResView();
                break;
            case R.id.uriBt:
                picUri = resIdToUri(context,R.drawable.ic_launcher_background);
                showUriView(picUri);
                break;
            case R.id.cameraFileBt:
                take_photo();//从相机中获取
                break;
            case R.id.albumFileBt:
                select_photo();//相册中获取
                break;
        }
    }

    private void showUrlView(){
        Glide.with(this)
                .load(pictureUrl)//String 网络url
                .into(pictureIv);
    }
    private void showResView(){
        Glide.with(this)
                .load(resId)//本地drawable
                .into(pictureIv);
    }
    private void showUriView(Uri uri){//注意是Uri,不是Url
        Glide.with(this)
                .load(uri) //图片
                .into(pictureIv);
    }

    private void showFileView(String path){
        picFile = new File(path);
        Glide.with(this)
                .load(picFile) //图片
                .into(pictureIv);
    }


    //转换
    private static Uri resIdToUri(Context context,int resId){
        return  Uri.parse(ANDROID_RESOURCE+context.getPackageName()+FOREWARD_SLASH+resId);
    }

    /**
     * 从相册中获取图片
     * */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            //打开相册
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(intent,SELECT_PHOTO);
        }
    }

    /**
     *拍照获取图片
     **/
    public void take_photo() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            //创建File对象，用于存储拍照后的图片
            File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
            Log.i(TAG, "take_photo: camera take photo file-->"+outputImage.getAbsolutePath());
            //storage/emulated/0/Android/data/com.yehowah.myviewtest/cache/output_image.jpg
            try {
                if (outputImage.exists()) {
                    outputImage.delete();//不保存上次的，直接删除
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                //使用FileProvider生成Uri
                cameraImageUri = FileProvider.getUriForFile(this, "com.yehowah.myviewtest.ui.GlideTestActivity.fileprovider", outputImage);
            } else {
                cameraImageUri = Uri.fromFile(outputImage);
            }
            //启动相机程序
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        } else {

            Toast.makeText(this, "没有储存卡", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO :
                if (resultCode == RESULT_OK) {

                    Log.i(TAG, "onActivityResult: TAKE_PHOTO-- 将拍照获取到的Uri显示到当前ImageView");
//                    try {

//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(cameraImageUri));
//                        pictureIv.setImageBitmap(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
                    showUriView(cameraImageUri);
                }
                break;
            case SELECT_PHOTO :
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImgeOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     *4.4以下系统处理图片的方法
     * */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }
    /**
     * 4.4及以上系统处理图片的方法
     * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
                Log.i(TAG, "handleImgeOnKitKat: media imagePath-->"+imagePath);//获取到的图片文件名的绝对路径
                //

            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
                Log.i(TAG, "handleImgeOnKitKat: downloads imagePath-->"+imagePath);
            }else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri,null);
                Log.i(TAG, "handleImgeOnKitKat: content imagePath-->"+imagePath);
            }else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
                Log.i(TAG, "handleImgeOnKitKat: file imagePath-->"+imagePath);
            }
            //根据图片路径显示图片
            showFileView(imagePath);//storage/emulated/0/Download/browser/图片收藏/ad950efef8439f88a2aa05cbf916aa48.jpg
//            displayImage(imagePath);
        }
    }
    /**
     * 根据图片路径显示图片的方法
     * */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            pictureIv.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 通过uri和selection来获取真实的图片路径
     * */
    private String getImagePath(Uri uri,String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
