package com.puigros.couchbase.impl;

import com.couchbase.client.java.view.ViewQuery;
import com.puigros.couchbase.HotelTemplate;
import com.puigros.couchbase.dto.defaultBucket.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gpuigros on 26/09/17.
 */
@Service
@Qualifier("hotelTemplateService")
public class HotelTemplateServiceImpl implements HotelTemplate {
    private static final String DESIGN_DOC = "hotel";
    @Autowired
    @Qualifier("couchbaseTemplate")
    private CouchbaseTemplate template;

    @Override
    public Hotel findOne(String id) {
        return template.findById(id, Hotel.class);
    }
    @Override
    public List<Hotel> findAll() {
        return template.findByView(ViewQuery.from(DESIGN_DOC, "all"), Hotel.class);
    }
    @Override
    public List<Hotel> findByName(String name) {
        return template.findByView(ViewQuery.from(DESIGN_DOC, "byName"), Hotel.class);
    }
    @Override
    public void create(Hotel hotel) {
        template.insert(hotel);
    }
    @Override
    public void update(Hotel hotel) {
        template.update(hotel);
    }
    @Override
    public void delete(Hotel hotel) {
        template.remove(hotel);
    }
}
