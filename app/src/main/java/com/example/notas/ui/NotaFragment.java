package com.example.notas.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notas.NuevaNotaDialogFragment;
import com.example.notas.NuevaNotaDialogViewModel;
import com.example.notas.R;
import com.example.notas.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NotaEntity> notaList;
    private MyNotaRecyclerViewAdapter adapterNotas;
    private NuevaNotaDialogViewModel notaViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //indicamos que hay unmenu propio
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if(view.getId() == R.id.list){
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numerocolumnas = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numerocolumnas, StaggeredGridLayoutManager.VERTICAL));

            }
            notaList = new ArrayList<>();


            adapterNotas = new MyNotaRecyclerViewAdapter(notaList, getActivity());
            recyclerView.setAdapter(adapterNotas);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        notaViewModel = new ViewModelProvider(getActivity())
                .get(NuevaNotaDialogViewModel.class);
        notaViewModel.getAllNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(List<NotaEntity> notaEntities) {
                adapterNotas.setNuevasNotas(notaEntities);

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_nota_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_nota:
                mostrarDialogoNuevaNota();
                return true;
            default:
                return super.onOptionsItemSelected(item);

    }
    }

    private void mostrarDialogoNuevaNota() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NuevaNotaDialogFragment dialogoNuevaNota = new NuevaNotaDialogFragment();
        dialogoNuevaNota.show(fm, "NuevaNotaDialogFragment");
    }
}


