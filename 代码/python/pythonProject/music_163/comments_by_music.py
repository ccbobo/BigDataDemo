# -*-coding:UTF-8-*-

from urllib import request
from bs4 import BeautifulSoup
import requests
import json
import re
from urllib import parse

from music_163 import sql

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36"}


def comment_count(url, music_id):
    data = {
        "params": "GEoiI9t0BcqwjJmpk2MMV81svreo+2b278vg0N18fRksFwZbxycKgRE7nXQMBzsLqdTyWEKOmwcu+cE7e37w68iEVMySFe5xhTq8zUhloxE8u+TAilTTJURKPaeV4QWWJ6E4iL6rCp3wkWfSwNzNSYbdyVTJLYV0cJEWaoTKXDgExVljUBtrjzi+yP2w5Lm0aDtwVvAXyRwgM1GXlaCMMAO9cJb2HWQ776KImcNM5JLlxLiJHqkN0IC7o22mDnJEKvC1criOSypUoUUBxpvaM8ixsMvGZr4A/fe6oL+93pU=",
        "encSecKey": "0c320e8b45a49008f64550c6f4eeb1f6df9eb8fce22ff38b70f41e6ce7e49d562e7af1734b2835f83651e2a985aa25aea256c3881d07185b2d4a544dd234e945b0e5c108f5462ea658182d35f761e7655d1902114ab8e22e8423ee5bc3c5abf7b71ca55b8b2ce191c027e9ba607a7752c3172737fb6522de393be36f8e549695"}
    req = requests.post(url=url, headers=headers, data=data)
    req.encoding = "utf-8"
    comments = json.loads(req.text)
    # 假设comments是你的评论数据
    comments_data = comments["hotComments"]
    # 提取 hotComments 列表中的每条评论数据
    for comment_data in comments_data:
        # 提取用户ID、昵称和评论内容
        user_id = comment_data['user']['userId'] if 'user' in comment_data and 'userId' in comment_data[
            'user'] else 'N/A'
        nickname = comment_data['user']['nickname'] if 'user' in comment_data and 'nickname' in comment_data[
            'user'] else 'N/A'
        comment_content = comment_data['content'] if 'content' in comment_data else 'N/A'
        # 在这里可以对提取到的信息进行进一步处理或输出
        print("User ID:", user_id)
        print("Nickname:", nickname)
        print("Comment Content:", comment_content)
        print("\n---\n")
        sql.insert_comments(music_id, user_id, nickname, comment_content)


if __name__ == "__main__":
    musics = sql.get_all_music()
    for i in musics:
        try:
            url = "http://music.163.com/weapi/v1/resource/comments/R_SO_4_" + i['MUSIC_ID'] + "?csrf_token="
            print(url)
            comment_count(url, i['MUSIC_ID'])
            # print(i)
        except Exception as e:
            # 打印错误日志
            print(str(i) + ': ' + str(e))
