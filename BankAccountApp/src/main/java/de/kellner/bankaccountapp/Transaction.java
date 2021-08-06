package de.kellner.bankaccountapp;

import de.kellner.bankaccountapp.util.Date;

import java.util.UUID;

public abstract class Transaction {

    public UUID id;
    public float value;
    public String sender_recipient;
    public Date date;
    public Usage usage;

    public Transaction(float value, String sender_recipient, Date date, Usage usage) {
        id = UUID.randomUUID();
        this.value = value;
        this.sender_recipient = sender_recipient;
        this.date = date;
        this.usage = usage;
    }

    public Transaction(UUID id, float value, String sender_recipient, Date date, Usage usage) {
        this.id = id;
        this.value = value;
        this.sender_recipient = sender_recipient;
        this.date = date;
        this.usage = usage;
    }

    public abstract String getTransactionType();
}
