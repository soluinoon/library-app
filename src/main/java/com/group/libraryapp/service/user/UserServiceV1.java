package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void updateUser(UserUpdateRequest request) {
        if (userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        // .query는 list로 반환한다!
        if (userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }
        // 객체로 만들 수 있지만 쿼리 하나라 그냥 행
        userJdbcRepository.deleteUser(name);
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUser() {
        return userJdbcRepository.getUser();
    }
}
