package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RegionTest {

    @Test
    public void testFindByLabel() {
        assertThat(Region.Central_Coast, is(Region.findByLabel("Central Coast")));
        assertThat(Region.Northern_California, is(Region.findByLabel("Northern California")));
        assertThat(Region.Southern_California, is(Region.findByLabel("Southern California")));
        assertThat(Region.Varies, is(Region.findByLabel("Varies")));
    }

    @Test
    public void testGetLabel() {
        assertThat(Region.Central_Coast.getLabel(), is("Central Coast"));
        assertThat(Region.Northern_California.getLabel(), is("Northern California"));
        assertThat(Region.Southern_California.getLabel(), is("Southern California"));
        assertThat(Region.Varies.getLabel(), is("Varies"));
    }
}