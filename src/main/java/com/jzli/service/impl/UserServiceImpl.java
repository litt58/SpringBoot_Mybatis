package com.jzli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzli.bean.User;
import com.jzli.mapper.UserMapper;
import com.jzli.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int i = 1;

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

    @Override
    public Object find(Integer pageNo, Integer pageSize, User user) {
        List<User> list;
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNo, pageSize, true);
        list = userMapper.find(user);
        PageInfo result = new PageInfo<>(list);
        return result;
    }

    @Override
    public Object getAll() {
        return userMapper.getAll();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    @Transactional
    /**
     * Spring的事务传播策略在内部方法调用时将不起作用
     */
    public void test() {
        int j = i++;
        logger.info("开始" + j);
        try {
            updateUserCountById(1);
            int i=1/0;
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("结束" + j);
    }
}
