
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Info {

	public Queue<Combinaison> queue;
	public Stack<Combinaison> stack;
        public PriorityQueue<Combinaison> Dance_Table;
	public int time;
	private int maxQueueSize;
	public HashMap<Integer,Combinaison> Taboo_List; 
	
	
	public Info() {
		queue = new LinkedList<Combinaison>();
		stack = new Stack<Combinaison>();
                Dance_Table = new PriorityQueue<Combinaison>();
		time = 0;
		maxQueueSize = 0;
                Taboo_List = new HashMap<Integer,Combinaison>();
            }
	
        public void  makeDanceTable(Comparator c) {   
		Dance_Table = new PriorityQueue<Combinaison>(c);
	}
	public void incTime() { 
		time += 1;
	}
	
	public void queueSize() {   
		if(queue.size()>maxQueueSize) {
			maxQueueSize = queue.size();
		}
	}
	
	public void stackSize() {  
		if(stack.size()>maxQueueSize) {
			maxQueueSize = stack.size();
		}
	}
	
	public void pQueueSize() {  
		if(Dance_Table.size()>maxQueueSize) {
			maxQueueSize = Dance_Table.size();
		}
	}
	
	public int getTime() { 
		return time;
	}
	
	public int getSpace() {  
		return maxQueueSize;
	}
	
}

