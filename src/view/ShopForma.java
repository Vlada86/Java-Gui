package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Kontroler.Kontroler;
import domen.Artikal;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ShopForma extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private int idZaposleni;
	private String user;
	private String sdP;
	private int idP;

	public ShopForma(String user, int idZaposleni) {
		this.user = user;
		this.idZaposleni = idZaposleni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 456);
		setTitle("ALFA MOBIL ");
		getContentPane().setLayout(null);

		// ScrollPane

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 899, 253);
		getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex == 1) {
					return false; // Disallow Column 0
				} else {
					return true; // Allow the editing

				}

			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				if (column == 0) {
					comp.setBackground(Color.lightGray);
				} else {
					comp.setBackground(Color.white);
				}
				return comp;
			}

		};

		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		table.getTableHeader().setForeground(Color.blue);

		// Column Width
		// table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.setForeground(Color.black);
		// Row Height
		table.setRowHeight(20);
		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel() {

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return Integer.class;
				case 4:
					return Integer.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				default:
					return String.class;
				}
			}
		};
		table.setModel(model);
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					int redovi = table.getSelectedRow();
				

					String id = table.getModel().getValueAt(redovi, 0).toString();
					String cena = table.getValueAt(redovi, 2).toString();
					String opis = table.getValueAt(redovi, 3).toString();
					String opcija = table.getModel().getValueAt(redovi, 4).toString();

					java.util.Date date = new java.util.Date();
					java.sql.Date datum = new java.sql.Date(date.getTime());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					Kontroler.getInstanca().ubaciUKorpu(idZaposleni, Integer.parseInt(id), opis, opcija,
							Integer.parseInt(cena), datum);
					System.out.println(opcija);
					prikaziPodatke();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "SELEKTUJTE ARTIKAL KOJI UBACUJETE U KORPU");
				}
			}
		});
		btnNewButton.setBounds(796, 45, 113, 23);
		getContentPane().add(btnNewButton);
		
		JComboBox cB = new JComboBox();
		cB.addItem("Belo");
		cB.addItem("Crno");
		cB.setFont(new Font("Myriad Pro Light", Font.PLAIN, 14));
		cB.setBounds(681, 46, 86, 20);
		getContentPane().add(cB);

		prikaziPodatke();
	}

	private void prikaziPodatke() {
		table.setModel(new DefaultTableModel());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("RB");
		model.addColumn("ŠIFRA");
		model.addColumn("NAZIV");
		model.addColumn("NABAVNA CENA");
		model.addColumn("PRODAJNA CENA");
		model.addColumn("MODEL");
		

		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tcm.removeColumn(tcm.getColumn(2));

		Object[] redovi = new Object[3];
		model.setRowCount(0);

		for (Artikal art : Kontroler.getInstanca().prikaziPodatke()) {

			redovi[0] = art.getIdArtikal();
			redovi[1] = art.getSifra();
			redovi[2] = art.getNaziv();

			model.addRow(redovi);
		}
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				int red = table.getSelectedRow();
				String id = (table.getValueAt(red, 0).toString());
				idP = Integer.valueOf(id);

			}

		});

		
	}
}
