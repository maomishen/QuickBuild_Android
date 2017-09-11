package com.maomishen.luna.quickbuild.model;


import io.realm.RealmObject;

/**
 * Created by luna on 2017/9/11.
 */

public class Server extends RealmObject {
    private String serverName;
    private String name;
    private String password;
    private String address;
    private String port;
    private String version;
    private Boolean isActivity;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getActivity() {
        return isActivity;
    }

    public void setActivity(Boolean activity) {
        isActivity = activity;
    }
}
