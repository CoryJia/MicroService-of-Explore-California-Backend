package com.example.ec.service;

import com.example.ec.domain.TourRating;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TourRatingServiceIntegrationTest {
    private static final int CUSTOMER_ID = 456;
    private static final int TOUR_ID = 1;
    private static final int NOT_A_TOUR_ID = 123;

    @Autowired
    private TourRatingService tourRatingService;

    //Happy path delete existing TourRating
    @Test
    public void delete() {
        List<TourRating> tourRatings = tourRatingService.lookupAll();
        tourRatingService.delete(tourRatings.get(0).getTour().getId(), tourRatings.get(0).getCustomerId());

        assertThat(tourRatingService.lookupAll().size(), is(tourRatings.size() - 1));
    }

    //Unhappy path, Tour NOT_A_TOUR_ID does not exist

    @Test(expected = NoSuchElementException.class)
    public void deleteException() {
        tourRatingService.delete(NOT_A_TOUR_ID, 1234);
    }

    //Happy Path to create a new Tour Rating

    @Test
    public void createNew() {
        //would throw NoSuchElementException if TourRating for TOUR_ID by CUSTOMER_ID already exist
        tourRatingService.createNew(TOUR_ID, CUSTOMER_ID, 2, "it was fair");

        //Verify new Tour Rating created
        TourRating newTourRating = tourRatingService.verifyTourRating(TOUR_ID, CUSTOMER_ID);
        assertThat(newTourRating.getTour().getId(), is(TOUR_ID));
        assertThat(newTourRating.getCustomerId(), is(CUSTOMER_ID));
        assertThat(newTourRating.getComment(), is("it was fair"));
        assertThat(newTourRating.getScore(), is(2));
    }

    //Unhappy path, Tour NOT_A_TOUR_ID does not exist
    @Test(expected = NoSuchElementException.class)
    public void createNewException() {
        tourRatingService.createNew(NOT_A_TOUR_ID, CUSTOMER_ID, 2, "IT WAS FAIR");
    }

    //Happy path many customers Rate our Tour


    @Test
    public void rateMany() {
        int ratings = tourRatingService.lookupAll().size();

        tourRatingService.rateMany(TOUR_ID, 5, new Integer[]{100, 101, 102});
        assertThat(tourRatingService.lookupAll().size(), is(ratings + 3));
    }

    //Unhappy Path, 2nd Invocation would create duplicates in teh database, DataIntegrityViolationException thrown

    @Test(expected = DataIntegrityViolationException.class)
    public void rateManyProveRollback() {
        int ratings = tourRatingService.lookupAll().size();
        Integer[] customers = {101, 102, 103};
        tourRatingService.rateMany(TOUR_ID, 3, customers);
        tourRatingService.rateMany(TOUR_ID, 3, customers);
    }

    //Happy Path, Update a Tour Rating already in the database
    @Test
    public void update() {
        createNew();
        TourRating tourRating = tourRatingService.update(TOUR_ID, CUSTOMER_ID, 1, "one");
        assertThat(tourRating.getTour().getId(), is(TOUR_ID));
        assertThat(tourRating.getCustomerId(), is(CUSTOMER_ID));
        assertThat(tourRating.getScore(), is(1));
        assertThat(tourRating.getComment(), is("one"));
    }

    //Unhappy path, no Tour Rating exists for tourId=1 and customer=1
    @Test(expected = NoSuchElementException.class)
    public void updateException() throws Exception {
        tourRatingService.update(1, 1, 1, "one");
    }

    //Happy Path, Update a Tour Rating already in the database
    @Test
    public void updateSome() {
        createNew();
        TourRating tourRating = tourRatingService.update(TOUR_ID, CUSTOMER_ID, 1, "one");
        assertThat(tourRating.getTour().getId(), is(TOUR_ID));
        assertThat(tourRating.getCustomerId(), is(CUSTOMER_ID));
        assertThat(tourRating.getScore(), is(1));
        assertThat(tourRating.getComment(), is("one"));
    }

    //Unhappy path, no Tour Rating exists for tourId=1 and customer=1
    @Test(expected = NoSuchElementException.class)
    public void updateSomeException() throws Exception {
        tourRatingService.update(1, 1, 1, "one");
    }


    //Happy Path get average score of a Tour.
    @Test
    public void getAverageScore() {
        assertTrue(tourRatingService.getAverageScore(TOUR_ID) == 4.0);
    }

    //UnHappy Path, Tour NOT_A_TOUR_ID does not exist
    @Test(expected = NoSuchElementException.class)
    public void getAverageScoreException() {
        tourRatingService.getAverageScore(NOT_A_TOUR_ID); //That tour does not exist
    }

}
