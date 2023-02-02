package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction; 을 해준다. (트랜잭션 시작)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시 문제가 생긴다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
//        throw new IllegalArgumentException(); 트랜잭셔널이 있기 때문에 오류 감지하고 롤백함.
    }

    @Transactional
    public List<UserResponse> getUser() {
        // findAll -> select * from
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                // .map(UserResponse::new) 로 가능 (유저에 생성자 추가하면)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // select * from user where id = ?;
        // 결과 : Optional<User>
        // 있으면 담고, 없으면 에러발생
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalAccessError::new);

        user.updateName(request.getName());
        // 자동으로 UPDATE SQL이 날라가게 됨.
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
        // SELECT * FROM user WHERE name = ?
//        User user = userRepository.findByName(name);
//        if (user == null) {
//            throw new IllegalArgumentException();
//        }
//
        User user2 = userRepository.findByName(name)
                        .orElseThrow(IllformedLocaleException::new);

        userRepository.delete(user2);
    }
}
