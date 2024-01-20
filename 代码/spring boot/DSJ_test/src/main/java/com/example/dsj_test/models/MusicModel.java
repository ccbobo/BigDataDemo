package com.example.dsj_test.models;

public class MusicModel {
    private String music_id;
    private String music_name;
    private String album_id;

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return "Music{" +
                "music_id='" + music_id + '\'' +
                ", music_name='" + music_name + '\'' +
                ", album_id='" + album_id + '\'' +
                '}';
    }
}
