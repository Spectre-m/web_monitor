from bs4 import BeautifulSoup
import re
import url_get_utf8
import url_get_gbk
import url_get_gb2312
from old_or_new import clarify_old_or_new

#url = 'http://www.zjgsu.edu.cn/news/' #浙江工商大学新闻网

def news_get(url,fold_name,selectors): #参数url：要爬取的网站，fold_name：创建存放txt文件的文件夹名，selectors传入任意个数selector
    

    try:
        html = url_get_utf8.get_html(url) #获取html
    except:
        try:
            html = url_get_gbk.get_html(url)
        except:
            try:
                html = url_get_gb2312.get_html(url)
            except Exception as e:
                print(e)

    soup = BeautifulSoup(html,'html.parser')  #定义一个Soup对象

    data = [] 
    selectors=selectors.split(',')

    for i in selectors:
        try:
            packages = soup.select(i) # 一定注意selector的正确性
        
            for package in packages:
                
                package = str(package).replace("\n","") #去换行符
                #print(package)
                #print('----------------------------')

                nodes_a = re.compile(r'<a .*?>.*?</a>') #匹配一对<a>标签

                href = re.compile(r'href=".*?"') # 匹配 href="..."
            
                re_link = re.compile(r'[0-9a-zA-Z/.:_-]{1,}') # 匹配href="..."中的...

                re_title = re.compile(r'>.+<') # 匹配标题

                str_package = str(package) # findall方法参数需要str类型
            
                a = nodes_a.findall(str_package)
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
                
                    info = title[3:-3]+' '+link
                    data.append(info)
                #print('---------------------------------------------------------')
            
        except Exception as e:
            print(e)
    #print(data) # 查看爬取信息是否正确
    
    my_data = [] # 删除字典中的重复元素
    my_data = list(set(data)) 
    my_data.sort(key=data.index)
    #print(my_data)
    #for x in my_data:
    #   print(x)
    clarify_old_or_new(my_data,fold_name)



