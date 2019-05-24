package lu.uni.jungao.tiktok_proxy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker {
    private static Context thisApp;
    private static Context invokee;
    private static Invoker singleton;
    private ClassLoader loader;
    private static String invokeeName;

    public static Invoker getInstance() {
        return singleton;
    }

    public static Invoker getInstance(Context thisApp, String invokeeName) {
        if (singleton == null) {
            Invoker.thisApp = thisApp;
            Invoker.invokeeName = invokeeName;
            singleton = new Invoker();
        }
        return singleton;
    }

    private Invoker() {
        try {
            invokee = thisApp.createPackageContext(invokeeName,
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            loader = invokee.getClassLoader();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MyApp", "Invoker constructor", e);
        }
    }

    public void loadNative(String libName, int libBit) {
        String nativeLibDir = Invoker.invokee.getApplicationInfo().nativeLibraryDir + "/" + libName;
        switch (libBit) {
            case 32:
                // To use "load" to load an 32 bit *.so native lib, Load a 32 bit lib by using
                // "loadLibrary" to fall back to 32 bit mode.
                System.loadLibrary("gif");
            default:
                System.load(nativeLibDir);
        }
    }

    public String getUserInfo(int i, String str, String[] strArr) {
        String info = null;
        try {
            Class cls = loader.loadClass("com.ss.android.common.applog.UserInfo");
            Method m = cls.getDeclaredMethod("getUserInfo", int.class, String.class, String[].class);
            info = (String) m.invoke(null, i, str, strArr);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp", "getUserInfo", e);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "getUserInfo", e);
        } finally {
            return info;
        }
    }

    public void setAppId(int i) {
        try {
            Class cls = loader.loadClass("com.ss.android.common.applog.UserInfo");
            Method m = cls.getDeclaredMethod("setAppId", int.class);
            m.invoke(null, i);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp", "setAppId", e);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "setAppId", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp", "setAppId", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp", "setAppId", e);
        }
    }

    public int initUser(String id) {
        int rt = 0;
        try {
            Class cls = loader.loadClass("com.ss.android.common.applog.UserInfo");
            Method m = cls.getDeclaredMethod("initUser", String.class);
            rt = (int) m.invoke(null, id);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp", "initUser", e);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "initUser", e);
        } finally {
            return rt;
        }
    }

    public byte[] encode(byte[] bArr) {
        byte[] rt = null;
        try {
            Class cls = loader.loadClass("com.ss.sys.ces.a");
            Method m = cls.getDeclaredMethod("e", byte[].class);
            rt = (byte[]) m.invoke(null, bArr);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp", "encode", e);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "encode", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp", "encode", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp", "encode", e);
        } finally {
            return rt;
        }
    }

    public String byteArrayToHexStr(byte[] bArr) {
        String rt = null;
        try {
            Class cls = loader.loadClass("com.ss.android.common.applog.i");
            Method m = cls.getDeclaredMethod("byteArrayToHexStr", byte[].class);
            rt = (String) m.invoke(null, bArr);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } finally {
            return rt;
        }
    }
}
