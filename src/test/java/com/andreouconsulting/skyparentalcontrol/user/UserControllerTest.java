package com.andreouconsulting.skyparentalcontrol.user;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    private final UserController controller = new UserController();

    @Test
    public void shouldReturn200WithABooleanValueAsBodyWhenTheServiceRespondsNormally() {
        ResponseEntity response = controller.isAllowedToWatchMovie(randomUUID(), randomUUID());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Boolean);
    }
}