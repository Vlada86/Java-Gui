package domen;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ArtikalTableModel extends AbstractTableModel {

	private final List<Artikal> artLista;

	private final String[] columnNames = new String[] { "RB", "SIFRA", "ARTIKAL", "OPIS", "NABAVNA CENA" };

	private final Class[] columnClass = new Class[] { Integer.class, Integer.class, String.class, String.class,
			Integer.class };

	public ArtikalTableModel(List<Artikal> artLista) {
		this.artLista = artLista;
	}

	public List<Artikal> getArtLista() {
		return artLista;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public Class[] getColumnClass() {
		return columnClass;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return artLista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Artikal row = artLista.get(rowIndex);
		if (0 == columnIndex) {
			return row.getIdArtikal();
		} else if (1 == columnIndex) {
			return row.getSifra();
		} else if (2 == columnIndex) {
			return row.getNaziv();
		} else if (3 == columnIndex) {
			return row.getCena();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Artikal row = artLista.get(rowIndex);
		if (0 == columnIndex) {
			row.setIdArtikal((Integer) aValue);
		} else if (1 == columnIndex) {
			row.setSifra((Integer) aValue);
		} else if (2 == columnIndex) {
			row.setNaziv((String) aValue);
		} else if (3 == columnIndex) {
			row.setCena((Integer) aValue);
		}
	}

	public void add(Artikal a) {

		add(a);

	}

}
