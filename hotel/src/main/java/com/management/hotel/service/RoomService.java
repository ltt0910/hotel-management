package com.management.hotel.service;

import com.management.hotel.builder.Response;

public interface RoomService {

    Response getEmptyRoom(int type, String keyword) throws Exception;

    Response findAll(int type, String keyword) throws Exception;

    Response getRentedRoom(int type, String key) throws Exception;

    Response updateStatusById(long id) throws Exception;

    Response getRoomByCode(Long code) throws Exception;

}
