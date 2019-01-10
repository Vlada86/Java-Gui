package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import Kontroler.Kontroler;
import domen.Korpa;
import domen.Zaposleni;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class ZaposleniForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfImePrezime;
	private JTextField tfUser;
	private JTextField tfPass;
	private JTextField tfTelefon;
	private JDateChooser dcDatum;
	private JButton button;
	private JTable table;
	DefaultTableModel dtm = new DefaultTableModel();
	private int idZaposleni;

	public ZaposleniForma(int idZaposleni) {
		this.idZaposleni = idZaposleni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
		setBounds(100, 100, 937, 520);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfImePrezime = new JTextField();
		tfImePrezime.setBorder(roundedBorder);
		tfImePrezime.setBounds(355, 61, 153, 32);
		contentPane.add(tfImePrezime);
		tfImePrezime.setColumns(10);

		tfUser = new JTextField();
		tfUser.setBorder(roundedBorder);
		tfUser.setBounds(355, 103, 153, 32);
		contentPane.add(tfUser);
		tfUser.setColumns(10);

		tfPass = new JTextField();
		tfPass.setBorder(roundedBorder);
		tfPass.setBounds(355, 145, 153, 32);
		contentPane.add(tfPass);
		tfPass.setColumns(10);

		tfTelefon = new JTextField();
		tfTelefon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					evt.consume();
				}
			}
		});
		tfTelefon.setBorder(roundedBorder);
		tfTelefon.setBounds(355, 229, 153, 32);
		contentPane.add(tfTelefon);
		tfTelefon.setColumns(10);

		dcDatum = new JDateChooser();
		dcDatum.setBorder(roundedBorder);
		dcDatum.setBounds(355, 187, 153, 32);
		contentPane.add(dcDatum);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		comboBox.setBorder(roundedBorder);
		comboBox.addItem("0");
		comboBox.addItem("1");
		comboBox.setBounds(355, 271, 43, 32);
		contentPane.add(comboBox);

		JButton btnDodaj = new JButton("Sacuvaj");
		btnDodaj.setBorder(roundedBorder);
		Image img1 = new ImageIcon(this.getClass().getResource("/radnik.png")).getImage();
		btnDodaj.setIcon(new ImageIcon(img1));
		btnDodaj.setForeground(Color.BLACK);
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				if (tfImePrezime.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null,
							" POLJE ZA IME I PREZIME  JE OSTALO PRAZNO,POPUNITE GA ZA NASTAVAK !");
				else if (tfUser.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null, "USERNAME POLJE JE OSTALO PRAZNO,POPUNITE GA ZA NASTAVAK !");
				else if (tfPass.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null, "PASSWORD POLJE JE OSTALO PRAZNO,POPUNITE GA ZA NASTAVAK !");
				else if (dcDatum.getDate() == null) // Checking for empty field
					JOptionPane.showMessageDialog(null, "DATUM POLJE JE OSTALO PRAZNO,POPUNITE GA ZA NASTAVAK !");
				else if (tfTelefon.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null, "TELEFON POLJE JE OSTALO PRAZNO,POPUNITE GA ZA NASTAVAK !");

				else {
					try {
						String imePrezime = tfImePrezime.getText().toString();
						String userName = tfUser.getText().toString();
						String password = tfPass.getText().toString();
						String date = df.format(dcDatum.getDate());
						String telefon = tfTelefon.getText().toString();
						String prioritet = comboBox.getSelectedItem().toString();

						Kontroler.getInstanca().upisiZaposlenog(imePrezime, userName, password, date, telefon,
								Integer.parseInt(prioritet));
						JOptionPane.showMessageDialog(null, "Uspesna registracija");
						prikaziZaposlene();
						tfImePrezime.setText("");
						tfUser.setText("");
						tfPass.setText("");
						tfTelefon.setText("");
						comboBox.setSelectedItem("0");
						dcDatum.setDate(null);

					} catch (Exception e1) {

					}
				}
			}

		});
		btnDodaj.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnDodaj.setBounds(401, 271, 107, 32);
		contentPane.add(btnDodaj);

		button = new JButton("Nazad");
		button.setBorder(roundedBorder);
		Image img = new ImageIcon(this.getClass().getResource("/nazad.png")).getImage();
		button.setIcon(new ImageIcon(img));
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String user = null;
				MedjuForma mf = new MedjuForma(user, idZaposleni);
				mf.setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		button.setBounds(24, 11, 116, 41);
		contentPane.add(button);

		JLabel lblImePrezime = new JLabel("Ime i prezime :");
		lblImePrezime.setForeground(Color.BLACK);
		lblImePrezime.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblImePrezime.setBounds(212, 73, 133, 19);
		contentPane.add(lblImePrezime);

		JLabel lblUsername = new JLabel("UserName :");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblUsername.setBounds(212, 116, 116, 19);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("PassWord : ");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblPassword.setBounds(212, 156, 89, 19);
		contentPane.add(lblPassword);

		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja :");
		lblDatumRodjenja.setForeground(Color.BLACK);
		lblDatumRodjenja.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblDatumRodjenja.setBounds(212, 200, 143, 19);
		contentPane.add(lblDatumRodjenja);

		JLabel lblMobilni = new JLabel("Mobilni :");
		lblMobilni.setForeground(Color.BLACK);
		lblMobilni.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblMobilni.setBounds(212, 242, 70, 19);
		contentPane.add(lblMobilni);

		JLabel lblPrioritet = new JLabel("Prioritet :");
		lblPrioritet.setForeground(Color.BLACK);
		lblPrioritet.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblPrioritet.setBounds(212, 284, 89, 19);
		contentPane.add(lblPrioritet);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 332, 901, 139);
		contentPane.add(scrollPane);

		table = new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnBrisi = new JButton("Izbrisi");
		Image img2 = new ImageIcon(this.getClass().getResource("/kanta.png")).getImage();
		btnBrisi.setIcon(new ImageIcon(img2));
		btnBrisi.setBorder(roundedBorder);
		btnBrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int redovi = table.getSelectedRow();
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					String id = table.getModel().getValueAt(redovi, 0).toString();

					Kontroler.getInstanca().izbrisiZaposlenog(Integer.parseInt(id));
					prikaziZaposlene();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "SELEKTUJTE ZAPOSLENOG KOJEG ZELITE DA OBRIŠETE");
				}
			
			}
		});
		btnBrisi.setHorizontalAlignment(SwingConstants.LEFT);
		btnBrisi.setForeground(Color.BLACK);
		btnBrisi.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnBrisi.setBounds(518, 271, 107, 32);
		contentPane.add(btnBrisi);

		JTableHeader head = table.getTableHeader();
		head.setFont(new Font("Myriad Pro Light", Font.CENTER_BASELINE, 16));
		head.setBackground(Color.BLACK);
		head.setForeground(Color.RED);

		Object[] kolone1 = new Object[7];
		kolone1[0] = "RB";
		kolone1[1] = "IME I PREZIME ";
		kolone1[2] = "USERNAME";
		kolone1[3] = "PASSWORD";
		kolone1[4] = "DATUM RODJENJA";
		kolone1[5] = "KONTAKT TELEFON";
		kolone1[6] = "PRIORITET";

		dtm.addColumn(kolone1[0]);
		dtm.addColumn(kolone1[1]);
		dtm.addColumn(kolone1[2]);
		dtm.addColumn(kolone1[3]);
		dtm.addColumn(kolone1[4]);
		dtm.addColumn(kolone1[5]);
		dtm.addColumn(kolone1[6]);

		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		prikaziZaposlene();

	}

	private void prikaziZaposlene() {
		Object[] redovi = new Object[7];
		dtm.setRowCount(0);

		for (Zaposleni zap : Kontroler.getInstanca().prikaziZaposlene()) {

			redovi[0] = zap.getIdZaposleni();
			redovi[1] = zap.getImePrezime();
			redovi[2] = zap.getUserName();
			redovi[3] = zap.getPassWord();
			redovi[4] = zap.getDatumRodjenja();
			redovi[5] = zap.getTelefon();
			redovi[6] = zap.getPrioritet();

			dtm.addRow(redovi);

		}

	}
}
