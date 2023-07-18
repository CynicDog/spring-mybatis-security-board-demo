package kr.co.jhta.vo;

import lombok.Builder;
import org.apache.ibatis.type.Alias;

@Alias("Role")
public class Role {

    private User user;
    private String roleName;

    public Role() {
    }

    public Role(User user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
