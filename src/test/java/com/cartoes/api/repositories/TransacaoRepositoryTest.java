package com.cartoes.api.repositories;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cartoes.api.entities.Cartao;
import com.cartoes.api.entities.Transacao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class TransacaoRepositoryTest {

	@Autowired
	private TransacaoRepository transacaoRepository;  
	
	private Transacao transacaoTeste;
	
	private Cartao cartaoTeste;
	
	private void CriarTransacaoTestes() throws ParseException {
		
		transacaoTeste = new Transacao();
		
		transacaoTeste.setDataTransacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		transacaoTeste.setCnpj("05887098082");
		transacaoTeste.setQtdParcelas(3);
		transacaoTeste.setValor(120.00);
		transacaoTeste.setJuros(10.00);
		}
	
	@Before
	public void setUp() throws Exception {
		
		CriarTransacaoTestes();
		transacaoRepository.save(transacaoTeste);
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		transacaoRepository.deleteAll();
		
	}
	
	@Test
	public void testFindByCartaoId() {	
		
		List<Transacao> transacoes = transacaoRepository.findByCartaoId(transacaoTeste.getCartao().getId());
		assertEquals(cartaoTeste.getId(), transacoes.get(0));
		
	}
	
	/*@Test
	public void testFindByNumero() {
		
	
	}*/
	
}
