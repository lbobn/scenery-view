# 利用 wordcloud 库、jieba 库等制作词云。文本内容不限，格式不限。
# 三国演义人物词云
import wordcloud
import jieba
import sys

import re

# 假设text是你的中英文混合文本
text = sys.argv[1]
# text = "Hello, this is a test for jieba's mixed language segmentation. 你好，世界！"

# 使用正则表达式提取英文部分
english_pattern = r'\b[A-Za-z]+\b'
english_words = re.findall(english_pattern, text)

# 对英文部分进行分词（这里简单地将每个单词作为一个词元）
english_segments = [' '.join(word) for word in english_words]

# 对中文部分使用jieba进行分词
chinese_segments = jieba.lcut(re.sub(english_pattern, ' ', text))

# 合并中英文分词结果
mixed_segments = chinese_segments + english_segments
#
# # 输出结果
# print(mixed_segments)



# excludes = ['将军', '却说', '二人', '不可', '荆州', '如此', '不能',
#             '商议', '如何', '主公', '军士', '左右', '军马', '引兵',
#             '次日', '大喜', '天下', '于是', '东吴', '今日', '不敢',
#             '魏兵', '人马', '不知', '汉中', '陛下', '一人', '众将',
#             '只见', '蜀兵', '大叫', '上马', '此人', '后人', '城中']
# word_all = jieba.lcut(text)
# name_words = []
'''
for word in word_all:
    if word == "诸葛亮" or word == "孔明曰":
        name_words.append("孔明")
    elif word == "玄德" or word == "玄德曰" or word == "先主":
        name_words.append("刘备")
    elif word == "后主":
        name_words.append("刘禅")
    elif word == "天子":
        name_words.append("刘协")
    elif word == "关公" or word == "云长":
        name_words.append("关羽")
    elif word == "孟德" or word == "丞相":
        name_words.append("曹操")
    elif word in ['都督']:
        name_words.append("周瑜")
    elif word not in excludes and len(word) > 1:
        name_words.append(word)
'''

join = " ".join(mixed_segments)
wc = wordcloud.WordCloud(font_path="msyh.ttc",
                         max_words=100,
                         background_color='white',
                         width=1000, height=500)
wc.generate(join)
wc.to_file(r"D:\Test\Java\scenery-view\src\main\resources\static\wordcloud\word_cloud.jpg")