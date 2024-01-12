package com.legacy.lms.util.helper;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PaginationResult<T> {
    private final Integer totalPages;

    private final Long totalItems;

    private final Integer pageSize;

    private final Integer page;

    private final List<T> data;

    public PaginationResult(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
        this.pageSize = page.getPageable().getPageSize();
        this.page = page.getPageable().getPageNumber();
        this.data = page.getContent();
    }

    public PaginationResult(PaginationResult<?> otherResult, List<T> data) {
        this.totalPages = otherResult.totalPages;
        this.totalItems = otherResult.totalItems;
        this.pageSize = otherResult.pageSize;
        this.page = otherResult.page;
        this.data = data;
    }

    public PaginationResult() {
        this.totalPages = 1;
        this.totalItems = 0L;
        this.pageSize = 10;
        this.page = 1;
        this.data = Collections.emptyList();
    }

    public <C> PaginationResult<C> mapTo(ModelMapper modelMapper, Class<C> type) {
        var data = this.data.stream().map(item -> modelMapper.map(item, type)).collect(Collectors.toList());

        return new PaginationResult<>(this, data);
    }
}
