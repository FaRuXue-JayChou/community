package bilibili.majiang.community.mapper;

import bilibili.majiang.community.model.WechatUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName WechatUserMapper
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/24 10:23
 * @Version 1.0
 */
@Mapper
public interface WechatUserMapper {

    @Insert("insert into WECHATUSER(UNIONID, NICKNAME, HEADIMG_URL, TOKEN, GMT_CREATED, GMT_MODIFIED)" +
            "values(#{unionId}, #{nickName}, #{headImgUrl}, #{token}, #{gmtCreated}, #{gmtModified})")
    void create(WechatUser wechatUser);

    @Select("select * from WECHATUSER where UNIONID = #{unionId}")
    WechatUser findByUnionId(@Param("unionId") String unionId);

    @Select("select * from WECHATUSER where token = #{token}")
    WechatUser findByToken(@Param("token") String token);

}