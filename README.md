# WebCrawler
This is a Web Crawler App. It crawls pages with predefined depth (8) and pages to visit (10 000).
It starts from predefined page https://en.wikipedia.org/wiki/Elon_Musk and going deeper and further collected all links 
on current page and then switch to the next and so on.

The main goal is to accumulate statistic according to given terms (key words). So crawler find them on the page, 
count matches and write result into CSV file. Finally, after whole cycle app write in another CSV file the statistic 
about top 10 pages sorted by total hits.

## Requirements

## Quickstart
1. Download project from github.com/molocziszko/WebCrawlerApp and save it in your local directory.
2. After downloading navigate to WebCrawlerApp directory (use cd /WebCrawlerApp).
3. Open console and input:
   on Linux (Mac): 
   `gradlew run`
   on Windows (Powershell):
   `.\gradlew run`