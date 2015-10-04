package com.example.saroshmadara.chatterpatter.services.listeners;

/**
 * Created by Sarosh Madara on 02-10-2015.
 */
public interface ServiceListener<T> {
    public void success(T obj);
    public void error(ServiceError serviceError);
}
