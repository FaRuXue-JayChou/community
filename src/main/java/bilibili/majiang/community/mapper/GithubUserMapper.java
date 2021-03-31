package bilibili.majiang.community.mapper;

import bilibili.majiang.community.model.GithubUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @InterfaceName GithubUserMapper
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/13 17:54
 * @Version 1.0
 */

@Mapper
public interface GithubUserMapper {

    @Insert("insert into githubUser(ACCOUNT_ID, NAME, TOKEN, GMT_CREATED, GMT_MODIFIED, AVATAR_URL)" +
            "values(#{accountId}, #{name}, #{token}, #{gmtCreated}, #{gmtModified}, #{avatarUrl})")
    void insert(GithubUser githubUser);

    @Select("select * from githubUser where token = #{token}")
    GithubUser findByToken(@Param("token") String token);

    @Select("select count(*) from githubUser where ACCOUNT_ID = #{accountId}")
    Integer findByAccountId(@Param("accountId") Long accountId);

    @Select("select * from githubUser where id = #{id}")
    GithubUser findById(@Param("id") Integer id);

    @Select("select token from githubUser " +
            "where ACCOUNT_ID = #{accountId} " +
            "order by ACCOUNT_ID limit 1")
    String getToken(@Param("accountId") String accountId);

}