# 将经过difflib处理过的前后内容差异的内容写入文本
import codecs

def write_down_diff(diff_news,fold_name):
    file_name = fold_name+'/diff.txt'
    try:

        with codecs.open(file_name,'w',encoding = 'utf-8') as file_object:
            file_object.write(diff_news)      
    except Exception as e:
        print(e)
