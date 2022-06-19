

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MAHER
 */

public class Successeur {
    private String[] etat = new String [9];
    private String[] new_etat1 = new String [9];
    private String[] new_etat2 = new String [9];
    private String[] new_etat3 = new String [9];
    private String[] new_etat4 = new String [9];
    private int case_vide_index;
    
	public Successeur(Combinaison comb) {
                this.etat = Arrays.copyOf(comb.Get_etat(), etat.length);
                this.new_etat1 = Arrays.copyOf(comb.Get_etat(), etat.length);
                this.new_etat2 = Arrays.copyOf(comb.Get_etat(), etat.length);
                this.new_etat3 = Arrays.copyOf(comb.Get_etat(), etat.length);
                this.new_etat4 = Arrays.copyOf(comb.Get_etat(), etat.length);
                this.case_vide_index = comb.Get_case_vide_index();		
	}
        
        private void  Deplacer_case_vide(String[] X,int a, int b){
            String temp;
            temp = X[a];
            X[a] = X[b];
            X[b] = temp;
        }	
    
	public List<Combinaison> successor(Combinaison comb) {
            
		List<Combinaison> list = new ArrayList<Combinaison>();

		//ON PEUT GLISSER LA CASE VIDE EN (HAUT)
		if(this.case_vide_index - 3 >= 0)
                {
                    this.Deplacer_case_vide(this.new_etat1,(this.case_vide_index - 3), this.case_vide_index);
                    Combinaison Comb1 = comb.Creer_fils(comb, this.new_etat1);
                    Comb1.Set_direction(DIRECTIONS.HAUT);
                    list.add(Comb1);
		}
		
		//ON PEUT GLISSER LA CASE VIDE EN (BAS)
		if(this.case_vide_index + 3 < 9)
                {
                    this.Deplacer_case_vide(this.new_etat2 ,(this.case_vide_index + 3), this.case_vide_index);
                    Combinaison Comb2 = comb.Creer_fils(comb, this.new_etat2);
                    Comb2.Set_direction(DIRECTIONS.BAS);
                    list.add(Comb2);                        
		}	
                
		//ON PEUT GLISSER LA CASE VIDE A (DROITE)
		if(((this.case_vide_index + 1) % 3 == 1) || ((this.case_vide_index + 1) % 3 == 2))
                {
                    this.Deplacer_case_vide(this.new_etat3,(this.case_vide_index + 1), this.case_vide_index);
                    Combinaison Comb3 = comb.Creer_fils(comb, this.new_etat3);
                    Comb3.Set_direction(DIRECTIONS.DROITE);
                    list.add(Comb3);              
		}	
                
                
		//ON PEUT GLISSER LA CASE VIDE A (GAUCHE)
		if(((this.case_vide_index - 1) % 3 == 0) || ((this.case_vide_index - 1) % 3 == 1))
                {
                    this.Deplacer_case_vide(this.new_etat4,(this.case_vide_index - 1), this.case_vide_index);
                    Combinaison Comb4 = comb.Creer_fils(comb, this.new_etat4);
                    Comb4.Set_direction(DIRECTIONS.GAUCHE);
                    list.add(Comb4);                     
		}
                
		return list;  
		 
	}    
}
