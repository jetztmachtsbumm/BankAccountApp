package de.kellner.bankaccountapp;

import de.kellner.bankaccountapp.util.Date;

import java.util.UUID;

public class Revenue extends Transaction{
    public Revenue(float value, String sender_recipient, Date date, Usage usage) {
        super(value, sender_recipient, date, usage);
    }

    public Revenue(UUID id, float value, String sender_recipient, Date date, Usage usage) {
        super(id, value, sender_recipient, date, usage);
    }

    @Override
    public String getTransactionType() {
        return "Ausgabe";
    }

    @Override
    public String toString() {
        return "Wert: " + value + "€ | Empfänger: " + sender_recipient + " | Datum: " + date.toString();
    }
}
