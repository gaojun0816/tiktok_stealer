package lu.uni.jungao.plagiarist;

import android.app.Application;

public class MyApp extends Application {
    private static Application theApp;
    @Override
    public void onCreate() {
        super.onCreate();
        theApp = this;
        Invoker invoker = Invoker.getInstance(this, "com.zhiliaoapp.musically");
        invoker.loadNative("libcms.so", 32);
    }

    public static Application getApplication() {
        return theApp;
    }
}
