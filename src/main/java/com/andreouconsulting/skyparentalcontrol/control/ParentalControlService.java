package com.andreouconsulting.skyparentalcontrol.control;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public final class ParentalControlService {

    public boolean isAllowedToWatchMovie(ParentalControlLevel parentalControlLevel, UUID movieId) {
        return false;
    }
}
