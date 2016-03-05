package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.UserNotLoggedInException;
import com.truizlop.tripservice.user.User;
import org.junit.Test;

import java.util.List;

import static com.truizlop.tripservice.trip.UserBuilder.aUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TripServiceTest {

    private final User NOT_LOGGED_USER = null;
    private final User UNUSED_USER = null;
    private final User LOGGED_USER = new User();
    private final User FIRST_FRIEND = new User();
    private final User SECOND_FRIEND = new User();
    private final Trip FIRST_TRIP = new Trip();
    private final Trip SECOND_TRIP = new Trip();

    @Test(expected = UserNotLoggedInException.class)
    public void shouldThrowExceptionWhenUserIsNotLoggedIn(){
        TripService tripService = new TestableTripService(NOT_LOGGED_USER);

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void shouldReturnNoTripIfUsersAreNotFriends(){
        TripService tripService = new TestableTripService(LOGGED_USER);

        User anyUser = aUser()
                .withFriends(FIRST_FRIEND, SECOND_FRIEND)
                .withTrips(FIRST_TRIP, SECOND_TRIP)
                .build();

        List<Trip> tripList = tripService.getTripsByUser(anyUser);

        assertThat(tripList.size(), is(0));
    }

    @Test
    public void shouldReturnTripsIfUsersAreFriends(){
        TripService tripService = new TestableTripService(LOGGED_USER);

        User anyUser = aUser()
                .withFriends(FIRST_FRIEND, SECOND_FRIEND, LOGGED_USER)
                .withTrips(FIRST_TRIP, SECOND_TRIP)
                .build();

        List<Trip> tripList = tripService.getTripsByUser(anyUser);

        assertThat(tripList.size(), is(2));
    }

    static class TestableTripService extends TripService {

        private User loggedUser;

        public TestableTripService(User loggedUser) {
            this.loggedUser = loggedUser;
        }

        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> retrieveTripsByUser(User user) {
            return user.trips();
        }
    }
}