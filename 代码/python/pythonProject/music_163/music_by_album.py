"""
根据专辑 ID 获取到所有的音乐 ID
"""
import json

from music_163 import sql
from parse import *


def save_music(url, album_id):
    html = parse_url(url)
    etree_obj = parse_html(html)
    # 使用XPath提取包含歌曲信息的JSON字符串
    json_str = etree_obj.xpath('//textarea[@id="song-list-pre-data"]/text()')[0]
    # 将JSON字符串转换为Python对象
    song_data = json.loads(json_str)
    #print(song_data)
    # 提取歌曲信息
    song_info_list = []
    for song in song_data:
        try:
            song_info = {
                'song_id': song['privilege']['id'],
                'song_name': song['name'],
                'album_id': song['album']['id'],
                'album_name': song['album']['name'],
                'artist_id': song['artists'][0]['id'],
                'artist_name': song['artists'][0]['name']
            }
            print(song_info)
            song_info_list.append(song_info)
            sql.insert_music(song_info['song_id'], song_info['song_name'], album_id)
        except IndexError:
            # 处理索引错误，例如当XPath表达式找不到元素时
            print("未找到专辑信息")


if __name__ == '__main__':
    albums = sql.get_all_album()
    for i in albums:
        try:
            my_url = "{}{}id={}".format(base_url, "/album?", i['ALBUM_ID'])
            print(my_url)
            save_music(my_url, i['ALBUM_ID'])
            # print(i)
        except Exception as e:
            # 打印错误日志
            print(str(i) + ': ' + str(e))
