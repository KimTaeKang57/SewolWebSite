package com.Sewol.Sewol.dto;

import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DateDto {

    private LocalDateTime localDateTime;
    private String dayDiff;
    // private int count;

    @SneakyThrows
    public String dayDiff() {
        SimpleDateFormat NowDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat SewolDateFormat = new SimpleDateFormat("2014/04/16");

        String NowDate = NowDateFormat.format(new Date());
        String sewolDate = SewolDateFormat.format(new Date());

        Date now = NowDateFormat.parse(NowDate);
        Date sewol = NowDateFormat.parse(sewolDate);

        long diffSec = (now.getTime() - sewol.getTime()) / 1000; //초 차이
        long diffDays = diffSec / (24 * 60 * 60); //일자수 차이

        String day = String.valueOf(diffDays);

        return day;
    }
}
