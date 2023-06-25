package com.example.demo.events;

import javafx.event.Event;
import javafx.event.EventType;



public class GameOverEvent extends Event {

    private static String TYPE = "GAME_OVER";
    private int score;
    public final static EventType<GameOverEvent> GAME_OVER = new EventType<>(TYPE);

    public GameOverEvent( int score){
        super(GAME_OVER);
        this.score = score;
    }
}
