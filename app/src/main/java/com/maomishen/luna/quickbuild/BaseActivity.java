package com.maomishen.luna.quickbuild;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity mActivity;
    public Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        realm = Realm.getDefaultInstance();
    }
}
