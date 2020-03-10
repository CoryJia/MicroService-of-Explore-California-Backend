package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TourRatingTest{

    private Tour tour = new Tour("title","description","blurb", 50, "1 day", "bullet",
            "keywords",new TourPackage("CC","name"), Difficulty.Difficult, Region.Central_Coast);

    @Test
    public void testConstructor1() {
        TourRating rating = new TourRating(tour, 1, 1, "Comment");
        testIt(rating);
        assertThat(rating.getComment(), is("Comment"));
    }

    @Test
    public void testConstructor2() {
        TourRating rating = new TourRating(tour, 1, 1);
        testIt(rating);
        assertThat(rating.getComment(), is("Terrible"));
    }

    @Test
    private void testIt(TourRating rating) {
        assertThat(rating.getId(), is(nullValue()));
        assertThat(rating.getTour(), is(tour));
        assertThat(rating.getScore(), is(1));
        assertThat(rating.getCustomerId(), is(1));
    }

    @Test
    public void equalsHashCodeVerify(){
        TourRating rating1 = new TourRating(tour, 1, 1, "Comment");
        TourRating rating2 = new TourRating(tour, 1, 1, "Comment");

        assertEquals(rating1, rating2);
        assertEquals(rating1.hashCode(), rating2.hashCode());
    }
}