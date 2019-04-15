# 简单页面获取

#coding:utf-8
import urllib.request

def get_html(url):

    page = urllib.request.urlopen(url) # 打开网页
    
    htmlcode = page.read().decode("utf-8") # 读取页面源码 ‘gbk’解决中文乱码问题，不用utf-8因为utf-8报错，可能是因为特殊字符不支持
    #if page.getcode()==200:
    #    print("读取成功") # 获取状态码，200表示成功

    return htmlcode

