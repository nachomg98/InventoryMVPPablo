package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.dependency.DependencyListContract;

import java.util.ArrayList;

public class DependencyRepository implements DependencyListContract.Repository {

    private OnRepositoryListCallback callback;
    private static DependencyRepository instance;
    private ArrayList<Dependency> list;


    private DependencyRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new Dependency("Aula de 1 CFGS","1CFGS",null,null));
        list.add(new Dependency("Aula de 1 CFGM","1CFGM",null,null));
        list.add(new Dependency("Aula de 2 CFGS","2CFGS",null,null));
        list.add(new Dependency("Aula de 2 CFGM","2CFGM",null,null));
        list.add(new Dependency("BigData","BIG",null,null));
    }

    public static  DependencyRepository getInstance(OnRepositoryListCallback callback){
        if (instance == null){
            instance = new DependencyRepository();
        }
        instance.callback = callback;
        return instance;
    }

    @Override
    public void getList() {
        callback.onSuccess(list);
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }
}
