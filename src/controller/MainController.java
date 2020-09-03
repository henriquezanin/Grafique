package controller;

import javafx.fxml.FXML;

public class MainController {

    @FXML
    protected void imageViewerBacenClick(){
        Main.changeScreen(Utils.Screens.BACENMAIN);
    }

}
