# 简单页面获取

#coding:gbk
import urllib.request

def get_html(url):

    page = urllib.request.urlopen(url) # 打开网页

    htmlcode = page.read().decode('gbk')
    
    #if page.getcode()==200:
    #    print("读取成功") # 获取状态码，200表示成功

    return htmlcode


