package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class SignUpActivity extends AppCompatActivity implements RegisterView{
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
    @Bind(R.id.type_spinner)
    Spinner userType;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    SignUpPresenter presenter;
    UIHelper uiHelper;
    MaterialDialog progressDialog;

    int userYearSelected = 1;
    int userTypeSelected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        final List<String> type1 = new ArrayList<>();
        type1.add("إنتظام");
        type1.add("إنتساب");

        final List<String> type2 = new ArrayList<>();
        type2.add("إدارة");
        type2.add("محاسبة");
        type2.add("خارجية");

        final ArrayAdapter<String> typeAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type1);
        typeAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> typeAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type2);
        typeAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userYearSelected <= 2) {
                    userTypeSelected = position + 1;
                } else {
                    userTypeSelected = position + 3;
                }

                Log.e("MYLOG", "type id: " + userTypeSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type1);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(typeAdapter);

        userYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userYearSelected = position + 1;

                if (userYearSelected <= 2) {
                    userType.setAdapter(typeAdapter1);
                } else {
                    userType.setAdapter(typeAdapter2);
                }

                Log.e("MYLOG", "year id: " + userYearSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> years = new ArrayList<>();
        years.add("الفرقة الأولى");
        years.add("الفرقة الثانية");
        years.add("الفرقة الثالثة");
        years.add("الفرقة الرابعة");

        // Creating adapter for spinner
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userYear.setAdapter(yearAdapter);



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

        presenter.register(name, mail, mobile, password1, password2, userYearSelected, userTypeSelected);
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

    @Override
    public void showNetworkError() {
        Snackbar.make(coordinatorLayout, "تحقق من اتصال الإنترنت الخاص بك وحاول مرة أخرى", Snackbar.LENGTH_LONG).show();
    }
}
