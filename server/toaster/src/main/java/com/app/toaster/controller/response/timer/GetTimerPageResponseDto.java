package com.app.toaster.controller.response.timer;

import java.util.List;

import lombok.Builder;

@Builder
public record GetTimerPageResponseDto(List<CompletedTimerDto> completedTimerList, List<WaitingTimerDto> waitingTimerList) {
}
