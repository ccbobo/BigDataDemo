package com.example.dsj_test.models;

public class CommentModel {
    private String music_id;
    private String user_id;
    private String nickname;
    private String comment;

    @Override
    public String toString() {
        return "CommentModel{" +
                "music_id='" + music_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
