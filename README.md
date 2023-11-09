# JavaFX / Wayland Test
This is a simple JavaFX app for debugging touch scrolling/dragging issues on X11 vs Wayland images.

## System Setup
The Variscite DART-MX8M-PLUS kit was used for development and testing, using Yocto images with targets core-image-x11 and core-image-weston.

## Environment Setup
### OpenJDK
JDK 21 (Linux/AArch64) can be downloaded [here](https://download.java.net/java/GA/jdk21.0.1/415e3f918a1f4062a0074a2794853d0d/12/GPL/openjdk-21.0.1_linux-aarch64_bin.tar.gz)

Extract the JDK to /usr/lib/jvm:
```shell
tar xvzf openjdk-21.0.1_linux-aarch64_bin.tar.gz -C /usr/lib/jvm/
```

### OpenJFX 
JavaFX 21 can be downloaded [here](https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-aarch64_bin-sdk.zip)

Extract to /usr/share/java/
```shell
unzip -d /usr/share/java/ openjfx-21_linux-aarch64_bin-sdk.zip 
```
## Building the App
From the top of the project directory, set JAVA_HOME to the JDK location above and build with gradle:
```shell
export JAVA_HOME=/usr/lib/jvm/jdk-21.0.1/

./gradlew build
```

## Running the App
If running the app via SSH, remember to set the display environment variables
```shell
export DISPLAY=:0
```
And if on Wayland:
```shell
export WAYLAND_DISPLAY=wayland-0
```
Then, run the app from the top of the project directory:
```shell
/usr/lib/jvm/jdk-21.0.1/bin/java -Djava.library.path=/usr/lib/ -cp "build/libs/app.jar:build/libs/*" \
--module-path /usr/share/java/javafx-sdk-21/lib \
--add-modules=javafx.controls,javafx.fxml,javafx.base,javafx.graphics \
--add-exports javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED \
--add-exports javafx.graphics/com.sun.javafx.scene.traversal=ALL-UNNAMED \
--add-exports javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED \
--add-exports javafx.controls/com.sun.javafx.scene.control.skin=ALL-UNNAMED \
--add-exports javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED \
--add-exports javafx.graphics/com.sun.javafx.util=ALL-UNNAMED \
-D com.sealingtech.snts.waylandtest.HelloApplication
```

# The Problem
On the X11 based image, the application behaves as expected. The user can scroll the list of labels by dragging their 
finger on the touch screen. However, on the Weston/Wayland image, dragging to scroll does not work. The view shifts slightly but immediately stops scrolling.
On the X11 image, the MouseEvent.MOUSE_DRAGGED event is fired repeatedly while the user moves their finger on the screen. On the Weston/Wayland image the MOUSE_DRAGGED event is immediately followed by the MOUSE_RELEASED event, event though the user is still touching the screen.

