package com.puigros.dto;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.Data;

/**
 * Created by gpuigros on 31/08/17.
 */
@Data
public class RoomDTO {
    @Field
    private String type;
    @Field
    private String desciption;
}
