package bilibili.majiang.community.dto;

import bilibili.majiang.community.model.GithubUser;
import lombok.Data;

/**
 * @ClassName QuestionDTO
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/23 21:28
 * @Version 1.0
 */
@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Long gmtCreated;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private GithubUser githubUser;

}