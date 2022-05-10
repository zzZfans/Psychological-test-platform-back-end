from amoscore import Emotion

emotion = Emotion()
test_text = "首先，对于我自己而言，你们所说的话特别好听，但是我特别讨厌你们这么讲话"
result = emotion.emotion_count(test_text)
print(result)
