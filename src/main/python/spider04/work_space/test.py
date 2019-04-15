# 用来测试添加的选择器是否正确

from bs4 import BeautifulSoup
import re
import url_get_utf8
import url_get_gbk
import url_get_gb2312
import url_get_ISO

#添加需要测试网站的url
url = 'http://news.zjgsu.edu.cn/18/'
#获取html # 一定要注意不同网页的解码方式应不同
try:
    html = url_get_utf8.get_html(url) #获取html
except:
    try:
        html = url_get_gbk.get_html(url)
    except:
        try:
            html = url_get_gb2312.get_html(url)
        except:
            try:
                html = url_get_ISO.get_html(url)
            except Exception as e:
                print(e)
#print(html)
soup = BeautifulSoup(html,'html.parser')  #定义一个Soup对象

selectors = 'body > div.w1200 > div.col-l > dl > dd > ul > li'
selectors = selectors.split(',')
#print(selector)
data = [] 

for i in selectors:
    try:
        packages = soup.select(i) 
        
        for package in packages:
            package = str(package).replace("\n","") #去换行符
            #print('package:---------------------------')
            #print(package)

            nodes_a = re.compile(r'<a .*?>.*?</a>') #匹配一对<a>标签 更换正则表达式匹配规则<a .*?>.*?</a>

            href = re.compile(r'href=".*?"') # 匹配 href="..."更换正则表达式匹配规href="[a-zA-z]+://[^\s]*"
            
            re_link = re.compile(r'[0-9a-zA-Z/.:_-]{1,}') # 匹配href="..."中的...

            re_title = re.compile(r'>.+<') # 匹配标题

            str_package = str(package) # findall方法参数需要str类型
            
            a = nodes_a.findall(str_package)
            #print('a:---------------------------')
            #print(a)
            for item in a:

                #print(item)
                x  = href.findall(item)
                #print(x)
                link = str(re_link.findall(str(x))[1]) #取出href="..."中的...
                
                # 匹配中文
                title = str(re_title.findall(str(item)))
                #print(title)
                #print(link)
                #title = title.replace(" ","") #去空格
                #link = link.strip()    #去空格
                info = title[3:-3]+' '+link
                data.append(info)
            #print('---------------------------------------------------------')
            
    except Exception as e:
        print(e)
#print('---------------------------')
for i in data:
    print(i)
#print(data)

