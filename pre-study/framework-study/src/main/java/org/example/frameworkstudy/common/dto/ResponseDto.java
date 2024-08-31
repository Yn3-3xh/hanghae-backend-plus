package org.example.frameworkstudy.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDto<D> {

    private final HttpStatus httpStatus;
    private final String message;
    private final D data;

    public static <D> ResponseDto<D> ofSuccess(String message, D data) {
        return new ResponseDto<>(HttpStatus.OK, message, data);
    }
}
