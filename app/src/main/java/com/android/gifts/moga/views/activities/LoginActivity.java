package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.UIHelper;
import com.android.gifts.moga.presenter.login.LoginPresenter;
import com.android.gifts.moga.presenter.login.LoginPresenterImp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.pasword)
    EditText password;
    @Bind(R.id.credentials)
    EditText credentials;
    @Bind(R.id.forgot_pass_text)
    TextView forgotPassText;

    private UIHelper uiHelper;
    private MaterialDialog progressDialog;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        uiHelper = new UIHelper(this);
        progressDialog = uiHelper.getSpinnerProgressDialog("تسجيل الدخول");

        presenter = new LoginPresenterImp(this, this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(Constants.REGULAR_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        password.setGravity(Gravity.RIGHT);
    }

    @OnClick(R.id.login_btn)
    public void loginUser() {
        String login = credentials.getText().toString();
        String pass = password.getText().toString();

        // Clear previous errors
        credentials.setError(null);
        password.setError(null);

        presenter.login(login, pass);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void navigateToNextActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void setLoginError(String error) {
        credentials.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        password.setError(error);
    }
}
