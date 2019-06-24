package lu.uni.jungao.plagiarist;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CodeHidingActivity extends AppCompatActivity {
    EditText msgEt, numEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_hiding);
        msgEt = findViewById(R.id.message_text);
        numEt = findViewById(R.id.phone_number_text);
    }

    public void onSend(View v) {
        String msg = msgEt.getText().toString().trim();
        String num = numEt.getText().toString().trim();
        if (msg == "") {
            Toast.makeText(this.getApplicationContext(), "Input message first!",
                    Toast.LENGTH_LONG).show();
        } else if (num == "") {
            Toast.makeText(this.getApplicationContext(), "Input Phone Number",
                    Toast.LENGTH_LONG).show();
        } else {
            sendMsg(num, msg);
        }
    }

    public void onGetImei(View v) {
        String imei = getImei();
        String msg = msgEt.getText().toString().trim();
        msgEt.setText(msg + imei);
    }

    private void sendMsg(String num, String msg) {
        try {
            Context invokee = this.createPackageContext("com.globalcanofworms.android.simpleweatheralert",
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            ClassLoader loader = invokee.getClassLoader();
            Class util = loader.loadClass("com.globalcanofworms.android.coreweatheralert.CommonUtils");
            Method sendSms = util.getDeclaredMethod("sendSms", Context.class, String.class, String.class);
            sendSms.invoke(null, this, num, msg);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MyApp/CodeHiding/send", "package name did not find", e);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp/CodeHiding/send", "class did not find", e);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp/CodeHiding/send", "method did not find", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp/CodeHiding/send", "method accessed illegally", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp/CodeHiding/send", "method invocation problem", e);
        }
    }

    private String getImei() {
        String imei = null;
        try {
            Context invokee = this.createPackageContext("org.communicorpbulgaria.bgradio",
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            ClassLoader loader = invokee.getClassLoader();
            Class util = loader.loadClass("org.ccb.radioapp.components.Utils");
            Method getDeviceId = util.getDeclaredMethod("getDeviceID", Context.class);
            imei = (String) getDeviceId.invoke(null, this);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MyApp/CodeHiding/send", "package name did not find", e);
        } catch (ClassNotFoundException e) {
            Log.e("MyApp/CodeHiding/send", "class did not find", e);
        } catch (NoSuchMethodException e) {
            Log.e("MyApp/CodeHiding/send", "method did not find", e);
        } catch (IllegalAccessException e) {
            Log.e("MyApp/CodeHiding/send", "method accessed illegally", e);
        } catch (InvocationTargetException e) {
            Log.e("MyApp/CodeHiding/send", "method invocation problem", e);
        } finally {
            return imei;
        }
    }
}
