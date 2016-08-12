package com.jzli.service;

import com.jzli.bean.User;

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
public interface IUserService {
    List<User> getUserById(int id);

    void updateUserCountById(int id);

    List<User> createUser(String name);

    void deleteUser(int id);

}
