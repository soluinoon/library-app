package com.group.libraryapp.config;

import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UserConfiguration {

     @Bean // 오른쪽 콩의 의미는 빈으로 주입한다는 의미
    // 들어오는 콩의 의미는 주입받는다는 의미, jdbcTemplate

     // 여기 다시 분석
    public UserJdbcRepository userJdbcRepository(JdbcTemplate jdbcTemplate) {
        return new UserJdbcRepository(jdbcTemplate);
    }
}
