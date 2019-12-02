package com.example.medicanet.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.medicanet.R;
import com.example.medicanet.base.BaseFragment;
import com.example.medicanet.util.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsFragment extends BaseFragment {



    @Inject
    ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.dialog_doc_agregar_medicamento_consulta;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    private void displayRepo() {
        detailsViewModel.getSelectedRepo().observe(this, repo -> {
            if (repo != null) {
            }
        });
    }
}
