package com.example.demo.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    private List<T> data;
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private int pageSize;
    private boolean hasNext;
    private boolean hasPrevious;
    private Integer nextPage;
    private Integer previousPage;

    public static <T> PaginatedResponse<T> from(Page<T> page) {
        PaginatedResponse<T> response = new PaginatedResponse<>();
        response.setData(page.getContent());
        response.setTotalItems(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber() + 1); // Convert to 1-based indexing
        response.setPageSize(page.getSize());
        response.setHasNext(page.hasNext());
        response.setHasPrevious(page.hasPrevious());
        response.setNextPage(page.hasNext() ? page.getNumber() + 2 : null); // Convert to 1-based indexing
        response.setPreviousPage(page.hasPrevious() ? page.getNumber() : null); // Convert to 1-based indexing
        return response;
    }
} 