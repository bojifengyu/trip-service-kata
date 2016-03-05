package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.UserNotLoggedInException;
import com.truizlop.tripservice.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.truizlop.tripservice.trip.UserBuilder.aUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    private final User NOT_LOGGED_USER = null;
    private final User UNUSED_USER = null;
    private final User LOGGED_USER = new User();
    private final User FIRST_FRIEND = new User();
    private final User SECOND_FRIEND = new User();
    private final Trip FIRST_TRIP = new Trip();
    private final Trip SECOND_TRIP = new Trip();

    @Mock TripDAO tripDAO;

    @Test(expected = UserNotLoggedInException.class)
    public void shouldThrowExceptionWhenUserIsNotLoggedIn(){
        TripService tripService = new TripService(tripDAO);

        tripService.getTripsByUser(UNUSED_USER, NOT_LOGGED_USER);
    }

    @Test
    public void shouldReturnNoTripIfUsersAreNotFriends(){
        TripService tripService = new TripService(tripDAO);

        User anyUser = aUser()
                .withFriends(FIRST_FRIEND, SECOND_FRIEND)
                .withTrips(FIRST_TRIP, SECOND_TRIP)
                .build();

        List<Trip> tripList = tripService.getTripsByUser(anyUser, LOGGED_USER);

        assertThat(tripList.size(), is(0));
    }

    @Test
    public void shouldReturnTripsIfUsersAreFriends(){
        TripService tripService = new TripService(tripDAO);

        User anyUser = aUser()
                .withFriends(FIRST_FRIEND, SECOND_FRIEND, LOGGED_USER)
                .withTrips(FIRST_TRIP, SECOND_TRIP)
                .build();
        given(tripDAO.findTripsBy(anyUser)).willReturn(anyUser.trips());

        List<Trip> tripList = tripService.getTripsByUser(anyUser, LOGGED_USER);

        assertThat(tripList.size(), is(2));
    }

}