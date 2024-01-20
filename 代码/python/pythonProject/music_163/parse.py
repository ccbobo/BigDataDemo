import time
import random
import requests
from lxml import etree

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36',
}
start_url = "https://music.163.com/discover/artist/"
base_url = "https://music.163.com/"

def parse_url(url):
    """解析url，得到响应内容"""
    #time.sleep(random.random())
    response = requests.get(url=url, headers=headers)
    return response.content.decode("utf-8")


def parse_html(html):
    """使用xpath解析html,返回xpath对象"""
    etree_obj = etree.HTML(html)
    return etree_obj
