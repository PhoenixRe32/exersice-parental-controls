package com.andreouconsulting.skyparentalcontrol.movie;

import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;

public interface MovieService {
    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
