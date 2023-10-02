package io.angularpay.livefeed.adapter.inbound;

import io.angularpay.livefeed.adapter.common.RedisConfiguration;
import io.angularpay.livefeed.configurations.AngularPayConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import static io.angularpay.livefeed.domain.AngularPayTopics.*;
import static io.angularpay.livefeed.models.platform.PlatformConfigurationIdentifier.*;

@Configuration
@RequiredArgsConstructor
public class RedisInboundConfiguration {

    private final RedisConfiguration redisConfiguration;

    // LIVE FEEDS

    @Bean
    ChannelTopic cryptoTopic() {
        return new ChannelTopic(CRYPTO.topic());
    }

    @Bean
    ChannelTopic menialTopic() {
        return new ChannelTopic(MENIAL.topic());
    }

    @Bean
    ChannelTopic paynableTopic() {
        return new ChannelTopic(PAYNABLE.topic());
    }

    @Bean
    ChannelTopic peerFundTopic() {
        return new ChannelTopic(PEER_FUND.topic());
    }

    @Bean
    ChannelTopic pitchTopic() {
        return new ChannelTopic(PITCH.topic());
    }

    @Bean
    ChannelTopic pmtTopic() {
        return new ChannelTopic(PMT.topic());
    }

    @Bean
    ChannelTopic supplyTopic() {
        return new ChannelTopic(SUPPLY.topic());
    }

    @Bean
    ChannelTopic smartSaveTopic() {
        return new ChannelTopic(SMART_SAVE.topic());
    }

    @Bean
    ChannelTopic stockTopic() {
        return new ChannelTopic(STOCK.topic());
    }

    @Bean
    ChannelTopic invoiceTopic() {
        return new ChannelTopic(INVOICE.topic());
    }

    @Bean
    ChannelTopic assetsTopic() {
        return new ChannelTopic(ASSETS.topic());
    }

    // PLATFORM CONFIGURATIONS

    @Bean
    ChannelTopic banksTopic() {
        return new ChannelTopic(PLATFORM_BANKS.getTopic());
    }

    @Bean
    ChannelTopic countriesTopic() {
        return new ChannelTopic(PLATFORM_COUNTRIES.getTopic());
    }

    @Bean
    ChannelTopic countryFeaturesTopic() {
        return new ChannelTopic(PLATFORM_COUNTRY_FEATURES.getTopic());
    }

    @Bean
    ChannelTopic currenciesTopic() {
        return new ChannelTopic(PLATFORM_CURRENCIES.getTopic());
    }

    @Bean
    ChannelTopic maturityConfigurationsTopic() {
        return new ChannelTopic(PLATFORM_MATURITY_CONFIGURATIONS.getTopic());
    }

    @Bean
    ChannelTopic notificationTypesTopic() {
        return new ChannelTopic(PLATFORM_NOTIFICATION_TYPES.getTopic());
    }

    @Bean
    ChannelTopic otpTypesTopic() {
        return new ChannelTopic(PLATFORM_OTP_TYPES.getTopic());
    }

    @Bean
    ChannelTopic servicesTopic() {
        return new ChannelTopic(PLATFORM_SERVICES.getTopic());
    }

    @Bean
    ChannelTopic ttlConfigurationTopic() {
        return new ChannelTopic(PLATFORM_TTL_CONFIGURATION.getTopic());
    }


    // LIVE FEEDS

