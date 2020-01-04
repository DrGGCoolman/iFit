
package de.hsba.ifit.slot;

public enum Daytime {
    MORNING("Morgen"), NOON("Mittag"), AFTERNOON("Nachmittag"), EVENING("Abend");

    private final String displayValue;

    private Daytime(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}