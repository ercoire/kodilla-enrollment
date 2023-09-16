package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentCreationDto;
import com.example.kodillaenrollment.domain.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMapper {

    @Autowired
    StudentMapper studentMapper;

    public PaymentDto mapToPaymentDto(final Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPaymentDate(),
                studentMapper.mapToStudentDto(payment.getStudent()),
                payment.getAmount(),
                payment.getCourseId()
        );
    }

    public Payment mapToPayment(final PaymentCreationDto paymentCreationDto) {
        return new Payment(paymentCreationDto.getId(), paymentCreationDto.getPaymentDate(),
                paymentCreationDto.getAmount(), paymentCreationDto.getCourseId());
    }

    public List<PaymentDto> mapToPaymentDtoList(final List<Payment> paymentList) {
        return paymentList.stream()
                .map(this::mapToPaymentDto)
                .collect(Collectors.toList());
    }

}
