package com.example.notas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notas.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application) {
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    //El fragment necesita
    public LiveData<List<NotaEntity>> getAllNotas() { return allNotas; }

    // El fragment debera comunicar al viewmodel
    public void insertarNotas(NotaEntity nuevaNotaEntity) {notaRepository.insert(nuevaNotaEntity);}

    public void updateNota(NotaEntity notaActualizarEntity) {
        notaRepository.update(notaActualizarEntity);
    }
}