package com.andreouconsulting.skyparentalcontrol.user;

import static com.andreouconsulting.skyparentalcontrol.movie.MovieRating.U;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import com.andreouconsulting.skyparentalcontrol.control.ParentalControlService;
import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    private final ParentalControlService parentalControlService = mock(ParentalControlService.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserController controller = new UserController(userRepository, parentalControlService);

    @Test
    public void shouldReturn200AndWhateverBooleanTheServiceReturnsAsItsBodyWhenThereIsNoError() throws TechnicalFailureException, TitleNotFoundException {
        doReturn(U).when(userRepository).getMaxMovieRatingPreference(any());
        doReturn(false).when(parentalControlService).isAllowedToWatchMovie(any(), any());

        ResponseEntity response = controller.isAllowedToWatchMovie(randomUUID(), randomUUID());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Boolean);
    }

    @Test
    public void shouldReturn500WhenThereIsATechnicalError() throws TechnicalFailureException, TitleNotFoundException {
        doReturn(U).when(userRepository).getMaxMovieRatingPreference(any());
        doThrow(TechnicalFailureException.class).when(parentalControlService).isAllowedToWatchMovie(any(), any());

        ResponseEntity response = controller.isAllowedToWatchMovie(randomUUID(), randomUUID());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenTheTitleCannotBeFound() throws TechnicalFailureException, TitleNotFoundException {
        doReturn(U).when(userRepository).getMaxMovieRatingPreference(any());
        doThrow(TitleNotFoundException.class).when(parentalControlService).isAllowedToWatchMovie(any(), any());

        ResponseEntity response = controller.isAllowedToWatchMovie(randomUUID(), randomUUID());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}