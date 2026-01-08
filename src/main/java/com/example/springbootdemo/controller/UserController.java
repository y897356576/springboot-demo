package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理", description = "用户管理 API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    /**
     * 分页查询用户
     */
    @Operation(summary = "分页查询", description = "分页获取用户信息")
    @GetMapping("/page")
    public ResponseEntity<PageInfo<User>> getUserPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        PageInfo<User> pageResult = userService.findUserPage(pageNum, pageSize, keyword);
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 创建新用户
     */
    @Operation(summary = "创建用户", description = "创建新用户信息")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * 检查用户名是否可用
     */
    @Operation(summary = "用户名存在校验")
    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<Boolean> checkUsernameAvailability(@PathVariable String username) {
        User user = userService.findByName(username);
        return ResponseEntity.ok(user == null);
    }

    /**
     * 验证用户密码
     */
    @Operation(summary = "验证用户密码", description = "验证用户名和密码是否匹配")
    @PostMapping("/validatePassword")
    public ResponseEntity<Boolean> validatePassword(@RequestBody User user) {
        boolean isValid = userService.validatePassword(user);
        return ResponseEntity.ok(isValid);
    }
}
