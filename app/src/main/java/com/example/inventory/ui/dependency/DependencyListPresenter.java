package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.signup.SignUpContract;
import com.example.inventory.ui.signup.SignUpInteractor;

import java.util.ArrayList;
import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener {

    private DependencyListContract.View view;
    private DependencyListInteractor interactor;

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
        this.interactor = new DependencyListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor =null;
    }

    @Override
    public void load() {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0){
            view.showNoData();
        }else
            view.showData((ArrayList<Dependency>) list);
        view.hideProgress();
    }



    @Override
    public void onDeleteSucces(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }
}
