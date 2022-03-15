package org.openjfx.Views;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewLevel extends Scene {
    public ViewLevel(ViewManager viewManager) {
        super(viewManager.getMainPane(), viewManager.getSize()[0], viewManager.getSize()[1]);

    }
}
