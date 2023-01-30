package com.management.hotel.service.impl;

import com.management.hotel.builder.Response;
import com.management.hotel.entity.RoomEntity;
import com.management.hotel.entity.TransactionEntity;
import com.management.hotel.exception.TransactionsException;
import com.management.hotel.mapping.TransactionMapping;
import com.management.hotel.model.request.PaymentForm;
import com.management.hotel.model.request.TransactionForm;
import com.management.hotel.repository.RoomRepository;
import com.management.hotel.repository.TransactionRepository;
import com.management.hotel.service.RoomService;
import com.management.hotel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TransactionMapping mapping;

    @Transactional
    @Override
    public Response create(TransactionForm form) throws SQLException {

        TransactionEntity entity = mapping.mapToEntity(form);
        entity.setRoomId(roomRepository.getRoomByCode(form.getCode()).getId());
        TransactionEntity res = new TransactionEntity();
        try {
            res = transactionRepository.createTransaction(entity);
            roomService.updateStatusById(form.getCode());
        } catch (Exception e) {
            return new Response.Builder(1, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage("error")
                    .buildData(res)
                    .build();
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("create transaction success")
                .buildData(res)
                .build();
    }

    @Override
    public Response update(PaymentForm form) throws SQLException {
        TransactionEntity entity = mapping.mapPaymentFormToEntity(form);
        entity.setRoomId(roomRepository.getRoomByCode(form.getMaPhong()).getId());
        TransactionEntity res = new TransactionEntity();
        try {
            res = transactionRepository.update(entity);
            roomService.updateStatusById(form.getMaPhong());
        } catch (Exception e) {
            return new Response.Builder(1, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage("error")
                    .buildData(res)
                    .build();
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("update transaction success")
                .buildData(res)
                .build();
    }


    @Override
    public Response getByCode(Long code) throws SQLException {

        RoomEntity entity = roomRepository.getRoomByCode(code);
        TransactionEntity transaction = new TransactionEntity();
        transaction.setRoomId(entity.getId());
        transaction.setStatus(false);
        TransactionEntity res;
        try {
            res = transactionRepository.findByCodeAndStatus(transaction);
        } catch (Exception e) {
            e.printStackTrace();

            return new Response.Builder(1, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage("error")
                    .buildData(new TransactionEntity())
                    .build();
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("success")
                .buildData(res)
                .build();
    }

    @Override
    public Response findAll() throws SQLException, TransactionsException {

        List<TransactionEntity> entityList = transactionRepository.findAll();

        List<PaymentForm> res = entityList.stream().map(entity -> mapping.mapTransactionEntityToPaymentForm(entity)).collect(Collectors.toList());
        if (entityList.isEmpty()) {
            throw new TransactionsException("empty", HttpStatus.NOT_FOUND.value());
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("find all transaction success")
                .buildData(res)
                .build();
    }

    @Override
    public long getTotal() throws SQLException {
        long res = 0;
        for (TransactionEntity i : transactionRepository.findAll()) {
            res += i.getPrice();
        }
        return res;

    }

    @Override
    public Page<PaymentForm> findAll(Pageable pageable) throws SQLException {
        List<TransactionEntity> entityList = transactionRepository.findAll();

        List<PaymentForm> paymentFormList = entityList.stream().map(entity -> mapping.mapTransactionEntityToPaymentForm(entity)).collect(Collectors.toList());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PaymentForm> list;
        if (paymentFormList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, entityList.size());
            list = paymentFormList.subList(startItem, toIndex);

        }
        Page<PaymentForm> notifyPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), paymentFormList.size());
        return notifyPage;
    }

}
