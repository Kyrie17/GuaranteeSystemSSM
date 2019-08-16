package com.ssm.code.pojo;

/**
 * LoginState class
 *
 * @author Flc
 * @date 2019/5/24
 */

public class LoginState {
    /**
     * 登录状态
     */
    private String loginState;
    /**
     * 学生Id
     */
    private String stuId;

    private Student student;


    public LoginState() {
    }

    public LoginState(String loginState, String stuId) {
        this.loginState = loginState;
        this.stuId = stuId;
    }

    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
