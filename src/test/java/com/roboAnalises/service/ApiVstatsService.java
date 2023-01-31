package com.roboAnalises.service;

import com.roboAnalises.util.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class ApiVstatsService {


    @Test
    void getLastGamesService(){
        String tokenAuthorization = Token.TOKEN_AUTHORIZATION;
        ApiVsStatsService apiVsStatsService = new ApiVsStatsService("worldcup");
        apiVsStatsService.getLastGames();
    }
}
