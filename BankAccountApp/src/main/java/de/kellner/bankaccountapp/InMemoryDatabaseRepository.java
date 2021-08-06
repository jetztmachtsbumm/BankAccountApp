package de.kellner.bankaccountapp;

import java.io.IOException;
import java.util.List;

public class InMemoryDatabaseRepository implements DatabaseRepository {

    private static InMemoryDatabaseRepository instance;

    public static InMemoryDatabaseRepository GetInstance() throws IOException {
        if (instance == null)
        {
            instance = new InMemoryDatabaseRepository();
        }
        return instance;
    }

    private List<Revenue> revenues;
    private List<Receipt> receipts;

    private InMemoryDatabaseRepository() throws IOException {
        revenues = Workflow.GetInstance().getSQL_LITE().GetAllRevenues();
        receipts = Workflow.GetInstance().getSQL_LITE().GetAllReceipts();
    }

    @Override
    public void CreateRevenue(Revenue revenue) {
        revenues.add(revenue);
    }

    @Override
    public void CreateReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    @Override
    public List<Revenue> GetAllRevenues() {
        return revenues;
    }

    @Override
    public List<Receipt> GetAllReceipts() {
        return receipts;
    }
}
