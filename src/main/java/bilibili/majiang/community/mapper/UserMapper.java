package bilibili.majiang.community.mapper;

import bilibili.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName UserMapper
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/13 17:54
 * @Version 1.0
 */

@Mapper
public interface UserMapper {

    @Insert("insert into user(ACCOUNT_ID, NAME, TOKEN, GMT_CREATED, GMT_MODIFIED)" +
            "values(#{accountId}, #{name}, #{token}, #{gmtCreated}, #{gmtModified})")
    void insert(User user);

}