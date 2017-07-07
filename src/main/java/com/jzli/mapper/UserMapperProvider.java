package com.jzli.mapper;

import com.jzli.bean.User;
import org.springframework.util.ObjectUtils;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2017/7/7
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class UserMapperProvider {

    public String find(User user){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from  p_user t where 1=1 ");
        if(!ObjectUtils.isEmpty(user.getId())){
            sb.append(" and id=#{id}");
        }
        if(!ObjectUtils.isEmpty(user.getName())){
            sb.append(" and name=#{name}");
        }
        if(!ObjectUtils.isEmpty(user.getLocation())){
            sb.append(" and location=#{location}");
        }
        if(!ObjectUtils.isEmpty(user.getCount())){
            sb.append(" and count=#{count}");
        }
        return sb.toString();
    }
}
