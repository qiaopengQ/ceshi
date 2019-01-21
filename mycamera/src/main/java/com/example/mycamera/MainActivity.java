package com.example.mycamera;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button xiangce;
    private Button quanxian;
    private Button xiangji;
    private ImageView iv_test;
    private File cameraSavePath;//拍照照片路径
    private Uri uri;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        xiangce = (Button) findViewById(R.id.xiangce);
        quanxian = (Button) findViewById(R.id.quanxian);
        xiangji = (Button) findViewById(R.id.xiangji);
        iv_test = (ImageView) findViewById(R.id.iv_test);

        xiangce.setOnClickListener(this);
        quanxian.setOnClickListener(this);
        xiangji.setOnClickListener(this);
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiangce:
                goPhotoAlbum();
                break;
            case R.id.quanxian:
                getPermission();
                break;
            case R.id.xiangji:
                goCamera();
                break;
        }
    }
    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }
    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(MainActivity.this, "com.example.hxd.pictest.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        MainActivity.this.startActivityForResult(intent, 1);
    }
    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String photoPath1 = String.valueOf(cameraSavePath);
                Glide.with(MainActivity.this).load(photoPath1).into(iv_test);
            } else {
                String photoPath2 = uri.getEncodedPath();
                Glide.with(MainActivity.this).load(photoPath2).into(iv_test);
            }
            //Log.e("拍照返回图片路径:", photoPath);

        }else if (requestCode == 2 && resultCode == RESULT_OK) {
            String realPathFromUri = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            Glide.with(MainActivity.this).load(realPathFromUri).into(iv_test);
        }
    }
}
