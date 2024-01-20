package com.example.dsj_test.mapper;
import com.example.dsj_test.models.AlbumsModel;

import java.util.List;

public interface AlbumMapper {
    List<AlbumsModel> selectAlbum(AlbumsModel albumsModel);
}
