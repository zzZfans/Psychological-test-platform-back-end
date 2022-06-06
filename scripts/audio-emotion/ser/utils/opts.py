import argparse
import yaml

class Config:
    """dict -> Class"""
    def __init__(self, entries: dict={}):
        for k, v in entries.items():
            if k != 'params' and isinstance(v, dict):
                self.__dict__[k] = Config(v)
            else:
                self.__dict__[k] = v


def load_config(file_path: str) -> dict:
    """
    从 YAML 文件中加载配置

    Args:
        file_path (str): 配置文件路径

    Returns:
        config (dict): 配置项
    """
    f = open(file_path, 'r', encoding = 'utf-8')
    config = yaml.load(f.read(), Loader=yaml.FullLoader)
    return config

def parse_opt():
    # TODO
    parser = argparse.ArgumentParser()
    # config file
    parser.add_argument(
        '--config',
        type=str,
        default='C:\\Users\\Zfans\\Desktop\\ser\\configs\\lstm.yaml',
        help='path to the configuration file (yaml)'
    )
    parser.add_argument(
        '--audio',
        type=str,
        default='C:\\Users\\Zfans\\Desktop\\ser\\datasets\\CASIA\\happy\\201-happy-liuchanhg.wav',
        help='被分析的音频文件路径'
    )
    args = parser.parse_args()
    config_dict = load_config(args.config)
    config = Config(config_dict)
    config.audio_path = args.audio
    return config
