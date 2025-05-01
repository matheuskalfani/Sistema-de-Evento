package controller;

import model.Evento;
import model.Usuario;
import utils.ArquivoUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEvento {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Evento> eventos = ArquivoUtils.lerEventos();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> criarEvento();
                case 3 -> listarEventos();
                case 4 -> confirmarPresenca();
                case 5 -> cancelarPresenca();
                case 6 -> salvarEventos();
                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n===== MENU DO SISTEMA =====");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Criar evento");
        System.out.println("3 - Listar eventos");
        System.out.println("4 - Confirmar presença em evento");
        System.out.println("5 - Cancelar presença");
        System.out.println("6 - Salvar eventos em arquivo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarUsuario() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        usuarios.add(new Usuario(id, nome, email, cidade));
        System.out.println("Usuário cadastrado com sucesso.");
    }

    private void criarEvento() {
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Horário (AAAA-MM-DDTHH:MM): ");
        LocalDateTime horario = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        eventos.add(new Evento(nome, endereco, categoria, horario, descricao));
        System.out.println("Evento criado com sucesso.");
    }

    private void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);
            System.out.println("\n[" + i + "] " + e.getNome());
            System.out.println("Endereço: " + e.getEndereco());
            System.out.println("Categoria: " + e.getCategoria());
            System.out.println("Horário: " + e.getHorario());
            System.out.println("Descrição: " + e.getDescricao());
            System.out.println("Confirmados: " + e.getConfirmados().size());
        }
    }

    private void confirmarPresenca() {
        listarEventos();
        System.out.print("ID do usuário: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        System.out.print("Índice do evento: ");
        int idxEvento = Integer.parseInt(scanner.nextLine());

        if (idxEvento >= 0 && idxEvento < eventos.size()) {
            eventos.get(idxEvento).confirmar(idUsuario);
            System.out.println("Presença confirmada!");
        } else {
            System.out.println("Evento inválido.");
        }
    }

    private void cancelarPresenca() {
        listarEventos();
        System.out.print("ID do usuário: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        System.out.print("Índice do evento: ");
        int idxEvento = Integer.parseInt(scanner.nextLine());

        if (idxEvento >= 0 && idxEvento < eventos.size()) {
            eventos.get(idxEvento).cancelar(idUsuario);
            System.out.println("Presença cancelada!");
        } else {
            System.out.println("Evento inválido.");
        }
    }

    private void salvarEventos() {
        ArquivoUtils.salvarEventos(eventos);
        System.out.println("Eventos salvos com sucesso!");
    }
}