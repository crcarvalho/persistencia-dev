package br.com.fiap.application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fiap.dao.JdbcCursoCapacitacaoDAO;
import br.com.fiap.dao.JdbcEscolaDAO;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.CursoAluno;
import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;
import br.com.fiap.helper.AlunoHelper;
import br.com.fiap.helper.CursoAlunoHelper;

public class Aplicacao {

	public static void main(String[] args) {
		
		//incluirEscola();
		//incluirCurso();
		//incluirEscolaCurso();
		incluirAluno();
	}
	
	private static void incluirCurso() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
			List<Escola> escolas = ((JdbcEscolaDAO) context.getBean("jdbcEscolaDao")).listarEscolas();
			
			
			//Escola escola = (Escola) JOptionPane.showInputDialog(null, "Selecione a escola", "Escolas",
				//	JOptionPane.INFORMATION_MESSAGE, null, escolas.toArray(), null);
			System.out.println("1");
			CursoCapacitacao curso = new CursoCapacitacao();
			curso.setDescricao("TESTE");
			curso.setNome("TESTE");
			curso.setArea("AREA");
			curso.setCusto(10000);
			curso.setDuracao(2);
			curso.setEscola(escolas.get(0));
			System.out.println("2");
			
			context = new ClassPathXmlApplicationContext("beanJdbc.xml");
			System.out.println("3");
			
			((JdbcCursoCapacitacaoDAO) context.getBean("jdbcCursoCapacitacaoDao")).incluir(curso);
			System.out.println("REGISTRO INCLUIDO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void incluirEscolaCurso() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
			// INSERIR ESCOLA
			String nomeFantasia = "TESTE";
			String razao = "TESTE";
			String cnpj = "45789987000188";
			String dataFundacao = "10/05/2002";
			String endereco = "osasco";
	
			System.out.println("1");
			
			Escola escola = new Escola();
			escola.setCnpj(cnpj);
			escola.setNomeFantasia(nomeFantasia);
			escola.setRazaoSocial(razao);
			escola.setDataString(dataFundacao);
			escola.setEndereco(endereco);
			
			System.out.println("2");

			String nome = "NOME";
			String duracao = "1";
			String area = "AREA";
			String descricao = "DESCRIÇÃO";
			String custo = "1000";
			
			CursoCapacitacao curso = new CursoCapacitacao();
			curso.setNome(nome);
			curso.setDuracao(Integer.parseInt(duracao));
			curso.setArea(area);
			curso.setDescricao(descricao);
			curso.setCusto(Float.parseFloat(custo));
			curso.setEscola(escola);
			
			escola.getCursos().add( curso );
			System.out.println("3");
			
			((JdbcEscolaDAO) context.getBean("jdbcEscolaDao")).incluirEscola(escola);
			System.out.println("Escola incluída com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public static void incluirEscola() {
		
		try {
			// INSERIR ESCOLA
			ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
			JdbcEscolaDAO dao = (JdbcEscolaDAO) context.getBean("jdbcEscolaDao");
			
			Escola escola = new Escola();
			
			escola.setCnpj("789");
			escola.setRazaoSocial("TESTE");
			dao.incluirEscola(escola);
			System.out.println("Escola incluída com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		/*try {
			// INSERIR ESCOLA
			String nomeFantasia = JOptionPane.showInputDialog("Informe o nome da escola: ");
			String razao = JOptionPane.showInputDialog("Informe a razão social da escola: ");
			String cnpj = JOptionPane.showInputDialog("Informe o número do CNPJ: ");
			String dataFundacao = JOptionPane.showInputDialog("Informe a data de fundação (dd/MM/YYYY): ");
			String endereco = JOptionPane.showInputDialog("Informe o endereço da escola: ");
			
			Escola escola = new Escola();
			escola.setCnpj(cnpj);
			escola.setNomeFantasia(nomeFantasia);
			escola.setRazaoSocial(razao);
			escola.setDataString(dataFundacao);
			escola.setEndereco(endereco);
	
			ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
		
			((JdbcEscolaDAO) context.getBean("jdbcEscolaDao")).incluirEscola(escola);
			System.out.println("Escola incluída com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}*/
	}
	
	public static void incluirAluno() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_PROJECT");
		EntityManager em = emf.createEntityManager();
		AlunoHelper helper = new AlunoHelper(em);
		
		Aluno aluno = new Aluno();
		aluno.setNome("CARLOS");
		aluno.setCelular("11972847759");
		aluno.setTelefone("1141361814");
		aluno.setDataString("07/10/1985");
		aluno.setEmail("carlos@email.com");
		aluno.setRg("401097511");
		aluno.setCpf("34265901824");
		aluno.setSexo("M");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
		List<CursoCapacitacao> cursos = new ArrayList<>();
		try {
			cursos = ((JdbcCursoCapacitacaoDAO) context.getBean("jdbcCursoCapacitacaoDao")).listaCursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CursoAluno cursoAluno = new CursoAluno();
		cursoAluno.setAluno(aluno);
		cursoAluno.setCurso(cursos.get(0));
		cursoAluno.setDataInicioString("24/06/2018");
		
		aluno.getCursosAluno().add(cursoAluno);
	}
}
