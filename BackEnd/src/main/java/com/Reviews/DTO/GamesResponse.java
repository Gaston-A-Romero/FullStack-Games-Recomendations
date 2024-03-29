package com.Reviews.DTO;

import com.Reviews.Model.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GamesResponse {
    @JsonProperty("gamesList")
    private List<Game> gamesList;
    @JsonProperty("totalPages")
    private int totalPages;
}
