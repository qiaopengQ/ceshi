package com.example.qiaop.xiangmu_firstnavigation.activity;

import android.Manifest;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.base.activity.BaseActivity;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.presenter.IListNewsPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadingImaActivity extends BaseActivity<ListNewsInterFace.ListNewsView, IListNewsPresenter<ListNewsInterFace.ListNewsView>> implements ListNewsInterFace.ListNewsView, View.OnClickListener {

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
    EditText edUsername;
    @BindView(R.id.text_skip)
    TextView textskip;
    @BindView(R.id.image_accomplish)
    ImageView imageaccomplish;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_SMALL_PICTURE = 3;
    //调用照相机返回图片文件
    private File file;

    private View inflate;
    private TranslateAnimation translateAnimation;
    private String string;
    private Object picFromCamera;
    //最后显示的图片文件
    private String mFile;

    @Override
    protected void initEventAndData() {
        imgBack.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        imageportrait.setOnClickListener(this);
        imageaccomplish.setOnClickListener(this);
        textskip.setOnClickListener(this);
    }

    @Override
    protected IListNewsPresenter<ListNewsInterFace.ListNewsView> createPresenter() {
        return new IListNewsPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            View decorView = getWindow().getDecorView();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorLogin_text));
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

    }

    @Override
    public void HideLoadingAnimtion() {

    }

    @Override
    public void showListNewsTab(ListDataBean listDataBean) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

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
                break;
            case R.id.text_skip:

                break;
        }
    }

    public void showUpPop() {
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

        //设置按钮点击监听
        inflate.findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照
//                getPicFromCamera();
                takeCamera();
                popupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.btn_pick_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicFromAlbm();
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
        inflate.startAnimation(translateAnimation);
        //显示遮罩
        //v_mask.setVisibility(View.VISIBLE);
    }

    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
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
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        File out = new File(getPath());
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //裁剪后的地址
    public String getPath() {
        //resize image to thumb
        if (mFile == null) {
            mFile = Environment.getExternalStorageDirectory() + "/" + "wode/" + "outtemp.png";
            Log.e("UploadingImaActivity", mFile);
        }
        return mFile;
    }

    //相机拍照
    public void getPicFromCamera() {

    }

    //相册
    public void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }
    private void takeCamera() { Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String cameraFielPath = getApplication().getExternalCacheDir().getPath() + "/Camera.jpg";//图片存放在手机自带内存，地址在你的包名文件夹下
        File outputImage = new File(cameraFielPath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){ //根据6.0的要求需要对文件传输使用新的传输方式FileProvider，没有配置FileProvider 安卓6.0会报错退出当前activity
            //通过FileProvider创建一个content类型的Uri
            Uri imageUrl = FileProvider.getUriForFile(UploadingImaActivity.this, "com.example.qiaop.xiangmu_firstnavigation.provider", outputImage);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//对读取对Url进行临时授权
        }else { //6.0以下不做处理直接使用没问题
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
        } startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri result = null;
        if (requestCode == 2) {
            if (null != data && null != data.getData()) { //选择相机照相后的图片
                result = Uri.fromFile(file);
//            Bitmap bitmap = BitmapFactory.decodeFile(cameraFielPath);
//            compressImageToFile(bitmap, new File(cameraFielPath));//图片压缩
            }
        } else if (requestCode == 0) {
            if (data != null) {
                result = data.getData();
                //选择相册的图片
            }
        }

        //Log.e("liangxq",""+data.toString());
//        Bundle extras = data.getExtras();
//        extras.
//        if (data!=null){
//            switch (requestCode){
//                case CAMERA_REQUEST_CODE: //调用相机后返回
//                    Toast.makeText(this, "系统相机返回", Toast.LENGTH_SHORT).show();
//                        if (resultCode == RESULT_OK){
//                            //用相机返回的照片去调用剪裁也需要对Uri进行处理
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//                                Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", file);
//                                Log.i("========", "onActivityResult: "+contentUri);
//                                startPhotoZoom(contentUri);//开始对图片进行裁剪处理
//                            }else {
//                                Log.i("========", "onActivityResult: "+Uri.fromFile(file));
//                                startPhotoZoom(Uri.fromFile(file));//开始对图片进行裁剪处理
//                            }
//                    }
//
//                    break;
//                case ALBUM_REQUEST_CODE:
//                    if (resultCode == RESULT_OK) {
//                        Uri uri = data.getData();
//                        startPhotoZoom(uri); // 开始对图片进行裁剪处理
//                    }
//                    break;
//                case CROP_SMALL_PICTURE://调用剪裁后返回
//                    if (data != null) {
//                        // 让刚才选择裁剪得到的图片显示在界面上
//                        Bitmap photo =BitmapFactory.decodeFile(mFile);
//                        Log.e("UploadingImaActivity", "photo:" + photo);
//                        imageportrait.setImageBitmap(photo);
//                    } else {
//                        Log.e("data","data为空");
//                    }
//                    break;
//            }
//        }
    }
}
