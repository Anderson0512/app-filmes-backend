package com.example.aplication.service.impl;

import com.example.aplication.domain.model.Game;
import com.example.aplication.domain.model.GameRepository;
import com.example.aplication.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl extends BaseCrudServiceImpl<Game, GameRepository> implements GameService {

    @Autowired
    public GameServiceImpl(GameRepository repository) {
        super(repository);
    }

    @Override
    public void vote(Long gameId) {
        Game game = super.findById(gameId);
        game.setVotes(game.getVotes() + 1);

        super.update(gameId, game);
    }

    public List<Game> findAll() {
        return super.repository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }
}
