package br.com.pipocaagil.corraagil.corrida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CorridaService {
    @Autowired
   private CorridaRepository corridaRepository;

    public CorridaModel iniciarCorrida(){
        CorridaModel corridaModel = new CorridaModel();
        corridaModel.setInicio(LocalDateTime.now());
        corridaModel.setPausada(false);
        return corridaRepository.save(corridaModel);
    }
    public CorridaModel pausarCorrida(Long id){
        CorridaModel corridaModel = corridaRepository.findById(id).orElseThrow();
        corridaModel.setPausada(true);
        return corridaRepository.save(corridaModel);
    }
    public CorridaModel continuarCorrida(Long id){
        CorridaModel corridaModel = corridaRepository.findById(id).orElseThrow();
        corridaModel.setPausada(false);
        return corridaRepository.save(corridaModel);
    }
    public CorridaModel finalizarCorrida(Long id){
        CorridaModel corridaModel = corridaRepository.findById(id).orElseThrow();
        corridaModel.setFim(LocalDateTime.now());
        return corridaRepository.save(corridaModel);
    }
}
