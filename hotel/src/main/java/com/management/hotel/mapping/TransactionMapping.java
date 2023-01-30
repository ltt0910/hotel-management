package com.management.hotel.mapping;

import com.management.hotel.entity.TransactionEntity;
import com.management.hotel.model.request.PaymentForm;
import com.management.hotel.model.request.TransactionForm;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class TransactionMapping {

    Timestamp localDateTime = new Timestamp(System.currentTimeMillis());

    public TransactionEntity mapToEntity(TransactionForm form) {
        TransactionEntity res = new TransactionEntity();
        res.setCheckIn(localDateTime);
        res.setCustomerName(form.getCustomerName());
        res.setCustomerIdentification(form.getCustomerCode());
        res.setCustomerPhone(form.getCustomerPhone());

        return res;
    }

    public TransactionEntity mapPaymentFormToEntity(PaymentForm form) {
        TransactionEntity res = new TransactionEntity();
        res.setCheckIn(form.getNgayThue());
        res.setCheckOut(form.getNgayTra());
        res.setStatus(true);
        res.setCustomerIdentification(form.getMaKhachHang());
        res.setCustomerName(form.getHoTen());
        res.setPrice(form.getPrice());
        res.setCustomerPhone(form.getSoDienThoai());

        return res;

    }

    public PaymentForm mapTransactionEntityToPaymentForm(TransactionEntity entity) {
        PaymentForm res = new PaymentForm();
        res.setMaKhachHang(entity.getCustomerIdentification());
        res.setHoTen(entity.getCustomerName());
        res.setSoDienThoai(entity.getCustomerPhone());
        res.setPrice(entity.getPrice());
        res.setTrangThai(entity.getStatus());
        res.setNgayThue(entity.getCheckIn());
        res.setNgayTra(entity.getCheckOut());
        res.setMaPhong(entity.getCode());
        return res;
    }

}
