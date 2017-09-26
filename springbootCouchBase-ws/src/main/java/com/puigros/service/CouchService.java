package com.puigros.service;

import com.puigros.dto.HotelDTO;
import com.puigros.couchbase.dto.defaultBucket.Hotel;

import java.util.List;

/**
 * Created by gpuigros on 21/08/17.
 */
public interface CouchService {

    List<HotelDTO> findByName(String name);

    List<HotelDTO> findByGiata(String val);

    void create(Hotel hotel);

    void update(Hotel hotel);

    List<HotelDTO> findByNameTemplate(String name);

    List<HotelDTO> findAll();

    Hotel findOne(String id);

    void save(Hotel hotel);

    void delete(Hotel hotel);

    HotelDTO findMem(String key);


    void saveMem(com.puigros.couchbase.dto.secondaryBucket.Hotel hotel);
}
