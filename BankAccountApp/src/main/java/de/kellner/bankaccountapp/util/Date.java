package de.kellner.bankaccountapp.util;

public class Date {

    public int day;
    public int month;
    public int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString(){
        return day + "." + month + "." + year;
    }

    public static Date getDateFromString(String string){
        int day;
        int month;
        int year;
        try {
            day = Integer.parseInt(string.split("\\.")[0]);
            month = Integer.parseInt(string.split("\\.")[1]);
            year = Integer.parseInt(string.split("\\.")[2]);
        } catch (final NumberFormatException | IndexOutOfBoundsException e){
            return null;
        }
        return new Date(day, month, year);
    }

}
