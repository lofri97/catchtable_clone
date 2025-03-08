package com.lofri.catchtable.test.util;

import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class DocumentationUtils {
    private DocumentationUtils() {}

    public static final FieldDescriptor[] PAGING_FIELDS = new FieldDescriptor[] {
            fieldWithPath("pagination").description("페이징 정보"),
            fieldWithPath("pagination.total").description("검색 결과 총 개수"),
            fieldWithPath("pagination.perPage").description("페이지당 결과 개수"),
            fieldWithPath("pagination.currentPage").description("현재 페이지 번호"),
            fieldWithPath("pagination.totalPages").description("총 페이지 개수")
    };
}
