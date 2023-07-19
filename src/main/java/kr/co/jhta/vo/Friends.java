package kr.co.jhta.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Friends")
@Getter @Setter
public class Friends {

    private int followerId;
    private int followedId;
    private Date timestamp;

    public Friends() {
    }

    public Friends(int followerId, int followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
        this.timestamp = new Date();
    }
}
