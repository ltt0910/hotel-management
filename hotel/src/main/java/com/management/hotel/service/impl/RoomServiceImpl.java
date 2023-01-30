package com.management.hotel.service.impl;

import com.management.hotel.builder.Response;
import com.management.hotel.entity.RoomEntity;
import com.management.hotel.exception.RoomException;
import com.management.hotel.mapping.RoomMapping;
import com.management.hotel.model.dto.RoomDto;
import com.management.hotel.repository.RoomRepository;
import com.management.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapping roomMapping;

    @Override
    public Response findAll(int type, String keyword) throws Exception {
        List<RoomEntity> entities = roomRepository.findAll(keyword);
        if (entities.isEmpty()) {
            throw new RoomException("list room is not found", HttpStatus.NOT_FOUND.value());
        }
        List<RoomDto> result = new ArrayList<>();
        if (keyword != null && keyword != "") {
            for (RoomEntity e : entities) {
                result.add(roomMapping.convertToDto(e));
            }
        } else {
            for (RoomEntity e : entities) {
                if (e.getType() == type) {
                    result.add(roomMapping.convertToDto(e));
                }
            }
        }


        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("get room empty empty success")
                .buildData(result)
                .build();
    }

    @Override
    public Response getEmptyRoom(int type, String keyword) throws Exception {

        List<RoomEntity> entities = roomRepository.getEmptyRoom(keyword);
        if (entities.isEmpty()) {
            throw new RoomException("list room is rented", HttpStatus.NOT_FOUND.value());
        }
        List<RoomDto> result = new ArrayList<>();
        if (keyword != null && keyword != "") {
            for (RoomEntity e : entities) {
                result.add(roomMapping.convertToDto(e));
            }
        } else {
            for (RoomEntity e : entities) {
                if (e.getType() == type) {
                    result.add(roomMapping.convertToDto(e));
                }
            }
        }


        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("get room empty empty success")
                .buildData(result)
                .build();
    }

    @Override
    public Response getRentedRoom(int type, String keyword) throws Exception {

        List<RoomEntity> entities = roomRepository.getRentedRoom(keyword);
        if (entities.isEmpty()) {
            throw new RoomException("empty", HttpStatus.NOT_FOUND.value());
        }
        List<RoomDto> result = new ArrayList<>();
        if (keyword != null && keyword != "") {
            for (RoomEntity e : entities) {
                result.add(roomMapping.convertToDto(e));
            }
        } else {
            for (RoomEntity e : entities) {
                if (e.getType() == type) {
                    result.add(roomMapping.convertToDto(e));
                }
            }
        }


        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("get room rented empty success")
                .buildData(result)
                .build();
    }


    @Override
    public Response updateStatusById(long code) throws Exception {

        try {
            RoomEntity room = roomRepository.getRoomByCode(code);
            if (room.getStatus()) {
                room.setStatus(false);
            } else {
                room.setStatus(true);
            }
            roomRepository.updateStatusByCode(room);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("update success")
                .build();
    }

    @Override
    public Response getRoomByCode(Long code) throws Exception {

        RoomEntity res = roomRepository.getRoomByCode(code);
        if (res == null) {
            throw new RoomException("list room is rented", HttpStatus.NOT_FOUND.value());
        }
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("get room by code success")
                .buildData(res)
                .build();
    }


}
