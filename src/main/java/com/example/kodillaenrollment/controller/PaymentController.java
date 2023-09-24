package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentCreationDto;
import com.example.kodillaenrollment.mapper.PaymentMapper;
import com.example.kodillaenrollment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPayment(@RequestBody PaymentCreationDto paymentCreationDto) {
        Payment payment = paymentMapper.mapToPayment(paymentCreationDto);
        paymentService.savePayment(payment, paymentCreationDto.getStudentId());
        return ResponseEntity.ok().build();
    }


}
