package rocha.guilherme.jose.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import rocha.guilherme.jose.controller.helper.GerarSenhaHelper;
import rocha.guilherme.jose.model.ModelSenha;
import rocha.guilherme.jose.view.GerarSenhaView;

public class GerarSenhaController {

	private GerarSenhaView gerarSenhaView;
	private GerarSenhaHelper helper;
	
	public GerarSenhaController(GerarSenhaView gerarSenhaView) {
		this.gerarSenhaView = gerarSenhaView;
		this.helper = new GerarSenhaHelper(gerarSenhaView);
	}

	public void gerarSenha() {
		ModelSenha senha = helper.obterModelo();
		
		if(helper.validarQuantLetras()) {
			if(helper.validarQuantNumeros()) {
				if(helper.validarQuantCaractere()) {
					if(helper.validarPalavraPessoal()) {
						if(helper.validarTamanhoDaSenha(senha)) {
							senha.gerarSenha();
							helper.mostrarSenha(senha);
						}else {
							gerarSenhaView.exibeMensagemInformativa("O tamanho da sua senha(Com exceção "
									+ "da sua palavra) não pode ser menor que 8 ou maior que 30.");
						}
					}else {
						gerarSenhaView.exibeMensagemInformativa("A sua palavra não pode está vazia e "
								+ "deve conter apenas letras!");
					}
				}else {
					gerarSenhaView.exibeMensagemInformativa("A quantidade de caracteres deve conter "
							+ "apenas números e ser um inteiro(1, 2, 3, 4...)!");
				}
			}else {
				gerarSenhaView.exibeMensagemInformativa("A quantidade de números deve conter apenas "
						+ "números e ser um inteiro(1, 2, 3, 4...)!");
			}
		}else {
			gerarSenhaView.exibeMensagemInformativa("A quantidade de letras deve conter apenas números "
					+ "e ser um inteiro(1, 2, 3, 4...)!");
		}
		
	}

	public void copiarSenha(String senha) {
		if(!senha.isEmpty()) {
			StringSelection selecao = new StringSelection(senha);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selecao, null);
			helper.personalizarBotaoCopiado();
		}
	}

	public void abrirFecharCampoPalavra() {
		if(gerarSenhaView.getRdbtnSimPalavra().isSelected()) {
			gerarSenhaView.getTextFieldPalavraPessoal().setEditable(true);
		}else {
			gerarSenhaView.getTextFieldPalavraPessoal().setEditable(false);
		}
		
	}
}
