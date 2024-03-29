package com.example.mybatis.controller;

import com.example.mybatis.application.SseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final SseService sseService;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeSse(
            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
            HttpServletResponse response) {
        logger.info("====== /sse [" + getClass().getSimpleName() + "subscribeSse()] start ======");
        logger.info("====== " + getClass().getSimpleName() + "subscribeSse()] lastEventId :", lastEventId);
        //nginx리버스 프록시에서 버퍼링 기능으로 인한 오동작 방지
        response.setHeader("X-Accel-Buffering", "no");
        logger.info("====== /sse [" + getClass().getSimpleName() + "subscribeSse()] end ======");
        return sseService.connectSse();
    }
}
