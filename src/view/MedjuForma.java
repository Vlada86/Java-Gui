package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MedjuForma extends JFrame {

	private JPanel contentPane;
	private String user;
	private int idZaposleni;

	public MedjuForma(String user,int idZaposleni) {
		this.user = user;
		this.idZaposleni=idZaposleni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
		setBounds(100, 100, 479, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlfa = new JLabel("");
		Image img5 = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblAlfa.setIcon(new ImageIcon(img5));
		lblAlfa.setBounds(119, 0, 230, 130);
		contentPane.add(lblAlfa);

		JButton btnTelefoni = new JButton("Artikli");
		btnTelefoni.setForeground(Color.BLACK);
		btnTelefoni.setBackground(Color.WHITE);
		btnTelefoni.setBorder(roundedBorder);
		Image img = new ImageIcon(this.getClass().getResource("/stanje.png")).getImage();
		btnTelefoni.setIcon(new ImageIcon(img));
		btnTelefoni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArtikalForma tf = new ArtikalForma(idZaposleni);
				tf.setVisible(true);
				dispose();
			}
		});
		btnTelefoni.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		btnTelefoni.setBounds(41, 147, 170, 51);

		contentPane.add(btnTelefoni);
		Image img1 = new ImageIcon(this.getClass().getResource("/oprema.png")).getImage();

		JButton btnServis = new JButton(" Zaposleni");
		btnServis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZaposleniForma z = new ZaposleniForma(idZaposleni);
				z.setVisible(true);
				dispose();
			}
		});

		btnServis.setSelectedIcon(null);
		btnServis.setForeground(Color.BLACK);
		btnServis.setBorder(roundedBorder);
		btnServis.setBackground(new Color(255, 255, 255));
		Image img2 = new ImageIcon(this.getClass().getResource("/zaposleni.png")).getImage();
		btnServis.setIcon(new ImageIcon(img2));
		btnServis.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		btnServis.setBounds(250, 147, 170, 51);
		contentPane.add(btnServis);

		JButton btnKorpa = new JButton("Korpa");
		btnKorpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminKorpaForma kf = new AdminKorpaForma(idZaposleni);
				kf.setVisible(true);
				dispose();
			}
		});
		btnKorpa.setBackground(Color.WHITE);
		btnKorpa.setBorder(roundedBorder);
		Image img3 = new ImageIcon(this.getClass().getResource("/korpa.png")).getImage();
		btnKorpa.setIcon(new ImageIcon(img3));
		btnKorpa.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		btnKorpa.setBounds(41, 219, 170, 51);
		contentPane.add(btnKorpa);
		
		JButton btnZarada = new JButton("Prodaja");
		btnZarada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ProdajaForma pf = new ProdajaForma(user,idZaposleni);
				pf.setVisible(true);
				dispose();
			}
		});
		btnZarada.setBorder(roundedBorder);
		Image img6 = new ImageIcon(this.getClass().getResource("/kes.png")).getImage();
		btnZarada.setIcon(new ImageIcon(img6));
		btnZarada.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		btnZarada.setBounds(250, 219, 170, 51);
		contentPane.add(btnZarada);
	}

	
}
