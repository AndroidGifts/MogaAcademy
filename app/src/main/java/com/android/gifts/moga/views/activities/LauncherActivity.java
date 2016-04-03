package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.presenter.login.LoginPresenter;
import com.android.gifts.moga.presenter.login.LoginPresenterImp;
import com.android.gifts.moga.views.BaseView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LauncherActivity extends AppCompatActivity {
    public static LauncherActivity instance = null;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        setContentView(R.layout.activity_launcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        presenter = new LoginPresenterImp(this, null);
        // Check if patient has not logged in, go to login activity
        if (presenter.isUserHere()) {
            Log.e("FOF", "YEEEEEESS USER HEEEEEEEREEEEEE");
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(Constants.REGULAR_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @OnClick(R.id.login_btn)
    public void goToLogin() {
        startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.sign_up_btn)
    public void goToSignUp() {
        startActivity(new Intent(LauncherActivity.this, SignUpActivity.class));
    }

}