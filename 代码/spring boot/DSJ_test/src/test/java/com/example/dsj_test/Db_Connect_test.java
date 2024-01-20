package com.example.dsj_test;
import com.example.dsj_test.mapper.AlbumMapper;
import com.example.dsj_test.mapper.ArtistMapper;
import com.example.dsj_test.mapper.MusicMapper;
import com.example.dsj_test.models.AlbumsModel;
import com.example.dsj_test.models.ArtistsModel;
import com.example.dsj_test.models.MusicModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Db_Connect_test {
        @Test
        public void album_connect() throws IOException {
            //加载核心文件
            AlbumsModel albumsModel=new AlbumsModel();
            albumsModel.setArtist_id("1015114");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //工厂建造器
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
            //根据配置文件建工厂
            SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
            //建对话
            SqlSession sqlSession=sqlSessionFactory.openSession();
            //获取mapper接口对象
            AlbumMapper albumMapper= sqlSession.getMapper(AlbumMapper.class);
            List<AlbumsModel> modelList= albumMapper.selectAlbum(albumsModel);
            System.out.println(modelList);
            sqlSession.commit();
        }
        @Test
        public void artist_connect() throws IOException {
            //加载核心文件
            ArtistsModel artistsModel=new ArtistsModel();
            artistsModel.setArtist_name("郑宰沅");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //工厂建造器
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
            //根据配置文件建工厂
            SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
            //建对话
            SqlSession sqlSession=sqlSessionFactory.openSession();
            //获取mapper接口对象
            ArtistMapper artistsMapper1= sqlSession.getMapper(ArtistMapper.class);
            ArtistsModel artistsModel1= artistsMapper1.selectArtist(artistsModel);
            System.out.println(artistsModel1);
            sqlSession.commit();
        }
        @Test
        public void music_connect() throws IOException {
            //加载核心文件
            MusicModel musicModel = new MusicModel();
            musicModel.setAlbum_id("10062");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //工厂建造器
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
            //根据配置文件建工厂
            SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
            //建对话
            SqlSession sqlSession=sqlSessionFactory.openSession();
            //获取mapper接口对象
            MusicMapper musicMapper= sqlSession.getMapper(MusicMapper.class);
            List< MusicModel > models=musicMapper.selectMusic(musicModel);
            System.out.println(models);
            sqlSession.commit();
        }
    }
