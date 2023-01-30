package com.management.hotel.builder;

public class Response {

    private int code;
    private int status;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    private Response(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.code = builder.code;
        this.data = builder.data;
    }

    public static final class Builder {
        //required parameters
        private int status;
        private int code;

        //optional parameters
        private String message = "message from server";
        private Object data = new Object();

        /**
         * @param status status response from server. 1 if OK, 0 if not
         * @param code   code response from server. ex: 404,500,200
         */
        public Builder(int status, int code) {
            this.status = status;
            this.code = code;
        }

        /**
         * build message response from server
         *
         * @param message message from server
         * @return builder class
         */
        public Builder buildMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * build data response from server
         *
         * @param data data response from server
         * @return builder class
         */
        public Builder buildData(Object data) {
            this.data = data;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}


