package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import Kontroler.Kontroler;
import domen.Artikal;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;

public class ProdajaForma extends JFrame {

	private JPanel contentPane;
	private String user;
	private int idZaposleni;
	private JTable table;
	private JTextField tfPretraga;
	DefaultTableModel dtm = new DefaultTableModel();
	private ButtonGroup bg = new ButtonGroup();
	private JTextField tfCena;
	private JComboBox cB;
	private JRadioButton rbCeo, rbDeoNaziva;
	private int idP;
	private int ukupStanje;
	private String date;
	private String sdP;
	private JTextField tfModel;

	public ProdajaForma(String user, int idZaposleni) {
		this.user=user;
		this.idZaposleni = idZaposleni;
		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 810, 365);
		contentPane.add(scrollPane);

		table = new JTable(dtm);
		table.setRowHeight(30);
		table.setGridColor(Color.black);
		table.setForeground(Color.RED);
		table.setFont(new Font("Myriad Pro Light", Font.CENTER_BASELINE, 15));
		table.setShowGrid(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
	

		JTableHeader head = table.getTableHeader();
		head.setFont(new Font("Myriad Pro Light", Font.CENTER_BASELINE, 20));
		head.setBackground(Color.BLACK);
		head.setForeground(Color.RED);

		tfPretraga = new JTextField();
		tfPretraga.setBorder(roundedBorder);
		tfPretraga.setToolTipText("Pretraga po sifri");
		tfPretraga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String query = tfPretraga.getText().toString();
				dtm.setRowCount(0);
				for (Artikal art : Kontroler.getInstanca().filtrirajPretragu(query)) {

					Object[] redovi = new Object[4];

					redovi[0] = art.getIdArtikal();
					redovi[1] = art.getSifra();
					redovi[2] = art.getNaziv();
					redovi[3] = art.getCena();

					dtm.addRow(redovi);

				}

			}
		});
		tfPretraga.setBounds(277, 105, 220, 26);
		contentPane.add(tfPretraga);
		tfPretraga.setColumns(10);

		tfCena = new JTextField();
		tfCena.setBorder(roundedBorder);
		tfCena.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					evt.consume();
				}
			}
		});
		tfCena.setBounds(473, 546, 117, 40);
		contentPane.add(tfCena);
		tfCena.setColumns(10);

		JButton btnUbaci = new JButton("Korpa");
		Image img3 = new ImageIcon(this.getClass().getResource("/ubaci.png")).getImage();
		btnUbaci.setIcon(new ImageIcon(img3));
		btnUbaci.setBorder(roundedBorder);
		btnUbaci.setForeground(Color.BLACK);
		btnUbaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int redovi = table.getSelectedRow();

					String id = table.getModel().getValueAt(redovi, 0).toString();
					String artikal = table.getValueAt(redovi, 1).toString();
					//String model = table.getValueAt(redovi, 2).toString();
					// String nabavnaCena = table.getModel().getValueAt(redovi, 4).toString();

					String model = tfModel.getText().toString();
					String opcija = cB.getSelectedItem().toString();
					String cena = tfCena.getText().toString();
					java.util.Date date = new java.util.Date();

					java.sql.Date datum = new java.sql.Date(date.getTime());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd : HH-MM");

					if  (tfModel.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Napisite koji je model u pitanju za selektovani artikal");
					} else if (tfCena.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Upisite cenu");
					}else {
						Kontroler.getInstanca().ubaciUKorpu(idZaposleni, Integer.parseInt(id), model, opcija,Integer.parseInt(cena), datum);
						System.out.println("ID ZAPOSLENOG " + idZaposleni);
						prikaziPodatke();
						tfModel.setText("");
						tfCena.setText("");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "SELEKTUJTE ARTIKAL KOJI UBACUJETE U KORPU");
				}
				
			}

		});
		btnUbaci.setFont(new Font("Myriad Pro Light", Font.PLAIN, 24));
		btnUbaci.setBounds(652, 547, 168, 43);
		contentPane.add(btnUbaci);

		JLabel lblModel = new JLabel("Model");
		lblModel.setForeground(Color.BLACK);
		lblModel.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		lblModel.setBounds(22, 559, 57, 29);
		contentPane.add(lblModel);

		cB = new JComboBox();
		cB.setBorder(roundedBorder);
		cB.setFont(new Font("Myriad Pro Light", Font.PLAIN, 24));
		cB.addItem("Belo");
		cB.addItem("Crno");
		cB.setBounds(283, 546, 101, 40);
		contentPane.add(cB);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setForeground(Color.BLACK);
		lblCena.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		lblCena.setBounds(409, 554, 69, 29);
		contentPane.add(lblCena);

		JLabel lblOpcija = new JLabel("Opcija");
		lblOpcija.setForeground(Color.BLACK);
		lblOpcija.setFont(new Font("Myriad Pro Light", Font.PLAIN, 20));
		lblOpcija.setBounds(216, 557, 57, 29);
		contentPane.add(lblOpcija);

		tfModel = new JTextField();
		tfModel.setBorder(roundedBorder);
		tfModel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfModel.setBounds(89, 546, 117, 40);
		contentPane.add(tfModel);
		tfModel.setColumns(10);
		
		JButton button = new JButton("Nazad");
		button.setBorder(roundedBorder);
		Image img = new ImageIcon(this.getClass().getResource("/nazad.png")).getImage();
		button.setIcon(new ImageIcon(img));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedjuForma mf = new MedjuForma(user, idZaposleni);
				mf.setVisible(true);
				dispose();
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		button.setBounds(10, 11, 116, 41);
		contentPane.add(button);

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

				// String model = (table.getValueAt(red, 2).toString());
				// sdP = String.valueOf(model);

			}
		});

		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tcm.removeColumn(tcm.getColumn(2));

		prikaziPodatke();

	}

	private void prikaziPodatke() {

		Object[] redovi = new Object[3];
		dtm.setRowCount(0);
		
		for (Artikal art : Kontroler.getInstanca().prikaziPodatke()) {

			redovi[0] = art.getIdArtikal();
			redovi[1] = art.getSifra();
			redovi[2] = art.getNaziv();

			dtm.addRow(redovi);
		}
	}
}
