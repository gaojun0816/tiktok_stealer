package lu.uni.jungao.plagiarist;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class InstalledAppsActivity extends AppCompatActivity {
    TextView tvApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_apps);
        tvApps = findViewById(R.id.apps_list);
        tvApps.setMovementMethod(new ScrollingMovementMethod());
    }

    public void getInstalledApps(View v) {
        StringBuilder sb = new StringBuilder();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appsInstalled = this.getPackageManager().queryIntentActivities(i, 0);
        PackageManager pm = getPackageManager();
        for (ResolveInfo ri : appsInstalled) {
            String pkgName = ri.toString().split(" ")[1].split("/")[0];
            PackageInfo pi = null;
            try {
                pi = pm.getPackageInfo(pkgName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Long verCode = null;
            if (Build.VERSION.SDK_INT < 28) {
                verCode = (long) pi.versionCode;
            } else {
                verCode = pi.getLongVersionCode();
            }
            String verName = pi.versionName;
            sb.append(pkgName);
            sb.append("| ");
            sb.append(verCode.toString());
            sb.append("| ");
            sb.append(verName);
            sb.append("\n");
        }
        tvApps.setText(sb.toString());
    }
}
