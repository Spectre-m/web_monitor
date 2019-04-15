import difflib
from read_by_line import read_line
from write_down_diff import write_down_diff

def has_diff(fold_name):
    
    try:
        file1_lines = read_line(fold_name+'/new.txt')
    except:
        exit(0)
    
    file2_lines = read_line(fold_name+'/old.txt')

    d = difflib.Differ()    #创建Diff对象
    diff = d.compare(file1_lines,file2_lines)
    
#    print('\n'.join(list(diff)))
    diff_news = [] # 差异内容
    diff_news = '\n'.join(list(diff))

    write_down_diff(diff_news,fold_name)
    read_diff = read_line(fold_name+'/diff.txt')# 写入文件再读出为了使每一行成为一个元素
    
    diff_text = [] # 差异内容（每一行为一个元素）
    for item in read_diff:
        try:
            if item[0]=='-':
                diff_text.append(item[2:]) # 将开头的-号和‘ ’去掉
        except:
            pass

    # 得到的内容再与old.txt做循环比较，若无相同元素，则为用户需要的新信息
    #print(file2_lines) 
    for i in file2_lines:
        for j in diff_text:
            if i == j:
                diff_text.remove(j)

    return diff_text

#    d = difflib.HtmlDiff()
#    print(d.make_file(file1_lines,file2_lines))
    
