package com.puigros.service;

import com.puigros.couchbase.HotelTemplate;
import com.puigros.couchbase.MemHotelRepository;
import com.puigros.couchbase.dto.defaultBucket.Room;
import com.puigros.dto.HotelDTO;
import com.puigros.couchbase.HotelRepository;
import com.puigros.couchbase.dto.defaultBucket.Hotel;
import com.puigros.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gpuigros on 21/08/17.
 */
@Service("CouchService")
public class CouchServiceImpl implements CouchService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private MemHotelRepository memHotelRepository;
    @Autowired
    private HotelTemplate template;

    private HotelDTO convertHotel(Hotel hot){
        HotelDTO hotelDTO=new HotelDTO();
        hotelDTO.setId(hot.getId());
        hotelDTO.setGiata(hot.getGiata());
        hotelDTO.setName(hot.getName());
        if (hot.getRooms()!=null && !hot.getRooms().isEmpty()){
            hotelDTO.setRooms(new ArrayList<>());
            for (Room ro:hot.getRooms()){
                RoomDTO room = new RoomDTO();
                room.setType(ro.getType());
                room.setDesciption(ro.getDesciption());
                hotelDTO.getRooms().add(room);
            }


        }
        return hotelDTO;

    }
    private HotelDTO convertMemHotel(com.puigros.couchbase.dto.secondaryBucket.Hotel hot){
        HotelDTO hotelDTO=new HotelDTO();
        hotelDTO.setId(hot.getId());
        hotelDTO.setGiata(hot.getGiata());
        hotelDTO.setName(hot.getName());
        if (hot.getRooms()!=null && !hot.getRooms().isEmpty()){
            hotelDTO.setRooms(new ArrayList<>());
            for (com.puigros.couchbase.dto.secondaryBucket.Room ro:hot.getRooms()){
                RoomDTO room = new RoomDTO();
                room.setType(ro.getType());
                room.setDesciption(ro.getDesciption());
                hotelDTO.getRooms().add(room);
            }


        }
        return hotelDTO;

    }
    @Override
    public List<HotelDTO> findByName(String name){
        List<HotelDTO> hotelsDTO=new ArrayList<>();
        List<Hotel> hotels = hotelRepository.findByName(name);
        if (hotels!=null && !hotels.isEmpty()){
            for(Hotel hot:hotels){
                hotelsDTO.add(convertHotel(hot));
            }
        }
        return hotelsDTO;
    }
    @Override
    public List<HotelDTO> findByGiata(String val){
        List<HotelDTO> hotelsDTO=new ArrayList<>();
        List<Hotel> hotels = hotelRepository.findByGIATA("giata",val);
        if (hotels!=null && !hotels.isEmpty()){
            for(Hotel hot:hotels){
                hotelsDTO.add(convertHotel(hot));
            }
        }
        return hotelsDTO;
    }
    @Override
    public void create(Hotel hotel) {
        template.create(hotel);
    }
    @Override
    public void update(Hotel hotel) {
        template.update(hotel);
    }
    @Override
    public List<HotelDTO> findByNameTemplate(String name){
        List<HotelDTO> hotelsDTO=new ArrayList<>();
        List<Hotel> hotels = template.findByName(name);
        if (hotels!=null && !hotels.isEmpty()){
            for(Hotel hot:hotels){
                hotelsDTO.add(convertHotel(hot));
            }
        }
        return hotelsDTO;
    }
    @Override
    public List<HotelDTO> findAll(){
        List<HotelDTO> hotelsDTO=new ArrayList<>();
        Iterable<Hotel> hotels = hotelRepository.findAll();
        if (hotels!=null ){
            Iterator<Hotel> ite = hotels.iterator();
            while(ite.hasNext()){
                Hotel hot = ite.next();
                HotelDTO hotelDTO=convertHotel(hot);
                hotelsDTO.add(hotelDTO);
            }
        }
        return hotelsDTO;
    }

    @Override
    public Hotel findOne(String id){
        return hotelRepository.findOne(id);
    }
    @Override
    public void save(Hotel hotel){
        hotelRepository.save(hotel);
    }
    @Override
    public void delete(Hotel hotel){
        hotelRepository.delete(hotel);
    }


    @Override
    public HotelDTO findMem(String key){
        List<HotelDTO> hotelsDTO=new ArrayList<>();
        com.puigros.couchbase.dto.secondaryBucket.Hotel hotel = memHotelRepository.findOne(key);
        return convertMemHotel(hotel);
    }
    @Override
    public void saveMem(com.puigros.couchbase.dto.secondaryBucket.Hotel hotel){
        memHotelRepository.save(hotel);
    }

}
