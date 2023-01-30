package com.management.hotel.constants;

public class RoomEnum {

    public enum RoomStatus {
        EMPTY(1),
        RENTED(0);

        private int code;

        RoomStatus(int code) {
            this.code = code;
        }

        public static String getRoomStatus(int code) {
            for (RoomStatus status : RoomStatus.values()) {
                if (status.code == code) {
                    return status.name();
                }
            }
            return null;
        }
    }

    public enum RoomType {
        ONE_BEDROOM(1),
        TWO_BEDROOM(2);

        private int type;

        RoomType(int type) {
            this.type = type;
        }

        public static String getType(int typeId) {
            for (RoomType type : RoomType.values()) {
                if (type.type == typeId) {
                    return type.name();
                }
            }
            return null;
        }
    }


}
