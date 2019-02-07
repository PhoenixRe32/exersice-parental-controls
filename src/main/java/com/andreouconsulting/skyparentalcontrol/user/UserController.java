package com.andreouconsulting.skyparentalcontrol.user;

import java.util.UUID;

import com.andreouconsulting.skyparentalcontrol.control.ParentalControlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v0/user/{userId}")
public class UserController {

    private final UserRepository userRepository;
    private final ParentalControlService parentalControlService;

    public UserController(UserRepository userRepository, ParentalControlService parentalControlService) {
        this.userRepository = userRepository;
        this.parentalControlService = parentalControlService;
    }

    @GetMapping(path = "/watch/{movieId}/permission")
    public ResponseEntity isAllowedToWatchMovie(
            @PathVariable(value = "userId") UUID cargoId,
            @PathVariable(value = "movieId") UUID movieId) {
        return ResponseEntity.ok(true);
    }
}
