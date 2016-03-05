package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.UserNotLoggedInException;
import com.truizlop.tripservice.user.User;
import com.truizlop.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        validateUser(loggedUser);

        return loggedUser.areFriends(user) ?
                retrieveTripsByUser(user) :
                noTrips();
    }

    private void validateUser(User loggedUser) {
        if(loggedUser == null){
            throw new UserNotLoggedInException();
        }
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<>();
    }

    private List<Trip> retrieveTripsByUser(User user) {
        return tripDAO.findTripsBy(user);
    }

}
