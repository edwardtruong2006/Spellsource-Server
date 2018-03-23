package com.hiddenswitch.spellsource.impl.util;

import jskills.Rating;

import java.io.Serializable;

public class RatingRecord implements Serializable {
	public double conservativeStandardDeviationMultiplier;
	public double mean;
	public double standardDeviation;
	public double conservativeRating;

	public Rating toRating() {
		return new Rating(mean, standardDeviation, conservativeStandardDeviationMultiplier);
	}

	public static RatingRecord create(Rating rating) {
		RatingRecord record = new RatingRecord();
		record.conservativeStandardDeviationMultiplier = rating.getConservativeStandardDeviationMultiplier();
		record.mean = rating.getMean();
		record.standardDeviation = rating.getStandardDeviation();
		record.conservativeRating = rating.getConservativeRating();
		return record;
	}
}
