package io.angularpay.livefeed.adapter.inbound;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;

@RequiredArgsConstructor
public class UpdatesMessageSubscriber implements MessageListener {

    private final RedisMessageAdapter redisMessageAdapter;
    private final ChannelTopic channelTopic;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        redisMessageAdapter.onMessage(String.valueOf(message), this.channelTopic.getTopic());
    }
}
