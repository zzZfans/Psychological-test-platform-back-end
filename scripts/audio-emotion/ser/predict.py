import os
import numpy as np
import extract_feats.opensmile as of
import extract_feats.librosa as lf
import models
import utils


def predict(config, audio_path: str, model) -> None:
    """
    预测音频情感

    Args:
        config: 配置项
        audio_path (str): 要预测的音频路径
        model: 加载的模型
    """

    test_feature = lf.get_data(config, audio_path, train=False)

    result = model.predict(test_feature)
    result_prob = model.predict_proba(test_feature)
    print('Recogntion: ', config.class_labels[int(result)])
    print('Probability: ', result_prob)
    # utils.radar(result_prob, config.class_labels)


if __name__ == '__main__':
    config = utils.parse_opt()
    model = models.load(config)
    predict(config, config.audio_path, model)
