package ba.unsa.etf.si.local_server.configurations;

import ba.unsa.etf.si.local_server.services.MainStompSessionHandler;
import ba.unsa.etf.si.local_server.services.MainSyncUpService;
import ba.unsa.etf.si.local_server.services.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StompClientConfiguration {
    private final String URL = "ws://log-server-si.herokuapp.com/ws";
    private final MainSyncUpService mainSyncUpService;
    private final ReceiptService receiptService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public WebSocketStompClient getStompClient() {
        WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(simpleWebSocketClient));
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.connect(URL, new MainStompSessionHandler(receiptService, mainSyncUpService, simpMessagingTemplate));
        return stompClient;
    }

}
