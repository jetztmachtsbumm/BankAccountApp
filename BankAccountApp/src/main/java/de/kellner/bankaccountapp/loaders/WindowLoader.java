package de.kellner.bankaccountapp.loaders;

import de.kellner.bankaccountapp.Receipt;
import de.kellner.bankaccountapp.Revenue;
import de.kellner.bankaccountapp.Transaction;
import de.kellner.bankaccountapp.Workflow;
import de.kellner.bankaccountapp.logic.MainWindowLogic;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
public class WindowLoader {

    public final Stage mainWindow;
    public final Stage addWindow;

    private final Stage errorWindow;
    private final Stage chartWindow;

    private ListView<Revenue> revenuesView;
    private ListView<Receipt> receiptsView;
    private Label balanceLabel;

    public WindowLoader() {
        mainWindow = new Stage();
        addWindow = new Stage();
        errorWindow = new Stage();
        chartWindow = new Stage();
    }

    public void loadMainWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BankAccountAppMainWindow.fxml"));

        Scene scene = new Scene(root, 600, 400);

        revenuesView = (ListView<Revenue>) scene.lookup("#revenuesView");
        receiptsView = (ListView<Receipt>) scene.lookup("#receiptsView");
        balanceLabel = (Label) scene.lookup("#balanceLabel");

        mainWindow.setTitle("Bank Account App");
        mainWindow.setScene(scene);
        mainWindow.show();
        Workflow.GetInstance().setMainWindowLogic(new MainWindowLogic(receiptsView, revenuesView, Workflow.GetInstance().getReceipts(), Workflow.GetInstance().getRevenues(), balanceLabel));
        Workflow.GetInstance().getMainWindowLogic().displayTransactions();
        Workflow.GetInstance().getMainWindowLogic().updateBalance();
    }

    public void loadAddWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BankAccountAppAddWindow.fxml"));

        Scene scene = new Scene(root, 400, 260);

        addWindow.setTitle("Add Transaction");
        addWindow.setScene(scene);
        addWindow.show();
    }

    public void loadEditWindow(Transaction transaction) throws Exception{
        MenuButton transactions_btn;
        MenuButton usage_btn;
        TextField textField_value;
        TextField textField_date;
        TextField textField_otheraccount;

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BankAccountAppAddWindow.fxml"));

        Scene scene = new Scene(root, 400, 260);

        transactions_btn = (MenuButton) scene.lookup("#transactions_btn");
        usage_btn = (MenuButton) scene.lookup("#usage_btn");
        textField_value = (TextField) scene.lookup("#textfield_value");
        textField_date = (TextField) scene.lookup("#textfield_date");
        textField_otheraccount = (TextField) scene.lookup("#textfield_otheraccount");

        transactions_btn.setText(transaction.getTransactionType());
        usage_btn.setText(transaction.usage.getTextRepresentation());
        textField_value.setText(String.valueOf(transaction.value));
        textField_date.setText(transaction.date.toString());
        textField_otheraccount.setText(transaction.sender_recipient);

        addWindow.setTitle("Edit Transaction");
        addWindow.setScene(scene);
        addWindow.show();
    }

    public void loadErrorWindow(String error) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BankAccountAppErrorWindow.fxml"));

        Scene scene = new Scene(root, 300, 150);

        Label label = (Label) scene.lookup("#label");
        label.setTextFill(Color.RED);
        label.setText(error);

        errorWindow.setTitle("Error!");
        errorWindow.setScene(scene);
        errorWindow.show();
    }

    public void loadSuccessWindow(String error) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BankAccountAppErrorWindow.fxml"));

        Scene scene = new Scene(root, 300, 150);

        Stage stage = new Stage();

        Label label = (Label) scene.lookup("#label");
        label.setFont(Font.font(10));
        label.setTextFill(Color.GREEN);
        label.setText(error);

        errorWindow.setTitle("Success!");
        errorWindow.setScene(scene);
        errorWindow.show();
    }

    public void loadChartWindow(ObservableList<PieChart.Data> data) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BankAccountAppChartWindow.fxml"));

        Scene scene = new Scene(root, 375, 247);

        PieChart chart = (PieChart) scene.lookup("#pie_chart");
        chart.setData(data);

        chartWindow.setTitle("Diagramm");
        chartWindow.setScene(scene);
        chartWindow.show();
    }

    public void hideErrorWindow(){
        errorWindow.hide();
    }

    public ListView<Revenue> getRevenuesView() {
        return revenuesView;
    }

    public ListView<Receipt> getReceiptsView() {
        return receiptsView;
    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }
}
