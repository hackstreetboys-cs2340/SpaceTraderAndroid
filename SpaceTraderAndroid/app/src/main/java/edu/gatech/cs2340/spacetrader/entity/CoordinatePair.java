package edu.gatech.cs2340.spacetrader.entity;

import android.util.Pair;

public class CoordinatePair<F, S> extends Pair {
    public final F first;
    public final S second;
    public CoordinatePair() {
        this(null,null);
    }
    public CoordinatePair(F first, S second) {
        super(first, second);
        this.first = first;
        this.second = second;
    }
}
