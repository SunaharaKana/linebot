import spacy
import re
from pysummarization.nlpbase.auto_abstractor import AutoAbstractor
from pysummarization.tokenizabledoc.mecab_tokenizer import MeCabTokenizer
from pysummarization.abstractabledoc.top_n_rank_abstractor import TopNRankAbstractor
import MeCab

nlp = spacy.load('ja_ginza_electra')


class Summary:
    def __init__(self,document):
        self.document = str(document).lower()

    def make_new_text(self):
        # 自動要約のオブジェクト
        auto_abstractor = AutoAbstractor()
        # トークナイザー設定（MeCab使用）
        auto_abstractor.tokenizable_doc = MeCabTokenizer()
        # 区切り文字設定
        auto_abstractor.delimiter_list = ["。", "\n"]
        # 抽象化&フィルタリングオブジェクト
        abstractable_doc = TopNRankAbstractor()
        # 文書要約
        result_dict = auto_abstractor.summarize(self.document, abstractable_doc)
        return ''.join(result_dict["summarize_result"])