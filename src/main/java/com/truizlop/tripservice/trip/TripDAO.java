package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.exception.CollaboratorCallException;
import com.truizlop.tripservice.user.User;

import java.util.List;

public class TripDAO {
    public static List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException(
                "TripDAO should not be invoked on an unit test.");
    }
}
