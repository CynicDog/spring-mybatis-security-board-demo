package kr.co.jhta.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("FriendsRequest")
@Getter @Setter
public class FriendsRequest {

    private int id;
    private int senderId;
    private int recipientId;
    private RequestStatus status;

    public FriendsRequest() {
    }

    public FriendsRequest(int senderId, int recipientId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.status = RequestStatus.PENDING;
    }

    public enum RequestStatus {
        PENDING,
        ACCEPTED,
        DECLINED
    }
}
