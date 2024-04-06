# 利用 wordcloud 库、jieba 库等制作词云。文本内容不限，格式不限。
# 三国演义人物词云
import wordcloud
import jieba
import sys

import re

# 假设text是你的中英文混合文本
stop_words = sys.argv[1]
word_cloud = sys.argv[2]
font_path = sys.argv[3]
text = sys.argv[4]
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
lines = open(stop_words, 'r',
             encoding='utf-8').readlines()
stopwords = set()
content = [line.strip() for line in lines]
stopwords.update(content)

join = " ".join(mixed_segments)
wc = wordcloud.WordCloud(font_path=font_path,
                         max_words=50,
                         background_color='white',
                         width=640, height=480,
                         stopwords=stopwords)
wc.generate(join)
wc.to_file(word_cloud)
