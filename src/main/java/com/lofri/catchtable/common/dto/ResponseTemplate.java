package com.lofri.catchtable.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lofri.catchtable.common.code.ResponseCode;
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
    public static class Status {

        @Schema(name = "상태 코드")
        private String code;

        @Schema(name = "상세 메시지")
        private String message;

        Status() {
            this.code = "000_000";
            this.message = "Success";
        }

        @Builder
        Status(String code, String message) {
            this.code = code;
            this.message = message;
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

    public static ResponseTemplate<Void> of(ResponseCode responseCode) {
        return ResponseTemplate.of(responseCode, null);
    }

    public static <T> ResponseTemplate<T> of(ResponseCode code, T data) {
        return ResponseTemplate.<T>builder()
                .status(
                        Status.builder()
                                .code(code.name().substring(1))
                                .message(code.getMessage())
                                .build()
                )
                .data(data)
                .build();
    }
}
