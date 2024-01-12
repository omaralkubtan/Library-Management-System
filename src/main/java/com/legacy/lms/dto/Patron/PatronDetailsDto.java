package com.legacy.lms.dto.Patron;

import lombok.Data;

@Data
public class PatronDetailsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String address;
}