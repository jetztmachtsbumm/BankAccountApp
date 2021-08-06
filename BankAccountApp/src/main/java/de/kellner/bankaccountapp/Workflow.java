package de.kellner.bankaccountapp;

import de.kellner.bankaccountapp.database.SQLLite;
import de.kellner.bankaccountapp.loaders.WindowLoader;
import de.kellner.bankaccountapp.logic.AddWindowLogic;
import de.kellner.bankaccountapp.logic.ChartWindowLogic;
import de.kellner.bankaccountapp.logic.MainWindowLogic;
import de.kellner.bankaccountapp.util.CSVUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Workflow {
    private static Workflow instance;

    private final WindowLoader windowLoader;
    private final SQLLite SQL_LITE;
    private final DatabaseRepository repository;
    private final AddWindowLogic addWindowLogic;
    private final ChartWindowLogic chartWindowLogic;
    private MainWindowLogic mainWindowLogic;
    private List<Revenue> revenues;
    private List<Receipt> receipts;

    private Workflow() throws IOException {
        instance = this;
        windowLoader = new WindowLoader();
        SQL_LITE = new SQLLite();
        SQL_LITE.connect();
        SQL_LITE.createTable();
        repository = InMemoryDatabaseRepository.GetInstance();
        receipts = repository.GetAllReceipts();
        revenues = repository.GetAllRevenues();
        windowLoader.loadMainWindow();
        addWindowLogic = new AddWindowLogic();
        chartWindowLogic = new ChartWindowLogic();
    }

    public static Workflow GetInstance() throws IOException {
        if(instance == null){
            return new Workflow();
        }
        return instance;
    }

    public SQLLite getSQL_LITE() {
        return SQL_LITE;
    }

    public WindowLoader getWindowLoader() {
        return windowLoader;
    }

    public MainWindowLogic getMainWindowLogic() {
        return mainWindowLogic;
    }

    public void setMainWindowLogic(MainWindowLogic mainWindowLogic) {
        this.mainWindowLogic = mainWindowLogic;
    }

    public List<Revenue> getRevenues() {
        return revenues;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public AddWindowLogic getAddWindowLogic() {
        return addWindowLogic;
    }

    public ChartWindowLogic getChartWindowLogic() {
        return chartWindowLogic;
    }
}
