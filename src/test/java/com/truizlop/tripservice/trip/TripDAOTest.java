package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.CollaboratorCallException;
import com.truizlop.tripservice.user.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TripDAOTest {

    private User ANY_USER = new User();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenRequestTripsByUser(){
        TripDAO tripDAO = new TripDAO();

        thrown.expect(CollaboratorCallException.class);
        thrown.expectMessage("TripDAO should not be invoked on an unit test.");

        tripDAO.findTripsBy(ANY_USER);
    }
}