package com.example.demo.events;

import com.example.demo.application.model.Coor;
import javafx.event.Event;
import javafx.event.EventType;

public class DirectionChangedEvent extends Event {
    public static String TYPE = "DIRECTION_LISTENER_EVENT_TYPE";

    private char newDir;

    public final static EventType<DirectionChangedEvent> DIRECTION_LISTENER_EVENT_TYPE = new EventType<>(TYPE);

    public DirectionChangedEvent(Object source, char newDir){
        super(DIRECTION_LISTENER_EVENT_TYPE);
        this.newDir = newDir;
    }

    public char getNewDir(){return newDir;}


}
