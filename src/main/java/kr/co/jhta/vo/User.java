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
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    // replaced by `@Builder` from Lombok project
//    public static class Builder {
//        private int id;
//        private String email;
//        private String password;
//        private Date updateDate;
//        private Date createDate;
//
//        public Builder() {
//        }
//
//        public Builder setId(int id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder setEmail(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Builder setPassword(String password) {
//            this.password = password;
//            return this;
//        }
//
//        public Builder setUpdateDate(Date updateDate) {
//            this.updateDate = updateDate;
//            return this;
//        }
//
//        public Builder setCreateDate(Date createDate) {
//            this.createDate = createDate;
//            return this;
//        }
//
//        public User build() {
//            User user = new User();
//            user.id = this.id;
//            user.email = this.email;
//            user.password = this.password;
//            user.updateDate = this.updateDate;
//            user.createDate = this.createDate;
//            return user;
//        }
//    }
}
