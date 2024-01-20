"""
获取所有的歌手信息
"""
from music_163 import sql
from parse import *
start_url = "https://music.163.com/discover/artist/"
def save_artist(url):


    # 网页解析
    html = parse_url(url)
    etree_obj = parse_html(html)
    artist_elements = etree_obj.xpath('//li[contains(@class, "sml")]')
    # 遍历每个li元素并提取ID和姓名
    for artist_element in artist_elements:
        # 使用XPath提取歌手ID
        artist_id = artist_element.xpath('.//a[contains(@class, "nm")]/@href')[0].split('=')[-1]
        # 使用XPath提取歌手姓名
        artist_name = artist_element.xpath('.//a[contains(@class, "nm")]/text()')[0]
        try:
            sql.insert_artist(artist_id, artist_name)
        except Exception as e:
            # 打印错误日志
            print(e)

def get_type_url():
    """获取所有的歌手类型"""
    types = []

    html = parse_url(start_url)
    etree_obj = parse_html(html)
    type_name_list = etree_obj.xpath('//a[@class="cat-flag"]/text()')
    # print(type_name_list)
    type_url_list = etree_obj.xpath('//a[@class="cat-flag"]/@href')
    data_zip = zip(type_name_list[1:], type_url_list[1:])

    for data in data_zip:
        type = {}
        type["name"] = data[0]
        type["url"] = data[1]
        print(type)
        types.append(type)

    return types

types = get_type_url()
# print(types)
for type in types:
    for i in range(65, 91):
        url = "{}{}&initial={}".format(base_url, type["url"], i)
        print(url)
        save_artist(url)
