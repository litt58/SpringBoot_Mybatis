package com.jzli.mapper;

import com.jzli.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

/**
 * =======================================================
 *
 * @Company 金色家网络科技有限公司-开发测试云服务部
 * @Date ：2016/7/7
 * @Author ：li.jinzhao
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public interface UserMapper {
    @Select("SELECT * FROM p_user WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "count", column = "count")
    })
    User getUserById(@Param("id") int id);

    @Select("SELECT * FROM p_user ")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "count", column = "count")
    })
    List<User> list();

    @Select("SELECT id,count FROM p_user ")
    List<HashMap> getAll();

    @SelectProvider(method = "find", type = UserMapperProvider.class)
    List<User> find(User user);

    @Update("update p_user set count = count+1 where id=#{id}")
    int updateUserCountById(@Param("id") int id);

    @Insert("insert into p_user(id,name) values(#{id},#{name})")
    int insertUser(@Param("id") int id, @Param("name") String name);

    @Delete("delete from p_user where id = #{id}")
    int deleteUser(@Param("id") int id);
}
