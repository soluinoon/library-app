package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
//    private long userId;
    @ManyToOne // 내가 여러개고 너가 한개야
    private User user; // 매니투원 없으면 오류, 테이블에 유저가 없기 떄문에 매핑을 모른다.
    private String bookName;
    private boolean isReturn;  // 불리안으로 설정하면 테이블에서 1, 0으로 트루 참 알아서 표현가능

    protected UserLoanHistory() {

    }
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
