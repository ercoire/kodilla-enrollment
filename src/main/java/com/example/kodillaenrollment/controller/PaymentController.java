package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentDto;
import com.example.kodillaenrollment.mapper.PaymentMapper;
import com.example.kodillaenrollment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final DbService dbService;
    private final PaymentMapper paymentMapper;

    @GetMapping(value = "{studentId}/students")
    public ResponseEntity<List<PaymentDto>> fetchPaymentsByStudentId(@PathVariable Long studentId) {
        List<Payment> paymentsByStudent = dbService.getAllPayments().stream()
                .filter(payment -> payment.getStudent() != null &&
                        payment.getStudent().getId().equals(studentId))
                .toList();
        return ResponseEntity.ok(paymentMapper.mapToPaymentDtoList(paymentsByStudent));
    }
    //take all payments, find studentId, if matches - create a list of payments

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPayment(@RequestBody PaymentDto paymentDto) {
        Payment payment = paymentMapper.mapToPayment(paymentDto);
        dbService.savePayment(payment);
        return ResponseEntity.ok().build();
    }
}
