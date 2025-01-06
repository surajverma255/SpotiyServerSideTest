package com.spotify.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpotifyAPIClient {

    private final String baseUrl = "https://api.spotify.com/v1";

    public Response getTrack(String trackId) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + AuthenticationManager.getAccessToken())
                .get("/tracks/" + trackId);
    }

    public Response searchTrack(String query) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + AuthenticationManager.getAccessToken())
                .queryParam("q", query)
                .queryParam("type", "track")
                .get("/search");

    }


    public Response getArtistAlbums(String artistId) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + AuthenticationManager.getAccessToken())
                .get("/artists/" + artistId + "/albums");
    }
}

