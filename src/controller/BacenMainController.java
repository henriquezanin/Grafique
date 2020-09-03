package controller;


import javafx.fxml.FXML;

public class BacenMainController {
    @FXML
    void buttonChartClick(){
        Main.changeScreen(Utils.Screens.BACENCHART);
    }
}
