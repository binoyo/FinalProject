/**
 * 
 */
package com.lambton.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Bino Yohannan
 *
 */
@Entity
public class Customer {
	@Id
    private String username;
    private String password;
    private String passwordConfirm;

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
