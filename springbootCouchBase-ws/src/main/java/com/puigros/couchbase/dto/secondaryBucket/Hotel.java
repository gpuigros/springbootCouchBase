package com.puigros.couchbase.dto.secondaryBucket;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gpuigros on 22/08/17.
 */
@Data
@Document(expiry = 60)
//expiry = 60 = 1 min.
public class Hotel {

        @Id
        private String id;
        @Field
        private String giata;
        @Field
        @NotNull
        private String name;
        @Field
        private List<Room> rooms;
}
