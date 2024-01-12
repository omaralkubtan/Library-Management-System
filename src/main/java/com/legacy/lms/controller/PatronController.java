package com.legacy.lms.controller;

import com.legacy.lms.dto.Patron.PatronCreateDto;
import com.legacy.lms.dto.Patron.PatronDetailsDto;
import com.legacy.lms.dto.Patron.PatronListDto;
import com.legacy.lms.dto.Patron.PatronUpdateDto;
import com.legacy.lms.rbac.Permissions;
import com.legacy.lms.service.PatronService;
import com.legacy.lms.util.helper.Constants;
import com.legacy.lms.util.helper.PaginationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Tag(name = "Patrons")
@RestController
@Validated
public class PatronController extends ApiController {

    @Autowired
    private PatronService patronService;

    @Operation(
            summary = "Get All Patrons",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/patron")
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ALL_PATRONS + AUTH_SUFFIX)
    public PaginationResult<PatronListDto> getAllPatrons(
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE)
            @PositiveOrZero
            Integer page,

            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE)
            @Positive
            Integer pageSize
    )
    {
        var patrons = patronService.getAllPatrons(page, pageSize);
        return patrons.mapTo(modelMapper, PatronListDto.class);
    }

    @Operation(
            summary = "Get Patron by ID",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/patron/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_PATRON_DETAILS + AUTH_SUFFIX)
    public PatronDetailsDto getPatron(@PathVariable Long id) {
        var patron = patronService.getPatron(id);
        return modelMapper.map(patron, PatronDetailsDto.class);
    }

    @Operation(
            summary = "Create New Patron",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/patron")
    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_PATRON + AUTH_SUFFIX)
    public PatronDetailsDto createPatron(@Valid @RequestBody PatronCreateDto request) {
        var patron = patronService.createPatron(request);
        return modelMapper.map(patron, PatronDetailsDto.class);
    }

    @Operation(
            summary = "Update Existing Patron",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/patron/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_PATRON + AUTH_SUFFIX)
    public PatronDetailsDto updatePatron(
            @PathVariable("id") Long id,
            @Valid @RequestBody PatronUpdateDto request
    ) {
        var patron = patronService.updatePatron(id, request);
        return modelMapper.map(patron, PatronDetailsDto.class);
    }

    @Operation(
            summary = "Delete Existing Patron",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @DeleteMapping("/patron/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.DELETE_PATRON + AUTH_SUFFIX)
    public void deletePatron(@PathVariable("id") Long id) {
        patronService.deletePatron(id);
    }
}
