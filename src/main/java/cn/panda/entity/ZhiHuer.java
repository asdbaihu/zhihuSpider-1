package cn.panda.entity;

import java.util.Date;

/**
 * Created by lingj on 2017/2/13 0013.
 */
public class ZhiHuer implements java.io.Serializable{

    Long id;
    String userName;    //用户名
    String userId;      //用户id
    String slogan;      //标语
    Integer following;  //关注了
    Integer followee;   //关注人数
    Integer agree;     //感谢数
    String collect;    //收藏数
    Date updateTime;

    //toString

    @Override
    public String toString() {
        return "ZhiHuer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", slogan='" + slogan + '\'' +
                ", following=" + following +
                ", followee=" + followee +
                ", agree=" + agree +
                ", collect='" + collect + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getFollowee() {
        return followee;
    }

    public void setFollowee(Integer followee) {
        this.followee = followee;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
