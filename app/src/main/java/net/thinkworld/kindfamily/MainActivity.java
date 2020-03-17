package net.thinkworld.kindfamily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_RESULT = "result";
    private static final String KEY_PHONE = "phone";

    /**
     * 跳转验证结果页，这版只显示成功页
     * @param context
     * @param success true显示成功，false显示失败
     * @param phone 验证的手机号
     */
    public static void startActivity(Context context, boolean success, String phone) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_RESULT, success);
        intent.putExtra(KEY_PHONE, phone);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Activity context, int requestCode, boolean success, String phone) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_RESULT, success);
        intent.putExtra(KEY_PHONE, phone);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent() == null) {
            finish();
        }
        boolean success = getIntent().getBooleanExtra(KEY_RESULT, true);
        String phone = getIntent().getStringExtra(KEY_PHONE);
    }


}