package com.example.demo.events;

import javafx.event.Event;
import javafx.event.EventType;


public class EatFruitEvent extends Event {

    public static String TYPE = "EAT";

    private int x;
    private int y;
    public final static EventType<EatFruitEvent> EAT = new EventType<>(TYPE);

    public EatFruitEvent( int x, int y){
        super(EAT);
        this.x = x;
        this.y = y;
        System.out.println("callin fruit event" );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
