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
    @Cacheable(value = "users", keyGenerator = "keyGenerator")
    User getUserById(int id);

    @CachePut(value = "users", keyGenerator = "keyGenerator")
    User updateUserCountById(int id);

    @CachePut(value = "users", keyGenerator = "keyGenerator")
    User createUser(int id, String name);

    @CacheEvict(value = "users", keyGenerator = "keyGenerator")
    void deleteUser(int id);

    Object list(Integer pageNo, Integer pageSize);

    Object find(Integer pageNo, Integer pageSize,User user);

    Object getAll();

    void test();

    void test1();

    void test2();
}
