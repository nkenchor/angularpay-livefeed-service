package io.angularpay.livefeed.adapter.inbound;

import io.angularpay.livefeed.adapter.outbound.WebsocketAdapter;
import io.angularpay.livefeed.domain.commands.PlatformConfigurationsConverterCommand;
import io.angularpay.livefeed.models.platform.PlatformConfigurationIdentifier;
import io.angularpay.livefeed.ports.inbound.InboundMessagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.angularpay.livefeed.models.platform.PlatformConfigurationSource.TOPIC;

@Service
@RequiredArgsConstructor
public class RedisMessageAdapter implements InboundMessagingPort {

    private final WebsocketAdapter featureServiceAdapter;
    private final PlatformConfigurationsConverterCommand converterCommand;

    @Override
    public void onMessage(String message, String topic) {
        featureServiceAdapter.publish(message, topic);
    }

    @Override
    public void onMessage(String message, PlatformConfigurationIdentifier identifier) {
        this.converterCommand.execute(message, identifier, TOPIC);
    }
}
