package lu.uni.jungao.plagiarist;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class FaceDetector {
    private static String tag = "Plagiarist/FaceDtr";
    private static String pkgName = "com.mt.mtxx.mtxx";
    //    private static String pkgName = "com.commsource.beautyplus";
    private static FaceDetector singleton;
    private static Context thisApp;
    private static Context invokee;
    private static ClassLoader loader;
    private static String[] nativeLibs = {"libgnustl_shared.so", "libmtnn.so",
            "libmtface.so", "libmtface_jni.so", "libmtface_ext.so"};
    private static String[] nativeClasses = {
            "com.meitu.face.detect.MTFaceDetector",
            "com.meitu.face.ext.MTFaceDataUtils"
    };

    public static FaceDetector getInstance(Context thisApp) {
        if (singleton == null) {
            FaceDetector.thisApp = thisApp;
            singleton = new FaceDetector();
        }
        return singleton;
    }

    private FaceDetector() {
        try {
            invokee = thisApp.createPackageContext(pkgName,
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            loader = invokee.getClassLoader();
            setupMTBaseApplication();
            setupMteApplication();
//            loadNativeClasses();
//            for (String lib : nativeLibs) {
//                loadNative(lib);
//            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(tag, "FaceDetector constructor", e);
        }
    }

    private void loadNative(String libName) {
//        String nativeLibDir = invokee.getApplicationInfo().nativeLibraryDir + "/" + libName;
//        System.load(nativeLibDir);
    }

    private void loadNativeClasses() {
        for (String nc : nativeClasses) {
            try {
                loader.loadClass(nc);
                Log.d(tag, "loaded: " + nc);
            } catch (ClassNotFoundException e) {
                Log.e(tag, "loadNative", e);
            }
        }
    }

    private void setupMteApplication() {
        try {
            Class cls = loader.loadClass("com.meitu.core.MteApplication");
            Method m = cls.getDeclaredMethod("getInstance");
            Field ctx = cls.getDeclaredField("context");
            ctx.setAccessible(true);
            Object mtApp = m.invoke(null);
            ctx.set(mtApp, thisApp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void setupMTBaseApplication() {
        try {
            Class cls = loader.loadClass("com.meitu.library.application.BaseApplication");
            Field baseApp = cls.getDeclaredField("mBaseApplication");
            baseApp.setAccessible(true);
            baseApp.set(null, thisApp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class getMTImageClass() {
        Class clsMTImage = null;
        try {
            clsMTImage = loader.loadClass("com.meitu.face.bean.MTImage");
        } catch (ClassNotFoundException e) {
            Log.e(tag, "no class MTImage", e);
        } finally {
            return clsMTImage;
        }
    }

    public Object genFaceData(Object mTImage, ArrayList arrayListOfMTFaceFeature) {
        Object mtFaceData = null;
        try {
            Class cls = loader.loadClass("com.meitu.face.ext.MTFaceData");
            mtFaceData = cls.getConstructor(mTImage.getClass(), ArrayList.class)
                    .newInstance(mTImage, arrayListOfMTFaceFeature);
        } catch (NoSuchMethodException e) {
            Log.e(tag, "no createImageFromBitmap method", e);
        } catch (IllegalAccessException e) {
            Log.e(tag, "method createImageFromBitmap invocation illegal", e);
        } catch (InvocationTargetException e) {
            Log.e(tag, "method createImageFromBitmap invocation exception", e);
        } catch (ClassNotFoundException e) {
            Log.e(tag, "no class MTImage", e);
        } finally {
            return mtFaceData;
        }
    }

    public Object genNativeBitmap(Bitmap bitmap) {
        Object nativeBitmap = null;
        try {
            Class cls = loader.loadClass("com.meitu.core.types.NativeBitmap");
            Method m = cls.getDeclaredMethod("createBitmap", Bitmap.class);
            nativeBitmap = m.invoke(null, bitmap);
        } catch (NoSuchMethodException e) {
            Log.e(tag, "genNativeBitmap:", e);
        } catch (IllegalAccessException e) {
            Log.e(tag, "genNativeBitmap:", e);
        } catch (InvocationTargetException e) {
            Log.e(tag, "genNativeBitmap:", e);
        } catch (ClassNotFoundException e) {
            Log.e(tag, "genNativeBitmap:", e);
        } finally {
            return nativeBitmap;
        }
    }

    public Object genFaceData(Object nativeBitmap) {
        Object mtFaceData = null;
        try {
            Class<Enum> modeCls = (Class<Enum>) loader.loadClass("com.meitu.face.detect.MTFaceDetector$MTFaceDetectMode");
            Log.d(tag, Enum.valueOf(modeCls, "MTFACE_MODE_IMAGE_FD_FA").toString());
            Class cls = loader.loadClass("com.meitu.image_process.d");
            Method m = cls.getDeclaredMethod("b", nativeBitmap.getClass(), modeCls);
            mtFaceData = m.invoke(null, nativeBitmap, Enum.valueOf(modeCls, "MTFACE_MODE_IMAGE_FD_FA"));
//            Class cls = loader.loadClass("com.meitu.image_process.FaceManager");
//            Method m = cls.getDeclaredMethod("faceDetect_NativeBitmap", nativeBitmap.getClass(), modeCls);
//            mtFaceData = m.invoke(null, nativeBitmap, Enum.valueOf(modeCls, "MTFACE_MODE_IMAGE_FD_FA"));
//            Class cls = loader.loadClass("com.commsource.beautyplus.e.a");
//            Method gen = cls.getDeclaredMethod("a");
//            Method create = cls.getDeclaredMethod("c", nativeBitmap.getClass());
//            Object a = gen.invoke(null);
//            mtFaceData = create.invoke(a, nativeBitmap);
        } catch (NoSuchMethodException e) {
            Log.e(tag, "genFaceData:", e);
        } catch (IllegalAccessException e) {
            Log.e(tag, "genFaceData:", e);
        } catch (InvocationTargetException e) {
            Log.e(tag, "genFaceData:", e);
        } catch (ClassNotFoundException e) {
            Log.e(tag, "genFaceData:", e);
        } finally {
            return mtFaceData;
        }
    }

    public Integer getFaceNum(Bitmap bitmap) {
        int num = -1;
        Object nativeBitmap = genNativeBitmap(bitmap);
        if (nativeBitmap == null) {
            Log.d(tag, "native bitmap is null");
        }
        Object faceData = genFaceData(nativeBitmap);
        if (faceData == null) {
            Log.d(tag, "facedata is null");
        }
        try {
            Class cls = loader.loadClass("com.meitu.face.ext.MTFaceData");
            Method m = cls.getDeclaredMethod("getFaceCounts");
            num = (int) m.invoke(faceData);
//            Class cls = loader.loadClass("com.meitu.core.types.FaceData");
//            Method m = cls.getDeclaredMethod("getFaceCount");
//            num = (int) m.invoke(faceData);
        } catch (NoSuchMethodException e) {
            Log.e(tag, "getFaceNum:", e);
        } catch (IllegalAccessException e) {
            Log.e(tag, "getFaceNum:", e);
        } catch (InvocationTargetException e) {
            Log.e(tag, "getFaceNum:", e);
        } catch (ClassNotFoundException e) {
            Log.e(tag, "getFaceNum:", e);
        } finally {
            return num;
        }
    }

    public Bitmap cut(Bitmap bitmap) {
        Bitmap cutted = null;
        Object nativeBitmap = genNativeBitmap(bitmap);
        try {
            Class cls = loader.loadClass("com.meitu.core.types.NativeBitmap");
            Method m = cls.getDeclaredMethod("getImage", int.class, int.class);
            cutted = (Bitmap) m.invoke(nativeBitmap, 200, 200);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return cutted;
        }

    }

    public void showAssests() {
        Log.d(tag, "this app's assests:");
        Log.d(tag, thisApp.getAssets().toString());
        Log.d(tag, "invokee app's assests:");
        Log.d(tag, invokee.getAssets().toString());
    }

}
