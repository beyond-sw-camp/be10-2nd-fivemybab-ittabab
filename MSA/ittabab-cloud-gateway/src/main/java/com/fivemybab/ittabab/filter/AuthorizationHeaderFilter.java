package com.fivemybab.ittabab.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/* API 게이트웨이의 요청을 처리하기 전에 JWT 검증 역할 수행 필터(커스텀 필터) */
@Component
public class AuthorizationHeaderFilter
        extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    Environment environment;
    public AuthorizationHeaderFilter(Environment environment) {
        super(Config.class);
        this.environment = environment;
    }

    /* Gateway Filter를 반환하며 exchange와 chain 객체를 사용하여 요청과 응답 처리 및 다음 필터 실행 */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {   // exchange는 request와 response가 캡슐화 된 하나의 객체

            ServerHttpRequest request = exchange.getRequest();

            // Swagger UI 및 API 문서 경로 제외
            if (request.getURI().getPath().contains("/swagger-ui") ||
                    request.getURI().getPath().contains("/v3/api-docs")) {
                return chain.filter(exchange);
            }

            /* request 헤더에 authorization 없으면 에러 발생 */
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Authorization header is missing");
            }

            /* authorization 에 있는게 bearer 토큰이 아니면 에러 발생 */
            String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if(!bearerToken.startsWith("Bearer ")) {
                return onError(exchange, "Invalid or missing Bearer token");
            }

            /* jwt 토큰 검증 후 아니면 에러 발생 */
            String jwt = bearerToken.replace("Bearer ", "");
            if(!isJwtValid(jwt)) {
                return onError(exchange, "Invalid or expired JWT token");
            }

            // 요청 헤더에 Authorization 헤더 추가
            exchange.getRequest()
                    .mutate()
                    .header(HttpHeaders.AUTHORIZATION, bearerToken)
                    .build();

            /* 다 통과하면 다음 필터로 넘김 */
            return chain.filter(exchange);
        };
    }

    /* jwt 토큰 검증 */
    private boolean isJwtValid(String jwt) {
        boolean valid = true;
        String subject = null;

        try {
            // JWT 서명을 검증하고, 유효한 경우 subject를 가져옴
            subject = Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))  // 비밀 키 설정
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        } catch (io.jsonwebtoken.security.SignatureException e) {
            valid = false;
            System.err.println("Invalid JWT signature: " + e.getMessage());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            valid = false;
            System.err.println("JWT token is expired: " + e.getMessage());
        } catch (Exception e) {
            valid = false;
            System.err.println("JWT token validation error: " + e.getMessage());
        }

        // subject가 없거나 비어 있을 경우 유효하지 않음
        if (subject == null || subject.isEmpty()) {
            valid = false;
        }

        return valid;
    }

    /* Mono는 0 or 1의 객체를 비동기적으로 처리할 때 사용 (비동기 작업의 성공 or 실패를 나타내기 위한 반환 타입) */
    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage) {

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        /* 상태 코드만 설정해서 에러 발생 시 바로 응답 처리 할 경우 */
//        return response.setComplete();

        /* 에러 메세지도 담아서 응답 처리 할 경우 */
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String responseBody = "{\"error\":\"" + errorMessage + "\"}";

        DataBufferFactory bufferFactory = response.bufferFactory();
        DataBuffer buffer = bufferFactory.wrap(responseBody.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
    public static class Config {}
}
