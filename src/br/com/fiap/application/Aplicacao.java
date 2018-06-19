package br.com.fiap.application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fiap.dao.JdbcCursoCapacitacaoDAO;
import br.com.fiap.dao.JdbcEscolaDAO;
import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;

public class Aplicacao 
{
	
	public static void main(String[] args) {
		
		String nomeFantasia = "TESTE";
		String razao = "TESTE";
		String cnpj = "45789987000149";
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
		ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
		JdbcEscolaDAO dao = (JdbcEscolaDAO) context.getBean("jdbcEscolaDao");

		System.out.println("3");
		try {
			dao.incluirEscola(escola);
			System.out.println("Escola incluída com sucesso!");
			
		} catch (Exception e) {
			System.out.println("Erro: "+e.getMessage());
			
		}
		

		System.out.println("4");
		List<Escola> escolas = new ArrayList<>();
		try {
			escolas = dao.listarEscolas();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("5");
		//INSERIR ESCOLA
		
		/*String nome = JOptionPane.showInputDialog("Informe o nome do curso: ");
		String duracao = JOptionPane.showInputDialog("Informe a duração do curso: ");
		String area = JOptionPane.showInputDialog("Informe a área: ");
		String descricao = JOptionPane.showInputDialog("Informe a descrição: ");
		String custo = JOptionPane.showInputDialog("Informe o custo: ");
		*/
		String nome = "NOME";
		String duracao = "1";
		String area = "AREA";
		String descricao = "DESCRIÇÃO";
		String custo = "1000";
		

		System.out.println("6");
		escola = (Escola) JOptionPane.showInputDialog(null, "Selecione a escola", "Escolas",
				JOptionPane.INFORMATION_MESSAGE, null, escolas.toArray(), null);
		
		CursoCapacitacao curso = new CursoCapacitacao();
		curso.setNome(nome);
		curso.setDuracao(Integer.parseInt(duracao));
		curso.setArea(area);
		curso.setDescricao(descricao);
		curso.setCusto(Float.parseFloat(custo));
		curso.setEscola(escola);

		System.out.println("7");
		try {
			JdbcCursoCapacitacaoDAO dao2 = (JdbcCursoCapacitacaoDAO) context.getBean("jdbcCursoCapacitacaoDao");
			dao2.incluir(curso);
			System.out.println("Curso incluído com sucesso!");
			JOptionPane.showMessageDialog(null, "Curso incluído com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		

	}
}
