package cn.panda.hello;

import cn.panda.spider.ZhiHuSpider;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lingj on 2017/2/16 0016.
 */
public class SpiderTask {


    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.schedule(new SpiderJob(),1000,1000*60*10);

    }


}


class SpiderJob extends TimerTask{


    public void run() {

        try{

            ZhiHuSpider.spiderRun();

        }catch (Exception e){

            e.printStackTrace();
        }


    }
}
