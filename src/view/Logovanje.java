package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Kontroler.Kontroler;
import domen.Zaposleni;

public class Logovanje extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(Logovanje.class.getName());

	private JPanel contentPane;
	private JTextField tfUser;
	private int prioritet;
	private int idZaposleni;
	private JPasswordField tfPass;

	public static void main(String[] args) throws IOException {
		setupLogger();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logovanje frame = new Logovanje();
					frame.setVisible(true);
					frame.setTitle("ALFA MOBIL");

				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Root logger je uhvatio error", e);
				}
			}
		});
	}

	private static void setupLogger() throws IOException {

		// get the global logger to configure it
		Logger logger = Logger.getLogger("");

		logger.setLevel(Level.INFO);
		FileHandler fileTxt = new FileHandler("alfamobil.log", 50 * 1024 * 1024, 2, true);

		// create a TXT formatter
		SimpleFormatter formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
	}

	public Logovanje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 396);
		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlfa = new JLabel("");
		Image img5 = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblAlfa.setIcon(new ImageIcon(img5));
		lblAlfa.setBounds(50, 11, 230, 141);
		contentPane.add(lblAlfa);

		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUser.setBorder(roundedBorder);
		tfUser.setBounds(50, 184, 230, 36);
		contentPane.add(tfUser);
		tfUser.setColumns(10);

		tfPass = new JPasswordField();
		tfPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					proveriKorisnika();
				}
			}
		});
		tfPass.setBorder(roundedBorder);
		tfPass.setBounds(50, 253, 230, 36);
		contentPane.add(tfPass);
		tfPass.setColumns(10);

		JCheckBox cbPrikaz = new JCheckBox("");
		cbPrikaz.setToolTipText("Vidi sifru");
		cbPrikaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbPrikaz.isSelected()) {

					tfPass.setEchoChar((char) 0);
				} else {
					tfPass.setEchoChar('*');
				}
			}
		});

		JButton btnLogin = new JButton("LogIN");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					proveriKorisnika();
				}
			}
		});
		btnLogin.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				proveriKorisnika();

			}
		});
		btnLogin.setBounds(50, 310, 230, 37);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setForeground(new Color(255, 0, 0));
		contentPane.add(btnLogin);

		JLabel lblIme = new JLabel("UserName");
		lblIme.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		lblIme.setBounds(124, 163, 107, 21);
		lblIme.setForeground(new Color(0, 0, 0));
		contentPane.add(lblIme);

		JLabel lblPrezime = new JLabel("PassWord");
		lblPrezime.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		lblPrezime.setBounds(124, 231, 108, 20);
		lblPrezime.setForeground(new Color(0, 0, 0));
		contentPane.add(lblPrezime);

		cbPrikaz.setBounds(287, 269, 21, 20);
		contentPane.add(cbPrikaz);

		proveriKorisnika();
	}

	private void proveriKorisnika() {
		String user = tfUser.getText().toString();
		String pass = tfPass.getText().toString();

		for (Zaposleni z : Kontroler.getInstanca().vratiZaposlene()) {
			if (user.equals(z.getUserName()) && pass.equals(z.getPassWord()))
				idZaposleni = z.getIdZaposleni();
		}
		LOGGER.info(" Zaposleni je " + idZaposleni);
	
		if (tfUser.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Unesite userName");
		} else if (tfPass.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Unesite sifru");
		} else if (proveraZaposlenog(user, pass, prioritet = 0)) {
			ProdajaKupacForma rf = new ProdajaKupacForma(user, idZaposleni);
			rf.setVisible(true);
			dispose();
		} else if (proveraZaposlenog(user, pass, prioritet = 1)) {
			MedjuForma mf = new MedjuForma(user, idZaposleni);
			mf.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, " USERNAME ILI PASSWORD JE NEISPRAVAN !!! ");
			LOGGER.info("USERNAME ILI PASSWORD JE NEISPRAVAN");
		}

	}

	private boolean proveraZaposlenog(String user, String pass, int prioritet) {
		boolean primer = Kontroler.getInstanca().proveraZaposlenog(user, pass, prioritet);
		return primer;
	
	}

}
