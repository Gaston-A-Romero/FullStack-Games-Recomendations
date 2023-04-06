# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class JuegosMetacriticItem(scrapy.Item):
    title = scrapy.Field()
    platform = scrapy.Field()
    company = scrapy.Field()
    release_date = scrapy.Field()
    developer = scrapy.Field()
    genres = scrapy.Field()
    picture = scrapy.Field()

    #Only for links
    #link_to_game = scrapy.Field()

