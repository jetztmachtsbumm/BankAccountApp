package de.kellner.bankaccountapp;

public enum Usage {

    COMMERCIALLY("Geschäftlich"),
    PRIVATE("Privat");

    private String textRepresentation;

    Usage(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }



}
