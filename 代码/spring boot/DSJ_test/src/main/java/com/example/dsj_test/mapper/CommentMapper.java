package com.example.dsj_test.mapper;

import com.example.dsj_test.models.CommentModel;
import com.example.dsj_test.models.UserModel;

import java.util.List;

public interface CommentMapper {
    List<UserModel> selectComment();
}
