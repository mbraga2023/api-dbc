package com.dbc.api.controller;

import com.dbc.api.domain.Ranking;
import com.dbc.api.repository.RankingRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    @Autowired
    private RankingRepository rankingRepository;

    // Get all rankings
    @GetMapping
    public ResponseEntity<List<Ranking>> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    // Get a ranking by detectiveId
    @GetMapping("/{detectiveId}")
    public ResponseEntity<Ranking> getRankingById(@PathVariable Long detectiveId) {
        Optional<Ranking> ranking = rankingRepository.findById(detectiveId);
        if (ranking.isPresent()) {
            return new ResponseEntity<>(ranking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new ranking
    @PostMapping
    public ResponseEntity<Ranking> createRanking(@RequestBody Ranking ranking) {
        try {
            Ranking newRanking = rankingRepository.save(ranking);
            return new ResponseEntity<>(newRanking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing ranking
    @PutMapping("/{detectiveId}")
    public ResponseEntity<Ranking> updateRanking(@PathVariable Long detectiveId, @RequestBody Ranking ranking) {
        Optional<Ranking> rankingData = rankingRepository.findById(detectiveId);

        if (rankingData.isPresent()) {
            Ranking existingRanking = rankingData.get();
            existingRanking.setName(ranking.getName());
            existingRanking.setPoints(ranking.getPoints());
            return new ResponseEntity<>(rankingRepository.save(existingRanking), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a ranking
    @DeleteMapping("/{detectiveId}")
    public ResponseEntity<HttpStatus> deleteRanking(@PathVariable Long detectiveId) {
        try {
            rankingRepository.deleteById(detectiveId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
