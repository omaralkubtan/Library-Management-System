package com.legacy.lms.dto;

import com.legacy.lms.util.helper.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListResponse {
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime deletedAt;
}
