package kr.co.jhta.repository;

import kr.co.jhta.vo.FriendsRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FriendsRequestDao {

    void insertFriendsRequest(FriendsRequest request);

    boolean checkIfExists(@Param("sender-id") int senderId, @Param("recipient-id") int recipientId);
}
