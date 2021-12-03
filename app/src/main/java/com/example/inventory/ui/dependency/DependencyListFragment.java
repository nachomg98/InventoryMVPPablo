package com.example.inventory.ui.dependency;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentAddInventoryBinding;
import com.example.inventory.databinding.FragmentDependencyListBinding;
import com.example.inventory.ui.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class DependencyListFragment extends Fragment implements DependencyListContract.View, DependencyAdapter.OnManageDependencyListener {

   private FragmentDependencyListBinding binding;
   private DependencyAdapter adapter;
   private DependencyListContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.- Se debe indicar a la activity que se quiere modificar el menu
        setHasOptionsMenu(true);
        //2.- Se inicializa el presenter
        presenter = new DependencyListPresenter(this);


    }
    //2.- Sobreescribir el metodo onCreateOptionsMenu para añadir el menu del fragment
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmentlist_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //3.- Implementar las acciones especificas (item) del menu del fragment
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_dependencis:
                Toast.makeText(getActivity(),"Se ha pulsado ordenar dependencias",Toast.LENGTH_SHORT).show();
                return true;

            default:
                //Si los fragment modifican el menu de la activity se devuelve false
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDependencyListBinding.inflate(inflater, container, false);
        Log.d(TAG, "DependencyListFragment -> onCreateView()");
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();

    }

    @Override
    public void onStart() {
        super.onStart();
        //Pido y solicito los datos
        presenter.load();
    }

    /**
     * Este metodo inicializa el componente recycler view
     */
    private void initRvDependency() {
        //1.- Sera inicializar dicho adapter
        adapter = new DependencyAdapter(new ArrayList<>(),this);
        //2.- OBLIGATORIOMENTE se debe indicae que diseño (layout) tendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        //3.- Asgino el layout al recyclerView
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4.- Asigno a la vista sus datos (modelo)
        binding.rvDependency.setAdapter(adapter);
    }


    //region Metodos que vienen de la interfaz del adapter

    @Override
    public void onEditDependency(Dependency dependency) {
        Toast.makeText(getActivity(), "Se edita la dependencia: "+ dependency.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        //Toast.makeText(getActivity(), "Se eliminar la dependencia: "+ dependency.getName(), Toast.LENGTH_SHORT).show();
        Bundle bundle= new Bundle();
        bundle.putString(BaseDialogFragment.TITLE,"Eliminar Dependencia");
        bundle.putString(BaseDialogFragment.MESSAGE,"¿Deseas eliminar la dependencia "+ dependency.getName()+"?");
        //Registrar el listener del BaseDialog. Este codigo sirve para comunicar dos fragments (padre-hijo)
        // en el cual el padre necesita un resultado del hijo.
        //MUY IMPORTANTE: si se usa la libreria de soporte se debe llamar a GETSUPPORTFRAGMENTMANAGER.
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST,
                this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        //Si la respuesta del usuario es true, se llama al presentador.
                        if(bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE))
                            presenter.delete(dependency);
                    }
                });
        NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_baseDialogFragment,bundle);

    }

    //endregion

    //region Metodos que vienen de la respuesta del repositorio

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {

    }

    @Override
    public void onDeleteSucces(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }

    //endregion

    //region Metodos que vienen del requisito que  se debe mostrar una barra de progreso
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    //endregion

    //region Metodos del contrato VISTA-PRESENTER

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(ArrayList<Dependency> list) {
        adapter.update(list);
    }

    //endregion
}