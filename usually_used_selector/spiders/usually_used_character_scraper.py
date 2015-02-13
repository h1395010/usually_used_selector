from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from scrapy.http import Request
from usually_used_selector.items import UsuallyUsedSelectorItem
from urlparse import urljoin
from collections import OrderedDict

class DmozSpider(BaseSpider):
    name = "usually_used_character_scraper"
    allowed_domains = ["zdic.net"]
    start_urls = ['http://www.zdic.net/z/zb/cc1.htm']



    def parse(self, response):
        for url in response.xpath('//a/@href').extract():
            cb = self.parse if '/z/d' in url.lower() else self.parse_item
            yield Request(urljoin(response.url, url), callback=cb) 




    def parse_item(self, response):
        hxs = HtmlXPathSelector(response)
        item =  UsuallyUsedSelectorItem()
        item["character"] = hxs.xpath('//*[@id="ziip"]/text()').extract()
        item["delimiter"] = "#######################"
        return item





# http://www.zdic.net/z/14/js/4E00.htm 

# http://www.zdic.net/z/15/js/4E59.htm




# http://www.zdic.net/z/15/js/4E8C.htm

# http://www.zdic.net/z/16/js/5341.htm

# http://www.zdic.net/z/14/js/4E01.htm
