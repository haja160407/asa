package com.school.luc.endpoint.event.consumer.model;

import com.school.luc.PojaGenerated;
import com.school.luc.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
