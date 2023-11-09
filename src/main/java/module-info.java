module com.sealingtech.snts.waylandtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.sealingtech.snts.waylandtest to javafx.fxml;
    exports com.sealingtech.snts.waylandtest;
}