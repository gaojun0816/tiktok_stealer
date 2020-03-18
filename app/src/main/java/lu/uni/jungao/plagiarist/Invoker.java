package lu.uni.jungao.plagiarist;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Invoker {
    private static Context thisApp;
    private static Context invokee;
    private static Invoker singleton;
    private ClassLoader loader;
    private static String invokeeName;
    private Class userInfo;
    private Class a;
    private Class i;

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
            prepareClsA();  // Class a need to be loaded first since this class loads relevant native lib.
            prepareUserInfo();
            prepareClsI();
            loadLib();
            checkLoadedLibs();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MyApp", "Invoker constructor", e);
        }
    }

    private void loadLib() {
        try {
            Class c = loader.loadClass("com.ss.android.ugc.aweme.framework.c.i");
            Method m = c.getDeclaredMethod("loadLibrary", Context.class, String.class);
            m.invoke(null, thisApp, "cms");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void prepareUserInfo() {
        if (userInfo == null) {
            try {
                userInfo = loader.loadClass("com.ss.android.common.applog.UserInfo");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void prepareClsA() {
        if (a == null) {
            try {
                a = loader.loadClass("com.ss.sys.ces.a");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void prepareClsI() {
        if (i == null) {
            try {
                i = loader.loadClass("com.ss.android.common.applog.i");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserInfo(int i, String str, String[] strArr) {
        String info = null;
        if (userInfo == null) {
            Log.e("Invoker::getUserInfo", "UserInfo is null");
            return info;
        }
        try {
            Method m = userInfo.getDeclaredMethod("getUserInfo", int.class, String.class, String[].class);
            info = (String) m.invoke(null, i, str, strArr);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "getUserInfo", e);
        } finally {
            return info;
        }
    }

    public void setAppId(int i) {
        if (userInfo == null) {
            Log.e("Invoker::setAppId", "Userinfo is null!");
            return;
        }
        try {
            for (Method m : userInfo.getDeclaredMethods()) {
                Log.d("userinfo::methods", m.getName());
            }
            Method m = userInfo.getDeclaredMethod("setAppId", int.class);
            m.invoke(null, i);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "setAppId", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp", "setAppId", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp", "setAppId", e);
        }
    }

    public void initUser(String id) {
        if (userInfo == null) {
            Log.e("Invoker::initUser", "UserInfo is null");
            return;
        }
        try {
            Method m = userInfo.getDeclaredMethod("initUser", String.class);
            m.invoke(null, id);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "initUser", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public byte[] encode(byte[] bArr) {
        byte[] rt = null;
        if (a == null) {
            Log.e("Invoker::encode", "Class a is null");
            return rt;
        }
        try {
            Method m = a.getDeclaredMethod("e", byte[].class);
            rt = (byte[]) m.invoke(null, bArr);
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
        if (i == null) {
            Log.e("Invoker::byteArrayTo...", "Class i is null");
            return rt;
        }
        try {
            Method m = i.getDeclaredMethod("byteArrayToHexStr", byte[].class);
            rt = (String) m.invoke(null, bArr);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp", "byteArrayToHexStr", e);
        } finally {
            return rt;
        }
    }

    private void checkLoadedLibs() {
        try {
            Set<String> libs = new HashSet();
            String mapsFile = "/proc/" + android.os.Process.myPid() + "/maps";
            BufferedReader reader = new BufferedReader(new FileReader(mapsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith(".so")) {
                    int n = line.lastIndexOf(" ");
                    libs.add(line.substring(n + 1));
                }
            }
//            Log.d("Ldd", libs.size() + " libraries:");
            for (String lib : libs) {
                Log.d("Ldd", lib);
            }
        } catch (FileNotFoundException e) {
            // Do some error handling...
        } catch (IOException e) {
            // Do some error handling...
        }
    }
}
