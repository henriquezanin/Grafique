package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    private static Scene bacenMainScene;
    private static Scene bacenChartScene;

    //Inicializa os atributos da classe
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Grafique");
        //Inicializa o atributo de classe mainScene com o fmxl do painel principal
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        mainScene = new Scene(fxmlMain, 1280, 720);
        //Inicializa o atributo de classe bacenScene com o fxml do painel Bacen
        Parent fxmlBacen = FXMLLoader.load(getClass().getResource("/view/bacen_main.fxml"));
        bacenMainScene = new Scene(fxmlBacen, 1280, 720);
        //Inicializa a cena do grafico do Bacen
        Parent fxmlBacenChart = FXMLLoader.load(getClass().getResource("/view/bacen_chart.fxml"));
        bacenChartScene = new Scene(fxmlBacenChart, 1280, 720);
        //Inicializa o processo com a cena principal
        stage.setScene(mainScene);
        stage.show();
    }

    //Metodo responsavel pela troca de cenas. Carrega as cenas a partir dos atributos de classe
    public static void changeScreen(Utils.Screens screens){
        //Utiliza um enum para organizar as trocas de tela
        switch(screens){
            case MAIN:
                stage.setScene(mainScene);
                break;
            case BACENMAIN:
                stage.setScene(bacenMainScene);
                break;
            case BACENCHART:
                stage.setScene(bacenChartScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
