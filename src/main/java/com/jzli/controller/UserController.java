package com.jzli.controller;

import com.jzli.bean.User;
import com.jzli.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @Qualifier("userServiceImpl")
    private IUserService userService;
    @Autowired
    private List<IUserService> list;
    @Autowired
    private Map<String, IUserService> map;

    @RequestMapping("/welcome")
    @ApiOperation(value = "欢迎", httpMethod = "GET", notes = "欢迎")
    public String welcome() {
        userService.test3();
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


    @RequestMapping("/list")
    @ApiOperation(value = "用户列表", httpMethod = "GET", notes = "用户列表")
    public Object list(@ApiParam(name = "pageNo", value = "页数") @RequestParam(value = "pageNo", required = false) Integer pageNo,
                       @ApiParam(name = "pageSize", value = "条数") @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return userService.list(pageNo, pageSize);
    }

    @RequestMapping("/find")
    @ApiOperation(value = "用户列表", httpMethod = "GET", notes = "用户列表")
    public Object find(@ApiParam(name = "pageNo", value = "页数") @RequestParam(value = "pageNo", required = false) Integer pageNo,
                       @ApiParam(name = "pageSize", value = "条数") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                       @ApiParam(name = "id", value = "id") @RequestParam(value = "id", required = false) Integer id,
                       @ApiParam(name = "name", value = "name") @RequestParam(value = "name", required = false) String name,
                       @ApiParam(name = "location", value = "location") @RequestParam(value = "location", required = false) String location,
                       @ApiParam(name = "count", value = "count") @RequestParam(value = "count", required = false) Integer count) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLocation(location);
        user.setCount(count);
        return userService.find(pageNo, pageSize, user);
    }

    @RequestMapping("/getAll")
    @ApiOperation(value = "用户列表", httpMethod = "GET", notes = "用户列表")
    public Object list() {
        return userService.getAll();
    }
}
