#Spider that gets all the links of the games
# import scrapy
# from ..items import JuegosMetacriticItem

# class MetacriticSpider(scrapy.Spider):
#     name = "metacritic"
#     allowed_domains = ["www.metacritic.com"]
#     start_urls = ["https://www.metacritic.com/browse/games/score/metascore/all/all/filtered?view=detailed&sort=desc&page=0"]
#     num_page = 1

#     def parse(self, response):
        
#         data = JuegosMetacriticItem()
        
#         link = response.css('a.title::attr(href)').getall()
#       
#         data['link_to_game'] = link
        
#         yield data
        
#         next_page = ('https://www.metacritic.com/browse/games/score/metascore/all/all/filtered?view=detailed&sort=desc&page='+ str(MetacriticSpider.num_page))

#         if MetacriticSpider.num_page <= 202:
#             MetacriticSpider.num_page += 1 
#             yield response.follow(next_page,callback = self.parse)
            
