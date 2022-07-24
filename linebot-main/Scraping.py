from bs4 import BeautifulSoup
import requests
import re

class Scraping:
    def __init__(self,keyword):
        self.keyword = keyword

    def text_scraping(self):
        url = 'https://ja.wikipedia.org/wiki/{}'.format(self.keyword)

        # リンクを呼び出すコード
        r = requests.get(url)

        soup = BeautifulSoup(r.text, 'html.parser')
        text_list = soup.find_all('p')

        # print(text_list.text)

        flag = 0
        text_list_2 = []
        for i, l in enumerate(text_list):
            if '<b>' in str(l):
                flag = 1

            if flag != 0:
                l = l.text
                l = re.sub('\[.*?\]', '', l)
                text_list_2.append(l)

        if not text_list:
            return 'ページが存在しませんでした。'

        return ''.join(text_list_2)


