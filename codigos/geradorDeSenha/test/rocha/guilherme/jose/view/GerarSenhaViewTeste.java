package rocha.guilherme.jose.view;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GerarSenhaViewTeste {

	private GerarSenhaView gerarSenhaView;
	
	@BeforeEach
	void iniciar() {
		gerarSenhaView = new GerarSenhaView();
	}

	@Test
	void test() {
		GerarSenhaView.main(null);
		gerarSenhaView.setResizable(false);
		gerarSenhaView.setLocationRelativeTo(null);
		gerarSenhaView.setVisible(true);
		
		assertTrue(gerarSenhaView.isVisible());
	}

}
