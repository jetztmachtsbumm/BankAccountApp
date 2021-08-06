package de.kellner.bankaccountapp;

import java.util.List;

public interface DatabaseRepository {

    void CreateRevenue(final Revenue revenue);
    void CreateReceipt(final Receipt receipt);
    List<Revenue> GetAllRevenues();
    List<Receipt> GetAllReceipts();

}
