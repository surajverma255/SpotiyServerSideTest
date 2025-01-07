package com.spotify.test;

import io.qameta.allure.*;
import io.restassured.response.Response;
import com.spotify.models.Track;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Spotify API Automation")
@Feature("Track and Artist Details Validation")
public class TrackTests extends BaseTest {

    @Test(priority = 0)
    @Story("Search Track")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies the ability to search for a track by a given query.")
    public void testSearchTrack() {
        String query = "Imagine Dragons";

        Allure.step("Searching for the track: " + query);
        Response response = spotifyAPIClient.searchTrack(query);

        Allure.step("Validating response status code");
        Assert.assertEquals(response.getStatusCode(), 200);

        Allure.step("Extracting the first track name from the response");
        String firstTrackName = response.jsonPath().getString("tracks.items[0].name");
        Assert.assertNotNull(firstTrackName, "First track name is null");

        System.out.println("Executed testSearchTrack on Thread: " + Thread.currentThread().getId());
    }

    @Test
    @Story("Get Track Details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies the details of a track by its unique ID.")
    public void testGetTrackDetails() {
        String trackId = "3n3Ppam7vgaVa1iaRUc9Lp"; // Example track ID

        Allure.step("Fetching track details for track ID: " + trackId);
        Response response = spotifyAPIClient.getTrack(trackId);

        Allure.step("Validating response status code");
        Assert.assertEquals(response.getStatusCode(), 200);

        Allure.step("Mapping response to Track POJO and verifying track details");
        Track track = response.as(Track.class); // Map response to POJO
        Assert.assertEquals(track.getName(), "Mr. Brightside");
        Assert.assertEquals(track.getAlbum().getName(), "Hot Fuss");
        Assert.assertEquals(track.getArtists()[0].getName(), "The Killers");

        System.out.println("Executed testGetTrackDetails on Thread: " + Thread.currentThread().getId());
    }

    @Test(priority = -1)
    @Story("Get Artist Albums")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies the albums of an artist using their unique artist ID.")
    public void testGetArtistAlbums() {
        String artistName = "Imagine Dragons";

        Allure.step("Searching for the artist: " + artistName);
        Response searchResponse = spotifyAPIClient.searchTrack(artistName);

        Allure.step("Validating search response status code");
        Assert.assertEquals(searchResponse.getStatusCode(), 200);

        Allure.step("Extracting artist ID from the response");
        String artistId = searchResponse.jsonPath().getString("tracks.items[0].album.artists[0].id");
        Assert.assertNotNull(artistId, "Artist ID is null");

        Allure.step("Fetching albums for the artist ID: " + artistId);
        Response albumsResponse = spotifyAPIClient.getArtistAlbums(artistId);

        Allure.step("Validating albums response status code");
        Assert.assertEquals(albumsResponse.getStatusCode(), 200);

        Allure.step("Extracting and validating the first album name");
        String firstAlbumName = albumsResponse.jsonPath().getString("items[0].name");
        Assert.assertNotNull(firstAlbumName, "First album name is null");

        System.out.println("First Album Name: " + firstAlbumName);
        System.out.println("Executed testGetArtistAlbums on Thread: " + Thread.currentThread().getId());
    }

    @Test(priority = 1)
    @Story("Search and Verify My Track")
    @Severity(SeverityLevel.MINOR)
    @Description("This test verifies the ability to search and retrieve the first track's name.")
    public void testMyTrack() {
        String query = "Imagine Dragons";

        Allure.step("Searching for the track: " + query);
        Response response = spotifyAPIClient.searchTrack(query);

        Allure.step("Validating response status code");
        Assert.assertEquals(response.getStatusCode(), 200);

        Allure.step("Extracting the first track name from the response");
        String firstTrackName = response.jsonPath().getString("tracks.items[0].name");
        Assert.assertNotNull(firstTrackName, "First track name is null");

        System.out.println("Executed testMyTrack on Thread: " + Thread.currentThread().getId());
    }


    @Test(priority = 1)
    @Story("Search and Verify My Track")
    @Severity(SeverityLevel.MINOR)
    @Description("This test verifies the ability to search and retrieve the first track's name.")
    public void testPlaylistTrack() {
        String query = "Imagine Dragons";

        Allure.step("Searching for the track: " + query);
        Response response = spotifyAPIClient.searchTrack(query);

        Allure.step("Validating response status code");
        Assert.assertEquals(response.getStatusCode(), 200);

        Allure.step("Extracting the first track name from the response");
        String firstTrackName = response.jsonPath().getString("tracks.items[0].name");
        Assert.assertNotNull(firstTrackName, "First track name is null");

        System.out.println("Executed testMyTrack on Thread: " + Thread.currentThread().getId());
    }
}
