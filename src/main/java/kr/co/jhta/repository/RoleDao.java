package kr.co.jhta.repository;

import kr.co.jhta.vo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface RoleDao {

    void insertRole(Role role);
    List<Role> findByUserId(int userId);
}
