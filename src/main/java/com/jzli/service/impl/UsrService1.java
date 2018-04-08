package com.jzli.service.impl;

import com.jzli.bean.User;
import com.jzli.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2017/8/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
@Service
public class UsrService1 implements IUserService {
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User updateUserCountById(int id) {
        return null;
    }

    @Override
    public User createUser(int id, String name) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public Object list(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Object find(Integer pageNo, Integer pageSize, User user) {
        return null;
    }

    @Override
    public Object getAll() {
        return null;
    }

    @Override
    public void test() {

    }

    @Override
    public void test1() {

    }

    @Override
    public void test2() {

    }
}
