package dvdverleih.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Pnl_Objekt extends JPanel {
	/**
	 * Dieses Panel stellt objektspezifische Informationen dar.
	 */
	private static final long serialVersionUID = -5720377657032847431L;
	private JButton btnAendern;
	private JButton btnLoeschen;
	private JButton btnSchliessen;
	private JLabel art;
	private JLabel genre;
	private JLabel format;
	private JLabel titel;
	private JLabel originaltitel;
	private JLabel verfuegbar;
	private JLabel gesAnzahl;
	private JLabel preis;
	private JLabel fsk;
	private JLabel jahr;
	private JTextPane txtBeschreibung;

	public Pnl_Objekt(final String id) {
		setBackground(Color.DARK_GRAY);
		init();
	}

	private void init() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(224, 5, 1, 1);
		panel.setLayout(null);

		// Buttons
		btnAendern = new JButton("\u00C4ndern");
		btnAendern.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAendern.setBounds(10, 270, 126, 36);

		btnLoeschen = new JButton("L\u00F6schen");
		btnLoeschen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnLoeschen.setBounds(148, 270, 126, 36);

		btnSchliessen = new JButton("Schlie\u00DFen");
		btnSchliessen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSchliessen.setBounds(404, 285, 126, 25);

		// Label (dynamisch)
		art = new JLabel();
		art.setVerticalAlignment(SwingConstants.BOTTOM);
		art.setForeground(new Color(245, 245, 245));
		art.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		art.setBounds(114, 27, 184, 25);

		genre = new JLabel();
		genre.setVerticalAlignment(SwingConstants.BOTTOM);
		genre.setForeground(new Color(245, 245, 245));
		genre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		genre.setBounds(114, 58, 184, 27);

		format = new JLabel();
		format.setVerticalAlignment(SwingConstants.BOTTOM);
		format.setForeground(new Color(245, 245, 245));
		format.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		format.setBounds(404, 27, 126, 25);

		titel = new JLabel();
		titel.setVerticalAlignment(SwingConstants.BOTTOM);
		titel.setForeground(new Color(245, 245, 245));
		titel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		titel.setBounds(114, 90, 416, 25);

		jahr = new JLabel();
		jahr.setVerticalAlignment(SwingConstants.BOTTOM);
		jahr.setHorizontalAlignment(SwingConstants.RIGHT);
		jahr.setForeground(new Color(245, 245, 245));
		jahr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jahr.setBounds(64, 149, 93, 28);

		fsk = new JLabel();
		fsk.setVerticalAlignment(SwingConstants.BOTTOM);
		fsk.setForeground(new Color(245, 245, 245));
		fsk.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		fsk.setBounds(404, 58, 46, 27);

		preis = new JLabel();
		preis.setVerticalAlignment(SwingConstants.BOTTOM);
		preis.setHorizontalAlignment(SwingConstants.RIGHT);
		preis.setForeground(new Color(245, 245, 245));
		preis.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		preis.setBounds(89, 176, 68, 28);

		verfuegbar = new JLabel();
		verfuegbar.setVerticalAlignment(SwingConstants.BOTTOM);
		verfuegbar.setHorizontalAlignment(SwingConstants.RIGHT);
		verfuegbar.setForeground(new Color(245, 245, 245));
		verfuegbar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		verfuegbar.setBounds(111, 213, 46, 25);

		gesAnzahl = new JLabel();
		gesAnzahl.setVerticalAlignment(SwingConstants.BOTTOM);
		gesAnzahl.setHorizontalAlignment(SwingConstants.RIGHT);
		gesAnzahl.setForeground(new Color(245, 245, 245));
		gesAnzahl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		gesAnzahl.setBounds(111, 242, 46, 25);

		originaltitel = new JLabel();
		originaltitel.setVerticalAlignment(SwingConstants.BOTTOM);
		originaltitel.setForeground(new Color(245, 245, 245));
		originaltitel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		originaltitel.setBounds(114, 121, 416, 25);

		// Label (statisch)
		JLabel lblGesAnzahl = new JLabel("Gesamtanzahl");
		lblGesAnzahl.setForeground(new Color(245, 245, 245));
		lblGesAnzahl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGesAnzahl.setBounds(10, 242, 106, 25);

		JLabel lblVerf = new JLabel("Verf\u00FCgbar");
		lblVerf.setForeground(new Color(245, 245, 245));
		lblVerf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVerf.setBounds(10, 213, 83, 25);

		JLabel lblPreis = new JLabel("Preis");
		lblPreis.setForeground(new Color(245, 245, 245));
		lblPreis.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPreis.setBounds(10, 178, 42, 28);

		JLabel lblJahr = new JLabel("Jahr");
		lblJahr.setForeground(new Color(245, 245, 245));
		lblJahr.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblJahr.setBounds(9, 149, 42, 28);

		JLabel lblTitel = new JLabel("Titel");
		lblTitel.setForeground(new Color(245, 245, 245));
		lblTitel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTitel.setBounds(10, 90, 91, 25);

		JLabel lblFormat = new JLabel("Format");
		lblFormat.setForeground(new Color(245, 245, 245));
		lblFormat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFormat.setBounds(310, 28, 72, 25);

		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setForeground(new Color(245, 245, 245));
		lblGenre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGenre.setBounds(10, 59, 131, 27);

		JLabel lblArt = new JLabel("Art");
		lblArt.setForeground(new Color(245, 245, 245));
		lblArt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblArt.setBounds(10, 28, 131, 25);

		JLabel lblFsk = new JLabel("FSK");
		lblFsk.setForeground(new Color(245, 245, 245));
		lblFsk.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFsk.setBounds(310, 58, 56, 28);

		JLabel lblOriginaltitel = new JLabel("Originaltitel");
		lblOriginaltitel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOriginaltitel.setForeground(new Color(245, 245, 245));
		lblOriginaltitel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblOriginaltitel.setBounds(10, 121, 91, 25);

		JLabel lblBeschreibung = new JLabel("Beschreibung");
		lblBeschreibung.setForeground(new Color(245, 245, 245));
		lblBeschreibung.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblBeschreibung.setBounds(193, 151, 119, 25);

		// Hinzufuegen

		add(panel);
		add(btnAendern);
		add(btnLoeschen);
		add(btnSchliessen);

		// Label (dynamisch)
		add(art);
		add(genre);
		add(format);
		add(titel);
		add(fsk);
		add(jahr);
		add(preis);
		add(verfuegbar);
		add(gesAnzahl);
		add(originaltitel);

		// Label (statisch)
		add(lblOriginaltitel);
		add(lblFsk);
		add(lblGesAnzahl);
		add(lblVerf);
		add(lblPreis);
		add(lblJahr);
		add(lblTitel);
		add(lblFormat);
		add(lblGenre);
		add(lblArt);
		add(lblBeschreibung);

		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 176, 337, 87);

		add(scrollPane);

		// TextFeld auf ScrollPane
		txtBeschreibung = new JTextPane();
		scrollPane.setViewportView(txtBeschreibung);
		txtBeschreibung.setEditable(false);
		txtBeschreibung.setForeground(Color.DARK_GRAY);
		txtBeschreibung.setFont(new Font("Segoe UI", Font.PLAIN, 12));

	}

	public void setArt(String art) {
		this.art.setText(art);
	}

	public void setBeschreibung(String beschreibung) {
		txtBeschreibung.setText(beschreibung);
	}

	public void setFormat(String format) {
		this.format.setText(format);
	}

	public void setFsk(String fsk) {
		this.fsk.setText(fsk);
	}

	public void setGenre(String genre) {
		this.genre.setText(genre);
	}

	public void setGesAnzahl(String gesAnzahl) {
		this.gesAnzahl.setText(gesAnzahl);
	}

	public void setJahr(String jahr) {
		this.jahr.setText(jahr);
	}

	public void setListener_BtnAendern(MouseAdapter ma) {
		btnAendern.addMouseListener(ma);
	}

	public void setListener_BtnLoeschen(MouseAdapter ma) {
		btnLoeschen.addMouseListener(ma);
	}

	public void setListener_BtnSchliessen(MouseAdapter ma) {
		btnSchliessen.addMouseListener(ma);
	}

	public void setOriginaltitel(String originaltitel) {
		this.originaltitel.setText(originaltitel);
	}

	public void setPreis(String preis) {
		this.preis.setText(preis);
	}

	public void setTitel(String titel) {
		this.titel.setText(titel);
	}

	public void setVerfuegbar(String verfuegbar) {
		this.verfuegbar.setText(verfuegbar);
	}
}