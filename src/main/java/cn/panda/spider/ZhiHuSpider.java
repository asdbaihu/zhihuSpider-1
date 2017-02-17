package cn.panda.spider;

import cn.panda.dao.ZhihuDao;
import cn.panda.entity.ZhiHuer;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lingj on 2017/2/12 0012.
 */
public class ZhiHuSpider implements PageProcessor {


    private Site site = Site.me().
            addCookie("login", "\"MTllMWVjYWFkZTk1NDhmNjlmMzdlM2E1NjQ1MGU5ZGE=|1487207038|27d0e9ca2c312efbdb92f040cd07f0f3fe9e6d9a\"").
            addCookie("z_c0", "Mi4wQUFCQWVxb1pBQUFBRU1LMTdva3FDeGNBQUFCaEFsVk5mb2ZNV0FCYlNOeWZwV0xuLWtpLUxWMDhjQUozQW9PVmRR|1487207041|c641962f481a01c21bd477cab4c64245a4db7247").
            setCharset("utf-8").
            setRetryTimes(10).
            setSleepTime(1002).
            setTimeOut(10 * 1000).
            setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.21 Safari/537.36");

    String peopleLink = "https://www.zhihu.com/people/\\w+\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*";
    String followpage = "https://www.zhihu.com/people/\\w+\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*/following";
    String followeePage = "https://www.zhihu.com/people/\\w+\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*\\-*\\w*/followers";




    public void process(Page page) {

        Long startTime = System.currentTimeMillis();

        ZhihuDao zhihuDao = new ZhihuDao();

        page.addTargetRequests(page.getHtml().regex(peopleLink).all());
        page.addTargetRequests(page.getHtml().regex(followpage).all());
        page.addTargetRequests(page.getHtml().regex(followeePage).all());

        String userName;    //用户名
        String userId;      //用户id
        String slogan;      //标语
        Integer following;  //关注了
        Integer followee;   //关注人数
        Integer agree;     //赞同
        //Integer collect;    //收藏数

        String userNameXpath = "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div/div[2]/div[1]/h1/span[1]/text()";
        String sloganXpath = "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div/div[2]/div[1]/h1/span[2]/text()";
        String followingXpath = "/html/body/div[1]/div/main/div/div/div[2]/div[2]/div[2]/div[1]/a[1]/div[2]/text()";
        String followeeXpath = "/html/body/div[1]/div/main/div/div/div[2]/div[2]/div[2]/div[1]/a[2]/div[2]/text()";
        String agreeXpath = "/html/body/div[1]/div/main/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/text()";
        String collectXpath = "/html/body/div[1]/div/main/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/text()";

        String followingStr = page.getHtml().xpath(followingXpath).toString();
        String followeeStr = page.getHtml().xpath(followeeXpath).toString();
        String agreeStr = page.getHtml().xpath(agreeXpath).toString();
        String collectStr = page.getHtml().xpath(collectXpath).toString();


//        System.out.println("followingStr--->"+followingStr);
//        System.out.println("followeeStr---->"+followeeStr);
//        System.out.println("agreeStr---->"+agreeStr);
//        System.out.println("collectStr--->"+collectStr);

        if(null == followingStr){
            followingStr = "0";
        }

        if(null == followeeStr){
            followeeStr = "0";
        }

        if(null == agreeStr || agreeStr.trim().length()==0){
            agreeStr = "0";
        }else{

            if(agreeStr.contains("优秀回答者")){
                agreeStr = "0";
            }else{
                agreeStr = agreeStr.replace("获得 ","").replace(" 次赞同","");
            }

        }

//        if(null == collectStr){
//            collectStr = "0";
//        }


        userName = page.getHtml().xpath(userNameXpath).toString();
        userId = page.getUrl().toString().replace("https://www.zhihu.com/people/","").replace("/following","").replace("/followers","");
        slogan = page.getHtml().xpath(sloganXpath).toString();

        following = Integer.parseInt(followingStr) ;
        followee = Integer.parseInt(followeeStr);
        agree = Integer.parseInt(agreeStr);
       // collect = Integer.parseInt(page.getHtml().xpath(collectXpath).toString());

//        System.out.println();
//        System.out.println("=========zhihu开始========");
//        System.out.println(page.getUrl());
//        System.out.println("userName------->"+userName);
//        System.out.println("userId------->"+userId);
//        System.out.println("slogan------->"+slogan);
//        System.out.println("following------->"+following);
//        System.out.println("followee------->"+followee);
//        System.out.println("agree------->"+agree);
//        System.out.println("collect------->"+collectStr);
//        System.out.println("============zhihu结束==============");

        //======开始储存======

        ZhiHuer zhiHuer = new ZhiHuer();

        zhiHuer.setUserName(userName);
        zhiHuer.setUserId(userId);
        zhiHuer.setSlogan(slogan);

        zhiHuer.setFollowing(following);
        zhiHuer.setAgree(agree);
        zhiHuer.setFollowee(followee);
        zhiHuer.setCollect(collectStr);


        if(null != userName){

            try{
                zhihuDao.add(zhiHuer);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Long endTime = System.currentTimeMillis();

        System.out.println("spend-->"+(endTime-startTime)+"ms");

        //======开始储存======

    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        Spider.create(new ZhiHuSpider()).
                addUrl("https://www.zhihu.com/topic").
                setScheduler(new QueueScheduler()).
                thread(30).
                run();

    }

    public static void spiderRun(){

        Spider.create(new ZhiHuSpider()).
                addUrl("https://www.zhihu.com/topic").
                setScheduler(new QueueScheduler()).
                thread(30).
                run();


    }


}
