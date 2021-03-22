package bilibili.majiang.community.model;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/13 18:08
 * @Version 1.0
 */
@Data
public class User {

    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreated;
    private Long gmtModified;
    private String avatarUrl;

}