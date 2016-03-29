package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.UIHelper;
import com.android.gifts.moga.presenter.signUp.SignUpPresenter;
import com.android.gifts.moga.presenter.signUp.SignUpPresenterImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpActivity extends AppCompatActivity implements RegisterView, AdapterView.OnItemSelectedListener {
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.user_email)
    EditText userMail;
    @Bind(R.id.user_mobile)
    EditText userMobile;
    @Bind(R.id.user_password)
    EditText userPassword;
    @Bind(R.id.user_password_2)
    EditText userPasswordConfirm;
    @Bind(R.id.year_spinner)
    Spinner userYear;

    SignUpPresenter presenter;
    UIHelper uiHelper;
    MaterialDialog progressDialog;

    int userYearSelected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        userYear.setOnItemSelectedListener(this);
        List<String> years = new ArrayList<>();
        years.add("الفرقة الأولى");
        years.add("الفرقة الثانية");
        years.add("الفرقة الثالثة");
        years.add("الفرقة الرابعة");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userYear.setAdapter(dataAdapter);

        uiHelper = new UIHelper(this);
        progressDialog = uiHelper.getSpinnerProgressDialog("إنشاء حساب");

        presenter = new SignUpPresenterImp(this, this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(Constants.REGULAR_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        userPassword.setGravity(Gravity.RIGHT);
        userPasswordConfirm.setGravity(Gravity.RIGHT);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userYearSelected = position + 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do Nothing
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @OnClick(R.id.sign_up_btn)
    public void signUp() {
        String name = userName.getText().toString();
        String mail = userMail.getText().toString();
        String mobile = userMobile.getText().toString();
        String password1 = userPassword.getText().toString();
        String password2 = userPasswordConfirm.getText().toString();

        userName.setError(null);
        userMail.setError(null);
        userMobile.setError(null);
        userPassword.setError(null);

        presenter.register(name, mail, mobile, password1, password2, userYearSelected);
    }

    @Override
    public void setNameError(String error) {
        userName.setError(error);
    }

    @Override
    public void setMailError(String error) {
        userMail.setError(error);
    }

    @Override
    public void setMobileError(String error) {
        userMobile.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        userPassword.setError(error);
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
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }
}
