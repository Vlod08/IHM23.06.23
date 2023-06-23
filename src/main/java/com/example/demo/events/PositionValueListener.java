package com.example.demo.events;

import java.util.EventListener;

public interface PositionValueListener extends EventListener {

    public void positionValueChanged(PostionValueChangedEvent event);
}
