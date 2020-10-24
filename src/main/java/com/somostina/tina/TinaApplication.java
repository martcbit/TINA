package com.somostina.tina;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.somostina.tina.domain.Cidade;
import com.somostina.tina.domain.Cliente;
import com.somostina.tina.domain.Endereco;
import com.somostina.tina.domain.Estado;
import com.somostina.tina.domain.Procedimento;
import com.somostina.tina.domain.Servico;
import com.somostina.tina.domain.SexoCliente;
import com.somostina.tina.repositories.CidadeRepository;
import com.somostina.tina.repositories.ClienteRepository;
import com.somostina.tina.repositories.EnderecoRepository;
import com.somostina.tina.repositories.EstadoRepository;
import com.somostina.tina.repositories.ProcedimentoRepository;
import com.somostina.tina.repositories.ServicoRepository;

@SpringBootApplication
public class TinaApplication implements CommandLineRunner {
	
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TinaApplication.class, args);		
		}
	
		public void run(String... args) throws Exception {
			
			Servico serv1 = new Servico(null, "Cabelo");
			Servico serv2 = new Servico(null, "Maquiagem");

			Procedimento p1 = new Procedimento(null, "Corte Feminino", 50.00,serv1);
			Procedimento p2 = new Procedimento(null, "Corte Masculino", 30.00,serv1);
			Procedimento p3 = new Procedimento(null, "Hidratação", 150.00,serv1);
			Procedimento p4 = new Procedimento(null, "Maquiagem Artistica", 200.00,serv2);
			Procedimento p5 = new Procedimento(null, "Maquiagem + Cílios", 150.00,serv2);		

			servicoRepository.saveAll(Arrays.asList(serv1, serv2));
			procedimentoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
			
			Estado est1 = new Estado(null, "Piauí");
			Estado est2 = new Estado(null, "Maranhão");

			Cidade c1 = new Cidade(null, "Teresina", est1);
			Cidade c2 = new Cidade(null, "Floriano", est1);
			Cidade c3 = new Cidade(null, "São Luís", est2);

			est1.getCidades().addAll(Arrays.asList(c1));
			est2.getCidades().addAll(Arrays.asList(c2, c3));

			estadoRepository.saveAll(Arrays.asList(est1, est2));
			cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
			
			Cliente cli1 = new Cliente(null, "Eduardo Henrique", "eduhenriquesrs@gmail.com","(89) 99921-7065" ,"36378912377", SexoCliente.MASCULINO);
 
			Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
			Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

			cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

			clienteRepository.saveAll(Arrays.asList(cli1));
			enderecoRepository.saveAll(Arrays.asList(e1, e2));
		}

}
