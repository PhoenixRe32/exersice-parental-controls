package com.andreouconsulting.skyparentalcontrol.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v0/user/{userId}")
public class UserController {

    @RequestMapping(path = "/watch/{movieId}/permission")
    public ResponseEntity isAllowedToWatchMovie(
            @PathVariable(value = "userId") UUID cargoId,
            @PathVariable(value = "movieId") UUID movieId) {
        return ResponseEntity.ok(true);
    }
}
