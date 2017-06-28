package com.kvprasad.zbarbarcodescanner;

import android.util.Log;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by desenvolv3 on 21-Jun-17.
 */
public class SimpleScannerActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Registrar a activity como um manipulador dos resultados.
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Manipular o resultado aqui.
        Log.v("BARCODE", rawResult.getContents());
        Log.v("TYPE", rawResult.getBarcodeFormat().getName());

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle("BARCODE")
                .setMessage(rawResult.getContents());
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Continuar o escaneamento
                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
            }
        });
        alertDialog.show();
    }
}