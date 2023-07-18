package kr.co.jhta.vo;

import lombok.Builder;
import org.apache.ibatis.type.Alias;
import org.springframework.data.relational.core.mapping.Table;

@Alias("Role")
@Table("roles")
public class Role {
    private User user;
    // TODO: 1) change to `Integer userId`
    // TODO: 2) assign constraint of composite primary keys
    // TODO: 3) create a composite primary key class
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
