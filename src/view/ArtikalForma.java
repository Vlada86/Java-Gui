package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

import Kontroler.Kontroler;
import domen.Artikal;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;

public class ArtikalForma extends JFrame{

	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTable table;
	private JTextField tfSifra;
	private JTextField tfArtikal;
	private JTextField tfNabavna;
	private JButton button;
	private int idP;
	private int idZaposleni;

	public ArtikalForma(int idZaposleni) {
		this.idZaposleni = idZaposleni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
		setBounds(100, 100, 744, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 708, 283);
		contentPane.add(scrollPane);

		table = new JTable(dtm);
		table.setBorder(roundedBorder);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JTableHeader head = table.getTableHeader();
		head.setFont(new Font("Myriad Pro Light", Font.CENTER_BASELINE, 20));
		head.setBackground(Color.BLACK);
		head.setForeground(Color.RED);

		tfSifra = new JTextField();
		tfSifra.setBorder(roundedBorder);
		tfSifra.setToolTipText("Unos sifre artikla je celobrojnog tipa");
		tfSifra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					evt.consume();
				}
			}
		});
		tfSifra.setBounds(10, 137, 140, 34);
		contentPane.add(tfSifra);
		tfSifra.setColumns(10);

		tfArtikal = new JTextField();
		tfArtikal.setBorder(roundedBorder);
		tfArtikal.setToolTipText("Unos naziva artikla je tekstualnog tipa");
		tfArtikal.setBounds(160, 137, 140, 34);
		tfArtikal.setBorder(roundedBorder);
		contentPane.add(tfArtikal);
		tfArtikal.setColumns(10);

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setForeground(Color.BLACK);
		btnDodaj.setBorder(roundedBorder);
		Image img2 = new ImageIcon(this.getClass().getResource("/dodaj.png")).getImage();
		btnDodaj.setIcon(new ImageIcon(img2));
		btnDodaj.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfSifra.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null, "UNESI SIFRU TELEFONA  !");
				else if (tfArtikal.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null, "UNESI NAZIV TELEFONA!");
				else if (tfNabavna.getText().length() == 0) // Checking for empty field
					JOptionPane.showMessageDialog(null, "UNESI CENU TELEFONA !");

				else {

					String sifra = tfSifra.getText().toString();
					String naziv = tfArtikal.getText().toString();
					String cena = tfNabavna.getText().toString();

					Kontroler.getInstanca().upisiArtikal(sifra, naziv, cena);
					prikaziPodatke();
					tfSifra.setText("");
					tfArtikal.setText("");
					tfNabavna.setText("");
				}
			}

		});
		btnDodaj.setBounds(470, 137, 116, 34);
		contentPane.add(btnDodaj);

		tfNabavna = new JTextField();
		tfNabavna.setBorder(roundedBorder);
		tfNabavna.setToolTipText("Unos cene artikla je celobrojnog tipa");
		tfNabavna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					evt.consume();
				}
			}
		});
		tfNabavna.setBounds(310, 137, 140, 34);
		contentPane.add(tfNabavna);
		tfNabavna.setColumns(10);

		JButton btnNazad = new JButton("Nazad");
		btnNazad.setBorder(roundedBorder);
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String user = null;
				MedjuForma mf = new MedjuForma(user, idZaposleni);
				mf.setVisible(true);
				dispose();
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/nazad.png")).getImage();
		btnNazad.setIcon(new ImageIcon(img));
		btnNazad.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnNazad.setBounds(10, 10, 116, 41);
		contentPane.add(btnNazad);

		JLabel lblSifra = new JLabel("\u0160ifra");
		lblSifra.setForeground(Color.BLACK);
		lblSifra.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblSifra.setBounds(10, 108, 46, 28);
		contentPane.add(lblSifra);

		JLabel lblNaziv = new JLabel("Artikal");
		lblNaziv.setForeground(Color.BLACK);
		lblNaziv.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblNaziv.setBounds(160, 108, 59, 28);
		contentPane.add(lblNaziv);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setForeground(Color.BLACK);
		lblCena.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblCena.setBounds(310, 108, 46, 31);
		contentPane.add(lblCena);

		JButton btnBrisi = new JButton("Izbri\u0161i");
		Image img1 = new ImageIcon(this.getClass().getResource("/izbrisi.png")).getImage();
		btnBrisi.setIcon(new ImageIcon(img1));
		btnBrisi.setForeground(Color.BLACK);
		btnBrisi.setBorder(roundedBorder);
		btnBrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int redovi=table.getSelectedRow();
					
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					String id = table.getModel().getValueAt(redovi, 0).toString();

					Kontroler.getInstanca().izbrisiArtikal(Integer.parseInt(id));
					prikaziPodatke();
					System.out.println(id);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "SELEKTUJTE ARTIKAL KOJI ZELITE DA OBRIŠETE");
				}
			}
		});
		btnBrisi.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnBrisi.setBounds(602, 137, 116, 34);
		contentPane.add(btnBrisi);

		Object[] kolone = new Object[4];
		kolone[0] = "RB";
		kolone[1] = "ŠIFRA";
		kolone[2] = "ARTIKAL";
		kolone[3] = "NABAVNA CENA";

		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer); 

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				int red = table.getSelectedRow();
				String id = (table.getValueAt(red, 0).toString());
				idP = Integer.valueOf(id);

			}
		});

		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		prikaziPodatke();
	}

	private void prikaziPodatke() {
		Object[] redovi = new Object[4];
		dtm.setRowCount(0);

		for (Artikal art : Kontroler.getInstanca().prikaziPodatke()) {

			redovi[0] = art.getIdArtikal();
			redovi[1] = art.getSifra();
			redovi[2] = art.getNaziv();
			redovi[3] = art.getCena();

			dtm.addRow(redovi);

		}
	}
}
