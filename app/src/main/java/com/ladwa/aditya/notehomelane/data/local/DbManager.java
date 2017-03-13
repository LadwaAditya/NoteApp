package com.ladwa.aditya.notehomelane.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Aditya on 13-Mar-17.
 */
@Singleton
public class DbManager {

    public static final int DATA_SCHEMA_VERSION = 1;
    public static final String DATA_DB = "data.realm";

    @Inject
    public DbManager() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DATA_DB)
                .schemaVersion(DATA_SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }


    public Realm getDataRealm() {
        return Realm.getDefaultInstance();
    }
}
