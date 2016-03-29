package com.android.gifts.moga.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.afollestad.materialdialogs.MaterialDialog;

public class UIHelper {
    private Context context;

    public UIHelper(Context context) {
        this.context = context;
    }

    public MaterialDialog getSpinnerProgressDialog(String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .progress(true, 0)
                .content(message)
                .cancelable(false);
        return builder.build();
    }
}
