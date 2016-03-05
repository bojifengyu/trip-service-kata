package com.truizlop.tripservice.trip;

import com.truizlop.tripservice.user.User;

class UserBuilder {
    private User[] friends;
    private Trip[] trips;

    public UserBuilder() {
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withFriends(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addFriends(user, friends);
        addTrips(user, trips);
        return user;
    }

    private void addTrips(User user, Trip[] trips) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }

    private void addFriends(User user, User[] friends) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }
}
