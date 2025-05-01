package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Integer> idsUsuariosConfirmados = new ArrayList<>();

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public List<Integer> getConfirmados() { return idsUsuariosConfirmados; }

    public void confirmar(int idUsuario) {
        if (!idsUsuariosConfirmados.contains(idUsuario)) {
            idsUsuariosConfirmados.add(idUsuario);
        }
    }

    public void cancelar(int idUsuario) {
        idsUsuariosConfirmados.remove((Integer) idUsuario);
    }
}
