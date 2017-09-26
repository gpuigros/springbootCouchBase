package com.puigros.couchbase;

import com.puigros.couchbase.dto.defaultBucket.Hotel;

import java.util.List;

/**
 * Created by gpuigros on 26/09/17.
 */
public interface HotelTemplate {
    Hotel findOne(String id);

    List<Hotel> findAll();

    List<Hotel> findByName(String name);

    void create(Hotel hotel);

    void update(Hotel hotel);

    void delete(Hotel hotel);
}
