package com.fivemybab.ittabab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(500)
                .message("알 수 없는 에러가 발생했습니다.")
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    /* 요청한 데이터가 없을 때 */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(404)
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    /* 서버 내부 오류가 발생하면 */
    @ExceptionHandler(ServerInternalException.class)
    public ResponseEntity<ErrorResponse> handleServerInternalException(ServerInternalException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(500)
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    /* 데이터를 잘못 넘기면 발생 */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(400)
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
