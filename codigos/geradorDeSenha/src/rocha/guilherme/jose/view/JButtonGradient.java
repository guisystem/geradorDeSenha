package rocha.guilherme.jose.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class JButtonGradient extends JButton {

	private float alpha = 0.3f;
	
	public JButtonGradient() {
		setContentAreaFilled(false);
		setForeground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(new EmptyBorder(10, 20, 10, 20));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Color color = Color.decode("#0F2027");
		GradientPaint gp = new GradientPaint(0, 0, color, width, height, color);
		g2d.setPaint(gp);
		
		g2d.fillRoundRect(0, 0, width, height, 5, 5);

		criarEstilo(g2d);
		
		g.drawImage(img, 0, 0, null);
		super.paintComponent(g);

	}
	
	private void criarEstilo(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		int width = getWidth();
		int height = getHeight();
		
		GradientPaint gp = new GradientPaint(0, 0, Color.decode("#2C5364"), 0, height, Color.decode("#2C5364"));
		g2d.setPaint(gp);
		
		Path2D.Float f = new Path2D.Float();
		f.moveTo(0, 0);
		int control = height + height / 2;
		f.curveTo(0, 0, width / 2, control, width, 0);
		g2d.fill(f);

	}
	
}
