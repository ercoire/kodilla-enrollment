package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentCreationDto;
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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPayment(@RequestBody PaymentCreationDto paymentCreationDto) {
        Payment payment = paymentMapper.mapToPayment(paymentCreationDto);
        dbService.savePayment(payment, paymentCreationDto.getStudentId());
        return ResponseEntity.ok().build();
    }


}
