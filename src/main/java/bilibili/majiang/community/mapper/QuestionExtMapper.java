package bilibili.majiang.community.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName QuestionExtMapper
 * @Description TODO
 * @Author 90855
 * @Date 2021/4/6 21:45
 * @Version 1.0
 */
public interface QuestionExtMapper {

    int increaseViewCount(@Param("id") Integer id);

}