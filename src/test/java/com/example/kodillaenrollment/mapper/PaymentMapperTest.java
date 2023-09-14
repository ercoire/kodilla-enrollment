package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentDto;
import com.example.kodillaenrollment.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;

//    @Test
//    void shouldMapToPaymentDto(){
//        //Given
//        Payment payment = new Payment(1L, LocalDate.of(2023,01,01), new Student(), 100, 1L)
//
//        //When
//        PaymentDto actual = paymentMapper.mapToPaymentDto(payment);
//        //Then
//
//    }
//
//    @Test
//    void shouldMapToPayment() {
//        //Given
//        PaymentDto dto = new Payment(1L, LocalDate.of(2023,01,01), new Student(), 100, 1L)
//
//        //When
//        Payment actual = paymentMapper.mapToPayment(dto);
//        //Then
//    }
}
