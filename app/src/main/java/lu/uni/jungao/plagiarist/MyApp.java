package lu.uni.jungao.plagiarist;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApp extends Application {
    private static Application theApp;

    @Override
    public void onCreate() {
        super.onCreate();
        storeUID();
        theApp = this;
        Invoker invoker = Invoker.getInstance(this, "com.zhiliaoapp.musically");
//        invoker.loadNative("libcms.so", 32);

        this.getApplicationInfo().packageName = "lu.uni.jungao.theft_protected";
    }

    public static Application getApplication() {
        return theApp;
    }


    private void storeUID() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("secret", Context.MODE_PRIVATE);
        // Check if the uid has been stored. Since uid cannot be less than 0, give a default value
        // of -1 to notice that the value has not been set.
        int storedUid = sharedPreferences.getInt("uid", -1);
        // the value has not been set yet, let's set it.
        if (storedUid < 0) {
            int uid = this.getApplicationInfo().uid;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("uid", uid);
            editor.apply();
        }

    }
}
