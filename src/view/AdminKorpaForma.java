package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import Kontroler.Kontroler;
import domen.Artikal;
import domen.Korpa;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class AdminKorpaForma extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel dtm = new DefaultTableModel();
	private DefaultTableModel dtm1 = new DefaultTableModel();
	private int ukupnaZaradaBelo = 0;
	private int ukupnaZaradaCrno = 0;
	private JLabel lblZaradaB;
	private JDateChooser dateChooserOd;
	private JDateChooser dateChooserDo;
	private JTextField Tsum;
	private int idZaposleni;

	public AdminKorpaForma(int idZaposleni) {
		this.idZaposleni = idZaposleni;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
		setBounds(100, 100, 1321, 749);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 188, 1285, 463);
		contentPane.add(scrollPane);

		table = new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JTableHeader head = table.getTableHeader();
		head.setFont(new Font("Myriad Pro Light", Font.CENTER_BASELINE, 20));
		head.setBackground(Color.BLACK);
		head.setForeground(Color.RED);

		JLabel lblBelo = new JLabel("Ukupno zarada :");
		lblBelo.setForeground(Color.BLACK);
		lblBelo.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblBelo.setBounds(1052, 680, 141, 20);
		contentPane.add(lblBelo);

		lblZaradaB = new JLabel("ZARADA   ");
		lblZaradaB.setForeground(Color.BLACK);
		lblZaradaB.setFont(new Font("Myriad Pro Light", Font.PLAIN, 40));
		lblZaradaB.setBounds(521, 138, 206, 39);
		contentPane.add(lblZaradaB);

		JComboBox comboBox = new JComboBox();
		comboBox.setBorder(roundedBorder);
		comboBox.setFont(new Font("Myriad Pro Light", Font.PLAIN, 14));
		comboBox.addItem("BELO");
		comboBox.addItem("CRNO");
		comboBox.setBounds(1189, 93, 106, 32);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Filtriraj");
		btnNewButton.setBorder(roundedBorder);
		Image img1 = new ImageIcon(this.getClass().getResource("/osvezi.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img1));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String d1 = null;
					String d2 = null;

					d1 = sdf.format(dateChooserOd.getDate());
					d2 = sdf.format(dateChooserDo.getDate());
					String opcija = comboBox.getSelectedItem().toString();
					System.out.println(d1);
					System.out.println(d2);

					Object[] redovi = new Object[8];
					dtm.setRowCount(0);

					for (Korpa kor : Kontroler.getInstanca().filtriraj(d1, d2, opcija)) {

						redovi[0] = kor.getDate();
						redovi[1] = kor.getImePrezime();
						redovi[2] = kor.getSifra();
						redovi[3] = kor.getNaziv();
						redovi[4] = kor.getOpcija();
						redovi[5] = kor.getProdajnaCena();
						redovi[6] = kor.getNabavnaCena();
						int zarada = kor.getProdajnaCena() - kor.getNabavnaCena();
						redovi[7] = zarada;
						ukupnaZaradaBelo += zarada;

						dtm.addRow(redovi);
						getSum();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "SELEKTUJTE DATUM");
					
				}
			}

		});
		btnNewButton.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnNewButton.setBounds(1189, 136, 106, 41);
		contentPane.add(btnNewButton);

		JLabel datumOd = new JLabel("Datum od :");
		datumOd.setForeground(Color.BLACK);
		datumOd.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		datumOd.setBounds(1091, 21, 92, 20);
		contentPane.add(datumOd);

		JLabel DatumDo = new JLabel("Datum do :");
		DatumDo.setForeground(Color.BLACK);
		DatumDo.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		DatumDo.setBounds(1091, 65, 102, 17);
		contentPane.add(DatumDo);

		dateChooserOd = new JDateChooser();
		dateChooserOd.setBorder(roundedBorder);
		dateChooserOd.setDateFormatString("d MMM, yyyy");
		dateChooserOd.setBounds(1187, 11, 108, 30);
		contentPane.add(dateChooserOd);

		dateChooserDo = new JDateChooser();
		dateChooserDo.setBorder(roundedBorder);
		dateChooserDo.setDateFormatString("d MMM, yyyy");
		dateChooserDo.setBounds(1187, 52, 108, 30);
		contentPane.add(dateChooserDo);

		JButton btnNazad = new JButton("Nazad");
		btnNazad.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		btnNazad.setBorder(roundedBorder);
		Image img = new ImageIcon(this.getClass().getResource("/nazad.png")).getImage();
		btnNazad.setIcon(new ImageIcon(img));
		btnNazad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String user = null;
				MedjuForma mf = new MedjuForma(user, idZaposleni);
				mf.setVisible(true);
				dispose();
			}
		});
		btnNazad.setBounds(10, 10, 116, 41);
		contentPane.add(btnNazad);

		Tsum = new JTextField();
		Tsum.setBorder(roundedBorder);
		Tsum.setFont(new Font("Myriad Pro Light", Font.PLAIN, 24));

		Tsum.setBounds(1189, 662, 106, 38);
		contentPane.add(Tsum);
		Tsum.setColumns(10);

		JLabel lblOpcija = new JLabel("Opcija :");
		lblOpcija.setForeground(Color.BLACK);
		lblOpcija.setFont(new Font("Myriad Pro Light", Font.PLAIN, 18));
		lblOpcija.setBounds(1093, 108, 86, 17);
		contentPane.add(lblOpcija);

		Object[] kolone = new Object[8];
		kolone[0] = "DATUM PRODAJE";
		kolone[1] = "ZAPOSLENI";
		kolone[2] = "ŠIFRA";
		kolone[3] = "ARTIKAL";
		kolone[4] = "OPCIJA";
		kolone[5] = "PRODAJNA CENA";
		kolone[6] = "NABAVNA CENA";
		kolone[7] = "ZARADA";

		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		dtm.addColumn(kolone[6]);
		dtm.addColumn(kolone[7]);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		prikaziPodatkeBelo();
		getSum();

	}

	private void getSum() {

		int sum = 0;
		for (int i = 0; i < table.getRowCount(); i++) {

			sum = sum + Integer.parseInt(table.getValueAt(i, 7).toString());

		}

		Tsum.setText(Integer.toString(sum));

	}

	private void prikaziPodatkeBelo() {
		Object[] redovi = new Object[8];
		dtm.setRowCount(0);

		for (Korpa kor : Kontroler.getInstanca().prikaziPodatkeBelo()) {

			redovi[0] = kor.getDate();
			redovi[1] = kor.getImePrezime();
			redovi[2] = kor.getSifra();
			redovi[3] = kor.getNaziv();
			redovi[4] = kor.getOpcija();
			redovi[5] = kor.getProdajnaCena();
			redovi[6] = kor.getNabavnaCena();
			int zarada = kor.getProdajnaCena() - kor.getNabavnaCena();
			redovi[7] = zarada;
			ukupnaZaradaBelo += zarada;
			dtm.addRow(redovi);
		}
	}
}
