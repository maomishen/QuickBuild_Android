package com.maomishen.luna.quickbuild.network;

import android.app.Activity;

import com.maomishen.luna.quickbuild.R;

import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by luna on 2017/9/10.
 */

public class GetApiVersion{

    private GetQuickBuildResponse qbResponse;
    private Activity mActivity;
    Call call;

    public GetApiVersion(GetQuickBuildResponse qbResponse, Activity activity) {
        this.qbResponse = qbResponse;
        this.mActivity = activity;
    }

    public void cancel() {
        call.cancel();
    }

    public void getApiVersion(String url, String user, String password){
        final OkHttpClient client = new OkHttpClient();
        final String credential = Credentials.basic(user, password);
        url += UrlLinks.version;
        final String finalUrl = url;
        new Thread(){
            public void run(){
                try {
                    Request request = new Request.Builder()
                            .url(finalUrl)
                            .header("Authorization", credential)
                            .build();
                    call = client.newCall(request);

                    Response response = client.newCall(request).execute();

                    qbResponse.getResponse(Network.VERSION, false, response.body().string());
                } catch (UnknownHostException ex) {
                    qbResponse.getResponse(Network.VERSION, true, mActivity.getString(R.string.errormessage_getapiversion_unknowhost));
                } catch (IOException ex) {
                    qbResponse.getResponse(Network.VERSION, true, ex.getMessage());
                }
            }
        }.start();
    }
}
