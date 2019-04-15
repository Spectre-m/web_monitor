py文件（可通用）：

url_get_utf8.py 获取页面源码，其中方法：
    ----get_html(url)
        ----url：需要获取信息网页的url
        
url_get_gb2312.py 获取页面源码，其中方法：
    ----get_html(url)
        ----url：需要获取信息网页的url

url_get_gbk.py 获取页面源码，其中方法：
    ----get_html(url)
        ----url：需要获取信息网页的url
        
main.py 获取页面源码，其中方法：
    ----main(url,fold_name,selectors) （方法可通用）#方法后期需加入input_text参数获得关键词信息
        ----url：需要获取信息网页的url
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名
        ----selectors：传入任意多的选择器（每个选择器之间用逗号隔开）

read_by_line.py 将文件按行读取，其中方法：
    ----read_line(filename)（方法可通用）
        ----filename：需要读取文件的文件名

write_down_diff.py 将经过difflib处理过的前后内容差异的内容写入文本，其中方法：
    ----write_down_diff(diff_news,fold_name)（方法可通用）
        ----diff_news：得到的所有可能有前后差异信息的内容
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名

news_get.py 得到网页的html信息，得到需要获取的网页的具体新闻信息，判断新旧写入文件，其中方法：
    ----news_get(url,fold_name,selectors)（方法可通用）
        ----url：需要获取信息网页的url
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名
        ----selectors：传入任意多的选择器（每个选择器之间用逗号隔开）

old_or_new.py 判断写入old.txt 还是 new.txt 文件，其中方法：
    ----clarify_old_or_new(data,fold_name)（方法可通用）
        ----data：需要获取的网页的具体新闻信息
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名

write_ways.py 将爬取到的字典中的信息，按一个键-值对一行的方式，写入old.txt 或 new.txt 文件，其中方法：
    ----write_down_old(data,fold_name)（方法可通用）
        ----data：爬取到的字典信息
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名
    ----write_down_new(data,fold_name)（方法可通用）
        ----data：爬取到的字典信息
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名

has_different_btw_two.py 判断前后两次爬取到的网页信息是否有差异，若有差异写入文件，返回差异信息，其中方法：
    ----has_diff(fold_name)（方法可通用）
        ----fold_name：新建存储爬取内容以及差异内容的文件夹名

test.py 测试传入的选择器是否正确

---------------------------------------------------------------------------------------

