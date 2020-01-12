
package de.hsba.ifit.event;

public enum Room {
    SWIMMING("Schwimmbad"), GYM1("Fitnessraum 1"), GYM2("Fitnessraum 2"), CROSS("Crossfit Area"), DANCING("Tanzstudio");

    private final String displayValue;

    private Room(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}