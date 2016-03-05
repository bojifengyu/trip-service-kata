package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.UserNotLoggedInException;
import com.truizlop.tripservice.user.User;
import com.truizlop.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<>();
        User loggedUser = getLoggedUser();
        if (loggedUser != null) {
            if (loggedUser.areFriends(user)) {
                tripList = retrieveTripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> retrieveTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
