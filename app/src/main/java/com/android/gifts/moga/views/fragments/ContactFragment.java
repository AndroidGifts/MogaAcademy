package com.android.gifts.moga.views.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.UIHelper;
import com.android.gifts.moga.presenter.main.MainPresenter;
import com.android.gifts.moga.presenter.main.MainPresenterImp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment implements ContactFragmentView {
    private MainPresenter presenter;

    private MaterialDialog progressDialog;

    @Bind(R.id.contact_msg)
    EditText messageBox;
    @Bind(R.id.contact_fragment_layout)
    FrameLayout fragmentLayout;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, rootView);

        UIHelper uiHelper = new UIHelper(getActivity());
        progressDialog = uiHelper.getSpinnerProgressDialog("جارى إرسال الرسالة");

        presenter = new MainPresenterImp(this, getActivity());

        return rootView;
    }

    @OnClick(R.id.send_btn)
    public void sendMessage() {
        String message = messageBox.getText().toString();

        if (message.isEmpty()) {
            messageBox.setError("نص الرسالة مطلوب");
        } else {
            // send it
            presenter.sendContactMsg(message);
        }
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
    public void showSuccessMessage() {
        Snackbar.make(fragmentLayout, "تم إرسال الرسالة بنجاح", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFailMessage() {
        Snackbar.make(fragmentLayout, R.string.error_msg, Snackbar.LENGTH_LONG).show();
    }
}
