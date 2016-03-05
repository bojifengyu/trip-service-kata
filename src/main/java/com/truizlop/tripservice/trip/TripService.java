package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.UserNotLoggedInException;
import com.truizlop.tripservice.user.User;
import com.truizlop.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        if(loggedUser == null){
            throw new UserNotLoggedInException();
        }

        List<Trip> tripList = new ArrayList<>();
        if (loggedUser.areFriends(user)) {
            tripList = retrieveTripsByUser(user);
        }
        return tripList;
    }

    protected List<Trip> retrieveTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
