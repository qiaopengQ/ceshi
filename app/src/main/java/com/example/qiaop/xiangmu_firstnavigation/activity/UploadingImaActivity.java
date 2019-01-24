package com.example.qiaop.xiangmu_firstnavigation.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.qiaop.xiangmu_firstnavigation.MainActivity;
import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.base.activity.BaseActivity;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListHeaderImageInterFace;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListHeaderImageChannel;
import com.example.qiaop.xiangmu_firstnavigation.presenter.IHeaderImagePresenter;
import com.example.qiaop.xiangmu_firstnavigation.presenter.IListNewsPresenter;
import com.example.qiaop.xiangmu_firstnavigation.utils.RegexUtils;
import com.example.qiaop.xiangmu_firstnavigation.utils.getPhotoFromPhotoAlbum;
import com.wang.avi.AVLoadingIndicatorView;
import com.xw.repo.xedittext.XEditText;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadingImaActivity extends BaseActivity<ListHeaderImageInterFace.ShowView, IHeaderImagePresenter<ListHeaderImageInterFace.ShowView>> implements ListHeaderImageInterFace.ShowView, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_portrait)
    ImageView imageportrait;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ed_username)
    XEditText edUsername;
    @BindView(R.id.text_skip)
    TextView textskip;
    @BindView(R.id.image_accomplish)
    ImageView imageaccomplish;


    private View inflate;
    private TranslateAnimation translateAnimation;
    //最后显示的图片文件
    private String mFile;
    private File cameraSavePath;//拍照照片路径
    private Uri uri;
    private boolean isBiaoShi=true;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private File file1;

    @Override
    protected void initEventAndData() {
        imgBack.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        imageportrait.setOnClickListener(this);
        imageaccomplish.setOnClickListener(this);
        textskip.setOnClickListener(this);
    }

    @Override
    protected IHeaderImagePresenter<ListHeaderImageInterFace.ShowView> createPresenter() {
        return new IHeaderImagePresenter<>();
    }

    @Override
    protected int createLayoutId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            View decorView = getWindow().getDecorView();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
            //设置全屏和状态栏透明View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            /*decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);*/
        }
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        permission();
        return R.layout.activity_uploading_ima;
    }
    private void permission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,//公共存储读写数据
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,//允许程序从非系统拨号器里输入电话号码
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS,
                    Manifest.permission.CAMERA};//允许访问摄像头进行拍照
            ActivityCompat.requestPermissions(UploadingImaActivity.this, mPermissionList, 123);
        }
    }

    @Override
    public void ShowLoadingAnimtion() {
        dialog.show();
    }

    @Override
    public void HideLoadingAnimtion() {
        dialog.hide();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void showList(ListHeaderImageChannel listHeaderImageChannel) {

        if (listHeaderImageChannel!=null){
            /*View viewa = LayoutInflater.from(this).inflate(R.layout.loginsuccessfully_view, null);
            AlertDialog login = new AlertDialog.Builder(this).setView(viewa).create();
            login.show();*/
            /*Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UploadingImaActivity.this,MainActivity.class);
            startActivity(intent);*/
        }else{

        }
            Log.e("图片上传服务器", listHeaderImageChannel.getHeadImagePath());
    }

    @Override
    public void showError(String error) {
        Log.e("error", error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_close:

                break;
            case R.id.image_portrait:
                showUpPop();
                break;
            case R.id.image_accomplish:
                String s = edUsername.getText().toString();
                boolean b = RegexUtils.checkChinese(s);
                if (s.equals("")){
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if (b & s.length()>=20){
                    Toast.makeText(this, "中文必须保持在20个之内", Toast.LENGTH_SHORT).show();
                }else if (!b & s.length()>=40){
                    Toast.makeText(this, "昵称保持在40个字符之内", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UploadingImaActivity.this,MainActivity.class);
                    startActivity(intent);
                    /*if (isBiaoShi){
                        mPresenter.getList("049de01db14a4c8184faa0aca7facf8a",cameraSavePath);
                    }else {
                        mPresenter.getList("049de01db14a4c8184faa0aca7facf8a",file1);
                    }*/
                }
                break;
            case R.id.text_skip:
                View view = LayoutInflater.from(UploadingImaActivity.this).inflate(R.layout.popup_view, null);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                AVLoadingIndicatorView viewById = view.findViewById(R.id.avi_popup);
                viewById.smoothToShow();
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(imageaccomplish, 150, -180);
                view.findViewById(R.id.tv_use).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UploadingImaActivity.this,MainActivity.class);
                        startActivity(intent);
                        popupWindow.dismiss();
                    }
                });
                view.findViewById(R.id.tv_alter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            }
        }
        public void showUpPop () {
            if (popupWindow == null) {
                inflate = View.inflate(this, R.layout.popupwindows_view, null);
                // 参数2,3：指明popupwindow的宽度和高度
                popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);

                // 设置背景图片，必须设置，不然动画没作用
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                // 设置点击popupwindow外屏幕其它地方消失
                popupWindow.setOutsideTouchable(false);
                // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
                translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
                translateAnimation.setInterpolator(new AccelerateInterpolator());
                translateAnimation.setDuration(200);
                ColorDrawable dw = new ColorDrawable(0x30000000);
                popupWindow.setBackgroundDrawable(dw);
            }
            cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
            //设置按钮点击监听
            inflate.findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //拍照
                    goCamera();
                    popupWindow.dismiss();
                }
            });
            inflate.findViewById(R.id.btn_pick_photo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //相册
                    goPhotoAlbum();
                    popupWindow.dismiss();
                }
            });
            inflate.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //取消
                    popupWindow.dismiss();
                }
            });

            // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
            popupWindow.showAtLocation(UploadingImaActivity.this.findViewById(R.id.uploading_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            //inflate.startAnimation(translateAnimation);
            //显示遮罩
            //v_mask.setVisibility(View.VISIBLE);
        }
        //激活相机操作
        private void goCamera () {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(UploadingImaActivity.this, "com.example.hxd.pictest.fileprovider", cameraSavePath);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(cameraSavePath);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            UploadingImaActivity.this.startActivityForResult(intent, 1);
        }
        //激活相册操作
        private void goPhotoAlbum () {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 2);
        }
        /**
         * 裁剪图片方法实现
         *
         * @param uri
         */
        protected void startPhotoZoom (Uri uri){

            if (uri == null) {
                Log.i("tag", "The uri is not exist.");
            }
//        tempUri = uri;
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uri, "image/*");
            // 设置裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 1000);
            intent.putExtra("outputY", 500);
            intent.putExtra("return-data", true);
            File out = new File(getPath());
            if (!out.getParentFile().exists()) {
                out.getParentFile().mkdirs();
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
            startActivityForResult(intent, 3);
        }
        //裁剪后的地址
        public String getPath(){
            //resize image to thumb
            if (mFile == null) {
                mFile = Environment.getExternalStorageDirectory() + "/" + "wode/" + "outtemp.png";
                Log.e("UploadingImaActivity", mFile);
            }
            return mFile;
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            if (requestCode == 1 && resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    String photoPath1 = String.valueOf(cameraSavePath);
                    RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop());
                    Glide.with(UploadingImaActivity.this)
                            .load(photoPath1)
                            .apply(requestOptions)
                            .into(imageportrait);
                    Log.e("头像", "7.0相机"+photoPath1);
                /*Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", cameraSavePath);
                startPhotoZoom(contentUri);//开始对图片进行裁剪处理*/
                } else {
                    isBiaoShi=true;
                    String photoPath2 = uri.getEncodedPath();
                    RequestOptions requestOptions=RequestOptions.bitmapTransform(new CircleCrop());

                                Glide.with(UploadingImaActivity.this)
                                        .load(photoPath2)
                                        .apply(requestOptions)
                                        .into(imageportrait);
                    Log.e("头像", "6.0相机"+photoPath2);
                    //startPhotoZoom(Uri.fromFile(cameraSavePath));//开始对图片进行裁剪处理
                }
                //Log.e("拍照返回图片路径:", photoPath);

            } else if (requestCode == 2 && resultCode == RESULT_OK) {
                String realPathFromUri = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
                RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop());
                Log.e("头像", "相册" + realPathFromUri);
                Glide.with(UploadingImaActivity.this)
                        .load(realPathFromUri)
                        .apply(requestOptions)
                        .into(imageportrait);
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                isBiaoShi=false;
                file1 = getFile(BitmapFactory.decodeFile(picturePath));
                Log.e("Uri-file", "file1:" + file1);
                //显示头像
                RequestOptions requestOptions1 = RequestOptions.circleCropTransform();
//            Glide.with(this).load("http:"+newsImg).apply(requestOptions).into(holder.newsImg);
                Glide.with(this).load(BitmapFactory.decodeFile(picturePath)).apply(requestOptions1).into(imageportrait);
            /*Bundle extras = data.getExtras();
            Bitmap data1 = (Bitmap) extras.get("data");*/
            } else if (requestCode == 3) {
                /*if (data != null) {
                    // 让刚才选择裁剪得到的图片显示在界面上
                    Bitmap photo = BitmapFactory.decodeFile(mFile);
                    imageportrait.setImageBitmap(photo);
                } else {
                    Log.e("data", "data为空");
                }*/
            }

        }

    public File getFile(Bitmap bitmap) {
        //转换成file
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
