package com.management.hotel.model.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentForm {

    private Long maPhong;
    private String maKhachHang;
    private String hoTen;
    private Boolean trangThai;
    private String soDienThoai;
    private Timestamp ngayThue;
    private Timestamp ngayTra;
    private Long price;

}
