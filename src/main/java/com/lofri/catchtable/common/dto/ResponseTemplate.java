package com.lofri.catchtable.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTemplate<T> {
    private Status status;
    private T data;

    @Getter
    @AllArgsConstructor
    public static class Status {

        @Schema(name = "상태 코드")
        private String code;

        @Schema(name = "상세 메시지")
        private String message;

        Status() {
            this.code = "000_000";
            this.message = "Success";
        }
    }

    private ResponseTemplate(T data) {
        this.status = new Status();
        this.data = data;
    }

    public static ResponseTemplate<Void> ok() {
        return new ResponseTemplate<>(null);
    }

    public static<T> ResponseTemplate<T> ok(T data) {
        return new ResponseTemplate<>(data);
    }
}
