package com.java.learn.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {

    public static void main(String[] args) {
        Subject subject = new Subject();
        Task observer = new Task();
        subject.addObserver(observer);
        subject.notifyObserver("xxxxx");
    }
}

class Subject {
    List<ObserverInterface> observerList = new ArrayList<>();
    
    public void addObserver(ObserverInterface observer) {
        observerList.add(observer);
    }

    public void removeObserver(ObserverInterface observer) {
        observerList.remove(observer);
    }

    public void notifyObserver(Object object) {
        for (ObserverInterface observer : observerList) {
            observer.update(object);
        }
    }
}

interface ObserverInterface {
    void update(Object object);
}

class Task implements ObserverInterface {
    @Override
    public void update(Object object) {
        System.out.println("任务进行。。。"+object);
    }
}