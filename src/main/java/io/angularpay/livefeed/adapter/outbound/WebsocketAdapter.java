package io.angularpay.livefeed.adapter.outbound;

import io.angularpay.livefeed.ports.outbound.WebsocketPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsocketAdapter implements WebsocketPort {

    private final SimpMessagingTemplate template;

    @Override
    public void publish(String message, String topic) {
        log.info("message received - topic: {}, message: {}", topic, message);
        this.template.convertAndSend("/topic/" + topic, message);
    }
}
