package lu.uni.jungao.plagiarist;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AntiTheftActivity extends AppCompatActivity {
    private Object instance;
    private Method protectedMethod, nonProtectedMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_theft);
        setupTheOtherApp();
    }

    public void onNoProtectionClick(View v) {
        try {
            nonProtectedMethod.invoke(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void onWithProtectionClick(View v) {
        try {
            protectedMethod.invoke(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setupTheOtherApp() {
        try {
            Context invokee = this.createPackageContext("lu.uni.jungao.theft_protected",
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            ClassLoader loader = invokee.getClassLoader();
            Class cls = loader.loadClass("lu.uni.jungao.theft_protected.ProtectedOrNot");
            Constructor constructor = cls.getConstructor(Context.class);
            instance = constructor.newInstance(this);
            nonProtectedMethod = cls.getMethod("noProtectionTest");
            protectedMethod = cls.getMethod("protectionTest");
            // setup the Global class
//            Class theAppCls = loader.loadClass("lu.uni.jungao.theft_protected.AntiTheftApp");
//            Constructor theAppConstructor = theAppCls.getConstructor();
//            Object theApp = theAppConstructor.newInstance();
            Class clsG = loader.loadClass("lu.uni.jungao.theft_protected.G");
//            Method setApp = clsG.getDeclaredMethod("setApp", theAppCls);
//            setApp.invoke(null, theApp);
            Method setApp = clsG.getDeclaredMethod("setApp", Application.class);
            setApp.invoke(null, this.getApplication());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
