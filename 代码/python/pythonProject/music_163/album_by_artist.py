"""
根据上一步获取的歌手的 ID 来用于获取所有的专辑 ID
"""
from music_163 import sql
from parse import *


def save_albums(my_url, artist_id):
    html = parse_url(my_url)
    etree_obj = parse_html(html)
    # 获取所有专辑
    album_elements = etree_obj.xpath('//ul[@class="m-cvrlst m-cvrlst-alb4 f-cb"]/li')
    print(album_elements)
    # 提取专辑信息
    for album_element in album_elements:
        # 使用try/except块来处理XPath表达式可能找不到元素的情况
        try:
            album_id = album_element.xpath('.//a[@class="tit s-fc0"]/@href')[0].split('=')[-1]
            album_name = album_element.xpath('.//a[@class="tit s-fc0"]/text()')[0]
            sql.insert_album(album_id, artist_id, album_name)
            print("专辑ID:", album_id)
            print("专辑标题:", album_name)
            print()
        except IndexError:
            # 处理索引错误，例如当XPath表达式找不到元素时
            print("未找到专辑信息")


if __name__ == '__main__':
    artists = sql.get_all_artist()
    for i in artists:
        try:
            my_url = "{}{}id={}&limit=1000&offset=0".format(base_url, "/artist/album?", i['ARTIST_ID'])
            print(my_url)
            save_albums(my_url, i['ARTIST_ID'])
            # print(i)
        except Exception as e:
            # 打印错误日志
            print(str(i) + ': ' + str(e))
