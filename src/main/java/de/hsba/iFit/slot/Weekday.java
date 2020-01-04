
package de.hsba.ifit.slot;

public enum Weekday {
    MO("Montag"), TU("Dienstag"), WE("Mittwoch"), TH("Donnerstag"), FR("Freitag"), SA("Samstag"), SU("Sonntag");

    private final String displayValue;

    private Weekday(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}