package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // User findByName(String name); // 함수이름으로 알아서 SQL이 조립됨.
    // find : 1개
    // By : 뒤에 붙는 필드 이름으로 SELECT 쿼리의 WHERE 문이 작성됨
    Optional<User> findByName(String name);
    boolean existsByName(String name);
}
