package com.example.qiaop.xiangmu_firstnavigation.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaop.xiangmu_firstnavigation.MainActivity;
import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.base.CodeListBean;
import com.example.qiaop.xiangmu_firstnavigation.http.CodeServer;
import com.example.qiaop.xiangmu_firstnavigation.utils.RegexUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    String verification = null;
    boolean isPhone = false;
    boolean isCode = false;
    boolean isCheck = false;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.toolbar_login)
    Toolbar toolbarLogin;
    @BindView(R.id.text_loge)
    TextView textLoge;
    @BindView(R.id.edtext_phone)
    EditText edtextPhone;
    @BindView(R.id.edtext_code)
    EditText edtextCode;
    @BindView(R.id.text_code)
    TextView textCode;
    @BindView(R.id.relativeLayout3)
    RelativeLayout relativeLayout3;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.button_login)
    ImageView buttonLogin;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.wechar_image)
    ImageView wecharImage;
    @BindView(R.id.qq_image)
    ImageView qqImage;
    @BindView(R.id.microblog_image)
    ImageView microblogImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        changeStatusBarTextColor(true);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5c43d26cf1f55625dd000459");
        UMConfigure.setLogEnabled(true);
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setSessionContinueMillis(1000);


        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }
    //修改安卓状态栏字体
    private void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            }else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }
    private void initData() {

        final String code = edtextCode.getText().toString();
        edtextPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    String phone = edtextPhone.getText().toString();
                    boolean b = RegexUtils.checkMobile(phone);
                    if (b) {
                        isPhone = true;
                        Toast.makeText(LoginActivity.this, "手机号可用", Toast.LENGTH_SHORT).show();
                    } else {
                        buttonLogin.setImageResource(R.mipmap.login_loge);
                        isPhone = false;
                        if (edtextPhone.getText().toString().equals("")) {
                            Toast.makeText(LoginActivity.this, "手机号有误", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "请先输入手机号", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        textCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CodeServer.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                CodeServer codeServer = retrofit.create(CodeServer.class);
                Observable<CodeListBean> code1 = codeServer.getCode();
                code1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CodeListBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CodeListBean value) {
                                Log.e("LoginActivity", value.getData());
                                //verification = value.getData().toString();
                                String s = edtextPhone.getText().toString();

                                boolean b = RegexUtils.checkMobile(s);
                                if (!b | s.equals("")) {
                                    Toast.makeText(LoginActivity.this, "请先输入手机号", Toast.LENGTH_SHORT).show();
                                } else {
                                    isCode = true;
                                    isPhone = true;
                                    edtextCode.setText(value.getData().toString());
                                    NotificationCompat.Builder firstnavigation = new NotificationCompat.Builder(LoginActivity.this)
                                            .setSmallIcon(R.drawable.firstnavigation_icon)
                                            .setContentTitle("第一通航")
                                            .setAutoCancel(true)
                                            .setContentText("[第一通航]验证码为:" + value.getData().toString());
                                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    mNotificationManager.notify(0, firstnavigation.build());
                                    buttonLogin.setImageResource(R.mipmap.login_loge);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        edtextCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    if (verification == null) {
                        verification = edtextCode.getText().toString();
                        Toast.makeText(LoginActivity.this, verification, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isPhone & isCode & isChecked) {
                    isCheck = isChecked;
                    buttonLogin.setImageResource(R.mipmap.login_loge2);
                    buttonLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(LoginActivity.this, UploadingImaActivity.class);
                            startActivity(intent1);
                        }
                    });
                } else {
                    buttonLogin.setImageResource(R.mipmap.login_loge);
                    Toast.makeText(LoginActivity.this, "不符合登录条件", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgClose.setOnClickListener(this);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
