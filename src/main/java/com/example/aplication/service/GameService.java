package com.example.aplication.service;

import com.example.aplication.domain.model.Game;

public interface GameService extends CrudService<Game>{

    public void vote(Long gameId);
}
