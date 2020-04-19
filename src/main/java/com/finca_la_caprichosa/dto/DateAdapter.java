package com.finca_la_caprichosa.dto;

import javax.json.bind.adapter.JsonbAdapter;
import java.util.Date;

public class DateAdapter implements JsonbAdapter<Date, Long> {

    @Override
    public Long adaptToJson(Date date) throws Exception {
        return date.getTime();
    }

    @Override
    public Date adaptFromJson(Long millis) throws Exception {
        return new Date(millis);
    }
}
