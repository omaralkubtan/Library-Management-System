package com.legacy.lms.entity;


import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "patrons")
public class Patron extends BaseEntity {

    @NotNull(message = "First name must not be null")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    private String lastName;

    @NotNull(message = "Mobile number must not be null")
    private String mobile;

    @Nullable
    private String address;
}
