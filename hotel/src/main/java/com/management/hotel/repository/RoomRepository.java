package com.management.hotel.repository;


import com.management.hotel.entity.RoomEntity;

import java.sql.SQLException;
import java.util.List;

public interface RoomRepository {

    List<RoomEntity> getEmptyRoom(String keyword);

    List<RoomEntity> getRentedRoom(String key);

    List<RoomEntity> findAll(String key);

    void updateStatusByCode(RoomEntity entity) throws SQLException;

    RoomEntity getRoomByCode(Long code) throws SQLException;

}
