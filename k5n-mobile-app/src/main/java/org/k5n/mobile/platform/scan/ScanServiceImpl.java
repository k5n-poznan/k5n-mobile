/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform.scan;

import android.app.Activity;
import android.content.Intent;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafxports.android.FXActivity;
import org.k5n.mobile.api.ScanService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class ScanServiceImpl implements ScanService {

    private final Intent scanIntent;

    private static final int SCAN_CODE = 10002;

    public ScanServiceImpl() {
        scanIntent = new Intent("com.android.scan.SCAN");
        scanIntent.addCategory(Intent.CATEGORY_DEFAULT);
    }

    @Override
    public StringProperty scan() {
        StringProperty result = new SimpleStringProperty();

        scanIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        scanIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        FXActivity.getInstance().setOnActivityResultHandler((requestCode, resultCode, data) -> {
            if (requestCode == SCAN_CODE) {
                if (resultCode == Activity.RESULT_CANCELED) {
                    // user canceled
                    Platform.runLater(() -> result.setValue(""));
                } else if (resultCode == Activity.RESULT_OK && data != null) {
                    // a barcode was scanned
                    Platform.runLater(() -> result.setValue((String) data.getExtras().get("SCAN_RESULT")));
                }
            }
        });
        FXActivity.getInstance().startActivityForResult(scanIntent, SCAN_CODE);

        return result;
    }
}
