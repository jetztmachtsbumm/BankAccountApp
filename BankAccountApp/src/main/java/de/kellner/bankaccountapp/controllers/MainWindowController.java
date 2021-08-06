package de.kellner.bankaccountapp.controllers;

import de.kellner.bankaccountapp.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainWindowController {
    public void filter_all_click(ActionEvent actionEvent) throws IOException {
        Workflow.GetInstance().getMainWindowLogic().displayTransactions();
    }

    public void filter_commercially_click(ActionEvent actionEvent) throws IOException {
        Workflow.GetInstance().getMainWindowLogic().displayTransactions(Usage.COMMERCIALLY);
    }

    public void filter_private_click(ActionEvent actionEvent) throws IOException {
        Workflow.GetInstance().getMainWindowLogic().displayTransactions(Usage.PRIVATE);
    }

    public void add_btn_click(MouseEvent mouseEvent) throws Exception {
        Workflow.GetInstance().getWindowLoader().loadAddWindow();
    }

    public void delete_btn_click(MouseEvent mouseEvent) throws IOException {
        Workflow.GetInstance().getMainWindowLogic().deleteTransaction();
    }

    public void receipts_view_clicked(MouseEvent mouseEvent) throws IOException {
        Workflow.GetInstance().getMainWindowLogic().clearSelectionOfOtherView("revenues");
    }

    public void revenues_view_clicked(MouseEvent mouseEvent) throws IOException {
        Workflow.GetInstance().getMainWindowLogic().clearSelectionOfOtherView("receipts");
    }

    public void menu_export_click(ActionEvent actionEvent) throws Exception {
        Workflow.GetInstance().getMainWindowLogic().exportToCsv();
    }

    public void menu_show_chart(ActionEvent actionEvent) throws Exception {
        Workflow.GetInstance().getChartWindowLogic().loadChart();
    }

    public void edit_btn_click(MouseEvent mouseEvent) throws Exception {
        Workflow.GetInstance().getMainWindowLogic().editTransaction();
    }
}
