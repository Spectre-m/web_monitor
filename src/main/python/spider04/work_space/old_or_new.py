# 判断写入old.txt 还是 new.txt 文件

import os
from write_ways import write_down_old
from write_ways import write_down_new

def clarify_old_or_new(data,fold_name):

      
    fold_name = fold_name.strip() # 去掉首位空格
    isExist = os.path.exists(fold_name) # 判断目录是否存在

    if not isExist:
        os.makedirs(fold_name) # 若不存在则创建目录
    else:
        pass

    
    result1 = os.path.isfile(fold_name+"/old.txt")   # old.txt存在为True
    result2 = os.path.isfile(fold_name+"/new.txt")   # new.txt存在为True

    if result1==False:
        # 'old.txt'不存在,写入'old.txt'
        write_down_old(data,fold_name)
    if result1==True:
        # 'old.txt'存在，判断'new.txt'是否存在
        if result2==False:
            # 若'new.txt'不存在，写入'new.txt'
            write_down_new(data,fold_name)
        if result2==True:
            # 若'new.txt'存在，则删除'old.txt',
            # 将'new.txt'改名为'old.txt'
            # 再将data写入新创建的'new.txt'
            try:
                os.remove(fold_name+"/old.txt")
            except Exception as e:
                print(e)
            try:
                os.rename(fold_name+"/new.txt",fold_name+"/old.txt")
            except Exception as e:
                print(e)
            write_down_new(data,fold_name)

