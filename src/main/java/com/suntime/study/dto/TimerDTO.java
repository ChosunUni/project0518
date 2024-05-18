package com.suntime.study.dto;

import com.suntime.study.entity.TimerEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TimerDTO {
    private String subject;
    private String email;
    private Long id;
    private int hours;
    private int minutes;
    private int seconds;

    public static TimerDTO fromTimerEntity(TimerEntity timerEntity) {
        TimerDTO timerDTO = new TimerDTO();
        int hours = timerEntity.getHours() != null ? timerEntity.getHours().intValue() : 0;
        int minutes = timerEntity.getMinutes() != null ? timerEntity.getMinutes().intValue() : 0;
        int seconds = timerEntity.getSeconds() != null ? timerEntity.getSeconds().intValue() : 0;
        timerDTO.setHours(hours);
        timerDTO.setMinutes(minutes);
        timerDTO.setSeconds(seconds);
        return timerDTO;
    }
}
