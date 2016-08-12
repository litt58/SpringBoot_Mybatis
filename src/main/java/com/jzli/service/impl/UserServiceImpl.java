package com.jzli.service.impl;

import com.jzli.bean.User;
import com.jzli.mapper.UserMapper;
import com.jzli.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<User> getUserById(int id) {
        List<User> list = userMapper.getUserById(id);
        return list;
    }

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

    @Transactional
    public List<User> createUser(String name) {
        int i = userMapper.insertUser(name);
        if (i == 1) {
            int id = userMapper.getLastId();
            List<User> list = userMapper.getUserById(id);
            return list;
        }
        return null;
    }

    @Transactional
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
