package br.com.pipocaagil.CorraAgil.Controller;

import br.com.pipocaagil.CorraAgil.DTO.CadastroDto;
import br.com.pipocaagil.CorraAgil.Model.CadastroModel;
import br.com.pipocaagil.CorraAgil.Repository.CadastroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastroController {
    @Autowired
    CadastroRepository cadastroRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroModel> saveCadastro(@RequestBody @Valid CadastroDto cadastroDto){
        var CadastroModel = new CadastroModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroRepository.save(CadastroModel));
    }
}
