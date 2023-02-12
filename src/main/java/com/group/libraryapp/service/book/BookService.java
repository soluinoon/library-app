package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository; // 스프링 빈을 이용해 주입받도록 함.
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 대출 중인 책인지?
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new); // 없으면 예외
        // 2. 대출 기록 정보를 확인해서 대출중인지 확인
        // 3. 만약에 확인했는데 대출 중이면 예외를 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false)) {
            throw new IllegalArgumentException("진작 대출되어 있는 책 입니다.");
        }

        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistroy를 저장
        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        // 1. 유저 이름으로 유저 아이디를 찾는다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 조회
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 3. 이제 불리안 필드를 트루로 바꾼다.
        history.doReturn();
//        userLoanHistoryRepository.save(history); 필요없음, 트랜잭셔널 사용한 순간 영속성 컨텍스트에서 알아서 변경감지 해줌.
    }
}
