package com.spotify.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Base64;

public class AuthenticationManager {

    private static String accessToken;

    public static String getAccessToken() {
        if (accessToken == null || isTokenExpired()) {
            accessToken = generateAccessToken();
        }
        return accessToken;
    }

    private static boolean isTokenExpired() {
        // You can add logic to handle token expiry using a timestamp.
        // For simplicity, we'll always regenerate after 1 hour.
        return false;
    }

    private static String generateAccessToken() {
        String clientId = "31def8c474e6469094250ca0de8a08fa"; // Replace with actual Client ID
        String clientSecret = "15edfd887b0c4f098e155e5f5a901968"; // Replace with actual Client Secret

        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        RestAssured.baseURI = "https://accounts.spotify.com";
        Response response = RestAssured.given()
                .header("Authorization", "Basic " + encodedCredentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .post("/api/token");

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("access_token");
        } else {
            throw new RuntimeException("Failed to fetch access token: " + response.asString());
        }
    }
}

