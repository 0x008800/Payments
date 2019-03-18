package com.oxoo88oo.doProvodki.client;

import com.oxoo88oo.doProvodki.entities.Entity;

import java.util.List;

public interface IClient<E extends Entity> {
    List<E> getAll();

    boolean send(E willBeSent);
}
