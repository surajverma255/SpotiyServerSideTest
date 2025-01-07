package com.spotify.test;

import io.restassured.response.Response;
import com.spotify.models.Track;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrackTests extends BaseTest {


    @Test(priority = 0)
    public void testSearchTrack() {
        String query = "Imagine Dragons";

        Response response = spotifyAPIClient.searchTrack(query);

        Assert.assertEquals(response.getStatusCode(), 200);

        String firstTrackName = response.jsonPath().getString("tracks.items[0].name");
        Assert.assertNotNull(firstTrackName, "First track name is null");

        System.out.println("Executed testGetTrackDetails on Thread: " + Thread.currentThread().getId());
    }

    @Test
    public void testGetTrackDetails() {
        String trackId = "3n3Ppam7vgaVa1iaRUc9Lp"; // Example track ID

        Response response = spotifyAPIClient.getTrack(trackId);


        Assert.assertEquals(response.getStatusCode(), 200);

        Track track = response.as(Track.class); // Map response to POJO
        Assert.assertEquals(track.getName(), "Mr. Brightside");
        Assert.assertEquals(track.getAlbum().getName(), "Hot Fuss");
        Assert.assertEquals(track.getArtists()[0].getName(), "The Killers");

        System.out.println("Executed testGetTrackDetails on Thread: " + Thread.currentThread().getId());
    }

    @Test(priority = -1)
    public void testGetArtistAlbums() {
        // Step 1: Search for the artist "Imagine Dragons"
        String artistName = "Imagine Dragons";
        Response searchResponse = spotifyAPIClient.searchTrack(artistName);

        // Validate search response
        Assert.assertEquals(searchResponse.getStatusCode(), 200);
        String artistId = searchResponse.jsonPath().getString("tracks.items[0].album.artists[0].id");
        Assert.assertNotNull(artistId, "Artist ID is null");

      //   Step 2: Use the artist ID to fetch their albums
        Response albumsResponse = spotifyAPIClient.getArtistAlbums(artistId);

        // Validate albums response
        Assert.assertEquals(albumsResponse.getStatusCode(), 200);
        String firstAlbumName = albumsResponse.jsonPath().getString("items[0].name");
        Assert.assertNotNull(firstAlbumName, "First album name is null");

        // Print the first album name for debugging
        System.out.println("First Album Name: " + firstAlbumName);

        System.out.println("Executed testGetTrackDetails on Thread: " + Thread.currentThread().getId());
    }



    @Test(priority = 1)
    public void testMyTrack() {
        String query = "Imagine Dragons";

        Response response = spotifyAPIClient.searchTrack(query);

        Assert.assertEquals(response.getStatusCode(), 200);

        String firstTrackName = response.jsonPath().getString("tracks.items[0].name");
        Assert.assertNotNull(firstTrackName, "First track name is null");

        System.out.println("Executed testGetTrackDetails on Thread: " + Thread.currentThread().getId());
    }
}

