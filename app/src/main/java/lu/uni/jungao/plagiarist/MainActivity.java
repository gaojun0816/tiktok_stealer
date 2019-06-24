package lu.uni.jungao.plagiarist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void proxyShow(View v) {
        Intent i = new Intent(this, TTProxyActivity.class);
        startActivity(i);
    }

    public void installedAppsShow(View v) {
        Intent i = new Intent(this, InstalledAppsActivity.class);
        startActivity(i);
    }

    public void codeHidingShow(View v) {
        Intent i = new Intent(this, CodeHidingActivity.class);
        startActivity(i);
    }

    public void funcBorrowingShow(View v) {
        Intent i = new Intent(this, FuncBorrowingActivity.class);
        startActivity(i);
    }
}
