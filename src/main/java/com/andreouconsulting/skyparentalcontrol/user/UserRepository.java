package com.andreouconsulting.skyparentalcontrol.user;

import java.util.UUID;

import com.andreouconsulting.skyparentalcontrol.movie.MovieRating;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public MovieRating getMaxMovieLevel(UUID userId) {
        // Use userId to get the profile of the user, or their settings or just the parental control level.
        // Could be another service, or a database.
        return MovieRating.TWELVE;
    }
}
