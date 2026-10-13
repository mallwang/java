package funktionen;

import java.util.Random;

public class Kartenspiel {

	private Karte[] stapel;
	private int kartenImSpiel;
	
	// Konstruktor
	public Kartenspiel(){
		
		// Anzahl der Karten definieren
		/*
		int boniKarten = 6;
		int sternKarten = 5;
		int aussetzenKarten = 6;
		int doppelteLotteKarten = 4;
		int tausenderKarten = 5;
		int strassenKarten = 5;
		int kleeblattKarten = 1;
		*/
		int boniKarten = 10;
		int sternKarten = 0;
		int aussetzenKarten = 0;
		int doppelteLotteKarten = 0;
		int tausenderKarten = 0;
		int strassenKarten = 0;
		int kleeblattKarten = 0;
		int gesamtanzahlKarten = 5*boniKarten + sternKarten + aussetzenKarten + doppelteLotteKarten + tausenderKarten + strassenKarten + kleeblattKarten;
		
		setKartenImSpiel(gesamtanzahlKarten);
		
		stapel = new Karte[gesamtanzahlKarten];
				
		// Bonus Karten erstellen
		for (int i=0; i<boniKarten; i++){
			stapel[i] = new Karte(0);
		}
		for (int i=boniKarten; i<(boniKarten*2); i++){
			stapel[i] = new Karte(1);
		}
		for (int i=(boniKarten*2); i<(boniKarten*3); i++){
			stapel[i] = new Karte(2);
		}
		for (int i=(boniKarten*3); i<(boniKarten*4); i++){
			stapel[i] = new Karte(3);
		}
		for (int i=(boniKarten*4); i<(boniKarten*5); i++){
			stapel[i] = new Karte(4);
		}
		
		// Stern Karten erstellen
		for (int i=(boniKarten*5); i<(boniKarten*5)+sternKarten; i++){
			stapel[i] = new Karte(5);
		}
		
		// Aussetzen Karten erstellen
		for (int i=(boniKarten*5)+sternKarten; i<(boniKarten*5)+sternKarten+aussetzenKarten; i++){
			stapel[i] = new Karte(6);
		}
		
		// Doppelte Lotte Karten erstellen
		for (int i=(boniKarten*5)+sternKarten+aussetzenKarten; i<(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten; i++){
			stapel[i] = new Karte(7);
		}
		
		// 1000er Karten erstellen
		for (int i=(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten; i<(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten+tausenderKarten; i++){
			stapel[i] = new Karte(8);
		}
		
		// Strasse Karten erstellen
		for (int i=(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten+tausenderKarten; i<(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten+tausenderKarten+strassenKarten; i++){
			stapel[i] = new Karte(9);
		}
		
		// Kleeblatt Karte erstellen
		for (int i=(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten+tausenderKarten+strassenKarten; i<(boniKarten*5)+sternKarten+aussetzenKarten+doppelteLotteKarten+tausenderKarten+strassenKarten+kleeblattKarten; i++){
			stapel[i] = new Karte(10);
		}
		mischen();
	}
	
	public void mischen(){
		Karte temp;
		Random rg = new Random();
		for (int i=kartenImSpiel-1; i>0; i--){
			int zufall = rg.nextInt(kartenImSpiel);
			if (zufall != i){
				temp = stapel[i]; // tausche
				stapel[i] = stapel[zufall];
				stapel[zufall] = temp;
			}
		}
	}
	
	public int kartenAnzahl(){
		return kartenImSpiel;
	}
	
	public Karte naechsteKarte(){
		if (kartenImSpiel == 0)
			mischen();
		kartenImSpiel--;
		return stapel[kartenImSpiel];
	}
	
	public void setKartenImSpiel(int anzahl){
		this.kartenImSpiel = anzahl;
	}
}
