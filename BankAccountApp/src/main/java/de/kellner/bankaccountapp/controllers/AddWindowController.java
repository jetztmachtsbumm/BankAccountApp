package de.kellner.bankaccountapp.controllers;

import de.kellner.bankaccountapp.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddWindowController {
    @FXML
    private MenuButton transactions_btn;
    @FXML
    private MenuButton usage_btn;
    @FXML
    private TextField textfield_value;
    @FXML
    private TextField textfield_date;
    @FXML
    private TextField textfield_otheraccount;

    public void menu_revenue_click(ActionEvent actionEvent) {
        transactions_btn.setText("Ausgabe");
    }

    public void menu_receipt_click(ActionEvent actionEvent) {
        transactions_btn.setText("Einnahme");
    }

    public void menu_commercially_click(ActionEvent actionEvent) {
        usage_btn.setText("Geschäftlich");
    }

    public void menu_private_click(ActionEvent actionEvent) {
        usage_btn.setText("Privat");
    }

    public void add_btn_click(MouseEvent mouseEvent) throws Exception {

        Workflow.GetInstance().getAddWindowLogic().addTransaction(transactions_btn, usage_btn, textfield_value, textfield_date, textfield_otheraccount);
    }

}
