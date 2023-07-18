package kr.co.jhta.repository;

import kr.co.jhta.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    void insertUser(User user);

    User findByEmail(String email);

    User findById(int id);

    void update(User user);
}
