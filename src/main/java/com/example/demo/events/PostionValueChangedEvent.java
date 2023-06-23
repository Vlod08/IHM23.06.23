package com.example.demo.events;

import com.example.demo.application.model.Coor;
import javafx.event.Event;
import javafx.event.EventType;

@SuppressWarnings("serial")
public class PostionValueChangedEvent extends Event{
    public static String TYPE = "MOVEMENT_LISTENER_EVENT_TYPE";

    private Coor newPos;

    public final static EventType<PostionValueChangedEvent> MOVEMENT_LISTENER_EVENT_TYPE = new EventType<>(TYPE);

    public PostionValueChangedEvent(Object source, Coor newCoor){
        super(MOVEMENT_LISTENER_EVENT_TYPE);
        this.newPos = newCoor;
    }

    public Coor getNewPos(){return newPos;}


}
