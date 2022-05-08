package fi.ottooks.dreamcatcherdemo;

import android.app.Application;
import android.content.Context;

/**
 * This class is used to get the context of application
 */
public class AppContext extends Application {

    private static Application application;

    public static Application getApplication() {return application;}

    public static Context getContext() {return getApplication().getApplicationContext();}

    @Override
    public void onCreate() {

        super.onCreate();
        application = this;

    }
}
