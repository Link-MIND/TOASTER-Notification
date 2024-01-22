package com.app.toaster.controller.response.category;

import java.util.List;

import com.app.toaster.controller.response.toast.ToastDto;

import lombok.Builder;

@Builder
public record GetCategoryResponseDto(
        int allToastNum,
        List<ToastDto> toastListDto
){

}
