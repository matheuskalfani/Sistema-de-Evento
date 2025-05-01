package utils;

import model.Evento;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {
    private static final String NOME_ARQUIVO = "events.data";

    public static void salvarEventos(List<Evento> eventos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Evento e : eventos) {
                writer.write(e.getNome() + ";" + e.getEndereco() + ";" + e.getCategoria()
                        + ";" + e.getHorario() + ";" + e.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public static List<Evento> lerEventos() {
        List<Evento> eventos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String nome = partes[0];
                    String endereco = partes[1];
                    String categoria = partes[2];
                    LocalDateTime horario = LocalDateTime.parse(partes[3]);
                    String descricao = partes[4];
                    eventos.add(new Evento(nome, endereco, categoria, horario, descricao));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao ler eventos: " + e.getMessage());
        }
        return eventos;
    }
}
