package de.hsba.ifit.event;

public enum EventState {
    OK("Ok"), CONFLICT("Konflikt");

    private final String displayValue;

    private EventState(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
