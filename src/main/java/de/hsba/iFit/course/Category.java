
package de.hsba.ifit.course;

public enum Category {
    BODYWEIGHT("Bodyweight"), WEIGHTS("Gewichte"), DANCE("Tanzen"), AQUA("Wassergymnastik"), YOGA("Yoga");

    private final String displayValue;

    private Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}