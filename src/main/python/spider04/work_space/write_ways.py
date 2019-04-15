# 将爬取到的字典中的信息，按一个键-值对一行的方式，写入一个空文件
# 写入old.txt 或 new.txt 文件

# 在文件夹fold_name中创建一个文件名为 ‘old.txt’ 用以存储爬取的字典内容
import codecs

def write_down_old(data,fold_name):
    file_name = fold_name+'/old.txt'
    try:
        with codecs.open(file_name,'w',encoding='utf-8') as file_object:
            for item in data:
                file_object.write(str(item)+'\n')        
    except Exception as e:
        print(e)
    
# 在文件夹fold_name中创建一个文件名为 ‘new.txt’ 用以存储爬取的字典内容
def write_down_new(data,fold_name):    
    file_name = fold_name+'/new.txt'
    try:
        with codecs.open(file_name,'w',encoding='utf-8') as file_object:
            for item in data:
                file_object.write(str(item)+'\n')        
    except Exception as e:
        print(e)


                

