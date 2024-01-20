package com.example.dsj_test.db_connect;

import com.example.dsj_test.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.example.dsj_test.models.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Db_Connect {
    public List<AlbumsModel> album_connect(AlbumsModel albumsModel) throws IOException {
        //加载核心文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //工厂建造器
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        //根据配置文件建工厂
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        //建对话
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取mapper接口对象
        AlbumMapper albumMapper= sqlSession.getMapper(AlbumMapper.class);
        List<AlbumsModel> albums=albumMapper.selectAlbum(albumsModel);
        sqlSession.commit();
        return albums;
    }
    public ArtistsModel artist_connect(ArtistsModel artistsModel) throws IOException {
        //加载核心文件
        ArtistsModel artist=new ArtistsModel();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //工厂建造器
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        //根据配置文件建工厂
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        //建对话
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取mapper接口对象
        ArtistMapper artistsMapper1= sqlSession.getMapper(ArtistMapper.class);
        artist=artistsMapper1.selectArtist(artistsModel);
        sqlSession.commit();
        return artist;
    }
    public List<MusicModel> music_connect(MusicModel musicModel) throws IOException {
        //加载核心文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //工厂建造器
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        //根据配置文件建工厂
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        //建对话
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取mapper接口对象
        MusicMapper musicMapper= sqlSession.getMapper(MusicMapper.class);
        List<MusicModel> models=musicMapper.selectMusic(musicModel);
        sqlSession.commit();
        return models;
    }
    public List<UserModel> comment_connect() throws IOException {
        //加载核心文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //工厂建造器
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        //根据配置文件建工厂
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        //建对话
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取mapper接口对象
        CommentMapper commentMapper= sqlSession.getMapper(CommentMapper.class);
        List<UserModel> models=commentMapper.selectComment();
        sqlSession.commit();
        return models;
    }
}
