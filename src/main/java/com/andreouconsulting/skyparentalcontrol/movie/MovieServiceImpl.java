package com.andreouconsulting.skyparentalcontrol.movie;

import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.EIGHTEEN;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.FIFTEEN;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.PG;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.TWELVE;
import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.U;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;
import com.andreouconsulting.skyparentalcontrol.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private static List<Movie> movies = List.of(
            new Movie(UUID.fromString("c1ccf910-2ecc-4018-b476-5e8957b5ba00"), U),
            new Movie(UUID.fromString("c1ccf910-2ecc-4018-b476-5e8957b5ba10"), PG),
            new Movie(UUID.fromString("c1ccf910-2ecc-4018-b476-5e8957b5ba12"), TWELVE),
            new Movie(UUID.fromString("c1ccf910-2ecc-4018-b476-5e8957b5ba15"), FIFTEEN),
            new Movie(UUID.fromString("c1ccf910-2ecc-4018-b476-5e8957b5ba18"), EIGHTEEN));

    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        return movies.stream()
                .filter(user -> user.id.toString().equals(movieId))
                .findFirst()
                .orElseThrow(TitleNotFoundException::new)
                .rating
                .toString();
    }

    private static final class Movie {
        UUID id;
        MovieRating rating;

        public Movie(UUID id, MovieRating rating) {
            this.id = id;
            this.rating = rating;
        }
    }
}
