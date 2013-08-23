package codeOrchestra.colt.core.ui.components.inputForms

import codeOrchestra.colt.core.ui.components.fileset.FilesetInput
import codeOrchestra.groovyfx.FXBindable
import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.Stage

/**
 * @author Dima Kruk
 */
class TestComponents extends Application {
    @FXBindable String text = "srt2/";

    @Override
    void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("COLT 1.2");
        VBox root = new VBox()
        root.stylesheets.add(getClass().getResource("../main.css").toString())
        FilesetInput input = new FilesetInput()
        input.files().bindBidirectional(text())
        root.children.add(input)

        Button button = new Button("Test")
        button.onAction = {
            text = "str/"
            text = "str2/"
        } as EventHandler
        root.children.add(button)

        Scene scene = new Scene(root, 580, 820)
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(TestComponents, args);
    }

}
