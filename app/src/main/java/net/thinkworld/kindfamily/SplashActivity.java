package net.thinkworld.kindfamily;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mob.MobSDK;
import com.mob.OperationCallback;
import com.mob.commons.dialog.entity.MobPolicyUi;

import net.thinkworld.kindfamily.privacy.OnDialogListener;
import net.thinkworld.kindfamily.privacy.PrivacyDialog;
import net.thinkworld.kindfamily.privacy.PrivacyHolder;
import net.thinkworld.kindfamily.util.DemoSpHelper;

import java.util.ArrayList;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class SplashActivity extends Activity implements View.OnTouchListener {

    private GestureDetector gestureDetector;
    private Handler handler;
    private static final int DELAY = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView imageView= new ImageView(this);
        imageView.setId(R.id.ivSplash);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.shijiaxiaozhen);
        setContentView(imageView);

        gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                /*startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();*/
                return false;
            }
        });

        imageView.setOnTouchListener(this);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,VerifyActivity.class));
                finish();
            }
        },DELAY);


        init();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void init() {
        if(!DemoSpHelper.getInstance().isPrivacyGranted()){
            PrivacyHolder.getInstance().init();
        }


        // 初始化隐私二次确认框相关自定义设置
        initPrivacyRecheckSettings();
    }




    private void initPrivacyRecheckSettings() {
        MobSDK.setAllowDialog(true);
        // 自定义UI
        MobPolicyUi mobPolicyUi = new MobPolicyUi.Builder()
                // 设置弹框背景色资源ID
//				.setBackgroundColorId(R.color.demo_common_white)
                // 设置同意按钮背景色资源ID
                .setPositiveBtnColorId(R.color.colorAccent)
                // 设置拒绝按钮背景色资源ID
//				.setNegativeBtnColorId(R.color.demo_common_white)
                .build();
        // 需在使用SDK接口前调用，否则不生效
        MobSDK.setPolicyUi(mobPolicyUi);
    }
}
