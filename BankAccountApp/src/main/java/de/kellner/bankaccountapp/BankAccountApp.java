package de.kellner.bankaccountapp;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class BankAccountApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Workflow.GetInstance();
    }

    public static void main(String[] args){
        launch(args);
    }
}
