
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author MAHER
 */
enum DIRECTIONS{
    HAUT,
    BAS,
    DROITE,
    GAUCHE
}

public class Combinaison {
    
    private String[] etat = new String [9];
    private Combinaison parent;
    private List<Combinaison> fils;
    private int cout;
    private int profondeur;
    private int case_vide_index;
    private DIRECTIONS direction;

    //CONSTRUIRE UNE COMBINAISON
    public Combinaison(String [] etat) {
        this.etat = etat;
        this.parent = null;
        this.fils = new ArrayList<Combinaison>();
        this.direction = null;
        this.case_vide_index = this.Trouver_Case_Vide(etat);
    }
    
    // ETAT GETTER
    public String[] Get_etat(){
        return this.etat;
    }
    
    // PARENT GETTER
    public Combinaison Get_parent(){
        return this.parent;
    }
    
    // FILS GETTER
    public List<Combinaison> Get_fils(){
        return this.fils;
    }
    
    //COUT GETTER
    public int Get_cout(){
        return this.cout;
    }
    
    //PROFONDEUR GETTER
    public int Get_profonduer(){
        return this.profondeur;
    }
    
    //DIRECTION GETTER
    public DIRECTIONS Get_direction(){
        return this.direction;
    }
    
    //DIRECTION SETTER
    public void Set_direction(DIRECTIONS X){
        this.direction = X;
    }
    
    //COUT SETTER
    public void Set_cout(int X){
        this.cout = X;
    }
    
    //INDEX CASE VIDE GETTER
    public int Get_case_vide_index(){
        return this.case_vide_index;
    }
    
    public int Trouver_Case_Vide(String [] etat){
        return Arrays.asList(etat).indexOf("0");
    }
    
    public boolean isGoal(String[] etat_init, String[] etat_fin){
        return (Arrays.equals(etat_init, etat_fin));
    }


    
    public void Afficher_etat(){
        int i;
        for(i=0;i<9;i++){
            System.out.println("*****************L'ETAT ***********************");
            System.out.print("[");
            for (i=0;i<9;i++)
            {
                System.out.print(" ["+etat[i]+"] ");
            }
            System.out.println("]");            
        }
    }
    
    public void Afficher_infos_Combinaison(){
        this.Afficher_etat();
        System.out.println("PARENT :" + this.parent);
        System.out.println("FILS :" + this.fils);
        System.out.println("INDEX CASE VIDE :" + this.case_vide_index);
        System.out.println("DIRECTION A UTILISER :" + this.direction);
    }
    
    public Combinaison Creer_fils(Combinaison parent,String[] new_etat){
        Combinaison comb_fils = new Combinaison(new_etat);
        comb_fils.parent = parent;
        comb_fils.profondeur = parent.profondeur + 1;
        return comb_fils;
    }
    
    public int HashCode(String n)
    {
        int result = Integer.parseInt(n);
            return result%362897;
    }
}

