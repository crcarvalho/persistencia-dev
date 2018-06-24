package br.com.fiap.helper;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.fiap.entity.CursoAluno;

public class CursoAlunoHelper {
	
	
	private EntityManager em;
	
	public CursoAlunoHelper(EntityManager em) {
		this.em = em;
	}
	
	//Salvar o registro de CursoAluno na base de dados 
	public String salvar(CursoAluno cursoAluno){
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.persist(cursoAluno);
			em.getTransaction().commit();
			msgRetorno = "Curso aluno foi salvo com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
		
	//Retorna uma lista de CursoAluno
	public List<CursoAluno> listarCursoAluno(){
		TypedQuery<CursoAluno> query = em.createQuery("SELECT c FROM CursoAluno c", CursoAluno.class);
		return query.getResultList();
	}
	
	//Exscluir CursoAluno da base de dados
	public String excluir(CursoAluno curso) {
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.remove(curso);
			em.getTransaction().commit();
			msgRetorno = "Curso alunno excluído com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	public CursoAluno buscarCursoAluno( int idCursoAluno ) {
		try {
			TypedQuery<CursoAluno> query = em.createQuery("SELECT c FROM CursoAluno c where c.Id =: idCursoAluno", CursoAluno.class);
			query.setParameter("idCursoAluno", idCursoAluno);
			return query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("Não foi possível localizar o registro com id: "+idCursoAluno);
			return null;
		}
	}
	
}
