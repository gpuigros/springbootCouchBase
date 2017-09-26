package com.puigros.couchbase;

import com.puigros.couchbase.dto.secondaryBucket.Hotel;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;

/**
 * Created by gpuigros on 22/08/17.
 */
public interface MemHotelRepository extends CouchbaseRepository<Hotel, String> {

    List<Hotel> findByName(String name);
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND #{[0]} = $2")
    List<Hotel> findByGIATA(String field, String giata);


}
