package io.angularpay.livefeed.domain;

public enum AngularPayTopics {
    CRYPTO("crypto"),
    MENIAL("menial"),
    PAYNABLE("paynable"),
    PEER_FUND("peer-fund"),
    PITCH("pitch"),
    PMT("pmt"),
    SUPPLY("supply"),
    SMART_SAVE("smart-save"),
    STOCK("stock"),
    INVOICE("invoice"),
    ASSETS("assets");

    private final String topic;

    public String topic() {
        return this.topic;
    }

    AngularPayTopics(String topic) {
        this.topic = topic;
    }
}
