package com.kvprasad.zbarbarcodescanner;

import android.os.Build;
import android.Manifest;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button scannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannerButton = (Button) findViewById(R.id.scannerButton);
        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED)) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
                } else {
                    Intent intent = new Intent(MainActivity.this, SimpleScannerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MainActivity.this, SimpleScannerActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}