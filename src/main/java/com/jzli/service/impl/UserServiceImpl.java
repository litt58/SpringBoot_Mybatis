package com.jzli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public User getUserById(int id) {
        System.err.println("没有走缓存！" + id);
        User user = userMapper.getUserById(id);
        return user;
    }


    @Transactional
    public User updateUserCountById(int id) {
        userMapper.updateUserCountById(id);
        return getUserById(id);
    }


    @Transactional
    public User createUser(int id, String name) {
        int i = userMapper.insertUser(id, name);
        if (i == 1) {
            return userMapper.getUserById(id);
        }
        return null;
    }

    @Transactional
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public PageInfo list(Integer pageNo, Integer pageSize) {
        List<User> list;
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNo, pageSize, true);
        list = userMapper.list();
        PageInfo result = new PageInfo<>(list);
        return result;
    }
}
