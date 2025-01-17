package com.app.toaster.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.common.dto.ApiResponse;
import com.app.toaster.controller.request.timer.CreateCronRequestDto;
import com.app.toaster.controller.request.timer.UpdateCronDateTimeDto;
import com.app.toaster.exception.Success;
import com.app.toaster.service.timer.TimerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timer")
public class TimerController {

	private final TimerService timerService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse createTimer(
		@RequestBody CreateCronRequestDto createCronRequestDto
	) {
		timerService.createTimer(createCronRequestDto);
		return ApiResponse.success(Success.CREATE_TIMER_SUCCESS);
	}

	@PatchMapping("/datetime/{timerId}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse updateTimerDatetime(
		@RequestBody UpdateCronDateTimeDto updateTimerDateTimeDto
	) {
		timerService.updateTimerDatetime(updateTimerDateTimeDto);
		return ApiResponse.success(Success.UPDATE_TIMER_DATETIME_SUCCESS);
	}

}
