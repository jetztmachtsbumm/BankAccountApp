package de.kellner.bankaccountapp.logic;

import de.kellner.bankaccountapp.*;
import de.kellner.bankaccountapp.util.Date;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class AddWindowLogic {

    public void addTransaction(MenuButton transactions_btn, MenuButton usage_btn, TextField textfield_value, TextField textfield_date, TextField textfield_otheraccount) throws Exception {
        float value;
        try {
            value = Float.parseFloat(textfield_value.getText());
        } catch (final NumberFormatException e){
            Workflow.GetInstance().getWindowLoader().loadErrorWindow("Der Betrag darf kein Zeichen enthalten und darf nicht leer sein!");
            return;
        }

        Usage usage;

        if(usage_btn.getText().equals("Geschäftlich")){
            usage = Usage.COMMERCIALLY;
        }else{
            usage = Usage.PRIVATE;
        }

        if(transactions_btn.getText().equals("Ausgabe")){
            Revenue revenue;
            if(!textfield_date.getText().isBlank()) {
                if(!textfield_otheraccount.getText().isBlank()) {
                    if(Date.getDateFromString(textfield_date.getText()) != null) {
                        revenue = new Revenue(value, textfield_otheraccount.getText(), Date.getDateFromString(textfield_date.getText()), usage);
                        InMemoryDatabaseRepository.GetInstance().CreateRevenue(revenue);
                        Workflow.GetInstance().getSQL_LITE().CreateRevenue(revenue);
                        Workflow.GetInstance().getMainWindowLogic().displayTransactions();
                        Workflow.GetInstance().getMainWindowLogic().updateBalance();
                        Workflow.GetInstance().getWindowLoader().mainWindow.show();
                        Workflow.GetInstance().getWindowLoader().addWindow.hide();
                    }else{
                        Workflow.GetInstance().getWindowLoader().loadErrorWindow("Ein Datum muss in folgendem Format geschrieben werden: (Tag.Monat.Jahr). Es müssen Zahlen verwendet werden!");
                    }
                } else {
                    Workflow.GetInstance().getWindowLoader().loadErrorWindow("Empfänger/Absender darf nicht leer stehen!");
                }
            }else{
                Workflow.GetInstance().getWindowLoader().loadErrorWindow("Das Datum darf nicht leer sein!");
            }
        }else{
            Receipt receipt;
            if(!textfield_date.getText().isBlank()) {
                if(!textfield_otheraccount.getText().isBlank()) {
                    if(Date.getDateFromString(textfield_date.getText()) != null) {
                        receipt = new Receipt(value, textfield_otheraccount.getText(), Date.getDateFromString(textfield_date.getText()), usage);
                        InMemoryDatabaseRepository.GetInstance().CreateReceipt(receipt);
                        Workflow.GetInstance().getSQL_LITE().CreateReceipt(receipt);
                        Workflow.GetInstance().getMainWindowLogic().displayTransactions();
                        Workflow.GetInstance().getMainWindowLogic().updateBalance();
                        Workflow.GetInstance().getWindowLoader().mainWindow.show();
                        Workflow.GetInstance().getWindowLoader().addWindow.hide();
                    }else{
                        Workflow.GetInstance().getWindowLoader().loadErrorWindow("Ein Datum muss in folgendem Format geschrieben werden: (Tag.Monat.Jahr). Es müssen Zahlen verwendet werden!");
                    }
                } else {
                    Workflow.GetInstance().getWindowLoader().loadErrorWindow("Empfänger/Absender darf nicht leer stehen!");
                }
            }else{
                Workflow.GetInstance().getWindowLoader().loadErrorWindow("Das Datum darf nicht leer sein!");
            }
        }
    }

}
