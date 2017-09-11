package com.maomishen.luna.quickbuild.network;

import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by luna on 2017/9/10.
 */

public class GetApiVersion{

    private String url = "/rest/version";
    private GetQuickBuildResponse qbResponse;
    Call call;

    public GetApiVersion(GetQuickBuildResponse qbResponse) {
        this.qbResponse = qbResponse;
    }

    public void cancel() {
        call.cancel();
    }

    public void getApiVersion(String url, String user, String password){
        final OkHttpClient client = new OkHttpClient();
        final String credential = Credentials.basic(user, password);
        url += this.url;
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

                    qbResponse.getResponse("version", false, response.body().string());
                } catch (Exception ex) {
                    qbResponse.getResponse("version", true, ex.getMessage());
                }
            }
        }.start();
    }
}
