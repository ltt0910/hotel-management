package com.management.hotel.mapping;

import com.management.hotel.constants.RoomEnum;
import com.management.hotel.entity.RoomEntity;
import com.management.hotel.model.dto.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapping {

    public RoomDto convertToDto(RoomEntity entity) {

        int check = 0;
        if (entity.getStatus()) {
            check = 1;
        }

        RoomDto result = new RoomDto();
        result.setId(entity.getId());
        result.setCode(entity.getCode());
        result.setDescription(entity.getDescription());
        result.setType(RoomEnum.RoomType.getType(entity.getType()));
        result.setStatus(RoomEnum.RoomStatus.getRoomStatus(check));
        result.setPrice(entity.getPricePerDay());
        result.setImage(entity.getImage());

        return result;

    }

}
