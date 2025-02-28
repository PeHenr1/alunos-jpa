package br.edu.ifsp.aissa.dao;

import br.edu.ifsp.aissa.modelo.Aluno;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        System.out.println("\nAluno cadastrado com sucesso!");
    }

    public void excluir(Long id) {
        Aluno aluno = em.find(Aluno.class, id);
        if (aluno != null) {
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
            System.out.print("\nAluno removido com sucesso!");
        }
        else {
            System.out.println("\nAluno não encontrado!");
        }
    }

    public void alterar(Long id, String novoNome, String novoRa, String novoEmail, BigDecimal novaNota1, BigDecimal novaNota2, BigDecimal novaNota3) {
        Aluno aluno = em.find(Aluno.class, id);
        if (aluno != null) {
            em.getTransaction().begin();
            aluno.setNome(novoNome);
            aluno.setRa(novoRa);
            aluno.setEmail(novoEmail);
            aluno.setNota1(novaNota1);
            aluno.setNota2(novaNota2);
            aluno.setNota3(novaNota3);
            em.getTransaction().commit();
            System.out.print("\nAluno alterado com sucesso!");
        }
        else {
            System.out.println("\nAluno não encontrado!");
        }
    }

    public List<Aluno> buscarPorNome(String nome) {
        List<Aluno> alunos = em.createQuery("SELECT a FROM Aluno a WHERE a.nome LIKE :nome", Aluno.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();

        if (alunos.isEmpty()) {
            System.out.println("\nAluno não encontrado!");
        }

        return alunos;
    }

    public List<Aluno> listarTodos() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }
}
