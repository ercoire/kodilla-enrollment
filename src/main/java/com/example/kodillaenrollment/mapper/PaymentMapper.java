package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public PaymentDto mapToPaymentDto(final Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPaymentDate(),
                payment.getStudent(),
                payment.getAmount(),
                payment.getCourseId(),
                payment.getEventId()
        );
    }

    public Payment mapToPayment(final PaymentDto paymentDto) {
        return new Payment(
                paymentDto.getId(),
                paymentDto.getPaymentDate(),
                paymentDto.getStudent(),
                paymentDto.getAmount(),
                paymentDto.getCourseId(),
                paymentDto.getEventId()
        );
    }

}
