package com.group.libraryapp.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    // select * from user_loan_history where book_name =? and is_return =?
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

    // 리팩토링을 거쳐 유저와 유저론히스토리가 통신하도록 바꿨기 때문에 이제 필요없음.
//    Optional<UserLoanHistory> findByUserIdAndBookName(Long userId, String bookName);
}
