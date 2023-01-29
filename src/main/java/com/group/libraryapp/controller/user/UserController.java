package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    // 메모리에 저장할 때
//    private final List<User> users = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    // 이렇게 하면 스프링이 알아서 jdbc템플릿 넣어줌
    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        // 유동적으로 입력 가능, jdbc.update는 데이터 변경을 의미하고, insert, update, delete 쿼리에 사용할 수 있다.
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user") // POST /user
    public List<UserResponse> getUser() {
//        List<UserResponse> responses = new ArrayList<>();
//
//        for (int i = 0; i < users.size(); i++) {
//            responses.add(new UserResponse(i + 1, users.get(i)));
//        }
//        return responses;
        String sql = "SELECT * FROM user";
        // row mapper 익명클래스
        // 들어온 데이터를 유저 리스폰스로 바꿔주는 역할
        // 람다로 변경
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // rs : 결과
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            return new UserResponse(id, name, age);
        });
    }

    @GetMapping("/fruit")
    public Fruit fruit() {
        return new Fruit("바나나", 1000);
    }
}
