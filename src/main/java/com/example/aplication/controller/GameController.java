package com.example.aplication.controller;

import com.example.aplication.domain.model.Game;
import com.example.aplication.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class GameController extends BaseRestController {

    @Autowired
    private GameService service;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PatchMapping("/games/{id}/vote")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Game vote(@PathVariable Long id) {
        this.service.vote(id);
        return this.service.findById(id);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> insert(@RequestBody Game game) {
        this.service.insert(game);
        return ResponseEntity.ok(game);
    }

    @PutMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
        this.service.update(id, game);
        return ResponseEntity.accepted().build();
    }
}
