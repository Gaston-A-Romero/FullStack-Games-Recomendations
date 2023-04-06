import scrapy
from ..items import JuegosMetacriticItem
import json

class MetacriticSpider(scrapy.Spider):
    name = "metacritic"
    allowed_domains = ["https://www.metacritic.com"]

    def start_requests(self):
        with open(r"path of all links after running manipulate-links file", "rb") as f:
            data = json.load(f)
        link_array = data['link_to_game']
        start_urls = []
        for i in range(0,len(link_array)):
            start_urls.append("https://www.metacritic.com" + str(link_array[i])) 

          
        for url in start_urls:
            print(url)
            yield scrapy.Request(url)    
        

    def parse(self, response):       
        
        data = JuegosMetacriticItem()

        title = response.css('h1::text').get()
        platform = response.css('span.platform::text').get().strip() or response.css('span.platform a::text').get().strip() or None
        if platform == None: platform = ""
        company = response.css('.publisher a::text').get().strip()
        release_date = response.css('.release_data .data::text').get()
        developer = response.css('.button::text').get()
        genres = response.css('.product_genre .data::text').getall()
        picture = response.css('img.product_image::attr(src)').get()

        data['title'] = title
        data['platform'] = platform
        data['company'] = company
        data['release_date'] = release_date
        data['developer'] = developer
        data['genres'] = genres
        data['picture'] = picture

        yield data

    
        

            
