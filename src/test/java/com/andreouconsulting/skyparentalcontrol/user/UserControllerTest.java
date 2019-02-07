package com.andreouconsulting.skyparentalcontrol.user;

import static com.andreouconsulting.skyparentalcontrol.control.ParentalControlLevel.EIGHTEEN;
import static com.andreouconsulting.skyparentalcontrol.control.ParentalControlLevel.U;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.andreouconsulting.skyparentalcontrol.control.ParentalControlService;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    private final ParentalControlService parentalControlService = mock(ParentalControlService.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserController controller = new UserController(userRepository, parentalControlService);

    @Test
    public void shouldReturn200AndWhateverBooleanTheServiceReturnsAsItsBodyWhenThereIsNoError() {
        doReturn(false).when(parentalControlService).isAllowedToWatchMovie(any(), any());
        doReturn(U).when(userRepository).getMaxMovieLevel(any());

        ResponseEntity response = controller.isAllowedToWatchMovie(randomUUID(), randomUUID());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Boolean);
    }
}