package com.andreouconsulting.skyparentalcontrol.control;

import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.U;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.stream.Stream;

import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.MovieRating;
import com.andreouconsulting.skyparentalcontrol.movie.MovieService;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ParentalControlServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final MovieService movieService = mock(MovieService.class);
    private final ParentalControlService service = new ParentalControlService(movieService);

    @Test
    public void shouldThrowExceptionWhenTheMovieCannotBeFound() throws TechnicalFailureException, TitleNotFoundException {
        thrown.expect(TitleNotFoundException.class);
        doThrow(TitleNotFoundException.class).when(movieService).getParentalControlLevel(any());

        service.isAllowedToWatchMovie(U, randomUUID());
    }


    @Test
    public void shouldThrowExceptionWhenThereIsATechnicalIssue() throws TechnicalFailureException, TitleNotFoundException {
        doThrow(TechnicalFailureException.class).when(movieService).getParentalControlLevel(any());
        thrown.expect(TechnicalFailureException.class);

        service.isAllowedToWatchMovie(U, randomUUID());
    }

    private Object[] allCombinationsOfCotrolLevelsAndExpectedResult() {
        return Stream.of(MovieRating.values())
                .flatMap(movieRating ->
                        Stream.of(MovieRating.values())
                                .map(preferenceRating -> new Object[] {movieRating, preferenceRating, movieRating.isEqualOrLesss(preferenceRating)})
                                .collect(toList())
                                .stream())
                .toArray();
    }

    @Test
    @Parameters(method = "allCombinationsOfCotrolLevelsAndExpectedResult")
    public void shouldReturnTrueWhenMovieRatingIsEqualOrLessThanThePreferenceRating(
            MovieRating movieRating,
            MovieRating preferenceRating,
            boolean expectedResult) throws TechnicalFailureException, TitleNotFoundException {
        doReturn(movieRating.name()).when(movieService).getParentalControlLevel(any());

        boolean isAllowedToWatchMovie = service.isAllowedToWatchMovie(preferenceRating, randomUUID());

        assertEquals(expectedResult, isAllowedToWatchMovie);
    }


}