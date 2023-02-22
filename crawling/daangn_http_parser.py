from cmath import e
import enum
from re import sub
import requests
from bs4 import BeautifulSoup
from datetime import datetime
import json
import re
import itertools
from dateutil.relativedelta import relativedelta


time_fix_ko = ['초','분', '시간', '일', '주', '달', '년']
time_fix_en = [1, 60, 60*60, 60*60*24, 60*60*24*7, 60*60*24*30, 60*60*24*30*12]

def make_realtime(idx, num):    
    return time_fix_en[idx]*num

def daangn_http_parsing(start_idx: int, count: int):
    category_fix = ['디지털기기', '생활가전', '가구/인테리어', '생활/주방', '유아동', '유아도서', '여성의류', '여성잡화', '남성패션/잡화',
                    '스포츠/레저', '취미/게임/음반', '도서', '티켓/교환권', '가공식품', '반려동물용품', '식물', '기타중고물품', '삽니다', '뷰티/미용']

    url = f'https://www.daangn.com/articles'

    #requests.get() 함수를 사용하여 해당 url을 사용친화적으로 만듬
    data = {}
    path = "./sample.json"
    for idx in range(count):
        req = requests.get(f'{url}/{start_idx+idx}')

        #requests.text 함수를 이용하여 현재 접속된 url를 html로 bs에 넘김
        html = req.text

        soup = BeautifulSoup(html, 'html.parser')

        try:
            salespost_id = start_idx+idx
            title = soup.find('h1', attrs={'property': "schema:name"}).text
            categoryandtimes = soup.find('p', attrs={'id': "article-category"}).text
            price = soup.find('p', attrs={'id': "article-price"}).text
            content = soup.find('div', attrs={'property': "schema:description"}).text
            nickname = soup.find('div', attrs={'id': "nickname"}).text
            region_name = soup.find('div', attrs={'id': "region-name"}).text
            img_url = soup.find('img', attrs={'class': "portrait"}).attrs['data-lazy']
            update_time = datetime.now().timestamp()
        except:
            continue
        
        salespost_id = int(salespost_id)
        title = title
        category = ''.join([x for x in categoryandtimes.replace(' ', '').replace('∙', '').split('\n') if x in category_fix])
        times = ''.join([x for x in categoryandtimes.replace(' ', '').replace('∙', '').split('\n') if any(str in x for str in time_fix_ko)])
        for idx, time in enumerate(time_fix_ko):
            if times.find(time) != -1:
                num = int(''.join([num for num in times if num.isdigit()]))
                deduct_time_delta = idx
            
                
            
        try:
            price = int(price.replace(' ','').replace('원', '').replace('\n', '').replace(',', ''))
        except:
            price = 0 # 나눔

        content = content
        nickname = nickname
        region_name = region_name
        # try:
        create_time = int(update_time) - make_realtime(deduct_time_delta, num)
        # except:
        #     create_time = update_time
        img_url = img_url
        update_time = update_time
    
        
        
        data_dict = {
            "salespost_id": salespost_id,
            "title": title,
            "category": category,
            "price": price,
            'content': content,
            'nickname': nickname,
            'region_name': region_name,
            'create_time': create_time,
            'img_url': img_url,
            'update_time': update_time,
        }

        data[f'{salespost_id}'] = data_dict

    with open(path, 'w', encoding="utf-8") as outfile:
        json.dump(data, outfile, indent=4,ensure_ascii=False)



if __name__ == "__main__":
    daangn_http_parsing(535400000, 10000)