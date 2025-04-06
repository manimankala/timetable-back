package com.major.TimeTable.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Response {
    private Object data;

    public Response(Object data) {
        this.data = data;
    }
    public Response(){

    }

}