package com.example.demo.events;

import javafx.event.Event;
import javafx.event.EventType;

import static com.example.demo.events.DirectionChangedEvent.TYPE;

public class GameOverEvent extends Event {
    private int score;
    public final static EventType<GameOverEvent> GAME_OVER = new EventType<>(TYPE);

    public GameOverEvent( int score){
        super(GAME_OVER);
        this.score = score;
    }
}
