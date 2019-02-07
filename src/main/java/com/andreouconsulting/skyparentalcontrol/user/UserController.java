package com.andreouconsulting.skyparentalcontrol.user;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.UUID;

import com.andreouconsulting.skyparentalcontrol.control.ParentalControlService;
import com.andreouconsulting.skyparentalcontrol.exceptions.TechnicalFailureException;
import com.andreouconsulting.skyparentalcontrol.movie.MovieRating;
import com.andreouconsulting.skyparentalcontrol.movie.exceptions.TitleNotFoundException;
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
            @PathVariable(value = "userId") UUID userId,
            @PathVariable(value = "movieId") UUID movieId) {
        MovieRating movieRatingPreference = userRepository.getMaxMovieRatingPreference(userId);
        try {
            boolean allowedToWatchMovie = parentalControlService.isAllowedToWatchMovie(movieRatingPreference, movieId);
            return ResponseEntity.ok(allowedToWatchMovie);
        } catch (TitleNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("The title is not available");
        } catch (TechnicalFailureException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("The title cannot be streamed at the moment");
        }
    }
}
