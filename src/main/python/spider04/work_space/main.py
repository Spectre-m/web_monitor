# 程序的主体调用部分

from news_get import news_get
from has_different_btw_two import has_diff
#import datetime
import sys
import hashlib

#从Java获取的参数值：

url = sys.argv[1] #对应网站url
selectors = sys.argv[2] #选择器
input_text = sys.argv[3] #用户选择的关键字，若为全选为all
needSpiderBoolean = sys.argv[4] #对于不同用户关注的统一网站以及选择器判断是否需要再次爬虫，优化性能

# print( url )
# print( needSpiderBoolean )

#根据url，selectors，采用md5加密为16进制字符串
temp_fold_name = url.replace("/","").replace(".","").replace(":","") + selectors.replace("/","").replace(".","").replace(":","").replace(" ","").replace(">","").replace("#","")
fold_name = 'python_spider/' + hashlib.md5(temp_fold_name.encode('utf-8')).hexdigest()

def main(url,fold_name,selectors,input_text,needSpiderBoolean):

    #仅python单独测试用
    #temp_fold_name = url.replace("/","").replace(".","").replace(":","") + selectors.replace("/","").replace(".","").replace(":","").replace(" ","").replace(">","").replace("#","")
    #fold_name = 'python_spider/' + hashlib.md5(temp_fold_name.encode('utf-8')).hexdigest()

    if needSpiderBoolean == 'true':

        # print( "true" )
        
        news_get(url,fold_name,selectors)

        diff_text = has_diff(fold_name)# 参数为fold_name 此参数的值非程序生成，需要同selectors一起给定

    #file_name = fold_name+'/out.txt' #测试用，向每文件写入这段时间网站的更新消息

    #time1 = datetime.datetime.now() # 得到当前时间
    #time1_str = datetime.datetime.strftime(time1,'%Y-%m-%d %H:%M:%S')
    #text = input_text()

# 判断输入是否为‘all’，若为all则输出网页所有的变化值························(此处可修改为需要的输入)
        if input_text !='all':
            out_put = []
            for item in diff_text:
                if input_text in item:
                    out_put.append(item)
            count = 0   #统计是否有输出
            for item in out_put:   
                print(item.split(" ")[-1])
                print(''.join(item.split(" ")[:-1]))   
            #try:
            #    with open(file_name,'a+') as file_object:
            #        file_object.write(time1_str+'\n')
            #        file_object.write(str(item)+'\n')            
            #except Exception as e:
            #    print(e)                
                count += 1 
            if count==0:
                print("没有相关关键字")
            #try:
            #    with open(file_name,'a+') as file_object:
            #        file_object.write(time1_str+'\n')
            #        file_object.write("没有相关关键字"+'\n')
            #except Exception as e:
            #    print(e)
        else:
            for item in diff_text:
                print(item.split(" ")[-1])
                print(''.join(item.split(" ")[:-1]))
            #try:
            #    with open(file_name,'a+') as file_object:
            #        file_object.write(time1_str+'\n')
            #        file_object.write(item+'\n')       
            #except Exception as e:
            #    print(e)       
            if len(diff_text)==0:
                print("没有更新消息")
            #try:
            #    with open(file_name,'a+') as file_object:
            #        file_object.write(time1_str+'\n')
            #        file_object.write("没有相关关键字"+'\n')   
            #except Exception as e:
            #    print(e)
    if needSpiderBoolean == 'false':

        # print( "false" )
        
        diff_text = has_diff(fold_name)
        
        if input_text !='all':
            out_put = []
            for item in diff_text:
                if input_text in item:
                    out_put.append(item)
            count = 0   #统计是否有输出
            for item in out_put:   
                print(item.split(" ")[-1])
                print(''.join(item.split(" ")[:-1]))             
                count += 1 
            if count==0:
                print("没有相关关键字")
            
        else:
            for item in diff_text:
                print(item.split(" ")[-1])
                print(''.join(item.split(" ")[:-1]))
             
            if len(diff_text)==0:
                print("没有更新消息")
        

#main('https://news.sina.com.cn/','python_spider/all',
#'#syncad_1 > h1,p:nth-of-type(4) > a,#ad_entry_b2 > ul > li.topli14,#ad_entry_b2 > ul > li',
#'all',
#'False')
#print('-------------------')

#main('https://news.sina.com.cn/','python_spider/all',
#'#syncad_1 > h1,p:nth-of-type(4) > a,#ad_entry_b2 > ul > li.topli14,#ad_entry_b2 > ul > li',
#'中',
#'False')
#print('-------------------')

#main('https://news.sina.com.cn/','python_spider/all',
#'#syncad_1 > h1,p:nth-of-type(4) > a,#ad_entry_b2 > ul > li.topli14,#ad_entry_b2 > ul > li',
#'国',
#'False')


main(url,fold_name,selectors,input_text,needSpiderBoolean)
