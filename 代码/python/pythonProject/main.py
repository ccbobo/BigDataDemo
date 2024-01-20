import requests
import random
import csv
import time
from lxml import etree

# num = [1001,1002,1003,2001,2002,2003,6001,6002,6003,7001,7002,7003,4001,4002,4003]
base_url = "https://music.163.com/"
# start_url = "https://music.163.com/discover/artist/cat?id=1001"
start_url = "https://music.163.com/discover/artist/"
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36',
}

items = []


def parse_url(url):
    """解析url，得到响应内容"""
    time.sleep(random.random())
    response = requests.get(url=url, headers=headers)
    return response.content.decode("utf-8")


def parse_html(html):
    """使用xpath解析html,返回xpath对象"""
    etree_obj = etree.HTML(html)
    return etree_obj


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


def get_data(url, type_name):
    """爬歌手数据"""
    item = {
        "type": type_name,
        "name": "",
        "url": ""
    }

    html = parse_url(url)
    etree_obj = parse_html(html)
    artist_name_list = etree_obj.xpath('//a[@class="nm nm-icn f-thide s-fc0"]/text()')
    print(artist_name_list)
    artist_url_list = etree_obj.xpath('//a[@class="nm nm-icn f-thide s-fc0"]/@href')
    artist_elements = etree_obj.xpath('//li[contains(@class, "sml")]')

    # 遍历每个li元素并提取ID和姓名
    for artist_element in artist_elements:
        # 使用XPath提取歌手ID
        artist_id = artist_element.xpath('.//a[contains(@class, "nm")]/@href')[0].split('=')[-1]

        # 使用XPath提取歌手姓名
        artist_name = artist_element.xpath('.//a[contains(@class, "nm")]/text()')[0]

        # 打印歌手ID和姓名
        print('歌手ID:', artist_id)
        print('歌手姓名:', artist_name)
        print('---')
    data_zip = zip(artist_name_list, artist_url_list)
    for data in data_zip:
        item["name"] = data[0]
        item["url"] = base_url + data[1][1:]
        items.append(item)


def save():
    """将数据保存到csv中"""
    with open("./wangyinyun.csv", "a", encoding="utf-8") as file:
        writer = csv.writer(file)
        for item in items:
            writer.writerow(item.values())


def start():
    """开始爬虫"""
    types = get_type_url()
    # print(types)
    for type in types:
        # url = base_url+type["url"]
        # url还不够完整
        # print(url)
        for i in range(65, 91):
            url = "{}{}&initial={}".format(base_url, type["url"], i)
            print(url)
            get_data(url, type["name"])
    save()
    # exit()


if __name__ == '__main__':
    start()
