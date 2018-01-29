package com.example.kcrimi.geniususers.presenter;

/**
 * Created by kcrimi on 1/29/18.
 */

public abstract class BasePresenter<T> implements Presenter {

    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}
