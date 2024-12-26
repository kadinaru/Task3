package com.example.task7;

public interface Subject {
    void notifyAllObservers();
    void attach(IObserver obs);
    void detach(IObserver obs);
}
