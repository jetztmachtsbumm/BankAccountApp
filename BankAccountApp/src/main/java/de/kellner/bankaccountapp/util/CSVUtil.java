package de.kellner.bankaccountapp.util;

import de.kellner.bankaccountapp.Receipt;
import de.kellner.bankaccountapp.Revenue;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtil {

    private final String[] HEADERS = {"transaction_type", "value", "sender/recipient", "date", "usage"};
    private final Writer writer = Files.newBufferedWriter(Paths.get(System.getProperty("user.home") + "/Documents/BankAccountAppData.csv"));
    private final CSVPrinter printer = CSVFormat.DEFAULT.withHeader(HEADERS).print(writer);

    public CSVUtil() throws IOException {
    }

    public void writeToCSV(List<Object> transactions) throws IOException {
        for(Object transaction : transactions) {
            if (transaction instanceof Receipt) {
                Receipt receipt = (Receipt) transaction;
                printer.printRecord("Receipt", receipt.value, receipt.sender_recipient, receipt.date.toString(), receipt.usage);
            } else if (transaction instanceof Revenue) {
                Revenue revenue = (Revenue) transaction;
                printer.printRecord("Receipt", revenue.value, revenue.sender_recipient, revenue.date.toString(), revenue.usage);
            }
        }
        printer.flush();
        printer.close();
    }

}
