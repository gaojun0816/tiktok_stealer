package lu.uni.jungao.tiktok_proxy;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Invoker invoker = Invoker.getInstance(this, "com.zhiliaoapp.musically");
        invoker.loadNative("libcms.so", 32);
    }
}
