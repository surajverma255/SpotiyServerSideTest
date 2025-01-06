package com.spotify.test;

import com.spotify.api.SpotifyAPIClient;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected SpotifyAPIClient spotifyAPIClient;

    @BeforeClass
    public void setup() {
        spotifyAPIClient = new SpotifyAPIClient();
    }
}

