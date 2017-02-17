package cn.panda.dao;

import cn.panda.daofactory.WordFactory;
import cn.panda.entity.Words;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by lingj on 2017/2/16 0016.
 */
public class WordDao {


       WordFactory wordFactory = new WordFactory();

       public void addOrUpdate(Words words) throws SQLException, ClassNotFoundException {


            Connection connection = wordFactory.getConnection();
            connection.setAutoCommit(false);

            String selectSql = "select * from words where word = ?";
            PreparedStatement selectSt = connection.prepareStatement(selectSql);
            selectSt.setString(1,words.getWord());
            ResultSet resultSet = selectSt.executeQuery();

           System.out.println("addOrUpdate--->"+words.getWord());

            //查询之后没有就新增
            if(!resultSet.next()){

                String insertSql = "insert into words (word,times) values(?,1)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
                preparedStatement.setString(1,words.getWord());

                try{
                    System.out.println("add--->"+words.getWord());
                    preparedStatement.execute();
                    connection.commit();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                    preparedStatement.close();
                    connection.close();
                }


            }else{ //有的话就更新

                String updateSql = "update words set times = times+1 where word = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setString(1,words.getWord());

                try{
                    System.out.println("update--->"+words.getWord());
                    preparedStatement.execute();
                    connection.commit();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                    preparedStatement.close();
                    connection.close();
                }
            }
        }


        public void batchAdd(Map<String,Integer> map) throws SQLException, ClassNotFoundException {


            Connection connection = wordFactory.getConnection();
            connection.setAutoCommit(false);

            int x = 1;
           String batchSql = "insert into wordsnew (word,times) values(?,?)";

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(batchSql);

            for(Map.Entry<String,Integer> entry:map.entrySet()){

                if(null != entry.getKey() && !entry.getKey().equals("")){

                    System.out.println((x++)+"--->"+"Key="+entry.getKey()+",Value="+entry.getValue());
                    preparedStatement.setString(1,entry.getKey());
                    preparedStatement.setInt(2,entry.getValue());

                    preparedStatement.addBatch();
                }


                if(x>=1000&&x%1000==0){

                    try{
                        preparedStatement.executeBatch();
                        connection.commit();

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        preparedStatement.close();
                        preparedStatement = connection.prepareStatement(batchSql);
                    }


                }

            }
//
//            try{
//
//                preparedStatement.executeBatch();
//                connection.commit();
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }finally {
//
//                preparedStatement.close();
//                connection.close();
//            }
        }


}
