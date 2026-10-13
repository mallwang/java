package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pnl_kundenkonto extends JPanel {
	/**
	 * Dieses Panel stellt kundenspezifische Informationen dar.
	 */
	private static final long serialVersionUID = -4004580736659719245L;
	private JButton btnLeihen;
	private JButton btnZurueckgeben;
	private JButton btnAendern;
	private JButton btnLoeschen;
	private JButton btnSchliessen;

	private JLabel vname;
	private JLabel nname;
	private JLabel tele;
	private JLabel geb;
	private JLabel pnr;
	private JLabel knr;
	private JLabel str;
	private JLabel hnr;
	private JLabel ort;
	private JLabel plz;

	public Pnl_kundenkonto(final String id) {
		setBackground(Color.DARK_GRAY);
		init();
	}

	private void init() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(224, 5, 1, 1);
		panel.setLayout(null);

		// Buttons
		btnLeihen = new JButton("Leihen");
		btnLeihen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnLeihen.setBounds(404, 19, 126, 45);

		btnZurueckgeben = new JButton("Zur\u00FCckgeben");
		btnZurueckgeben.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnZurueckgeben.setBounds(404, 62, 126, 45);

		btnAendern = new JButton("\u00C4ndern");
		btnAendern.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAendern.setBounds(404, 105, 126, 45);

		btnLoeschen = new JButton("L\u00F6schen");
		btnLoeschen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnLoeschen.setBounds(404, 148, 126, 45);

		btnSchliessen = new JButton("Schlie\u00DFen");
		btnSchliessen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSchliessen.setBounds(404, 275, 126, 25);

		// Label (dynamisch)
		vname = new JLabel();
		vname.setForeground(new Color(245, 245, 245));
		vname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		vname.setBounds(144, 66, 101, 16);

		nname = new JLabel();
		nname.setForeground(new Color(245, 245, 245));
		nname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		nname.setBounds(144, 102, 101, 16);

		geb = new JLabel();
		geb.setForeground(new Color(245, 245, 245));
		geb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		geb.setBounds(144, 189, 101, 16);

		tele = new JLabel();
		tele.setForeground(new Color(245, 245, 245));
		tele.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tele.setBounds(144, 218, 101, 16);

		pnr = new JLabel();
		pnr.setForeground(new Color(245, 245, 245));
		pnr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnr.setBounds(10, 275, 226, 16);

		knr = new JLabel();
		knr.setForeground(new Color(245, 245, 245));
		knr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		knr.setBounds(144, 32, 101, 16);

		str = new JLabel();
		str.setForeground(new Color(245, 245, 245));
		str.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		str.setBounds(144, 131, 101, 16);

		hnr = new JLabel();
		hnr.setForeground(new Color(245, 245, 245));
		hnr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		hnr.setBounds(292, 131, 56, 16);

		ort = new JLabel();
		ort.setForeground(new Color(245, 245, 245));
		ort.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ort.setBounds(144, 160, 101, 16);

		plz = new JLabel();
		plz.setForeground(new Color(245, 245, 245));
		plz.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		plz.setBounds(292, 160, 56, 16);

		// Label (statisch)
		JLabel lblPnr = new JLabel("Personalausweis-Nr.");
		lblPnr.setForeground(new Color(245, 245, 245));
		lblPnr.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPnr.setBounds(10, 253, 235, 16);

		JLabel lblTele = new JLabel("Telefon");
		lblTele.setForeground(new Color(245, 245, 245));
		lblTele.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTele.setBounds(10, 218, 131, 16);

		JLabel lblGeb = new JLabel("Geburtsdatum");
		lblGeb.setForeground(new Color(245, 245, 245));
		lblGeb.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGeb.setBounds(10, 189, 131, 16);

		JLabel lblOrt = new JLabel("Ort");
		lblOrt.setForeground(new Color(245, 245, 245));
		lblOrt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblOrt.setBounds(10, 160, 131, 16);

		JLabel lblStr = new JLabel("Stra\u00DFe");
		lblStr.setForeground(new Color(245, 245, 245));
		lblStr.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblStr.setBounds(10, 131, 131, 16);

		JLabel lblNname = new JLabel("Nachname");
		lblNname.setForeground(new Color(245, 245, 245));
		lblNname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNname.setBounds(10, 102, 131, 16);

		JLabel lblVname = new JLabel("Vorname");
		lblVname.setForeground(new Color(245, 245, 245));
		lblVname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVname.setBounds(10, 66, 131, 16);

		JLabel lblKnr = new JLabel("Kunden-Nr.");
		lblKnr.setForeground(new Color(245, 245, 245));
		lblKnr.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblKnr.setBounds(10, 32, 131, 16);

		JLabel lblHnr = new JLabel("Nr.");
		lblHnr.setForeground(new Color(245, 245, 245));
		lblHnr.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblHnr.setBounds(257, 131, 56, 16);

		JLabel lblPlz = new JLabel("PLZ");
		lblPlz.setForeground(new Color(245, 245, 245));
		lblPlz.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPlz.setBounds(257, 160, 56, 16);

		// Hinzufuegen

		add(panel);
		// Buttons
		add(btnLeihen);
		add(btnZurueckgeben);
		add(btnAendern);
		add(btnLoeschen);
		add(btnSchliessen);
		// Label (dynamisch)
		add(vname);
		add(nname);
		add(geb);
		add(tele);
		add(pnr);
		add(knr);
		add(str);
		add(hnr);
		add(ort);
		add(plz);
		// Label (statisch)
		add(lblPnr);
		add(lblTele);
		add(lblGeb);
		add(lblOrt);
		add(lblStr);
		add(lblNname);
		add(lblVname);
		add(lblKnr);
		add(lblHnr);
		add(lblPlz);

	}

	public void setGeb(String geb) {
		this.geb.setText(geb);
	}

	public void setHnr(String hnr) {
		this.hnr.setText(hnr);
	}

	public void setKnr(String knr) {
		this.knr.setText(knr);
	}

	public void setListener_BtnAendern(MouseAdapter ma) {
		btnAendern.addMouseListener(ma);
	}

	public void setListener_BtnLeihen(MouseAdapter ma) {
		btnLeihen.addMouseListener(ma);
	}

	public void setListener_BtnLoeschen(MouseAdapter ma) {
		btnLoeschen.addMouseListener(ma);
	}

	public void setListener_BtnSchliessen(MouseAdapter ma) {
		btnSchliessen.addMouseListener(ma);
	}

	public void setListener_BtnZurueckgeben(MouseAdapter ma) {
		btnZurueckgeben.addMouseListener(ma);
	}

	public void setNname(String nname) {
		this.nname.setText(nname);
	}

	public void setOrt(String ort) {
		this.ort.setText(ort);
	}

	public void setPlz(String plz) {
		this.plz.setText(plz);
	}

	public void setPnr(String pnr) {
		this.pnr.setText(pnr);
	}

	public void setStr(String str) {
		this.str.setText(str);
	}

	public void setTele(String tele) {
		this.tele.setText(tele);
	}

	public void setVname(String vname) {
		this.vname.setText(vname);
	}
}
