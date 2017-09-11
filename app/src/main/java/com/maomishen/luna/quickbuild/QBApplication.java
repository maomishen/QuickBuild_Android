package com.maomishen.luna.quickbuild;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class QBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("qbrealm.realm")
                .schemaVersion(0)
                // TODO: 2017/9/11 Publish need change this code
//                .migration(new QBMigration()) // public
                .deleteRealmIfMigrationNeeded() // test
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
