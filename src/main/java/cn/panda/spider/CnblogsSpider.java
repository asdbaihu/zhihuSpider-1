package cn.panda.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lingj on 2017/2/15 0015.
 */
public class CnblogsSpider implements PageProcessor {


    private Site site = Site.me().
            addCookie("login", "\"YjY0ZGRhNThjMmVjNDcyMGFmMzQwOWUzNjEyNjgyYTc=|1484613297|cfbabbf60f3aa16f50ef48777963a499f6ba1737\"").
            addCookie("z_c0", "Mi4wQUFCQWVxb1pBQUFBRU1LMTdva3FDeGNBQUFCaEFsVk5zZk9rV0FCTEJHdndHMmkzRlR4OGVPcFZvcFBnNmJkS1dR|1486888631|138f42da548ad49ecc74b2b0a172932a929b8aac").
            setCharset("utf-8").
            setRetryTimes(10).
            setSleepTime(100).
            setTimeOut(10 * 1000).
            setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.21 Safari/537.36");


    String pageUrl = "https://news.cnblogs.com/n/\\d+/";
    String pages = "https://news.cnblogs.com/n/page/\\d+/";


    public void process(Page page) {

        List<String> pageUrlList =  page.getHtml().regex(pageUrl).all();
        pageUrlList = new ArrayList<String>(new HashSet<String>(pageUrlList));

        List<String> pagesList = page.getHtml().regex(pages).all();
        pagesList = new ArrayList<String>(new HashSet<String>(pagesList));

        String titleXpath = "/html/head/title";

        page.addTargetRequests(pageUrlList);
        page.addTargetRequests(pagesList);

        System.out.println("-->"+page.getUrl()+"-->"+page.getHtml().xpath(titleXpath).toString().replace("<title>","").replace("_IT新闻_博客园</title>",""));

    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        Spider.create(new CnblogsSpider()).addUrl("https://news.cnblogs.com/").thread(1).run();



    }


}
