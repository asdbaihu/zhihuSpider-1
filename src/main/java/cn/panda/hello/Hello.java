package cn.panda.hello;

import cn.panda.dao.WordDao;
import cn.panda.entity.Words;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingj on 2017/2/16 0016.
 */
public class Hello {

    public static void main(String[] args) throws FileNotFoundException {

        WordDao wordDao = new WordDao();

        File file = new File("d://《三国演义》罗贯中.txt");
        System.out.println("file is exist:" + file.exists());
        FileReader reader = new FileReader(file);
        System.out.println(reader.getEncoding());

        Long fileLength = file.length();

        byte[] fileContent = new byte[fileLength.intValue()];

        try {

            FileInputStream in = new FileInputStream(file);

            in.read(fileContent);
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = null;

        try {
            content = new String(fileContent, "UTF8");
            content = content.trim().replace("\\n","");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("content.length--->" + content.length());

        //分词，2字到5字

        int rightIndex;
        int x=1;

        String str = null;

        Map<String,Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < content.length(); i++) {

            for (int y = 2; y <= 5; y++) {

                try{
                     str = content.substring(i, (i + y)).trim();

                     if(str.contains(",")||str.contains("。")||str.contains("，")||str.contains(".")||str.contains("!")||str.contains("！")||str.contains("(")||str.contains(")")||str.matches("\\d+")||str.contains("【")||str.contains("】")||str.contains("-")||str.contains("、")||str.contains("\\/")){
                         continue;
                     }

                     if(str.length()<=1){
                         continue;
                     }

                }catch (Exception e){
                    e.printStackTrace();
                }

                Integer num =  map.get(str);

                if(null == num ){
                    map.put(str,1);
                }else{
                    map.put(str,num+1);
                }

                Words words = new Words();
                words.setWord(str);

                System.out.println((x++)+"--->"+words.getWord());

            }
        }

            try {

                wordDao.batchAdd(map);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


    }

}
