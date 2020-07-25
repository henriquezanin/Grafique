package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    private static Scene bacenScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Grafique");
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        mainScene = new Scene(fxmlMain, 1280, 720);
        Parent fxmlBacen = FXMLLoader.load(getClass().getResource("/view/bacen_screen.fxml"));
        bacenScene = new Scene(fxmlBacen, 1280, 720);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void changeScreen(Utils.Screens screens){
        switch(screens){
            case MAIN:
                stage.setScene(mainScene);
                break;
            case BACEN:
                stage.setScene(bacenScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
