package com.lucasangelo.pedidos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.lucasangelo.pedidos.domain.Categoria;
import com.lucasangelo.pedidos.domain.Cidade;
import com.lucasangelo.pedidos.domain.Cliente;
import com.lucasangelo.pedidos.domain.Endereco;
import com.lucasangelo.pedidos.domain.Estado;
import com.lucasangelo.pedidos.domain.Pagamento;
import com.lucasangelo.pedidos.domain.PagamentoComBoleto;
import com.lucasangelo.pedidos.domain.PagamentoComCartao;
import com.lucasangelo.pedidos.domain.Pedido;
import com.lucasangelo.pedidos.domain.Produto;
import com.lucasangelo.pedidos.domain.enums.EstadoPagamento;
import com.lucasangelo.pedidos.domain.enums.TipoCliente;
import com.lucasangelo.pedidos.repositories.CategoriaRepository;
import com.lucasangelo.pedidos.repositories.CidadeRepository;
import com.lucasangelo.pedidos.repositories.ClienteRepository;
import com.lucasangelo.pedidos.repositories.EnderecoRepository;
import com.lucasangelo.pedidos.repositories.EstadoRepository;
import com.lucasangelo.pedidos.repositories.PagamentoRepository;
import com.lucasangelo.pedidos.repositories.PedidoRepository;
import com.lucasangelo.pedidos.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Criando Categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		// Criando Produtos
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		// Associando as produtos nas categorias (Associação muitos-para-muitos)
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		// Associando as categorias nos produtos (Associação muitos-para-muitos)
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		// Salvando produtos e categorias já associados no banco de dados
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		
		// Criando estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		// Criando cidades já com os estados (Associação um-para-um)
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		// Associando as cidades nos estados (Associação muitos-para-um)
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		// Salvando estados e cidades já associados no banco de dados (Respeitando "o todo" Estado)
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		// Criando o cliente
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		// Adicionando no Set os telefones do Cliente
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		// Criando os endereços relacionando com o cliente e cidade
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		// Associando os endereços do cliente
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		// Salvando os endereços e cliente no banco de dados (Respeitando "o todo" Cliente)
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));


		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);

		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

	}

}
