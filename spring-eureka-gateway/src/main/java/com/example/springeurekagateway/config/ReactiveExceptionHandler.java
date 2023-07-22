package com.example.springeurekagateway.config;

import java.util.Map; 
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.springeurekagateway.errors.ErrorResponse;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.server.ServerRequest;

//@Slf4j
@Component
public class ReactiveExceptionHandler extends AbstractErrorWebExceptionHandler {
    private final Map<Class<? extends Exception>, HttpStatus> exceptionToStatusCode;
    private final HttpStatus defaultStatus;

    public ReactiveExceptionHandler(ErrorAttributes errorAttributes,
    								WebProperties.Resources resources,
    								ApplicationContext applicationContext,
    								Map<Class<? extends Exception>, 
    								HttpStatus> exceptionToStatusCode,
    								HttpStatus defaultStatus,
    								ServerCodecConfigurer configurer) {
	  super(errorAttributes, resources, applicationContext);
	  this.exceptionToStatusCode = exceptionToStatusCode;
	  this.setMessageWriters(configurer.getWriters());
	  this.defaultStatus = HttpStatus.UNAUTHORIZED;
	} 

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

        Throwable error = getError(request);
//        log.error("An error has been occurred", error);
        HttpStatus httpStatus;
        if (error instanceof Exception exception) {
            httpStatus = exceptionToStatusCode.getOrDefault(exception.getClass(), defaultStatus);
        } else {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return ServerResponse
                      .status(httpStatus)
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(BodyInserters.fromValue(new ErrorResponse(401,"unauthorized")
                ));
    }
}