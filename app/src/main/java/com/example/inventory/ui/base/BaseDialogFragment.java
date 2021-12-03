package com.example.inventory.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.amulyakhare.textdrawable.TextDrawable;

public class BaseDialogFragment extends DialogFragment {
    public static final String REQUEST="requestDialog";
    public static final String KEY_BUNDLE= "result";
    public static final String TITLE= "title";
    public static final String MESSAGE="message";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if(getArguments()!=null){
            //Patron BUILDER, instancia el constructor del cuadro de dialogo.
            AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
            builder.setTitle("Borrar Dependencia");
            builder.setMessage("Seguro que quieres borrar la dependencia");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Registro la respuesta
                    Bundle bundle= new Bundle();
                    bundle.getBoolean(KEY_BUNDLE,true);
                    getActivity().getSupportFragmentManager().setFragmentResult(REQUEST,bundle);
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });
            builder.create();
        }
        return null;
    }
}
