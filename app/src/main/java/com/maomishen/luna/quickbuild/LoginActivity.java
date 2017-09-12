package com.maomishen.luna.quickbuild;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.maomishen.luna.quickbuild.model.Server;
import com.maomishen.luna.quickbuild.network.GetApiVersion;
import com.maomishen.luna.quickbuild.network.GetQuickBuildResponse;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements GetQuickBuildResponse {

    @BindView(R.id.editText_login_address)
    EditText address;
    @BindView(R.id.editText_login_port)
    EditText port;
    @BindView(R.id.editText_login_username)
    EditText username;
    @BindView(R.id.editText_login_password)
    EditText password;
    @BindView(R.id.button_login_login)
    Button login;
    @BindView(R.id.progressBar_login_progress)
    ProgressBar progressBar;
    @BindView(R.id.content_login)
    View content;

    String addressString;
    String portString;
    String usernameString;
    String passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Boolean first = getIntent().getExtras().getBoolean("First", false);
        if (!first) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.button_login_login) void login() {
        getInputValueIntoField();
        if (isEmpytInput()) {
            return;
        }
        setInputEnable(false);
        GetApiVersion apiVersion = new GetApiVersion(this);
        String url = address.getText().toString() + ":" + port.getText().toString();
        apiVersion.getApiVersion(url, username.getText().toString(), password.getText().toString());
    }

    private void getInputValueIntoField() {
        addressString = address.getText().toString();
        portString = port.getText().toString();
        usernameString = username.getText().toString();
        passwordString = password.getText().toString();
    }

    private Boolean isEmpytInput() {
        String errorMessage = null;
        if (TextUtils.isEmpty(addressString)) {
            errorMessage = "Address need Input";
        } else if (TextUtils.isEmpty(portString)) {
            errorMessage = "Port need Input";
        } else if (TextUtils.isEmpty(usernameString)) {
            errorMessage = "Username need Input";
        } else if (TextUtils.isEmpty(passwordString)) {
            errorMessage = "Password need Input";
        }
        if (errorMessage != null) {
            Snackbar.make(content, errorMessage, Snackbar.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void setInputEnable(Boolean enable) {
        progressBar.setVisibility(enable ? View.INVISIBLE : View.VISIBLE);
        address.setEnabled(enable);
        port.setEnabled(enable);
        username.setEnabled(enable);
        password.setEnabled(enable);
        login.setEnabled(enable);
    }

    @Override
    public void getResponse(String api, boolean isError, final String message) {
        if (isError) {
            Snackbar.make(content, "Error", Snackbar.LENGTH_LONG)
                    .setAction("Try Again", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            login();
                        }
                    }).show();
            KLog.e(message);
            setInputEnable(true);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    realm.beginTransaction();
                    Server server = realm.createObject(Server.class);
                    server.setActivity(true);
                    server.setAddress(addressString);
                    server.setName(usernameString);
                    server.setPassword(passwordString);
                    server.setPort(portString);
                    server.setVersion(message);
                    server.setServerName(addressString);
                    realm.commitTransaction();
                    Intent intent = new Intent(mActivity, MainActivity.class);
                    startActivity(intent);
                    mActivity.finish();
                }
            });
        }
    }
}
