package com.android.gifts.moga.views.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.UIHelper;
import com.android.gifts.moga.presenter.main.MainPresenter;
import com.android.gifts.moga.presenter.main.MainPresenterImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends Fragment implements SettingsFragmentView {
    private UIHelper uiHelper;
    private MaterialDialog progressDialog;

    private MainPresenter presenter;

    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.year_spinner)
    Spinner userYear;

    private int selectedYear;
    private RelativeLayout relativeLayout;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_settings, container, false);
        relativeLayout = (RelativeLayout) rootView.findViewById(R.id.settings_fragment_parent);
        ButterKnife.bind(this, rootView);

        uiHelper = new UIHelper(getActivity());
        progressDialog = uiHelper.getSpinnerProgressDialog("جارى تعديل البيانات");

        presenter = new MainPresenterImp(this, getActivity());
        UserVm user = presenter.getUser();

        userYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = position + 1;
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, years);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userYear.setAdapter(dataAdapter);

        userYear.setSelection((int) (user.getYearId() - 1), false);
        userName.setText(user.getName());

        return rootView;
    }

    @OnClick(R.id.edit_btn)
    public void editProfile() {
        String name = userName.getText().toString();

        userName.setError(null);

        presenter.updateUser(name, selectedYear);
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
    public void showNameError(String error) {
        userName.setError(error);
    }

    @Override
    public void showSuccessMessage() {
        Snackbar.make(relativeLayout, "تم تغيير الإعدادات الشخصية", Snackbar.LENGTH_LONG).show();
    }
}
