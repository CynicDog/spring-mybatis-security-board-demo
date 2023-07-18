package kr.co.jhta.vo;

import lombok.Builder;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Alias("User")
public class User {

    private int id;
    private String email;
    private String password;
    private Date updateDate;
    private Date createDate;
    private List<String> roles;

    public User() {
    }

    @Builder
    public User(String email, String password, Date updateDate, Date createDate, List<String> roles) {
        this.email = email;
        this.password = password;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }

        this.roles.add(role);
    }
}
