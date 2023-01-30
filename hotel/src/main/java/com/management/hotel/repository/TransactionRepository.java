package com.management.hotel.repository;

import com.management.hotel.entity.TransactionEntity;

import java.sql.SQLException;
import java.util.List;

public interface TransactionRepository {
    TransactionEntity createTransaction(TransactionEntity entity) throws SQLException;

    TransactionEntity findByCodeAndStatus(TransactionEntity entity) throws SQLException;

    TransactionEntity update(TransactionEntity entity) throws SQLException;

    List<TransactionEntity> findAll() throws SQLException;

    List<TransactionEntity> findToday() throws SQLException;

    List<TransactionEntity> findThisMonth() throws SQLException;
}
