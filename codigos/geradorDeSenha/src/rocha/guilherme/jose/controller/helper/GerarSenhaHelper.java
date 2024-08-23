package rocha.guilherme.jose.controller.helper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.*;

import rocha.guilherme.jose.model.ModelSenha;
import rocha.guilherme.jose.view.GerarSenhaView;

public class GerarSenhaHelper {

	private GerarSenhaView gerarSenhaView;

	public GerarSenhaHelper(GerarSenhaView gerarSenhaView) {
		this.gerarSenhaView = gerarSenhaView;
	}

	public ModelSenha obterModelo() {
		String quantLetras = gerarSenhaView.getTextFieldQuantLetras().getText();
		String quantNumeros = gerarSenhaView.getTextFieldQuantNumeros().getText();
		String quantCaracteres = gerarSenhaView.getTextFieldQuantCaractereEspecial().getText();
		String formaLetra = obterFormaLetra();
		String palavraPessoal = obterPalavraPessoal();
		
		int quantLetrasConvertida = converterQuantidade(quantLetras);
		int quantNumeroConvertida = converterQuantidade(quantNumeros);
		int quantCaractereConvertida = converterQuantidade(quantCaracteres);

		ModelSenha senhaTela = new ModelSenha(quantLetrasConvertida, quantNumeroConvertida, quantCaractereConvertida, formaLetra, palavraPessoal);
		
		return senhaTela;
	}

	private String obterFormaLetra() {
	    ButtonModel formaSelecionada = gerarSenhaView.getFormaDaLetra().getSelection();
	    
        for (Enumeration<AbstractButton> opcoes = gerarSenhaView.getFormaDaLetra().getElements(); opcoes.hasMoreElements();) {
            AbstractButton botao = opcoes.nextElement();
            if (botao.getModel() == formaSelecionada) {
                return botao.getText();
            }
        }
        
		return null;
	}
	
	private String obterPalavraPessoal() {
		ButtonModel palavraPessoal = gerarSenhaView.getPalavraPessoal().getSelection();
	    
        for (Enumeration<AbstractButton> opcoes = gerarSenhaView.getPalavraPessoal().getElements(); opcoes.hasMoreElements();) {
            AbstractButton botao = opcoes.nextElement();
            if(botao.getModel() == palavraPessoal) {
            	if (botao.getText().equals("Sim")) {
            		return gerarSenhaView.getTextFieldPalavraPessoal().getText();
            	}
            }
        }
        
		return null;
	}
	
	private int converterQuantidade(String quantidadeTela) {
		int quantidade = 0;
		
		if(quantidadeTela.trim().isEmpty()) {
			return quantidade;
		}
		
		try {
			quantidade = Integer.parseInt(quantidadeTela);
		} catch (NumberFormatException e) {
			return -1;
		}
		
		return quantidade;
	}

	public boolean validarQuantLetras() {
		if(converterQuantidade(gerarSenhaView.getTextFieldQuantLetras().getText()) == -1) {
			return false;
		}
		
		return true;
	}

	public boolean validarQuantNumeros() {
		if(converterQuantidade(gerarSenhaView.getTextFieldQuantNumeros().getText()) == -1) {
			return false;
		}
		
		return true;
	}

	public boolean validarQuantCaractere() {
		if(converterQuantidade(gerarSenhaView.getTextFieldQuantCaractereEspecial().getText()) == -1) {
			return false;
		}
		
		return true;
	}

	public boolean validarPalavraPessoal() {
		if(obterPalavraPessoal() == null){
			return true;
		}
		
		String palavraPessoal = gerarSenhaView.getTextFieldPalavraPessoal().getText();
		
		if(!palavraPessoal.matches("[\\p{L} ]+")) {
			return false;			
		}
		
		return true;
	}

	public boolean validarTamanhoDaSenha(ModelSenha senhaTela) {
		int tamanhoSenha = senhaTela.getQuantLetras() + senhaTela.getQuantNumeros() + senhaTela.getQuantCaracteres();
		
		if(tamanhoSenha < 8 || tamanhoSenha > 30) {
			return false;
		}

		return true;
	}

	public void mostrarSenha(ModelSenha senha) {
		gerarSenhaView.getTextFieldSenhaGerada().setText(senha.getSenhaGerada());
		
	}

	public void personalizarBotaoCopiado() {
		gerarSenhaView.getBtnCopiarSenha().setText("Copiado");
		gerarSenhaView.getBtnCopiarSenha().setBackground(Color.WHITE);
		gerarSenhaView.getBtnCopiarSenha().setEnabled(false);
	
		personalizarBotaoCopiar();
		
	}

	private void personalizarBotaoCopiar() {
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gerarSenhaView.getBtnCopiarSenha().setText("Copiar");
            	gerarSenhaView.getBtnCopiarSenha().setBackground(Color.BLACK);
            	gerarSenhaView.getBtnCopiarSenha().setEnabled(true);
            }
        });

        timer.setRepeats(false);
        timer.start();
	}

}
