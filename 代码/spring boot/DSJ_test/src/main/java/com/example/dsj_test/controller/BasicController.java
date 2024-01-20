/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.dsj_test.controller;

import com.example.dsj_test.checker.SignatureChecker;
//import com.example.dsj_test.db_connect.Db_Connect;
import com.example.dsj_test.models.AlbumsModel;
import com.example.dsj_test.models.ArtistsModel;
import com.example.dsj_test.models.MusicModel;
import com.example.dsj_test.models.UserModel;
import com.example.dsj_test.reply.TextReply;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.dsj_test.db_connect.*;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.dsj_test.reply.TextReply;
/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {
    @RequestMapping("/")
    @ResponseBody
    public String check(String echostr, String signature, String timestamp, String nonce) {
        boolean s = SignatureChecker.checkSignature(signature, timestamp, nonce);
        if (s)
            return echostr;
        else
            return "";
    }

    @PostMapping("/")
    @ResponseBody
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> map = new HashMap<>();
        ServletInputStream inputStream = request.getInputStream();
        SAXReader reader =new SAXReader();
        try {
//            byte[] b = new byte[1024];
//            int len;
//            while ((len = inputStream.read(b)) != -1) {
//                System.out.println(new String(b, 0, len));
//            }
            //读取request,获得document对象
            Document document = reader.read(inputStream);
            //获得root节点
            Element root = document.getRootElement();
            //获取所有子节点
            List<Element> elements = root.elements();
            for (Element element : elements) {
                map.put(element.getName(), element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        String message = map.get("Content");
        String[] words=message.split(" ");
        Db_Connect db_connect=new Db_Connect();
        StringBuilder resp= new StringBuilder();
        TextReply textReply=new TextReply();
        if(words[0].equals("歌手"))
        {
            ArtistsModel search_artist=new ArtistsModel();
            ArtistsModel find_artist=new ArtistsModel();
            //输入歌手
            search_artist.setArtist_name(words[1]);
            //搜索歌手
            find_artist=db_connect.artist_connect(search_artist);
            System.out.println(find_artist);
            //根据ID搜歌手的专辑
            AlbumsModel albumsModel=new AlbumsModel();
            albumsModel.setArtist_id(find_artist.getArtist_id());
            List<AlbumsModel> albumModels=db_connect.album_connect(albumsModel);
            System.out.println(albumModels);
            //返回结果
            resp = new StringBuilder("歌手ID：" + find_artist.getArtist_id() + "\n" + "歌手名称：" + find_artist.getArtist_name() + "\n");
            resp.append("歌手专辑信息如下：").append("\n");
            resp.append("-------------\n");
            for (AlbumsModel albumModel : albumModels) {
                String albumId = albumModel.getAlbum_id();
                String albumName = albumModel.getAlbum_name();
                // 打印或使用albumId和albumName
                System.out.println("Album ID: " + albumId);
                System.out.println("Album Name: " + albumName);

                // 可以根据需要将其拼接到resp字符串中
                resp.append("专辑ID: ").append(albumId).append("\n");
                resp.append("专辑名: ").append(albumName).append("\n");
                resp.append("-------------\n");
            }
            String result=textReply.getReply(map, resp.toString());
            return result;
        }
        else if(words[0].equals("专辑")){
            MusicModel search_album_id=new MusicModel();
            //输入专辑号
            search_album_id.setAlbum_id(words[1]);
            //搜索歌曲
            List<MusicModel> find_music= db_connect.music_connect(search_album_id);
            System.out.println(find_music);
            //返回结果
            resp = new StringBuilder("专辑包含歌曲信息如下：" + "\n");
            resp.append("-------------\n");
            for (MusicModel musicModel : find_music) {
                String musicId = musicModel.getMusic_id();
                String musicName = musicModel.getMusic_name();
                // 打印或使用musicId和musicName
                System.out.println("Music ID: " + musicId);
                System.out.println("Music Name: " + musicName);

                // 可以根据需要将其拼接到resp字符串中
                resp.append("歌曲ID: ").append(musicId).append("\n");
                resp.append("歌曲名: ").append(musicName).append("\n");
                resp.append("-------------\n");
            }
            return textReply.getReply(map, resp.toString());
        }
        else {
            List< UserModel> users=db_connect.comment_connect();
            resp= new StringBuilder("热评最多用户TOP10: \n");
            for(UserModel user:users){
                String user_id= user.getUser_id();
                String user_name=user.getUser_name();
                String m= String.valueOf(user.getComment_num());
                resp.append("用户ID: ").append(user_id).append("\n");
                resp.append("用户名: ").append(user_name).append("\n");
                resp.append("评论数: ").append(m).append("\n");
                resp.append("-------------\n");
            }
            return textReply.getReply(map, resp.toString());
        }
    }



}