package io.angularpay.livefeed.ports.inbound;

import io.angularpay.livefeed.models.platform.PlatformConfigurationIdentifier;

public interface InboundMessagingPort {
    void onMessage(String message, String topic);
    void onMessage(String message, PlatformConfigurationIdentifier identifier);
}
