package com.influxdata.PickACard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class CardController {

  private final DeckOfCardService deckOfCardService;

  public CardController(DeckOfCardService deckOfCardService){
    this.deckOfCardService = deckOfCardService;
  }

  // Render the webpage using Thymeleaf
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/cards")
  @ResponseBody
  public Mono<List<String>> getRandomCards() {
    return deckOfCardService.getRandomCardSvgs();
  }
}
