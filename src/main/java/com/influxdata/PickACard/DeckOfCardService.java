package com.influxdata.PickACard;

import com.fasterxml.jackson.databind.JsonNode;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckOfCardService {

  private final WebClient webclient;
  private final Counter cardsDealCounter;
  private final Random random = new Random();

  public DeckOfCardService(WebClient.Builder webClientBuilder, MeterRegistry meterRegistry) {
    this.webclient = webClientBuilder.baseUrl("https://deckofcardsapi.com/api").build();
    this.cardsDealCounter = Counter.builder("cards.dealt")
                                  .description("Number of cards dealt")
                                  .register(meterRegistry);
  }

  public Mono<List<String>>getRandomCardSvgs() {
    int cardCount = random.nextInt(52) + 1; // Random number between 1 and 52
    return webclient.get()
                    .uri("/deck/new/draw/?count=" + cardCount)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(jsonNode -> {
                      List<String> cardUrls = new ArrayList<>();
                      JsonNode cards = jsonNode.get("cards");
                      for (JsonNode card : cards) {
                        cardUrls.add(card.get("image").asText());
                      }
                      return cardUrls;
                    })
                    .doOnSuccess(s -> cardsDealCounter.increment(cardCount));
  }
}