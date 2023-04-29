import scrapy


class MetacriticLinksSpider(scrapy.Spider):
    name = "metacritic_links"
    allowed_domains = ["www.metacritic.com"]
    start_urls = ["http://www.metacritic.com/"]
    num_page = 1

    def parse(self, response):
        
        data = MetacriticLinksSpider()
        
        link = response.css('a.title::attr(href)').getall()
      
        data['link_to_game'] = link
        
        yield data
        
        next_page = ('https://www.metacritic.com/browse/games/score/metascore/all/all/filtered?view=detailed&sort=desc&page='+ str(MetacriticLinksSpider.num_page))

        if MetacriticLinksSpider.num_page <= 202:
            MetacriticLinksSpider.num_page += 1 
            yield response.follow(next_page,callback = self.parse)
