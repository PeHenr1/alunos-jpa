package br.edu.ifsp.aissa.testes;

// Desenvolvido com ♥ por Pedro Henrique Aissa e Vinicius de Matos Silva

import br.edu.ifsp.aissa.dao.AlunoDao;
import br.edu.ifsp.aissa.modelo.Aluno;
import br.edu.ifsp.aissa.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CadastroDeAluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);

        while (true) {
            System.out.println("\n----- MENU -----");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos");
            System.out.println("6 - Fim");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\n-- CADASTRO DE ALUNO: --\n");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("RA: ");
                    String ra = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nota 1: ");
                    BigDecimal nota1 = scanner.nextBigDecimal();
                    System.out.print("Nota 2: ");
                    BigDecimal nota2 = scanner.nextBigDecimal();
                    System.out.print("Nota 3: ");
                    BigDecimal nota3 = scanner.nextBigDecimal();
                    alunoDao.cadastrar(new Aluno(nome, ra, email, nota1, nota2, nota3));
                    break;
                case 2:
                    System.out.print("\n-- EXCLUIR ALUNO: --\n");
                    System.out.print("ID do aluno a excluir: ");
                    Long idExcluir = scanner.nextLong();
                    alunoDao.excluir(idExcluir);
                    break;
                case 3:
                    System.out.print("\n-- ALTERAR ALUNO: --\n");
                    System.out.print("ID do aluno a alterar: ");
                    Long idAlterar = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo RA: ");
                    String novoRa = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Nova Nota 1: ");
                    BigDecimal novaNota1 = scanner.nextBigDecimal();
                    System.out.print("Nova Nota 2: ");
                    BigDecimal novaNota2 = scanner.nextBigDecimal();
                    System.out.print("Nova Nota 3: ");
                    BigDecimal novaNota3 = scanner.nextBigDecimal();
                    alunoDao.alterar(idAlterar, novoNome, novoRa, novoEmail, novaNota1, novaNota2, novaNota3);
                    break;
                case 4:
                    System.out.print("\n-- BUSCAR ALUNO: --: \n");
                    System.out.print("Nome do aluno: ");
                    String buscaNome = scanner.nextLine();
                    List<Aluno> encontrados = alunoDao.buscarPorNome(buscaNome);
                    encontrados.forEach(a ->
                        System.out.println("\nNome: " + a.getNome() +
                                "\nEmail: " + a.getEmail() +
                                "\nRA: " + a.getRa() +
                                "\nNotas: " + a.getNota1() + " - "  + a.getNota2() + " - "  + a.getNota3()));
                    break;
                case 5:
                    List<Aluno> alunos = alunoDao.listarTodos();
                    System.out.print("\n-- EXIBINDO TODOS OS ALUNOS: --: \n");
                    alunos.forEach(a ->
                            System.out.println("\nNome: " + a.getNome() +
                                                "\nEmail: " + a.getEmail() +
                                                "\nRA: " + a.getRa() +
                                                "\nNotas: " + a.getNota1() + " - "  + a.getNota2() + " - "  + a.getNota3() +
                                                "\nMédia: " + a.calcularMedia() +
                                                "\nSituação: " + a.getSituacao() ));
                    break;
                case 6:
                    em.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

}
