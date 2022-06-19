
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PathActions {
    
	List<Combinaison> path;
	Info info; 
	
	public PathActions(Combinaison Comb_init, Combinaison Comb_actuel, Info info) { 
		path = this.getPath(Comb_init, Comb_actuel);
		this.info = info;
	}
	
	
	private List<Combinaison> getPath(Combinaison Comb_init, Combinaison Comb_actuel) {  
		Combinaison tempNode = Comb_actuel;
		List<Combinaison> list = new ArrayList<Combinaison>();
		
		while(!(tempNode.equals(Comb_init))) {
			list.add(tempNode);
			tempNode = tempNode.Get_parent();
			
		}
		list.add(Comb_init);
		return list;  
	}
}
