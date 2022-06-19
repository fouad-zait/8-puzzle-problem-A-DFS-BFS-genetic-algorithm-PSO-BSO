import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author MAHER
 */
public class Heuristique {
    
    public Heuristique(){
        
    }
    
    public int dist2correct_pos(String [] etat_actuel, String[] etat_final) {
            int h = 0;
            for(int i=0; i<etat_actuel.length; i++) {
                int correct_position = Arrays.asList(etat_final).indexOf(etat_actuel[i]);
                h = h + Math.abs(correct_position - i);
            }
            return h;
    } 
    
}
