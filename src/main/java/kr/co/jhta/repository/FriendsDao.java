package kr.co.jhta.repository;

import kr.co.jhta.vo.Friends;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendsDao {

    void insertFriends(Friends friends);
}
