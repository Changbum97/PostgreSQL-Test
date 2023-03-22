package com.study.springbootpostgresqltest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId) {
        System.out.println(userId);
        System.out.println(userRepository.findById(userId).get().toString());
        return userRepository.findById(userId).get().toString();
    }

    @PostMapping("")
    public String postUser(@RequestBody UserDto userDto) {
        Long savedUserId = userRepository.save(userDto.toEntity()).getId();
        return  savedUserId + "번 유저 등록 완료";
    }

    @PutMapping("/{userId}")
    @Transactional
    public String putUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userRepository.findById(userId).get();
        user.update(userDto);
        return user.toString();
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return userId + "번 유저 삭제 완료";
    }

    @GetMapping("/list")
    public String listUser() {
        List<User> users = userRepository.findAll();
        return users.toString();
    }

}
