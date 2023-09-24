package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentCreationDto;
import com.example.kodillaenrollment.domain.PaymentDto;
import com.example.kodillaenrollment.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void shouldMapToPaymentDto() {
        //Given
        Student student = new Student(1L, "name", "name1", "mail");
        Payment payment = new Payment(1L, LocalDate.of(2023, 1, 1), student, 100, 1L);

        //When
        PaymentDto actual = paymentMapper.mapToPaymentDto(payment);
        //Then
        assertEquals(payment.getPaymentDate(), actual.getPaymentDate());
        assertEquals(student.getId(), actual.getStudentDto().getId());
    }

    @Test
    void shouldMapToPayment() {
        //Given
        PaymentCreationDto dto = new PaymentCreationDto(1L, LocalDate.of(2023,5,1), 2L, 50, 3L);

        //When
        Payment actual = paymentMapper.mapToPayment(dto);

        //Then
        assertNull(actual.getStudent());
        assertEquals(dto.getAmount(), actual.getAmount());
        assertEquals(dto.getCourseId(), actual.getCourseId());
        assertEquals(dto.getPaymentDate(), actual.getPaymentDate());
    }
}
