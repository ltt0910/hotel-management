package com.management.hotel.controller.api;

import com.management.hotel.builder.Response;
import com.management.hotel.exception.RoomException;
import com.management.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RoomAPI {

    @Autowired
    private RoomService roomService;

    @GetMapping("/empty_room")
    public ResponseEntity<?> getEmptyRoom(int type, String keyword) {

        Response res;
        try {
            res = roomService.getEmptyRoom(type, keyword);
        } catch (RoomException re) {

            res = new Response.Builder(1, re.getCode())
                    .buildMessage(re.getMessage())
                    .buildData("")
                    .build();

        } catch (Exception e) {

            res = new Response.Builder(1, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }


        return ResponseEntity.ok(res);
    }

}
