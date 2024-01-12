package com.legacy.lms.dto;

import com.legacy.lms.dto.user.UserLightWithoutPermissionResponse;
import com.legacy.lms.util.helper.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailsResponse {
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createdAt;

    private UserLightWithoutPermissionResponse createdBy;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime updatedAt;

    private UserLightWithoutPermissionResponse updatedBy;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime deletedAt;

    private UserLightWithoutPermissionResponse deletedBy;
}
