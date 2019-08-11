package com.ssm.code.pojo;

/**
 * Score class
 *
 * @author Flc
 * @date 2019/5/28
 */
public class Score {
    private String satisfied;
    private String suggest;
    private int score;
    private String flag;
    private String username;
    private String repairman;
    private String time;
    private int rOrdernumber;

    public Score() {
    }

    public Score(String satisfied, String suggest, int score, String flag, String username, String repairman, String time, int rOrdernumber) {
        this.satisfied = satisfied;
        this.suggest = suggest;
        this.score = score;
        this.flag = flag;
        this.username = username;
        this.repairman = repairman;
        this.time = time;
        this.rOrdernumber = rOrdernumber;
    }

    public String getSatisfied() {
        return satisfied;
    }

    public void setSatisfied(String satisfied) {
        this.satisfied = satisfied;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepairman() {
        return repairman;
    }

    public void setRepairman(String repairman) {
        this.repairman = repairman;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getrOrdernumber() {
        return rOrdernumber;
    }

    public void setrOrdernumber(int rOrdernumber) {
        this.rOrdernumber = rOrdernumber;
    }
}
