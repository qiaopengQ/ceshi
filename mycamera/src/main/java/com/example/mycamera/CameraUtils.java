package com.example.mycamera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

public class CameraUtils {
   private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
   private File file =  new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
    //获取权限
    public void getPermission(Context context) {
        if (EasyPermissions.hasPermissions(context, permissions)) {
            //已经打开权限
            Toast.makeText(context, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions((Activity) context, "需要获取您的相册、照相使用权限", 1, permissions);
        }
    }
}
