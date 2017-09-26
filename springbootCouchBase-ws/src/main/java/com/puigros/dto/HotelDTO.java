package com.puigros.dto;
import lombok.Data;

import java.util.List;

/**
 * Created by gpuigros on 22/08/17.
 */
@Data
public class HotelDTO {


        private String id;
        private String giata;
        private String name;
        private List<RoomDTO> rooms;
}
