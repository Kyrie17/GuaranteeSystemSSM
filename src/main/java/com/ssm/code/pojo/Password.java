package com.ssm.code.pojo;

/**
 * Password class
 *
 * @author Flc
 * @date 2019/5/27
 */
public class Password {
    /**
     * 用户学号
     */
    private String stuId;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Password(String stuId, String oldPassword, String newPassword) {
        this.stuId = stuId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Password() {
    }
}
