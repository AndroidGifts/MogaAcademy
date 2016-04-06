package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
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

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

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

        UIHelper uiHelper = new UIHelper(this);
        progressDialog = uiHelper.getSpinnerProgressDialog("تسجيل الدخول");

        presenter = new LoginPresenterImp(this, this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(Constants.REGULAR_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        password.setGravity(Gravity.RIGHT);
    }

    @OnClick(R.id.send_btn)
    public void loginUser() {
        String login = credentials.getText().toString();
        String pass = password.getText().toString();

        // Clear previous errors
        credentials.setError(null);
        password.setError(null);

        presenter.login(login, pass);
    }

    @OnClick(R.id.forgot_pass_text)
    public void forgotPassOnClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText edittext = new EditText(this);
        edittext.setHint("البريد الإلكترونى");

        alert.setMessage(" سوف يتم إرسال كلمة مرور جديده للبريد الإلكتروني الخاص بك");
        alert.setView(edittext);

        alert.setPositiveButton("أرسل", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                presenter.sendNewPass(edittext.getText().toString());
            }
        });

        alert.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();

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
    public void showNetworkError() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "تحقق من اتصال الإنترنت الخاص بك وحاول مرة أخرى", Snackbar.LENGTH_LONG);

        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setGravity(Gravity.RIGHT);

        snackbar.show();
    }

    @Override
    public void setLoginError(String error) {
        credentials.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        password.setError(error);
    }

    @Override
    public void showSuccessMsg() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "تم إرسال كلمه مرور جديده", Snackbar.LENGTH_LONG);

        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setGravity(Gravity.RIGHT);

        snackbar.show();
    }
}
