package com.andreouconsulting.skyparentalcontrol.movie;

public enum MovieRating {
    U(0),
    PG(10),
    TWELVE(20),
    FIFTEEN(30),
    EIGHTEEN(40);

    private int comparisonValue;

    MovieRating(int ratingRelativeValue) {
        this.comparisonValue = ratingRelativeValue;
    }

    public Boolean isEqualOrLesss(MovieRating that) {
        return this.comparisonValue <= that.comparisonValue;
    }
}
