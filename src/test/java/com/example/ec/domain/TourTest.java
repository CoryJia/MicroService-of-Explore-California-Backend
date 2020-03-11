package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TourTest {
    TourPackage tourPackage = new TourPackage("CC", "Name");

    Tour tour1 = new Tour("title", "description", "blurb", 50, "1 day", "bullet",
            "keywords", tourPackage, Difficulty.Difficult, Region.Central_Coast);

    Tour tour2 = new Tour("title", "description", "blurb", 50, "1 day", "bullet",
            "keywords", tourPackage, Difficulty.Difficult, Region.Central_Coast);

    @Test
    public void testConstructorAndGetters() {
        assertNull(tour1.getId());
        assertThat(tour1.getTitle(), is("title"));
        assertThat(tour1.getDescription(), is("description"));
        assertThat(tour1.getBlurb(), is("blurb"));
        assertThat(tour1.getPrice(), is(50));
        assertThat(tour1.getDuration(), is("1 day"));
        assertThat(tour1.getBullets(), is("bullet"));
        assertThat(tour1.getKeywords(), is("keywords"));
        assertThat(tour1.getTourPackage().getCode(), is("CC"));
        assertThat(tour1.getDifficulty(), is(Difficulty.Difficult));
        assertThat(tour1.getRegion(), is(Region.Central_Coast));

    }

    @Test
    public void equalsHashCodeVerify() {
        assertThat(tour1, is(tour2));
        assertThat(tour1.hashCode(), is(tour2.hashCode()));
    }
}