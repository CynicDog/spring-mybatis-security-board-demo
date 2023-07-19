package kr.co.jhta.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Follows")
@Getter @Setter
public class Follows {

    private int followerId;
    private int followedId;
    private Date timestamp;

    public Follows() {
    }

    public Follows(int followerId, int followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
        this.timestamp = new Date();
    }
}
