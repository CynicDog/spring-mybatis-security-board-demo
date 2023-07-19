package kr.co.jhta.repository;

import kr.co.jhta.vo.Follows;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowsDao {

    void insertFollows(Follows follows);
}
