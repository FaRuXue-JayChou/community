package bilibili.majiang.community.model;

import lombok.Data;

/**
 * @ClassName WechatUser
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/24 9:46
 * @Version 1.0
 */
@Data
public class WechatUser {

    private Integer id;
    private String unionId;
    private String nickName;
    private String headImgUrl;
    private String token;
    private Long gmtCreated;
    private Long gmtModified;

}