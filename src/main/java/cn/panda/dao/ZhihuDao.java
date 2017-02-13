package cn.panda.dao;

import cn.panda.daofactory.ZhihuDaoFactory;
import cn.panda.entity.ZhiHuer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lingj on 2017/2/13 0013.
 */
public class ZhihuDao {


    ZhihuDaoFactory zhihuDaoFactory = new ZhihuDaoFactory();

  public   void add(ZhiHuer zhiHuer) throws ClassNotFoundException, SQLException {

        Connection connection = zhihuDaoFactory.getConnection();

        connection.setAutoCommit(false);

        String sql = "insert ignore into zhihuer (userName,userId,slogan,following,followee,agree,collect,updateTime) values(?,?,?,?,?,?,?,now())";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,zhiHuer.getUserName());
        preparedStatement.setString(2,zhiHuer.getUserId());
        preparedStatement.setString(3,zhiHuer.getSlogan());
        preparedStatement.setInt(4,zhiHuer.getFollowing());
        preparedStatement.setInt(5,zhiHuer.getFollowee());
        preparedStatement.setInt(6,zhiHuer.getAgree());
        preparedStatement.setString(7,zhiHuer.getCollect());

        try {

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
