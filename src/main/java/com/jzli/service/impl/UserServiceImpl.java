package com.jzli.service.impl;

import com.jzli.bean.User;
import com.jzli.mapper.UserMapper;
import com.jzli.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "#id", value = "com.jzli.bean.User")
    public User getUserById(int id) {
        System.err.println("没有走缓存！" + id);
        User user = userMapper.getUserById(id);
        return user;
    }

    @CacheEvict(key = "#id", value = "com.jzli.bean.User")
    @Transactional
    public void updateUserCountById(int id) {
        userMapper.updateUserCountById(id);
        //测试事物
//        int id2 = id + 1;
//        List<User> user = userMapper.getUserById(id2);
//        if (user != null && user.size() == 1) {
//            int i = 1 / 0;
//            userMapper.updateUserCountById(id2);
//        }
    }

    @CachePut(key = "#id", value = "com.jzli.bean.User")
    @Transactional
    public User createUser(int id, String name) {
        int i = userMapper.insertUser(id, name);
        if (i == 1) {
            return userMapper.getUserById(id);
        }
        return null;
    }

    @CacheEvict(key = "#id", value = "com.jzli.bean.User")
    @Transactional
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
