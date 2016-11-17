package com.jzli.service;

import com.jzli.bean.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * =======================================================
 *
 * @Company 金色家网络科技有限公司-开发测试云服务部
 * @Date ：2016/7/6
 * @Author ：li.jinzhao
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public interface IUserService {
    @Cacheable(key = "#id", value = "users")
    User getUserById(int id);

    @CachePut(key = "#id", value = "users")
    User updateUserCountById(int id);

    @CachePut(key = "#id", value = "users")
    User createUser(int id, String name);

    @CacheEvict(key = "#id", value = "users")
    void deleteUser(int id);

}
