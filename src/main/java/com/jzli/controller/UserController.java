package com.jzli.controller;

import com.jzli.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "/user", description = "用户")
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
        return userService.getUserById(id);
    }

    @RequestMapping("/{id}/count")
    @ApiOperation(value = "更新指定Id用户的次数", httpMethod = "GET", notes = "更新指定Id用户的次数")
    public Object updateUserCount(@ApiParam(required = true, name = "id", value = "用户Id") @PathVariable(value = "id") int id) {
        return userService.updateUserCountById(id);
    }

    @RequestMapping("/create/{id}/{name}")
    @ApiOperation(value = "创建指定名称的用户", httpMethod = "GET", notes = "创建指定名称的用户")
    public Object createUser(@PathVariable(value = "id") int id, @PathVariable(value = "name") String name) {
        return userService.createUser(id, name);
    }

    @RequestMapping("/{id}/delete")
    @ApiOperation(value = "删除指定Id的用户", httpMethod = "GET", notes = "删除指定Id的用户")
    public void deleteUser(@ApiParam(required = true, name = "id", value = "用户Id") @PathVariable(value = "id") int id) {
        userService.deleteUser(id);
    }

}
