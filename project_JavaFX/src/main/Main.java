package main;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        MainView view = new MainView();

        view.show(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}