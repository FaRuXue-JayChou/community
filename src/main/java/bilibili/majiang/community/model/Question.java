package bilibili.majiang.community.model;

import lombok.Data;

/**
 * @ClassName Question
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/21 9:30
 * @Version 1.0
 */
@Data
public class Question {

    private String title;
    private String description;
    private Long gmtCreated;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;

}