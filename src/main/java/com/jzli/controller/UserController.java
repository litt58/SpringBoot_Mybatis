package com.jzli.controller;

import com.jzli.bean.User;
import com.jzli.service.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
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
@RestController
@RequestMapping("/user")
@Api(value = "/user", description = "用户", produces = MediaType.APPLICATION_JSON)
class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/welcome")
    @ApiOperation(value = "欢迎", httpMethod = "GET", notes = "欢迎")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/{id}")
    @ApiOperation(value = "获取指定Id的用户", httpMethod = "GET", notes = "获取指定Id的用户")
    public Object getUserById(@ApiParam(required = true, name = "id", value = "用户Id") @PathVariable(value = "id") int id) {
        List<User> list = userService.getUserById(id);
        return list;
    }

    @RequestMapping("/{id}/count")
    @ApiOperation(value = "更新指定Id用户的次数", httpMethod = "GET", notes = "更新指定Id用户的次数")
    public Object updateUserCount(@ApiParam(required = true, name = "id", value = "用户Id") @PathVariable(value = "id") int id) {
        userService.updateUserCountById(id);
        List<User> list = userService.getUserById(id);
        return list;
    }

    @RequestMapping("/create/{name}")
    @ApiOperation(value = "创建指定名称的用户", httpMethod = "GET", notes = "创建指定名称的用户")
    public Object createUser(@PathVariable(value = "name") String name) {
        List<User> list = userService.createUser(name);
        return list;
    }

    @RequestMapping("/{id}/delete")
    @ApiOperation(value = "删除指定Id的用户", httpMethod = "GET", notes = "删除指定Id的用户")
    public void deleteUser(@ApiParam(required = true, name = "id", value = "用户Id") @PathVariable(value = "id") int id) {
        userService.deleteUser(id);
    }

}
