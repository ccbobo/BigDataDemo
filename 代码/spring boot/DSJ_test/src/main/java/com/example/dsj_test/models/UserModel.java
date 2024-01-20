package com.example.dsj_test.models;

import java.math.BigInteger;

public class UserModel {
    private String user_id;
    private String user_name;
    private int comment_num;
    private int total_comment_count;  // 新增的属性，用于存储 COUNT(*)

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", comment_num=" + comment_num +
                ", total_comment_count=" + total_comment_count +
                '}';
    }

    public int getTotal_comment_count() {
        return total_comment_count;
    }

    public void setTotal_comment_count(int total_comment_count) {
        this.total_comment_count = total_comment_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

}
