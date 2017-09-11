package com.maomishen.luna.quickbuild;

import android.content.Intent;
import android.os.Bundle;

import com.maomishen.luna.quickbuild.model.Server;

import io.realm.RealmResults;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RealmResults<Server> result = realm.where(Server.class)
                .findAll();
        Intent intent;
        if (result.isEmpty()) {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra("First", true);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        this.finish();
    }
}
