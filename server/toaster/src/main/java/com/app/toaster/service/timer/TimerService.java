package com.app.toaster.service.timer;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;


import org.springframework.stereotype.Service;

import com.app.toaster.controller.request.timer.CreateCronRequestDto;
import com.app.toaster.controller.request.timer.UpdateCronDateTimeDto;

import com.app.toaster.external.client.fcm.FCMService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimerService {

    private final FCMService fcmService;



    private final Locale locale = Locale.KOREA;

    private final int MaxTimerNumber = 5;
    private final int TimeIntervalInHours = 60;

    public void createTimer(CreateCronRequestDto createCronRequestDto){


        // 바뀐 타이머가 오늘 이후 설정되어있으면 새로운 schedule 추가
       if (createCronRequestDto.remindDates().contains(LocalDateTime.now().getDayOfWeek().getValue()))
           if(LocalTime.parse(createCronRequestDto.remindTime()).isAfter(LocalTime.now())){
               String cronExpression = String.format("0 %s %s * * ?", LocalTime.parse(createCronRequestDto.remindTime()).getMinute(),LocalTime.parse(createCronRequestDto.remindTime()).getHour());

               fcmService.schedulePushAlarm(cronExpression, createCronRequestDto.timerId());
           }
    }

    public void updateTimerDatetime(UpdateCronDateTimeDto updateTimerDateTimeDto){
        LocalDateTime now = LocalDateTime.now();

        // 바뀐 타이머가 오늘 이후 설정되어있으면 새로운 schedule 추가
       if (updateTimerDateTimeDto.remindDates().contains(now.getDayOfWeek().getValue()))
           if(LocalTime.parse(updateTimerDateTimeDto.remindTime()).isAfter(LocalTime.now())){
               String cronExpression = String.format("0 %s %s * * ?", LocalTime.parse(updateTimerDateTimeDto.remindTime()).getMinute(),LocalTime.parse(updateTimerDateTimeDto.remindTime()).getHour());

               fcmService.schedulePushAlarm(cronExpression, updateTimerDateTimeDto.timerId());
           }
    }

    // 인덱스로 요일 알아내기
    private String mapIndexToDayString(int index) {
        DayOfWeek dayOfWeek = DayOfWeek.of(index);
        String dayName = dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, locale);

        return dayName.substring(0, 1);
    }

}
