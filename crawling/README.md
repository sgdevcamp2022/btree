# Features

당근마켓 크롤링

URL : [https://www.daangn.com/articles/](https://www.daangn.com/articles/535400000) 

[535400000](https://www.daangn.com/articles/535400000)

[535410000](https://www.daangn.com/articles/535410000)

시간은 2023-2-15 오후 10시 경 부터 시작

총 10000개 수집

수집 내용 based on salespost from Post_server

- salespost ID = URL 가장 뒷번호 or not
- title
- category
- price
- content
- nickname
- region_name // 한글
- create_time // 실제로 만들어진 시간 (추정 )
- update_time // 현재 타임스탬프

# Usage

    git clone ...

    python daangn_http_parser.py

No Argparse because of simple

If you want to change daangn URL or count

you need to change Function daangn_http_parsing's params

## arg1 : start_idx 
## arg2 : count

start_idx means first url index you define
count means from start_idx how many you parse data from URL


# Future works

Considering that post need varaiations, additional features about random sampling is good for better than fixed

<br>


# Appendix

In my local computer, sampling 100 takes at onces
<br>
But sampling 10000 takes a while, about 30minutes .. ?


<br>
More Information is 
</br>

[In my Notion Link](https://serotina.notion.site/Crawling-a61579da455841c1a81b11c6e02bd698)