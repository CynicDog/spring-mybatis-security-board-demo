package kr.co.jhta.repository;

import kr.co.jhta.vo.FollowsRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowsRequestDao {

    void insertFollowsRequest(FollowsRequest request);

    boolean checkIfExists(@Param("sender-id") int senderId, @Param("recipient-id") int recipientId);

    List<FollowsRequest> findRequestsByRecipientId(int recipientId);

    List<FollowsRequest> findRequestsBySenderId(int senderId);

    FollowsRequest findRequestsBySenderIdAndRecipientId(@Param("sender-id") int senderId, @Param("recipient-id") int recipientId);

    void update(FollowsRequest followsRequest);
}
