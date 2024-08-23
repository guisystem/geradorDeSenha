package rocha.guilherme.jose.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelSenhaTeste {

	private ModelSenha senha;

	@BeforeEach
	void iniciar() {
		senha = new ModelSenha(1, 2, 3, "Só Maiúsculas", "Guilherme");
	}
	
	@Test
	void testarSetQuantLetras() {
		senha.setQuantLetras(5);
		assertEquals(5, senha.getQuantLetras());
	}
	
	@Test
	void testarSetQuantNumeros() {
		senha.setQuantNumeros(1);
		assertEquals(1, senha.getQuantNumeros());
	}
	
	@Test
	void testarSetQuantCaractere() {
		senha.setQuantCaracteres(2);
		assertEquals(2, senha.getQuantCaracteres());
	}
	
	@Test
	void testarSetFormaDaLetra() {
		senha.setFormaDaLetra("Maiúsculas e Minúsculas");
		assertEquals("Maiúsculas e Minúsculas", senha.getFormaDaLetra());
	}
	
	@Test
	void testarSetPalavraPessoal() {
		senha.setPalavraPessoal("José");
		assertEquals("José", senha.getPalavraPessoal());
	}
	
	@Test
	void testarSetSenhaGerada() {
		senha.setSenhaGerada("José1AZ=bt?T");
		assertEquals("José1AZ=bt?T", senha.getSenhaGerada());
	}
	
	@Test
	void testarGerarSenhaComPalavra() {
		senha.gerarSenha();
		assertTrue(senha.getSenhaGerada().contains("Guilherme"));
	}
	
	@Test
	void testarGerarSenhaMaiuscula() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		compararSenha(letras);
	}
	
	@Test
	void testarGerarSenhaMinuscula() {
		senha.setFormaDaLetra("Só Minúsculas");
		String letras = "abcdefghijklmnopqrstuvwxyz";
		compararSenha(letras);
	}
	
	@Test
	void testarGerarSenhaMaiusculaMinuscula() {
		senha.setFormaDaLetra("Maiúsculas e Minúsculas");
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		compararSenha(letras);
	}
	
	@Test
	private void compararSenha(String letras) {
		senha.setPalavraPessoal("");
		senha.gerarSenha();
		int tamanho = senha.getSenhaGerada().length();
		
		String numeros  = "1234567890";
		String caracteres = "!@#$%&-_=?";
		
		int quantLetras = 0;
		int quantNumeros = 0;
		int quantCaractere = 0;
		
		for(int i = 0; i < tamanho; i++) {
			char c = senha.getSenhaGerada().charAt(i);
			if(letras.contains(c + "")) {
				quantLetras += 1;
			}
			
			if(numeros.contains(c + "")) {
				quantNumeros += 1;
			}
			
			if(caracteres.contains(c + "")) {
				quantCaractere += 1;
			}
		}
		
		assertEquals(6, tamanho);
		assertEquals(1, quantLetras);
		assertEquals(2, quantNumeros);
		assertEquals(3, quantCaractere);
	}

}
