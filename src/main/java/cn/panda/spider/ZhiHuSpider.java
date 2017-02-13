package cn.panda.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by lingj on 2017/2/12 0012.
 */
public class ZhiHuSpider implements PageProcessor{


    private Site site = Site.me().
            addCookie("login","\"YjY0ZGRhNThjMmVjNDcyMGFmMzQwOWUzNjEyNjgyYTc=|1484613297|cfbabbf60f3aa16f50ef48777963a499f6ba1737\"").
            addCookie("z_c0","Mi4wQUFCQWVxb1pBQUFBRU1LMTdva3FDeGNBQUFCaEFsVk5zZk9rV0FCTEJHdndHMmkzRlR4OGVPcFZvcFBnNmJkS1dR|1486888631|138f42da548ad49ecc74b2b0a172932a929b8aac").
            setCharset("utf-8").
            setRetryTimes(5).
            setSleepTime(2000).
            setTimeOut(5 * 1000).
            addHeader("Referer", "https://www.zhihu.com/").
            setUserAgent("Mozilla/5.0 (compatible; WOW64; MSIE 10.0; Windows NT 6.2)");


    public void process(Page page) {

        System.out.println(page.getHtml());


    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {


        Spider.create(new ZhiHuSpider()).addUrl("https://www.zhihu.com/search?type=content&sort=upvote&q=java").thread(1).run();


    }

}
