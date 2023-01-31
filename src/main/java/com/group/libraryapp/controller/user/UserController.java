package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.UserService;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // 메모리에 저장할 때
//    private final List<User> users = new ArrayList<>();

    private final UserService userService;
    private final FruitService fruitService;

    // 이렇게 하면 스프링이 알아서 jdbc템플릿 넣어줌
    public UserController(UserService userService, @Qualifier("appleService") FruitService fruitService) {
        this.userService = userService;
        this.fruitService = fruitService;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
//        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
//        // 유동적으로 입력 가능, jdbc.update는 데이터 변경을 의미하고, insert, update, delete 쿼리에 사용할 수 있다.
//        jdbcTemplate.update(sql, request.getName(), request.getAge());
        userService.saveUser(request);
    }

    @GetMapping("/user") // POST /user
    public List<UserResponse> getUser() {
//        List<UserResponse> responses = new ArrayList<>();
//
//        for (int i = 0; i < users.size(); i++) {
//            responses.add(new UserResponse(i + 1, users.get(i)));
//        }
//        return responses;
//        String sql = "SELECT * FROM user";
//        // row mapper 익명클래스
//        // 들어온 데이터를 유저 리스폰스로 바꿔주는 역할
//        // 람다로 변경
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            // rs : 결과
//            long id = rs.getLong("id");
//            String name = rs.getString("name");
//            int age = rs.getInt("age");
//
//            return new UserResponse(id, name, age);
//        });
        return userService.getUser();
    }

//    // 3가지 역할 하고 있어서 리펙터링 해야함.
//    @PutMapping("/user")
//    public void updateUser(@RequestBody UserUpdateRequest request) {
//        String readSql = "SELECT * FROM user WHERE id = ?";
//        // .query는 list로 반환한다!
//        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
//        if (isUserNotExist) {
//            throw new IllegalArgumentException();
//        }
//        String sql = "UPDATE user SET name = ? WHERE id = ?";
//        jdbcTemplate.update(sql, request.getName(), request.getId());
//    }
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }


    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
//        String readSql = "SELECT * FROM user WHERE name = ?";
//        // .query는 list로 반환한다!
//        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
//        if (isUserNotExist) {
//            throw new IllegalArgumentException();
//        }
//        // 객체로 만들 수 있지만 쿼리 하나라 그냥 행
//        String sql = "DELETE FROM user WHERE name = ?";
//        jdbcTemplate.update(sql, name);
        userService.deleteUser(name);
    }

    @GetMapping("/fruit")
    public Fruit fruit() {
        return new Fruit("바나나", 1000);
    }
}
