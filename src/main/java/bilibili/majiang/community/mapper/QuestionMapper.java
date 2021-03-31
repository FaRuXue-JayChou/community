package bilibili.majiang.community.mapper;

import bilibili.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceName QuestionMapper
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/21 9:25
 * @Version 1.0
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_created, gmt_modified, creator, tag)" +
            "values(#{title}, #{description}, #{gmtCreated}, #{gmtModified}, #{creator}, #{tag})")
    int create(Question question);

    @Select("select * from question")
    List<Question> selectAll();

    @Select("select count(1) from question")
    Integer getCount();

    @Select("select * from question limit #{start}, #{offset}")
    List<Question> selectPointed(@Param("start") Integer start, @Param("offset") Integer offset);

    @Select("select count(1) from question where creator = #{creator}")
    Integer getCountByUser(@Param("creator") Integer creator);

    @Select("select * from question where creator = #{creator} limit #{start}, #{offset}")
    List<Question> selectPointedByUser(@Param("creator") Integer creator, @Param("start") Integer start, @Param("offset") Integer offset);

}