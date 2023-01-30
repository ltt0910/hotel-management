package com.management.hotel.controller.api;

import com.management.hotel.builder.Response;
import com.management.hotel.model.request.PaymentForm;
import com.management.hotel.model.request.TransactionForm;
import com.management.hotel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionAPI {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransactionForm form) {

        Response res;
        try {
            res = transactionService.create(form);
        } catch (Exception e) {

            res = new Response.Builder(1, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }


        return ResponseEntity.ok(res);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PaymentForm form) {

        Response res;
        try {
            res = transactionService.update(form);
        } catch (Exception e) {

            res = new Response.Builder(1, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }


        return ResponseEntity.ok(res);
    }

}
