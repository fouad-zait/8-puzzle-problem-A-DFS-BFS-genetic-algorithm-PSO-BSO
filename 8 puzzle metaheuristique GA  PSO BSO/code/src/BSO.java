/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author MAHER
 */
public class BSO {
    private Combinaison Comb_init;
    private Combinaison Comb_fin;
    private List Solution;
    private Info info_solution;
    Info info = new Info();
    private Integer Max_iter = 100000;
    
    
    public BSO(Combinaison Comb_init, Combinaison Comb_fin){
        this.Comb_init = Comb_init;
        this.Comb_fin = Comb_fin;
    }
    
    public List Solution(){
        return this.Solution;
    }
    
    public Info Info_Solution(){
        return this.info_solution;
    } 
    
    public int Calcule_Cout(Combinaison Comb_actuel, Combinaison Comb_fin){
        Heuristique heuris = new Heuristique();
        int h_n = heuris.dist2correct_pos(Comb_actuel.Get_etat(), Comb_fin.Get_etat());
        int g_n = Comb_actuel.Get_profonduer();
        Comb_actuel.Set_cout(g_n + h_n);
        return (g_n + h_n);
    }
  
    
    public boolean GoalFond(Combinaison C,Info info){
        if(C.isGoal(C.Get_etat(),Comb_fin.Get_etat())) 
        {
                PathActions p = new PathActions(Comb_init,C,info); 
                Solution = p.path;
                info_solution = p.info;
                return true;
        }
        else
        {
            return false;
        }
    }
    
    private class fComparator implements Comparator<Combinaison>{  

        @Override
        public int compare(Combinaison a, Combinaison b) {
                 return (Calcule_Cout(a, Comb_fin) - Calcule_Cout(b, Comb_fin));        
        }
    }
    
    public Boolean Chercher()
    {
            StringBuilder builder = new StringBuilder();    

            Combinaison Bee;
            String Bee_hash;


            //CREATE DANCE TABLE 
            info.makeDanceTable(new BSO.fComparator());


            //CREATE BEE INIT
             Bee = Comb_init;


            //TEST IF INIT BEE IS A SOLUTION
            if(GoalFond(Bee, info)) {
                    return true;
            }
            //STRAT BSO SEARCH
            else
            {
                //SET 1ST ITERATION
                int iteration = 0;

                //SET OPTIMAL TO FALSE
                boolean Optimal_found = false;

                while (iteration < Max_iter && Optimal_found==false)
                {

                    for (String value : Bee.Get_etat()) {
                        builder.append(value);
                    }
                    Bee_hash = builder.toString(); 
                    builder.setLength(0);

                    //INSERT "SREF" IN TABOOLIST
                    info.Taboo_List.put(Bee.HashCode(Bee_hash), Bee);

                    //DETREMINE SEARCH POINTS FROM "SREF"
                    Successeur s = new Successeur(Bee); 
                    List<Combinaison> SearchPoints = s.successor(Bee);


                    //SEARCH STARTING WITH THE ASSIGNED SOLUTION
                    SearchPoints.forEach((BeeParticle) -> 
                    {
                        Combinaison Comb_actuel = BeeParticle;
                        for (int i=0;i<10;i++){
                            Successeur childbee = new Successeur(Comb_actuel); 
                            List<Combinaison> list = childbee.successor(Comb_actuel);
                            Integer childsize = list.size();

                            Integer choix = (int)(Math.random() * (((childsize-1) - 0) + 1));
                            Combinaison temp = list.get(choix);
                            for (String value : temp.Get_etat()) {
                                builder.append(value);
                            } 

                            String temp_hash = builder.toString();
                            builder.setLength(0);
                            boolean ans = info.Taboo_List.containsKey(temp.HashCode(temp_hash)); 

                            if (ans==false) {
                                Comb_actuel = temp;
                                info.Dance_Table.add(Comb_actuel);      
                            }  
                     
                        }
                        

/*heeeer*/


                    });

                    //ASSIGN BEST SOLUTION FROM DANCE TABLE TO SREF
                    Bee = info.Dance_Table.poll();
 
                    info.incTime();

                    //CHECK IF SOLUTION FOUND
                    if(GoalFond(Bee, info)) {
                        Optimal_found = true;
                    }
                    else
                    {
                        iteration++;
                    }

                }
                return Optimal_found;
            }

    }

}
