package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TourPackageTest {

    @Test
    public void testConstructorAndGetters() {
        TourPackage p = new TourPackage("CC", "Name");
        assertThat(p.getName(), is("Name"));
        assertThat(p.getCode(), is("CC"));
    }

    @Test
    public void equalsHashCodeVerify(){
        TourPackage p1 = new TourPackage("CC", "Name");
        TourPackage p2 = new TourPackage("CC", "Name");

        assertThat(p1, is(p2));
        assertThat(p1.hashCode(), is(p2.hashCode()));
    }

}