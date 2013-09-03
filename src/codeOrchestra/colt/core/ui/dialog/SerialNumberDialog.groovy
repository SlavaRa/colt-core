package codeOrchestra.colt.core.ui.dialog

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.stage.Window

/**
 * @author Dima Kruk
 */
class SerialNumberDialog extends DialogWithImage {
    private HBox center

    Label inputLabel
    TextField serialNumber
    Image errorImage = new Image("/codeOrchestra/colt/core/ui/style/images/messages/error-48x48.png")

    SerialNumberDialog(Window owner) {
        super(owner)
    }

    @Override
    protected void initView() {
        super.initView()

        image = new Image("/codeOrchestra/colt/core/ui/style/images/ico-colt.png")
    }

    @Override
    protected void initHeader() {
        super.initHeader()

        message = "This is a demo version of COLT"
        comment = "10 compilations have remained."
    }

    @Override
    protected void initCenter() {
        center = new HBox(spacing: 8, padding: new Insets(22, 0, 24, 68))
        Button purchase = new Button(text: "Purchase", prefWidth: 204, focusTraversable: false)
        Button demo = new Button(text: "Continue With Demo", prefWidth: 204, focusTraversable: false)
        center.children.addAll(purchase, demo)

        children.add(center)
    }

    @Override
    protected void initButtons() {
        AnchorPane pane = new AnchorPane()

        inputLabel = new Label("Or enter a serial number:")
        AnchorPane.setRightAnchor(inputLabel, 0)
        AnchorPane.setLeftAnchor(inputLabel, 69)

        serialNumber = new TextField(prefHeight: 30)
        AnchorPane.setTopAnchor(serialNumber, 18)
        AnchorPane.setRightAnchor(serialNumber, 86)
        AnchorPane.setLeftAnchor(serialNumber, 63)

        ok_btn = new Button(text: "OK", prefWidth: 67, defaultButton: true, focusTraversable: false)
        AnchorPane.setTopAnchor(ok_btn, 18)
        AnchorPane.setRightAnchor(ok_btn, 0)

        pane.children.addAll(inputLabel, serialNumber, ok_btn)

        children.add(pane)

        ok_btn.onAction = {
            if(serialNumber.text.isEmpty()) {
                error("Error message")
            } else {
                stage.hide()
            }
        } as EventHandler
    }

    void error(String message, String comment = "") {
        this.message = message
        this.comment = comment

        if (children.contains(center)) {
            children.remove(center)
        }

        inputLabel.text = "Enter a serial number:"
        image = errorImage
        stage.sizeToScene()
    }

    void showInput() {
        message = "Please type the serial number purchased"
        comment = ""
        inputLabel.text = ""
        children.remove(center)

        super.show()
    }
}