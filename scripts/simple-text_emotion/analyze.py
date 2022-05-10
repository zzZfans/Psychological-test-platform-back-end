import sys

from emotion import Emotion

if __name__ == '__main__':
    args = sys.argv

    if len(args) != 2:
        print("请携带请求文本文件路径参数 python analyze.py file_path")
        sys.exit(0)
    text_path = args[1]
    textFile = open(text_path, "r", encoding='utf-8')
    context = textFile.read()
    emotion = Emotion()
    result = emotion.emotion_count(context)
    print(str(result))
