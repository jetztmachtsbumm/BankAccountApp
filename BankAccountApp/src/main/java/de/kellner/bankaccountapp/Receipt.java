package de.kellner.bankaccountapp;

import de.kellner.bankaccountapp.util.Date;

import java.util.UUID;

public class Receipt extends Transaction{

    public Receipt(float value, String sender_recipient, Date date, Usage usage) {
        super(value, sender_recipient, date, usage);
    }

    public Receipt(UUID id, float value, String sender_recipient, Date date, Usage usage) {
        super(id, value, sender_recipient, date, usage);
    }

    @Override
    public String getTransactionType() {
        return "Einnahme";
    }

    @Override
    public String toString() {
        return "Wert: " + value + "€ | Absender: " + sender_recipient + " | Datum: " + date.toString();
    }
}
