package com.legacy.lms.dto.Patron;

import com.legacy.lms.util.helper.Constants;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PatronCreateDto {

    @NotNull(message = "First name must not be null")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    private String lastName;

    @NotNull(message = "Mobile number must not be null")
    private String mobile;

    @Nullable
    private String address;
}
