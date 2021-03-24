package bilibili.majiang.community.dto;

import lombok.Data;

/**
 * @ClassName WeChatCode
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/24 10:37
 * @Version 1.0
 */
@Data
public class ForWechatToken {

    private String appid;
    private String secret;
    private String code;

}