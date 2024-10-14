package com.service.user.Model;

public class UserPasswordChangeDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
    
    public UserPasswordChangeDTO(String email, String oldPassword, String newPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
    public UserPasswordChangeDTO() {
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

}

