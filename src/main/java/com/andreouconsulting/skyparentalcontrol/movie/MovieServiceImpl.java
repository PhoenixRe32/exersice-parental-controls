package com.andreouconsulting.skyparentalcontrol.movie;

import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.FIFTEEN;

import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        return FIFTEEN.toString();
    }
}
