package com.maomishen.luna.quickbuild.network;

/**
 * Created by luna on 2017/9/10.
 */

public interface GetQuickBuildResponse {
    void getResponse(Network networkType, boolean isError, String message);
}
