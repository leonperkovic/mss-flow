package mars.mss.flow.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {
    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${mss-api.base-url}")
    private String mssApiBaseUrl;

    @Bean
    public WebClient mssWebClient() {
        return WebClient.builder()
                .filter(logRequest())
                .filter(logResponse())
                .baseUrl(mssApiBaseUrl)
                .build();
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("Sending request {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            var statusCode = clientResponse.statusCode();
            if (statusCode.isError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            log.warn("Received response with status '{}' and body '{}'", statusCode, body);
                            return Mono.just(clientResponse);
                        });
            } else {
                log.debug("Received response with status '{}'", statusCode);
                return Mono.just(clientResponse);
            }
        });
    }
}
