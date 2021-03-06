
package de.hsba.ifit.course;

public enum TargetGroup {

    PREVENTION("Verletzungsvorbeugung"), RELAXED("Casual"), REHA("Rehabilitation"), WEIGHTLOSS("Gewichtsverlust"),
    EXTREME("Extreme"), MUSCLES("Muskelaufbau");

    private final String displayValue;

    private TargetGroup(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
