package com.example.user.tvdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 2017/8/16.
 */

public class DetailActivity extends Activity {

    private final static String TEXT = "text";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String text = getIntent().getStringExtra(TEXT);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setBackgroundResource(R.drawable.focus_background);
        textView.setText(text);
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.hasFocus()) {
                    ViewCompat.animate(v).scaleX(1.5f).scaleY(1.5f).translationZ(2).start();
                } else {
                    ViewCompat.animate(v).scaleX(1f).scaleY(1f).translationZ(0).start();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
            }
        });
        textView.requestFocus();

    }

    public static Intent newIntent(Context context,String msg) {
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(TEXT,msg);
       return intent;
    }
}
