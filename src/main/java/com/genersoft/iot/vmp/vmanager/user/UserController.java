package com.genersoft.iot.vmp.vmanager.user;

import com.genersoft.iot.vmp.conf.security.SecurityUtils;
import com.genersoft.iot.vmp.conf.security.dto.LoginUser;
import com.genersoft.iot.vmp.service.IRoleService;
import com.genersoft.iot.vmp.service.IUserService;
import com.genersoft.iot.vmp.storager.dao.dto.Role;
import com.genersoft.iot.vmp.storager.dao.dto.User;
import com.genersoft.iot.vmp.utils.DateUtil;
import com.genersoft.iot.vmp.vmanager.bean.WVPResult;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Tag(name = "用户管理")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Operation(summary = "登录")
    @Parameter(name = "username", description = "用户名", required = true)
    @Parameter(name = "password", description = "密码（32位md5加密）", required = true)
    @GetMapping("/login")
    public WVPResult<LoginUser> login(@RequestParam String username, @RequestParam String password){
        LoginUser user = null;
        WVPResult<LoginUser> result = new WVPResult<>();
        try {
            user = SecurityUtils.login(username, password, authenticationManager);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMsg("fail");
        }
        if (user != null) {
            result.setCode(0);
            result.setMsg("success");
            result.setData(user);
        }else {
            result.setCode(-1);
            result.setMsg("fail");
        }
        return result;
    }

    @Operation(summary = "修改密码")
    @Parameter(name = "username", description = "用户名", required = true)
    @Parameter(name = "oldpassword", description = "旧密码（已md5加密的密码）", required = true)
    @Parameter(name = "password", description = "新密码（未md5加密的密码）", required = true)
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String password){
        // 获取当前登录用户id
        LoginUser userInfo = SecurityUtils.getUserInfo();
        if (userInfo== null) {
            return "fail";
        }
        String username = userInfo.getUsername();
        LoginUser user = null;
        try {
            user = SecurityUtils.login(username, oldPassword, authenticationManager);
            if (user != null) {
                int userId = SecurityUtils.getUserId();
                boolean result = userService.changePassword(userId, DigestUtils.md5DigestAsHex(password.getBytes()));
                if (result) {
                    return "success";
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "fail";
    }


    @Operation(summary = "增加用户角色")
    @Parameter(name = "username", description = "用户名", required = true)
    @Parameter(name = "password", description = "密码（未md5加密的密码）", required = true)
    @Parameter(name = "roleId", description = "角色ID", required = true)
    @PostMapping("/add")
    public ResponseEntity<WVPResult<Integer>> add(@RequestParam String username,
                                                 @RequestParam String password,
                                                 @RequestParam Integer roleId){
        WVPResult<Integer> result = new WVPResult<>();
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password) || roleId == null) {
            result.setCode(-1);
            result.setMsg("参数不可为空");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // 获取当前登录用户id
        int currenRoleId = SecurityUtils.getUserInfo().getRole().getId();
        if (currenRoleId != 1) {
            // 只用角色id为1才可以删除和添加用户
            result.setCode(-1);
            result.setMsg("用户无权限");
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        //新增用户的pushKey的生成规则为md5(时间戳+用户名)
        user.setPushKey(DigestUtils.md5DigestAsHex((System.currentTimeMillis()+password).getBytes()));
        Role role = roleService.getRoleById(roleId);

        if (role == null) {
            result.setCode(-1);
            result.setMsg("roleId is not found");
            // 角色不存在
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        user.setRole(role);
        user.setCreateTime(DateUtil.getNow());
        user.setUpdateTime(DateUtil.getNow());
        int addResult = userService.addUser(user);


        result.setCode(addResult > 0 ? 0 : -1);
        result.setMsg(addResult > 0 ? "success" : "fail");
        result.setData(addResult);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "用户Id", required = true)
    @DeleteMapping("/delete")
    public ResponseEntity<WVPResult<String>> delete(@RequestParam Integer id){
        // 获取当前登录用户id
        int currenRoleId = SecurityUtils.getUserInfo().getRole().getId();
        WVPResult<String> result = new WVPResult<>();
        if (currenRoleId != 1) {
            // 只用角色id为0才可以删除和添加用户
            result.setCode(-1);
            result.setMsg("用户无权限");
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        }
        int deleteResult = userService.deleteUser(id);

        result.setCode(deleteResult>0? 0 : -1);
        result.setMsg(deleteResult>0? "success" : "fail");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "查询用户")
    @GetMapping("/all")
    public ResponseEntity<WVPResult<List<User>>> all(){
        // 获取当前登录用户id
        List<User> allUsers = userService.getAllUsers();
        WVPResult<List<User>> result = new WVPResult<>();
        result.setCode(0);
        result.setMsg("success");
        result.setData(allUsers);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 分页查询用户
     *
     * @param page  当前页
     * @param count 每页查询数量
     * @return 分页用户列表
     */
    @Operation(summary = "分页查询用户")
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页查询数量", required = true)
    @GetMapping("/users")
    public PageInfo<User> users(int page, int count) {
        return userService.getUsers(page, count);
    }

    @Operation(summary = "修改pushkey")
    @Parameter(name = "userId", description = "用户Id", required = true)
    @Parameter(name = "pushKey", description = "新的pushKey", required = true)
    @RequestMapping("/changePushKey")
    public ResponseEntity<WVPResult<String>> changePushKey(@RequestParam Integer userId,@RequestParam String pushKey) {
        // 获取当前登录用户id
        int currenRoleId = SecurityUtils.getUserInfo().getRole().getId();
        WVPResult<String> result = new WVPResult<>();
        if (currenRoleId != 1) {
            // 只用角色id为0才可以删除和添加用户
            result.setCode(-1);
            result.setMsg("用户无权限");
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        }
        int resetPushKeyResult = userService.changePushKey(userId,pushKey);

        result.setCode(resetPushKeyResult > 0 ? 0 : -1);
        result.setMsg(resetPushKeyResult > 0 ? "success" : "fail");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "管理员修改普通用户密码")
    @Parameter(name = "adminId", description = "管理员id", required = true)
    @Parameter(name = "userId", description = "用户id", required = true)
    @Parameter(name = "password", description = "新密码（未md5加密的密码）", required = true)
    @PostMapping("/changePasswordForAdmin")
    public String changePasswordForAdmin(@RequestParam int userId, @RequestParam String password) {
        // 获取当前登录用户id
        LoginUser userInfo = SecurityUtils.getUserInfo();
        if (userInfo == null) {
            return "fail";
        }
        Role role = userInfo.getRole();
        if (role != null && role.getId() == 1) {
            boolean result = userService.changePassword(userId, DigestUtils.md5DigestAsHex(password.getBytes()));
            if (result) {
                return "success";
            }
        }

        return "fail";
    }
}
