package io.angularpay.livefeed.ports.outbound;

public interface WebsocketPort {
    void publish(String message, String topic);
}
