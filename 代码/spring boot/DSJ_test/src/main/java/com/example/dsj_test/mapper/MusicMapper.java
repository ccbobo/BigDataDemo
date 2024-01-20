package com.example.dsj_test.mapper;
import com.example.dsj_test.models.MusicModel;

import java.util.List;

public interface MusicMapper {
    List<MusicModel> selectMusic(MusicModel musicModel);
}
