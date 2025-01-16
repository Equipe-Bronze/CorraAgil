package br.com.pipocaagil.corraagil.corrida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corrida")
public class CorridaController {
    @Autowired
    private CorridaService corridaService;

    @PostMapping("/iniciar")
    public CorridaModel iniciarCorrida() {
        return corridaService.iniciarCorrida();
    }
    @PostMapping("/pausar/{id}")
    public CorridaModel pararCorrida(@PathVariable Long id) {
        return corridaService.pausarCorrida(id);
    }
    @PostMapping("/continuar/{id}")
    public CorridaModel continuarCorrida(@PathVariable Long id) {
        return corridaService.continuarCorrida(id);
    }
    @PostMapping("/finalizar/{id}")
    public CorridaModel finalizarCorrida(@PathVariable Long id) {
        return corridaService.finalizarCorrida(id);
    }


}
