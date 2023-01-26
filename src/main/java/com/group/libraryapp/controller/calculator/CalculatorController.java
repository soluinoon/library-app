package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // API의 진입 지점으로 만들어줌, 컨트롤러로 지정
public class CalculatorController {

    @GetMapping("/add") // GET /add
    public int addTwoNumbers(
//            @RequestParam int number1,  // @RequestParam 주어진 쿼리를 함수 파라미터로 넣는다.
//            @RequestParam int number2 -> 수가 많아지면 대응 불가능
            CalculatorAddRequest request
    ) {
//        return number1 + number2;
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") // POST /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}
