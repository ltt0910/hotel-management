package com.management.hotel.service;

import com.management.hotel.builder.Response;
import com.management.hotel.exception.TransactionsException;
import com.management.hotel.model.request.PaymentForm;
import com.management.hotel.model.request.TransactionForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;

public interface TransactionService {

    Response create(TransactionForm form) throws SQLException;

    Response update(PaymentForm form) throws SQLException;

    Response getByCode(Long code) throws SQLException;

    Response findAll() throws SQLException, TransactionsException;

    long getTotal() throws SQLException;

    Page<PaymentForm> findAll(Pageable pageable) throws SQLException;

}