    @Bean("cryptoListenerAdapter")
    MessageListenerAdapter cryptoListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.cryptoTopic()));
    }

    @Bean("menialListenerAdapter")
    MessageListenerAdapter menialListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.menialTopic()));
    }

    @Bean("paynableListenerAdapter")
    MessageListenerAdapter paynableListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.paynableTopic()));
    }

    @Bean("peerFundListenerAdapter")
    MessageListenerAdapter peerFundListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.peerFundTopic()));
    }

    @Bean("pitchListenerAdapter")
    MessageListenerAdapter pitchListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.pitchTopic()));
    }

    @Bean("pmtListenerAdapter")
    MessageListenerAdapter pmtListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.pmtTopic()));
    }

    @Bean("supplyListenerAdapter")
    MessageListenerAdapter supplyListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.supplyTopic()));
    }

    @Bean("smartSaveListenerAdapter")
    MessageListenerAdapter smartSaveListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.smartSaveTopic()));
    }

    @Bean("stockListenerAdapter")
    MessageListenerAdapter stockListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.stockTopic()));
    }

    @Bean("invoiceListenerAdapter")
    MessageListenerAdapter invoiceListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.invoiceTopic()));
    }

    @Bean("assetsListenerAdapter")
    MessageListenerAdapter assetsListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new UpdatesMessageSubscriber(redisMessageAdapter, this.assetsTopic()));
    }


    // PLATFORM CONFIGURATIONS

    @Bean("banksListenerAdapter")
    MessageListenerAdapter banksListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_BANKS));
    }

    @Bean("countriesListenerAdapter")
    MessageListenerAdapter countriesListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_COUNTRIES));
    }

    @Bean("countryFeaturesListenerAdapter")
    MessageListenerAdapter countryFeaturesListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_COUNTRY_FEATURES));
    }

    @Bean("currenciesListenerAdapter")
    MessageListenerAdapter currenciesListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_CURRENCIES));
    }

    @Bean("maturityConfigurationsListenerAdapter")
    MessageListenerAdapter maturityConfigurationsListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_MATURITY_CONFIGURATIONS));
    }

    @Bean("notificationTypesListenerAdapter")
    MessageListenerAdapter notificationTypesListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_NOTIFICATION_TYPES));
    }

    @Bean("otpTypesListenerAdapter")
    MessageListenerAdapter otpTypesListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_OTP_TYPES));
    }

    @Bean("servicesListenerAdapter")
    MessageListenerAdapter servicesListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_SERVICES));
    }

    @Bean("ttlConfigurationListenerAdapter")
    MessageListenerAdapter ttlConfigurationListenerAdapter(RedisMessageAdapter redisMessageAdapter) {
        return new MessageListenerAdapter(new PlatformUpdatesMessageSubscriber(redisMessageAdapter, PLATFORM_TTL_CONFIGURATION));
    }

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisMessageAdapter redisMessageAdapter, AngularPayConfiguration angularPayConfiguration) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConfiguration.connectionFactory(angularPayConfiguration));

        container.addMessageListener(cryptoListenerAdapter(redisMessageAdapter), cryptoTopic());
        container.addMessageListener(menialListenerAdapter(redisMessageAdapter), menialTopic());
        container.addMessageListener(paynableListenerAdapter(redisMessageAdapter), paynableTopic());
        container.addMessageListener(peerFundListenerAdapter(redisMessageAdapter), peerFundTopic());
        container.addMessageListener(pitchListenerAdapter(redisMessageAdapter), pitchTopic());
        container.addMessageListener(pmtListenerAdapter(redisMessageAdapter), pmtTopic());
        container.addMessageListener(supplyListenerAdapter(redisMessageAdapter), supplyTopic());
        container.addMessageListener(smartSaveListenerAdapter(redisMessageAdapter), smartSaveTopic());
        container.addMessageListener(stockListenerAdapter(redisMessageAdapter), stockTopic());
        container.addMessageListener(invoiceListenerAdapter(redisMessageAdapter), invoiceTopic());
        container.addMessageListener(assetsListenerAdapter(redisMessageAdapter), assetsTopic());

        container.addMessageListener(banksListenerAdapter(redisMessageAdapter), banksTopic());
        container.addMessageListener(countriesListenerAdapter(redisMessageAdapter), countriesTopic());
        container.addMessageListener(countryFeaturesListenerAdapter(redisMessageAdapter), countryFeaturesTopic());
        container.addMessageListener(currenciesListenerAdapter(redisMessageAdapter), currenciesTopic());
        container.addMessageListener(maturityConfigurationsListenerAdapter(redisMessageAdapter), maturityConfigurationsTopic());
        container.addMessageListener(notificationTypesListenerAdapter(redisMessageAdapter), notificationTypesTopic());
        container.addMessageListener(otpTypesListenerAdapter(redisMessageAdapter), otpTypesTopic());
        container.addMessageListener(servicesListenerAdapter(redisMessageAdapter), servicesTopic());
        container.addMessageListener(ttlConfigurationListenerAdapter(redisMessageAdapter), ttlConfigurationTopic());
        return container;
    }

}
