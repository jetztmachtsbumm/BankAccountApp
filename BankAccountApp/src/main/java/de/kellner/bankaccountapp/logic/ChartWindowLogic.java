package de.kellner.bankaccountapp.logic;

import de.kellner.bankaccountapp.Receipt;
import de.kellner.bankaccountapp.Revenue;
import de.kellner.bankaccountapp.Workflow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public class ChartWindowLogic {
    public void loadChart() throws Exception {
        float valueOfAllRevenues = 0;
        float valueOfAllReceipts = 0;
        for(Revenue revenue : Workflow.GetInstance().getRevenues()){
            valueOfAllRevenues += revenue.value;
        }
        for(Receipt receipt : Workflow.GetInstance().getReceipts()){
            valueOfAllReceipts += receipt.value;
        }

        ObservableList<PieChart.Data> pcd = FXCollections.observableList(new ArrayList<>());
        pcd.add(new PieChart.Data("Ausgaben", valueOfAllRevenues));
        pcd.add(new PieChart.Data("Einnahmen", valueOfAllReceipts));
        Workflow.GetInstance().getWindowLoader().loadChartWindow(pcd);
    }
}
