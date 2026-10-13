package rechner;

public class Test {

	
	
	public static void main(String[] args) {
		// Variablen deklarieren
        String anzahl_auslastung_gb = "";
        int anzahl_gb = 0;
        double auslastung_gb = 0;
        double auslastung_pro_gb = 0;

        // Werte berechnen
        auslastung_gb = (0.2/0.136363636)*100;
        anzahl_gb = (int) Math.ceil(0.2/0.136363636);
        auslastung_pro_gb = auslastung_gb/anzahl_gb;
        String s = String.valueOf(auslastung_pro_gb);
        float auslastung_pro_gb_rounded = Float.parseFloat(s.substring(0,s.lastIndexOf('.')+2));
        String s2 = String.valueOf(auslastung_pro_gb_rounded);
        if (s2.substring(s2.length()-1).equals("0")){
            s2 = s2.substring(0, s2.lastIndexOf('.'));
        }
        else{
            s2 = s2.replace('.', ',');
        }
        anzahl_auslastung_gb = anzahl_gb + "x (" + s2 + "%)";
		
		
		/*
		double prod_per_tick = 0.2727272727272727;
		double verbr_summe = 0.2727272727272728;
		int anzahl_fischer = (int) Math.ceil(verbr_summe / prod_per_tick);
		double auslastung_fisch = (verbr_summe/prod_per_tick)*100;
		double auslastung_pro_fischer = auslastung_fisch/anzahl_fischer;
		String s = String.valueOf(auslastung_pro_fischer);
        float auslastung_pro_gb_rounded = Float.parseFloat(s.substring(0,s.lastIndexOf('.')+2));
        String s2 = String.valueOf(auslastung_pro_gb_rounded);
        if (s2.substring(s2.length()-1).equals("0")){
            s2 = s2.substring(0, s2.lastIndexOf('.'));
        }
        else{
        	s2 = s2.replace('.', ',');
        }
        */
		/*
		String anzahl_auslastung_gb = "";
        float produktionsdauer_gb1 = 441;
        float produktionsdauer_gb2 = 220;
        float auslastung_gb = (produktionsdauer_gb1/produktionsdauer_gb2)*100;
        int anzahl_gb = (int) Math.ceil(produktionsdauer_gb1/produktionsdauer_gb2);
        float auslastung_pro_gb = auslastung_gb / anzahl_gb;
        String s = String.valueOf(auslastung_pro_gb);
        float auslastung_pro_gb_rounded = Float.parseFloat(s.substring(0,s.lastIndexOf('.')+2));
        String s2 = String.valueOf(auslastung_pro_gb_rounded);
        if (s2.substring(s2.length()-1).equals("0")){
            s2 = s2.substring(0, s2.lastIndexOf('.'));
        }
        else{
        	s2 = s2.replace('.', ',');
        }
        System.out.println(anzahl_gb + "x (" + s2 + "%)");
        */
	}
}
