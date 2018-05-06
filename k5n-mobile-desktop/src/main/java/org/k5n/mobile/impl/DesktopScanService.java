/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.impl;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.k5n.mobile.api.ScanService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class DesktopScanService implements ScanService {

    private Stage scanStage;
    private TextField scanText;
    private Button scanButton;

    public DesktopScanService() {
    }

    @Override
    public StringProperty scan() {
        SimpleStringProperty simpleStringProperty = new SimpleStringProperty();

        if (scanStage == null) {
            scanStage = new Stage();

            StackPane stackPane = new StackPane();
            scanText = new TextField();
            scanButton = new Button("scan");

            VBox vbox = new VBox(10.0);
            vbox.setPadding(new Insets(10.0));
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(new Label("Manually enter result of a scan below:"), scanText, scanButton);
            stackPane.getChildren().add(vbox);

            scanStage.setScene(new Scene(stackPane, 384, 256));
        }

        EventHandler action = e -> {
            simpleStringProperty.set(scanText.getText());
            scanStage.close();
        };
        scanText.setOnAction(action);
        scanButton.setOnAction(action);

        scanStage.show();

        return simpleStringProperty;
    }
}
