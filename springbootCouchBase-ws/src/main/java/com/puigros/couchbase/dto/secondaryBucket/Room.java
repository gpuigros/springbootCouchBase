package com.puigros.couchbase.dto.secondaryBucket;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.Data;

/**
 * Created by gpuigros on 31/08/17.
 */
@Data
public class Room {
    @Field
    private String type;
    @Field
    private String desciption;
}
