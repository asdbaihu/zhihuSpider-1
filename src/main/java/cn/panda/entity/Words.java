package cn.panda.entity;

/**
 * Created by lingj on 2017/2/16 0016.
 */
public class Words {


    Long id;
    String word;    //分词
    Integer times;  //出现的次数


    //toString
    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", times=" + times +
                '}';
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
