import model.Usuario;
import model.Evento;
import utils.ArquivoUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Usuario usuarioAtual = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Evento> eventos = ArquivoUtils.lerEventos();
        List<Usuario> usuarios = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Sistema de Eventos ---");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Entrar como usuário");
            System.out.println("3 - Criar evento");
            System.out.println("4 - Listar eventos");
            System.out.println("5 - Confirmar presença");
            System.out.println("6 - Cancelar presença");
            System.out.println("7 - Ver eventos confirmados");
            System.out.println("8 - Salvar eventos em arquivo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID do usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email do usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite a cidade do usuário: ");
                    String cidade = scanner.nextLine();

                    Usuario usuario = new Usuario(id, nome, email, cidade);
                    usuarios.add(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                }
                case 2 -> {
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                        break;
                    }
                    System.out.println("Usuários cadastrados:");
                    for (Usuario u : usuarios) {
                        System.out.println(u.getId() + " - " + u.getNome());
                    }
                    System.out.print("Digite o ID do usuário para login: ");
                    int idLogin = scanner.nextInt();
                    scanner.nextLine();

                    usuarioAtual = usuarios.stream()
                            .filter(u -> u.getId() == idLogin)
                            .findFirst()
                            .orElse(null);

                    if (usuarioAtual != null) {
                        System.out.println("Login realizado como " + usuarioAtual.getNome());
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                }
                case 3 -> {
                    if (usuarioAtual == null) {
                        System.out.println("Você precisa estar logado para criar eventos.");
                        break;
                    }
                    System.out.print("Digite o nome do evento: ");
                    String nomeEvento = scanner.nextLine();
                    System.out.print("Digite o endereço do evento: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Digite a categoria do evento: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Digite a data e hora do evento (AAAA-MM-DDTHH:MM): ");
                    String dataHora = scanner.nextLine();
                    LocalDateTime horario = LocalDateTime.parse(dataHora);
                    System.out.print("Digite a descrição do evento: ");
                    String descricao = scanner.nextLine();

                    Evento evento = new Evento(nomeEvento, endereco, categoria, horario, descricao);
                    eventos.add(evento);
                    System.out.println("Evento criado com sucesso!");
                }
                case 4 -> {
                    if (eventos.isEmpty()) {
                        System.out.println("Nenhum evento cadastrado.");
                        break;
                    }
                    System.out.println("\nEventos cadastrados:");
                    for (int i = 0; i < eventos.size(); i++) {
                        Evento evento = eventos.get(i);
                        System.out.println((i + 1) + ". " + evento.getNome() + " - " + evento.getHorario());
                    }
                }
                case 5 -> {
                    if (usuarioAtual == null) {
                        System.out.println("Você precisa estar logado para confirmar presença.");
                        break;
                    }
                    System.out.print("Escolha o número do evento para confirmar presença: ");
                    int indiceEvento = scanner.nextInt() - 1;
                    if (indiceEvento >= 0 && indiceEvento < eventos.size()) {
                        Evento evento = eventos.get(indiceEvento);
                        evento.confirmar(usuarioAtual.getId());
                        System.out.println("Presença confirmada no evento " + evento.getNome());
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                }
                case 6 -> {
                    if (usuarioAtual == null) {
                        System.out.println("Você precisa estar logado para cancelar presença.");
                        break;
                    }
                    System.out.print("Escolha o número do evento para cancelar presença: ");
                    int indiceEvento = scanner.nextInt() - 1;
                    if (indiceEvento >= 0 && indiceEvento < eventos.size()) {
                        Evento evento = eventos.get(indiceEvento);
                        evento.cancelar(usuarioAtual.getId());
                        System.out.println("Presença cancelada no evento " + evento.getNome());
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                }
                case 7 -> {
                    if (usuarioAtual == null) {
                        System.out.println("Você precisa estar logado.");
                        break;
                    }
                    System.out.println("Eventos confirmados por " + usuarioAtual.getNome() + ":");
                    for (Evento evento : eventos) {
                        if (evento.getConfirmados().contains(usuarioAtual.getId())) {
                            System.out.println("- " + evento.getNome() + " em " + evento.getHorario());
                        }
                    }
                }
                case 8 -> {
                    ArquivoUtils.salvarEventos(eventos);
                    System.out.println("Eventos salvos com sucesso!");
                }
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}