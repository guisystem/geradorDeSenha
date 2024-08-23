package rocha.guilherme.jose.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocha.guilherme.jose.view.GerarSenhaView;

class GerarSenhaControllerTeste {

	private GerarSenhaView gerarSenhaView;
	private GerarSenhaController controller;
	
	@BeforeEach
	void iniciar() {
		gerarSenhaView = new GerarSenhaView();
		controller = new GerarSenhaController(gerarSenhaView);
	}
	
	@Test
	void testarGerarSenha() {
		gerarSenhaView.getRdbtnSimPalavra().setSelected(true);
		gerarSenhaView.getTextFieldQuantLetras().setText("3");
		gerarSenhaView.getTextFieldQuantNumeros().setText("3");
		gerarSenhaView.getTextFieldQuantCaractereEspecial().setText("3");
		gerarSenhaView.getTextFieldPalavraPessoal().setText("Guilherme");
		controller.gerarSenha();
		
		assertFalse(gerarSenhaView.getTextFieldSenhaGerada().getText().isEmpty());
	}
	
	@Test
	void testarAbrirFecharCampo() {
		gerarSenhaView.getRdbtnSimPalavra().setSelected(true);
		controller.abrirFecharCampoPalavra();
		assertTrue(gerarSenhaView.getTextFieldPalavraPessoal().isEditable());

		gerarSenhaView.getRdbtnNaoPalavra().setSelected(true);
		controller.abrirFecharCampoPalavra();
		assertFalse(gerarSenhaView.getTextFieldPalavraPessoal().isEditable());
	}

}
