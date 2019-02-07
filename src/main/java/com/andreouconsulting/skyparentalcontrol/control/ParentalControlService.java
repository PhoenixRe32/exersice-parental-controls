package com.andreouconsulting.skyparentalcontrol.control;

import java.util.UUID;

import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.MovieRating;
import com.andreouconsulting.skyparentalcontrol.movie.MovieService;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ParentalControlService {

    private final MovieService movieService;

    public ParentalControlService(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean isAllowedToWatchMovie(MovieRating userPreferenceRating, UUID movieId) throws TechnicalFailureException, TitleNotFoundException {
        String movieRating = movieService.getParentalControlLevel(movieId.toString());
        return Enum.valueOf(MovieRating.class, movieRating).isEqualOrLesss(userPreferenceRating);
    }
}
