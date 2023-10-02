var stompClient = null;
var maxConnectionRetry = 5;
var connectionRetryCount = 0;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#livefeed_updates").html("");
    if (connected) {
        $("#connection_status").text('Connected!')
            .addClass("text-success")
            .removeClass("text-muted")
            .removeClass("text-danger");
        $("#livefeed_updates").append("<tr><td>When connected, Live feed updates will show up here...</td></tr>");
    } else {
        $("#connection_status").text('Disconnected!')
            .addClass("text-danger")
            .removeClass("text-muted")
            .removeClass("text-success");
        $("#livefeed_updates").append("<tr><td>When connected, Live feed updates will show up here...</td></tr>");
    }
}

function connect() {
    var accessToken = document.getElementById("access_token").value;
    var clientId = JSON.parse(atob(accessToken.split('.')[1])).azp;
    var endpoint = window.location.protocol + "//" + window.location.host
        + "/livefeed/connect?device_id=" + clientId
        + "&x-angularpay-user-agent=AngularPay-Test-Websocket-LF-Client-v1.0-M51316"
        + "&access_token=" + accessToken;

    var socket = new SockJS(endpoint);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        connectionRetryCount = 0;
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/crypto', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/menial', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/paynable', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/peer-fund', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/pitch', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/pmt', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/supply', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/smart-save', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/stock', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/invoice', function (message) {
            onMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/assets', function (message) {
            onMessage(JSON.parse(message.body));
        });
    }, function (message) {
        console.log('--------------- errorCallback ------------');
        console.log(message);
        if (message && message.includes("Whoops! Lost connection to")) {
            if (connectionRetryCount <= maxConnectionRetry) {
                setTimeout(() => {
                    console.log('--------------- retrying connection ------------');
                    ++connectionRetryCount;
                    connect();
                }, 1000);
            } else {
                connectionRetryCount = 0;
            }
        }
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function onMessage(message) {
    $("#livefeed_updates").append("<tr><td>" + JSON.stringify(message) + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
});

