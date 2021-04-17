package com.example.demo.controller;

import com.example.demo.model.DTO.PaymentModelDTO;
import com.example.demo.services.PaymentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/domestic-payment-requests")
public class MainController {

    public PaymentService paymentService;

    public MainController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/send-money/{route}")
    ResponseEntity<PaymentModelDTO> newEmployee(
            @RequestHeader String Cookie,
            @RequestHeader String RequestId,
            @PathVariable String route,
            @RequestBody PaymentModelDTO paymentDTO) {
            /// validations
        paymentService.makePayment(paymentDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add(RequestId, RequestId);

        return new ResponseEntity<PaymentModelDTO>(paymentDTO,
                headers,
                HttpStatus.ACCEPTED);

    }
}
