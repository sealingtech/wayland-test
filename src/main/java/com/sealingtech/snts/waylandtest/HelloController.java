package com.sealingtech.snts.waylandtest;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

public class HelloController {
    @FXML
    private ScrollPane scrollpane;

    @FXML
    public void initialize() {
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollpane.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
           System.out.println("MouseEvent.MOUSE_PRESSED");
        });
        scrollpane.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            System.out.println("MouseEvent.MOUSE_DRAGGED");
        });
        scrollpane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("MouseEvent.MOUSE_CLICKED");
        });
        scrollpane.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            System.out.println("MouseEvent.MOUSE_MOVED");
        });
        scrollpane.addEventFilter(MouseEvent.DRAG_DETECTED, event -> {
            System.out.println("MouseEvent.DRAG_DETECTED");
        });
        scrollpane.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            System.out.println("MouseEvent.MOUSE_RELEASED");
        });

        scrollpane.addEventFilter(TouchEvent.TOUCH_PRESSED, event -> {
            System.out.println("TouchEvent.TOUCH_PRESSED");
        });
        scrollpane.addEventFilter(TouchEvent.TOUCH_RELEASED, event -> {
            System.out.println("TouchEvent.TOUCH_RELEASED");
        });
        scrollpane.addEventFilter(TouchEvent.TOUCH_MOVED, event -> {
            System.out.println("TouchEvent.TOUCH_MOVED");
        });

        scrollpane.setOnTouchPressed(event -> {
            System.out.println("OnTouchPressed");
        });
        scrollpane.setOnTouchReleased(event -> {
            System.out.println("OnTouchReleased");
        });

        scrollpane.setOnScroll(event -> {
            System.out.println("OnScroll");
        });
        scrollpane.setOnScrollStarted(event -> {
            System.out.println("OnScrollStarted");
        });
        scrollpane.setOnScrollFinished(event -> {
            System.out.println("OnScrollFinished");
        });
    }
}