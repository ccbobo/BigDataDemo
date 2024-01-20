package com.example.dsj_test.models;

public class AlbumsModel {
    private String album_id;
    private String album_name;
    private String artist_id;

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    @Override
    public String toString() {
        return "Albums{" +
                "album_id='" + album_id + '\'' +
                ", album_name='" + album_name + '\'' +
                ", artist_id='" + artist_id + '\'' +
                '}';
    }
}
