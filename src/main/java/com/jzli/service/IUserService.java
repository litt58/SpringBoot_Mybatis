package com.jzli.service;

import com.jzli.bean.User;

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
    User getUserById(int id);

    void updateUserCountById(int id);

    User createUser(int id, String name);

    void deleteUser(int id);

}
