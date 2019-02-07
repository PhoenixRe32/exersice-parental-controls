package com.andreouconsulting.skyparentalcontrol.user;

import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.EIGHTEEN;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.FIFTEEN;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.PG;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.TWELVE;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.U;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.andreouconsulting.skyparentalcontrol.movie.MovieRating;
import com.andreouconsulting.skyparentalcontrol.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static List<User> users = Arrays.asList(
            new User(UUID.fromString("ce38a0b4-862f-456e-a08a-91f08181eb00"), U),
            new User(UUID.fromString("ce38a0b4-862f-456e-a08a-91f08181eb10"), PG),
            new User(UUID.fromString("ce38a0b4-862f-456e-a08a-91f08181eb12"), TWELVE),
            new User(UUID.fromString("ce38a0b4-862f-456e-a08a-91f08181eb15"), FIFTEEN),
            new User(UUID.fromString("ce38a0b4-862f-456e-a08a-91f08181eb18"), EIGHTEEN));

    public MovieRating getMaxMovieRatingPreference(UUID userId) throws UserNotFoundException {
        return users.stream()
                .filter(user -> user.id.equals(userId))
                .findFirst()
                .orElseThrow(UserNotFoundException::new)
                .ratingPreference;
    }

    private static final class User {
        UUID id;
        MovieRating ratingPreference;

        public User(UUID id, MovieRating ratingPreference) {
            this.id = id;
            this.ratingPreference = ratingPreference;
        }
    }
}
