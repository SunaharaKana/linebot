# coding: utf-8
import sys
sys.path.append('C:\\Users\\xsuna\\PycharmProjects\\pythonProject\\venv\\Lib\\site-packages')
print(sys.path)

from Scraping import Scraping
from Summary import Summary

scraping = Scraping(sys.argv[1])
t = scraping.text_scraping()
print(t)
summary = Summary(t)
text = summary.make_new_text()
f = open("log.txt", "w", encoding='UTF-8')
f.write(text)
f.close()