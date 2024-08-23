package rocha.guilherme.jose.controller.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocha.guilherme.jose.model.ModelSenha;
import rocha.guilherme.jose.view.GerarSenhaView;

class GerarSenhaHelperTeste {

	private GerarSenhaView gerarSenhaView;
	private GerarSenhaHelper helper;
	
	@BeforeEach
	void iniciar() {
		gerarSenhaView = new GerarSenhaView();
		helper = new GerarSenhaHelper(gerarSenhaView);
		
		gerarSenhaView.getTextFieldQuantLetras().setText("1");
		gerarSenhaView.getTextFieldQuantNumeros().setText("1");
		gerarSenhaView.getTextFieldQuantCaractereEspecial().setText("1");
		gerarSenhaView.getRdbtnSimPalavra().setSelected(true);
		gerarSenhaView.getTextFieldPalavraPessoal().setText("Guilherme");
	}
	
	@Test
	void testarObterModelo() {
		ModelSenha senha = helper.obterModelo();
		assertEquals(1, senha.getQuantNumeros());
		assertEquals("Guilherme", senha.getPalavraPessoal());
	}
	
	@Test
	void testarValidarQuantLetrasTrue() {
		gerarSenhaView.getTextFieldQuantLetras().setText("3");
		assertTrue(helper.validarQuantLetras());

	}
	
	@Test
	void testarValidarQuantLetrasFalse() {
		gerarSenhaView.getTextFieldQuantLetras().setText("t3");
		assertFalse(helper.validarQuantLetras());
	}
	
	@Test
	void testarValidarQuantNumerosTrue() {
		gerarSenhaView.getTextFieldQuantNumeros().setText("3");
		assertTrue(helper.validarQuantNumeros());
	}
	
	@Test
	void testarValidarQuantNumerosFalse() {
		gerarSenhaView.getTextFieldQuantNumeros().setText("t3");
		assertFalse(helper.validarQuantNumeros());
	}
	
	@Test
	void testarValidarQuantCaractereTrue() {
		gerarSenhaView.getTextFieldQuantCaractereEspecial().setText("3");
		assertTrue(helper.validarQuantCaractere());
	}
	
	@Test
	void testarValidarQuantCaractereFalse() {
		gerarSenhaView.getTextFieldQuantCaractereEspecial().setText("t3");
		assertFalse(helper.validarQuantCaractere());
	}
	
	@Test
	void testarValidarPalavraPessoalTrueNull() {
		gerarSenhaView.getRdbtnNaoPalavra().setSelected(true);
		assertTrue(helper.validarPalavraPessoal());
	}
	
	@Test
	void testarValidarPalavraPessoalTrue() {
		gerarSenhaView.getTextFieldPalavraPessoal().setText("Gui");
		assertTrue(helper.validarPalavraPessoal());
	}
	
	@Test
	void testarValidarPalavraPessoalFalse() {
		gerarSenhaView.getTextFieldPalavraPessoal().setText("Gui12-");
		assertFalse(helper.validarPalavraPessoal());
	}
	
	@Test
	void testarValidarTamanhoDaSenhaTrue() {
		ModelSenha senha = new ModelSenha(3, 3, 13, "Só Maiúsculas", null);
		assertTrue(helper.validarTamanhoDaSenha(senha));
	}
	
	@Test
	void testarValidarTamanhoDaSenhaFalseMenor() {
		ModelSenha senha = new ModelSenha(3, 3, 1, "Só Maiúsculas", null);
		assertFalse(helper.validarTamanhoDaSenha(senha));
	}
	
	@Test
	void testarValidarTamanhoDaSenhaFalseMaior() {
		ModelSenha senha = new ModelSenha(3, 23, 13, "Só Maiúsculas", null);
		assertFalse(helper.validarTamanhoDaSenha(senha));
	}
	
	@Test
	void testarMostrarSenha() {
		ModelSenha senha = new ModelSenha(3, 3, 1, "Só Maiúsculas", null);
		senha.setSenhaGerada("AQ54?0B");
		helper.mostrarSenha(senha);
		assertEquals("AQ54?0B", gerarSenhaView.getTextFieldSenhaGerada().getText());
	}
	
	@Test
	void testarPersonalizarBotaoCopiado() {
		helper.personalizarBotaoCopiado();
		assertEquals("Copiado", gerarSenhaView.getBtnCopiarSenha().getText());
	}

}
