package edu.gatech.cs2340.spacetrader.model;

public interface ResultHandler<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}