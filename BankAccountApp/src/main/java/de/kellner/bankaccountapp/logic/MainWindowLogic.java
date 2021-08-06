package de.kellner.bankaccountapp.logic;

import de.kellner.bankaccountapp.*;
import de.kellner.bankaccountapp.util.CSVUtil;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainWindowLogic {

    private final List<Receipt> receipts;
    private final List<Revenue> revenues;
    private final ListView<Receipt> receiptsView;
    private final ListView<Revenue> revenuesView;
    private final Label balanceLabel;

    public MainWindowLogic(ListView<Receipt> receiptsView, ListView<Revenue> revenuesView, List<Receipt> receipts, List<Revenue> revenues, Label balanceLabel) {
        this.receiptsView = receiptsView;
        this.revenuesView = revenuesView;
        this.receipts = receipts;
        this.revenues = revenues;
        this.balanceLabel = balanceLabel;
    }

    public void displayTransactions(){
        receiptsView.getItems().setAll(new ArrayList());
        revenuesView.getItems().setAll(new ArrayList());
        for(Receipt receipt : receipts){
            receiptsView.getItems().add(receipt);
        }

        for(Revenue revenue : revenues){
            revenuesView.getItems().add(revenue);
        }
    }

    public void displayTransactions(Usage filter){
        receiptsView.getItems().setAll(new ArrayList());
        revenuesView.getItems().setAll(new ArrayList());
        if(filter == Usage.COMMERCIALLY){
            for(Receipt receipt : receipts){
                if(receipt.usage == Usage.COMMERCIALLY) {
                    receiptsView.getItems().add(receipt);
                }
            }
            for(Revenue revenue : revenues){
                if(revenue.usage == Usage.COMMERCIALLY) {
                    revenuesView.getItems().add(revenue);
                }
            }
        }else{
            for(Receipt receipt : receipts){
                if(receipt.usage == Usage.PRIVATE) {
                    receiptsView.getItems().add(receipt);
                }
            }
            for(Revenue revenue : revenues){
                if(revenue.usage == Usage.PRIVATE) {
                    revenuesView.getItems().add(revenue);
                }
            }
        }
    }

    public void updateBalance(){
        float balance = 0;
        for(Revenue revenue : revenues){
            balance -= revenue.value;
        }
        for(Receipt receipt : receipts){
            balance += receipt.value;
        }
        balance = (float) Math.round(balance * 100) / 100;
        balanceLabel.setText("Kontostand: " + balance + "€");
        if(balance <= 0){
            balanceLabel.setTextFill(Color.web("#DE281F"));
        }else{
            balanceLabel.setTextFill(Color.BLACK);
        }
    }

    public void deleteTransaction() throws IOException {
        if(revenuesView.getSelectionModel().getSelectedItem() != null) {
            revenuesView.getItems().remove(revenuesView.getSelectionModel().getSelectedItem());
            revenues.remove(revenuesView.getSelectionModel().getSelectedItem());
            Workflow.GetInstance().getSQL_LITE().deleteTransaction(revenuesView.getSelectionModel().getSelectedItem().id.toString());
        } else if(receiptsView.getSelectionModel().getSelectedItem() != null){
            receiptsView.getItems().remove(receiptsView.getSelectionModel().getSelectedItem());
            receipts.remove(receiptsView.getSelectionModel().getSelectedItem());
            Workflow.GetInstance().getSQL_LITE().deleteTransaction(receiptsView.getSelectionModel().getSelectedItem().id.toString());
        }
        updateBalance();
    }

    public void editTransaction() throws Exception {
        Transaction transaction = null;
        if(getReceiptsView().getSelectionModel().getSelectedItem() != null) {
            transaction = getReceiptsView().getSelectionModel().getSelectedItem();
            Workflow.GetInstance().getReceipts().remove(transaction);
            getReceiptsView().getItems().remove(transaction);
        }else if(getRevenuesView().getSelectionModel().getSelectedItem() != null){
            transaction = getRevenuesView().getSelectionModel().getSelectedItem();
            Workflow.GetInstance().getRevenues().remove(transaction);
            getRevenuesView().getItems().remove(transaction);
        }
        if(transaction != null) {
            Workflow.GetInstance().getSQL_LITE().deleteTransaction(transaction.id.toString());
            Workflow.GetInstance().getWindowLoader().loadEditWindow(transaction);
        }
    }

    public void exportToCsv() throws Exception {
        List<Object> transactions = new ArrayList<>();
        transactions.addAll(revenues);
        transactions.addAll(receipts);
        new CSVUtil().writeToCSV(transactions);
        Workflow.GetInstance().getWindowLoader().loadSuccessWindow("BankAccountAppData.csv wurde erfolgreich in " + System.getProperty("user.home") + "/Documents/BankAccountAppData.csv gespeichert!");
    }

    public void clearSelectionOfOtherView(String viewName){
        if(viewName.equals("receipts")){
            receiptsView.getSelectionModel().clearSelection();
        }else if(viewName.equals("revenues")){
            revenuesView.getSelectionModel().clearSelection();
        }
    }

    public ListView<Receipt> getReceiptsView() {
        return receiptsView;
    }

    public ListView<Revenue> getRevenuesView() {
        return revenuesView;
    }
}
