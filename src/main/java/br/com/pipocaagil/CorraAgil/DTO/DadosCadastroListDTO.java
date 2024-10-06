package br.com.pipocaagil.CorraAgil.DTO;


import br.com.pipocaagil.CorraAgil.domain.UsuarioModel;

public record DadosCadastroListDTO(
        Long id,
        String nomecompleto,
        String email) {
    public DadosCadastroListDTO(UsuarioModel usuarioModelList) {
        this(usuarioModelList.getId(), usuarioModelList.getNomecompleto(), usuarioModelList.getLogin());
    }
}
