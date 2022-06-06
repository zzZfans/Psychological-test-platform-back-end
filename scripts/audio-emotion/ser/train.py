from tensorflow.keras.utils import to_categorical
import extract_feats.opensmile as of
import extract_feats.librosa as lf
import models
from utils import parse_opt


def train(config) -> None:
    """
    训练模型

    Args:
        config: 配置项

    Returns:
        model: 训练好的模型
    """

    # 加载被 preprocess.py 预处理好的特征
    x_train, x_test, y_train, y_test = lf.load_feature(config, train=True)

    # 搭建模型
    model = models.make(config=config, n_feats=x_train.shape[1])

    # 训练模型
    y_train, y_val = to_categorical(y_train), to_categorical(y_test)  # 独热编码
    model.train(
        x_train, y_train,
        x_test, y_val,
        batch_size=config.batch_size,
        n_epochs=config.epochs
    )

    # 验证模型
    model.evaluate(x_test, y_test)
    # 保存训练好的模型
    model.save(config.checkpoint_path, config.checkpoint_name)


if __name__ == '__main__':
    config = parse_opt()
    train(config)
