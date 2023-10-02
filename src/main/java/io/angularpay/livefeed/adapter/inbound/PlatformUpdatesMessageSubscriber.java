package io.angularpay.livefeed.adapter.inbound;

import io.angularpay.livefeed.models.platform.PlatformConfigurationIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@RequiredArgsConstructor
public class PlatformUpdatesMessageSubscriber implements MessageListener {

    private final RedisMessageAdapter redisMessageAdapter;
    private final PlatformConfigurationIdentifier identifier;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        redisMessageAdapter.onMessage(String.valueOf(message), this.identifier);
    }
}
