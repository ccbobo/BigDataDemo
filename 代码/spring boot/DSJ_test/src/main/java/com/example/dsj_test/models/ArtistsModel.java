package com.example.dsj_test.models;

public class ArtistsModel {
    private String artist_id;
    private String artist_name;

    @Override
    public String toString() {
        return "Artists{" +
                "artist_id='" + artist_id + '\'' +
                ", artist_name='" + artist_name + '\'' +
                '}';
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

}
