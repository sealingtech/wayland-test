package com.sealingtech.snts.waylandtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();

        double screenWidth = ScreenUtils.getScreenWidth();
        double screenHeight = ScreenUtils.getScreenHeight();
        double scaleFactor = ScreenUtils.getScaleFactor(screenWidth, screenHeight);
        Pane anchorPane = new AnchorPane(root);
        anchorPane.setPrefWidth(screenWidth / scaleFactor);
        anchorPane.setPrefHeight(screenHeight / scaleFactor);

        AnchorPane.setTopAnchor(root, 0.0); // 70.0 for leadtek display
        AnchorPane.setBottomAnchor(root, 0.0); // 70.0 for leadtek display
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        ScrollPane scrollPane = fxmlLoader.load();
        root.getChildren().add(scrollPane);

        Scene scene = new Scene(anchorPane);
        scene.getRoot().getTransforms().setAll(ScreenUtils.getScale(screenWidth, screenHeight));
        stage.setTitle("Hello!");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}