package rocha.guilherme.jose.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import rocha.guilherme.jose.controller.GerarSenhaController;
import rocha.guilherme.jose.controller.helper.GerarSenhaHelper;
import rocha.guilherme.jose.model.ModelSenha;

@SuppressWarnings("serial")
public class GerarSenhaView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldQuantLetras;
	private JTextField textFieldQuantNumeros;
	private JTextField textFieldQuantCaractereEspecial;
	private JTextField textFieldPalavraPessoal;
	private JTextField textFieldSenhaGerada;
	private ButtonGroup formaDaLetra;
	private ButtonGroup palavraPessoal;
	private JButton btnCopiarSenha;
	private JRadioButton rdbtnNaoPalavra;
	private JRadioButton rdbtnSimPalavra;
	private Color corCirculo = new Color(255, 0, 0, 0);
	
	private GerarSenhaController controller;
	private GerarSenhaHelper helper;
	private JLabel lblAvisoSenha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarSenhaView frame = new GerarSenhaView();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.drawLine(40, 142, 446, 142);
        g2d.drawLine(40, 191, 446, 191);
        g2d.drawLine(40, 385, 446, 385);
        g2d.drawLine(40, 459, 446, 459);
        g2d.drawLine(40, 550, 368, 550);
        g2d.drawLine(447, 495, 447, 550);
        
        g2d.setColor(corCirculo);
        int x = 40;
        int y = 532;
        int tamanho = 12;
        g2d.drawOval(x, y, tamanho, tamanho);

    }

	/**
	 * Create the frame.
	 */
	public GerarSenhaView() {
		setTitle("Gerador de Senha");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GerarSenhaView.class.getResource("/rocha/guilherme/jose/view/icones/trancar.png")));
		controller = new GerarSenhaController(this);
		helper = new GerarSenhaHelper(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 595);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gerador de Senha");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(154, 28, 163, 24);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantLetras = new JLabel("Quantas letras deseja em sua senha?");
		lblQuantLetras.setForeground(new Color(255, 255, 255));
		lblQuantLetras.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuantLetras.setBounds(32, 90, 261, 20);
		contentPane.add(lblQuantLetras);
		
		JLabel lblQuantNumeros = new JLabel("Quantos n\u00FAmeros deseja em sua senha?");
		lblQuantNumeros.setForeground(new Color(255, 255, 255));
		lblQuantNumeros.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuantNumeros.setBounds(32, 138, 285, 20);
		contentPane.add(lblQuantNumeros);
		
		JLabel lblMaiusculasOuMinusculas = new JLabel("As letras que cont\u00E9m em sua senha tem que ser:");
		lblMaiusculasOuMinusculas.setForeground(new Color(255, 255, 255));
		lblMaiusculasOuMinusculas.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMaiusculasOuMinusculas.setBounds(32, 184, 337, 20);
		contentPane.add(lblMaiusculasOuMinusculas);
		
		JLabel lblQuantCaractereEspecialUm = new JLabel("Quantos caracteres especiais(Ex: !, @, #, ");
		lblQuantCaractereEspecialUm.setForeground(new Color(255, 255, 255));
		lblQuantCaractereEspecialUm.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuantCaractereEspecialUm.setBounds(32, 312, 293, 20);
		contentPane.add(lblQuantCaractereEspecialUm);
		
		JLabel lblPalavraPessoal = new JLabel("Deseja adicionar alguma palavra em sua senha?");
		lblPalavraPessoal.setForeground(new Color(255, 255, 255));
		lblPalavraPessoal.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPalavraPessoal.setBounds(32, 379, 348, 20);
		contentPane.add(lblPalavraPessoal);
		
		JLabel lblQuantCaractereEspecialDois = new JLabel("$, %, &, -, _, =, ?) deseja em sua senha?");
		lblQuantCaractereEspecialDois.setForeground(new Color(255, 255, 255));
		lblQuantCaractereEspecialDois.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuantCaractereEspecialDois.setBounds(32, 332, 307, 20);
		contentPane.add(lblQuantCaractereEspecialDois);
		
		JLabel lblSuaSenha = new JLabel("Sua senha:");
		lblSuaSenha.setForeground(new Color(255, 255, 255));
		lblSuaSenha.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSuaSenha.setBounds(202, 466, 78, 20);
		contentPane.add(lblSuaSenha);
		
		lblAvisoSenha = new JLabel("");
		lblAvisoSenha.setForeground(new Color(255, 255, 255));
		lblAvisoSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAvisoSenha.setBounds(48, 500, 307, 16);
		contentPane.add(lblAvisoSenha);
		
		JRadioButton rdbtnMaiusculas = new JRadioButton("S\u00F3 Mai\u00FAsculas");
		rdbtnMaiusculas.setForeground(new Color(255, 255, 255));
		rdbtnMaiusculas.setOpaque(false);
		rdbtnMaiusculas.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnMaiusculas.setBounds(28, 212, 109, 20);
		contentPane.add(rdbtnMaiusculas);
		
		JRadioButton rdbtnMinusculas = new JRadioButton("S\u00F3 Min\u00FAsculas");
		rdbtnMinusculas.setForeground(new Color(255, 255, 255));
		rdbtnMinusculas.setOpaque(false);
		rdbtnMinusculas.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnMinusculas.setBounds(28, 240, 107, 20);
		contentPane.add(rdbtnMinusculas);
		
		JRadioButton rdbtnMaiusculasEMinusculas = new JRadioButton("Mai\u00FAsculas e Min\u00FAsculas");
		rdbtnMaiusculasEMinusculas.setForeground(new Color(255, 255, 255));
		rdbtnMaiusculasEMinusculas.setOpaque(false);
		rdbtnMaiusculasEMinusculas.setSelected(true);
		rdbtnMaiusculasEMinusculas.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnMaiusculasEMinusculas.setBounds(28, 268, 165, 20);
		contentPane.add(rdbtnMaiusculasEMinusculas);
		
		rdbtnNaoPalavra = new JRadioButton("N\u00E3o");
		rdbtnNaoPalavra.setForeground(new Color(255, 255, 255));
		rdbtnNaoPalavra.setOpaque(false);
		rdbtnNaoPalavra.setSelected(true);
		rdbtnNaoPalavra.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnNaoPalavra.setBounds(28, 406, 51, 20);
		contentPane.add(rdbtnNaoPalavra);
		
		rdbtnSimPalavra = new JRadioButton("Sim");
		rdbtnSimPalavra.setForeground(new Color(255, 255, 255));
		rdbtnSimPalavra.setOpaque(false);
		rdbtnSimPalavra.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnSimPalavra.setBounds(91, 406, 49, 20);
		contentPane.add(rdbtnSimPalavra);
		
		textFieldQuantLetras = new JTextField();
		textFieldQuantLetras.setBackground(new Color(255, 255, 255));
		textFieldQuantLetras.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	atualizarAviso();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	atualizarAviso();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	atualizarAviso();
            }
        });
		textFieldQuantLetras.setBorder(new LineBorder(Color.WHITE));
		textFieldQuantLetras.setBounds(380, 88, 60, 24);
		contentPane.add(textFieldQuantLetras);
		textFieldQuantLetras.setColumns(10);
		
		textFieldQuantNumeros = new JTextField();
		textFieldQuantNumeros.setBackground(new Color(255, 255, 255));
		textFieldQuantNumeros.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	atualizarAviso();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	atualizarAviso();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	atualizarAviso();
            }
        });
		textFieldQuantNumeros.setBorder(new LineBorder(Color.WHITE));
		textFieldQuantNumeros.setColumns(10);
		textFieldQuantNumeros.setBounds(380, 137, 60, 24);
		contentPane.add(textFieldQuantNumeros);
		
		textFieldQuantCaractereEspecial = new JTextField();
		textFieldQuantCaractereEspecial.setBackground(new Color(255, 255, 255));
		textFieldQuantCaractereEspecial.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	atualizarAviso();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	atualizarAviso();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	atualizarAviso();
            }
        });
		textFieldQuantCaractereEspecial.setBorder(new LineBorder(Color.WHITE));
		textFieldQuantCaractereEspecial.setColumns(10);
		textFieldQuantCaractereEspecial.setBounds(380, 331, 60, 24);
		contentPane.add(textFieldQuantCaractereEspecial);
		
		textFieldPalavraPessoal = new JTextField();
		textFieldPalavraPessoal.setBackground(new Color(255, 255, 255));
		textFieldPalavraPessoal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.abrirFecharCampoPalavra();
			}
		});
		textFieldPalavraPessoal.setEditable(false);
		textFieldPalavraPessoal.setBorder(new LineBorder(Color.WHITE));
		textFieldPalavraPessoal.setColumns(10);
		textFieldPalavraPessoal.setBounds(293, 405, 147, 24);
		contentPane.add(textFieldPalavraPessoal);
		
		textFieldSenhaGerada = new JTextField();
		textFieldSenhaGerada.setBackground(new Color(255, 255, 255));
		textFieldSenhaGerada.setBorder(new LineBorder(Color.WHITE));
		textFieldSenhaGerada.setColumns(10);
		textFieldSenhaGerada.setBounds(293, 464, 147, 24);
		contentPane.add(textFieldSenhaGerada);
		
		JButton btnGerarSenha = new JButtonGradient();
		btnGerarSenha.setText("GERAR SENHA");
		btnGerarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.gerarSenha();
			}
		});
		btnGerarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGerarSenha.setFont(new Font("Arial", Font.BOLD, 14));
		btnGerarSenha.setBounds(32, 460, 150, 32);
		contentPane.add(btnGerarSenha);
		
		btnCopiarSenha = new JButton("Copiar");
		btnCopiarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.copiarSenha(textFieldSenhaGerada.getText());
			}
		});
		btnCopiarSenha.setBackground(Color.BLACK);
		btnCopiarSenha.setForeground(Color.WHITE);
		btnCopiarSenha.setBorder(new LineBorder(Color.WHITE));
		btnCopiarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCopiarSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCopiarSenha.setBounds(368, 496, 72, 24);
		contentPane.add(btnCopiarSenha);
		
		formaDaLetra = new ButtonGroup();
		formaDaLetra.add(rdbtnMaiusculas);
		formaDaLetra.add(rdbtnMinusculas);
		formaDaLetra.add(rdbtnMaiusculasEMinusculas);
		
		palavraPessoal = new ButtonGroup();
		palavraPessoal.add(rdbtnSimPalavra);
		palavraPessoal.add(rdbtnNaoPalavra);
	}
	
	private void atualizarAviso() {
		ModelSenha senha = helper.obterModelo();
		int tamanho = senha.getQuantLetras() + senha.getQuantNumeros() + senha.getQuantCaracteres();

		if(!helper.validarQuantNumeros() || !helper.validarQuantLetras() || !helper.validarQuantCaractere()) {
			return;
		}
		
		if(tamanho == 0) {
			corCirculo = new Color(255, 0, 0, 0);
			lblAvisoSenha.setText("");
		}else if(tamanho < 8) {
            corCirculo = Color.RED;
            lblAvisoSenha.setText("Não é possivel gerar! Senha muito pequena");
        }else if(tamanho <= 10) {
            corCirculo = Color.BLUE;
            lblAvisoSenha.setText("Senha fraca");
        }else if(tamanho <= 12){        	
        	corCirculo = Color.YELLOW;
        	lblAvisoSenha.setText("Senha moderada");
        }else if(tamanho <= 30) {
        	corCirculo = Color.GREEN;
        	lblAvisoSenha.setText("Senha segura");
        }else {
        	corCirculo = Color.RED;
        	lblAvisoSenha.setText("Senha muito grande! O tamanho máximo deve ser 30.");
        }

        repaint();
		
	}

	public void exibeMensagemInformativa(String mensagem) {
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 12));
		UIManager.put("Panel.background", Color.decode("#000000"));
		UIManager.put("OptionPane.background", Color.decode("#000000"));
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("Button.background", Color.decode("#0F2027"));
		UIManager.put("Button.foreground", Color.WHITE);
		JOptionPane.showMessageDialog(null, mensagem, "Atenção", JOptionPane.INFORMATION_MESSAGE);
	}

	public JTextField getTextFieldQuantLetras() {
		return textFieldQuantLetras;
	}

	public void setTextFieldQuantLetras(JTextField textFieldQuantLetras) {
		this.textFieldQuantLetras = textFieldQuantLetras;
	}

	public JTextField getTextFieldQuantNumeros() {
		return textFieldQuantNumeros;
	}

	public void setTextFieldQuantNumeros(JTextField textFieldQuantNumeros) {
		this.textFieldQuantNumeros = textFieldQuantNumeros;
	}

	public JTextField getTextFieldQuantCaractereEspecial() {
		return textFieldQuantCaractereEspecial;
	}

	public void setTextFieldQuantCaractereEspecial(JTextField textFieldQuantCaractereEspecial) {
		this.textFieldQuantCaractereEspecial = textFieldQuantCaractereEspecial;
	}

	public JTextField getTextFieldPalavraPessoal() {
		return textFieldPalavraPessoal;
	}

	public void setTextFieldPalavraPessoal(JTextField textFieldPalavraPessoal) {
		this.textFieldPalavraPessoal = textFieldPalavraPessoal;
	}

	public JTextField getTextFieldSenhaGerada() {
		return textFieldSenhaGerada;
	}

	public void setTextFieldSenhaGerada(JTextField textFieldSenhaGerada) {
		this.textFieldSenhaGerada = textFieldSenhaGerada;
	}

	public ButtonGroup getFormaDaLetra() {
		return formaDaLetra;
	}

	public void setFormaDaLetra(ButtonGroup formaDaLetra) {
		this.formaDaLetra = formaDaLetra;
	}

	public ButtonGroup getPalavraPessoal() {
		return palavraPessoal;
	}

	public void setPalavraPessoal(ButtonGroup palavraPessoal) {
		this.palavraPessoal = palavraPessoal;
	}

	public JButton getBtnCopiarSenha() {
		return btnCopiarSenha;
	}

	public void setBtnCopiarSenha(JButton btnCopiarSenha) {
		this.btnCopiarSenha = btnCopiarSenha;
	}

	public JRadioButton getRdbtnNaoPalavra() {
		return rdbtnNaoPalavra;
	}

	public void setRdbtnNaoPalavra(JRadioButton rdbtnNaoPalavra) {
		this.rdbtnNaoPalavra = rdbtnNaoPalavra;
	}

	public JRadioButton getRdbtnSimPalavra() {
		return rdbtnSimPalavra;
	}

	public void setRdbtnSimPalavra(JRadioButton rdbtnSimPalavra) {
		this.rdbtnSimPalavra = rdbtnSimPalavra;
	}
	
}
