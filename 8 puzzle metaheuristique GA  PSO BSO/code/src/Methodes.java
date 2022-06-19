import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Methodes {
	int popu;
	float[]childs1;
	int k=0;
	int h=0;
	int val;
	int g=0;
	int f;
	int v[] = new int[9];
	boolean not_yet;
	boolean found=false;
	Node1 n1= new Node1();
	Queue<Node1> open = new LinkedList<Node1>(); 
	Queue<Population> populations = new LinkedList<Population>(); 
	static Antecedent[][] antecedent = new Antecedent[362897][100];
	static ArrayList<Integer> costs= new ArrayList<Integer>();
	static ArrayList<Integer> Actions = new ArrayList<>();
	int prof;
	Stack<Node1>stack= new Stack();
	int[][] closed = new int[362897][100];
	float[][] theFitesset = new float[2][popu];
	float[][] childs;
	Open[][] ouvert = new Open[362897][10];
	StringBuffer textArea = new StringBuffer();
	StringBuffer node = new StringBuffer();
	StringBuffer result = new StringBuffer();
	Node1 right;
	Node1 left;
	Node1 up;
	Node1 down;
	int [] act;
	ArrayList<Population> populations1 = new ArrayList<Population>();
	
	
	
	/***********************************************************partie methods*************************************************************/
	public StringBuffer fillInitialMatrix() {
		Random rand = new Random();
		for(int j=0;j<9;j++) {
			not_yet =true;
			while(not_yet) {
				val = rand.nextInt();
				if(val<0) {
					val=-val;
				}	
				val=val%(9);
				if(k==0)
				{
					not_yet=false;
				}
				else {
					for(int l=0;l<k;l++)
					{
						if(v[l]==val) {
							found=true;
							break;
						}
						else {
							found=false;
						}
					}
				}
				if(found==false) {
					not_yet=false;
				}
			}
			v[k]=val;
			node.append(String.valueOf(val));
			k++;
		}
		
		return node;
		
		
	}
	/*************************************************A* *********************************************************/
	public int HashFct(int n)
	{
		int hash;
		hash=n%362897;
		return hash;
	}
	public void addToAntecedent(Node1 n,Node1 np)
	{
		Antecedent a = new Antecedent();
		int k=HashFct(Integer.parseInt(n.node.toString()));
		boolean better = true;
		if(antecedent[k][0]==null)
		{
			a.cost_child=0;
			antecedent[k][0]=a;
		}
		for(int i=1;i<=antecedent[k][0].cost_child;i++)
		{
			if(Integer.parseInt(antecedent[k][i].id_child)==Integer.parseInt(n.node.toString()))
			{
				if(antecedent[k][i].cost_child<=n.f)
				{
					better=false;
				}
			}
		}
		if(better==true)
		{
			a = new Antecedent(); 
			k=HashFct(Integer.parseInt(n.node.toString()));
			if(antecedent[k][0]==null)
			{
				a.cost_child=0;
				antecedent[k][0]=a;
			}
			a.cost_child=antecedent[k][0].cost_child+1;
			antecedent[k][0]=a;
			a = new Antecedent(); 
			a.id_parent=np.node.toString();
			a.cost_parent=np.f;
			a.id_child=n.node.toString();
			a.cost_child=n.f;
			antecedent[k][antecedent[k][0].cost_child]=a;
			orgenizeOpen(n);
		}
	}
	public void orgenizeOpen(Node1 n)
	{
		int val;
		Node1 o = new Node1();
		int size=open.size();
		int i;
		Open oo = new Open();
		boolean better = true;
		int k=HashFct(Integer.parseInt(n.node.toString()));
		if(ouvert[k][0]==null)
		{
			oo.f=1;
			ouvert[k][0]=oo;
			oo = new Open();
			oo.node=n.node;
			oo.f=n.f;
			oo.g=n.g;
			ouvert[k][ouvert[k][0].f]=oo;
			costs.add(n.f);
		}
		else
		{
			oo = new Open();
			oo.f=ouvert[k][0].f+1;
			ouvert[k][0]=oo;
			oo = new Open();
			oo.node=n.node;
			oo.f=n.f;
			oo.g=n.g;
			ouvert[k][ouvert[k][0].f]=oo;
			costs.add(n.f);
			/*for(int l=1;l<ouvert[k][0].f;l++)
			{
				if(ouvert[k][l].node==n.node)
				{
					if(ouvert[k][l].f<=n.f)
					{System.out.println("yes");
						better=false;
					}
				}
			}*/
		}
		/*if(better=true)
		{
			oo = new Open();
			oo.f=ouvert[k][0].f+1;
			ouvert[k][0]=oo;
			oo = new Open();
			oo.node=n.node;
			oo.f=n.f;
			oo.g=n.g;
			ouvert[k][ouvert[k][0].f]=oo;
			costs.add(n.f);
		}*/
		/*if(size!=0)
		{
			for(i=0;i<size;i++)
			{
				o=open.poll();
				val=o.f;
				costs.remove(costs.indexOf(o.f));
				if(Integer.parseInt(n.node.toString())==Integer.parseInt(o.node.toString()))
				{
					
					if(n.f<o.f)
					{
						open.add(n);
						costs.add(n.f);
						break;
					}
					else {
						open.add(o);
						costs.add(val);
					}
				}
				else {
					open.add(o);
					costs.add(val);
				}
			}
			if(i==size)
			{
				open.add(n);
				costs.add(n.f);
			}
		}
		else
		{
			open.add(n);
			costs.add(n.f);
		}*/
	}
	public int ManhatenDistance(Node1 n,StringBuffer s)
	{
		int h=0;
		if(n.node!=null)
		{
			for(int i=0;i<n.node.length();i++)
			{
				int d=0;
				String c = new String();
				c=""+n.node.charAt(i);
				d=Math.abs(n.node.indexOf(c)-s.indexOf(c));
				d=(int)(d/Math.sqrt(n.node.length())+d%Math.sqrt(n.node.length()));
				h=h+d;
			}
		}
		return h;
	}
	public int missPlaced(Node1 n,StringBuffer s)
	{
		int h=0;
		if(n.node!=null)
		{
			for(int i=0;i<n.node.length();i++)
			{
				if(n.node.charAt(i)!='0')
				{
					if(s.charAt(i)!=n.node.charAt(i))
					{
						h=h+1;
					}
				}
			}
		}
		return h;
	}
	public void expand(StringBuffer s,Node1 n, int w)
	{
		n.right = new Node1();
		n.left = new Node1();
		n.up = new Node1();
		n.down = new Node1();
		k=HashFct(Integer.parseInt(n.node.toString()));
		/**************************************************create all nodes**********************************************************************/
		n.left=left(s,w,n);
		n.right=right(s,w,n);
		n.up=up(s,w,n);
		n.down=down(s,w,n);
		/*if(n.left!=null)
		{
			//costs.add(n.left.f);
			//System.out.println("left "+n.left.node+" "+n.left.f+" "+n.left.g+" "+n.left.h);
		}
		if(n.right!=null)
		{
			//costs.add(n.right.f);
			//System.out.println("right "+n.right.node+" "+n.right.f+" "+n.right.g+" "+n.right.h);
		}
		if(n.up!=null)
		{
			//costs.add(n.up.f);
			//System.out.println("up "+n.up.node+" "+n.up.f+" "+n.up.g+" "+n.up.h);
		}
		if(n.down!=null)
		{
			//costs.add(n.down.f);
			//System.out.println("down "+n.down.node+" "+n.down.f+" "+n.down.g+" "+n.down.h);
		}*/
	}
	public Node1 left(StringBuffer s, int w,Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		
		if(i%Math.sqrt(n.node.length())!=0)
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt(i-1) );
			n.node.setCharAt((i-1),tmp );
			n.g=node.g+1;
			if(w==1)
			{
				n.h=missPlaced(n,s);
			}
			else if(w==2)
			{
				n.h=ManhatenDistance(n,s);
			}
			n.f=n.g+n.h;
		}
		else {
			n=null;
		}
		return n;
	}
	public Node1 right(StringBuffer s,int w,Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		if(i%Math.sqrt(n.node.length())!=Math.sqrt(n.node.length())-1)
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt(i+1) );
			n.node.setCharAt((i+1),tmp );
			n.g=node.g+1;
			if(w==1)
			{
				n.h=missPlaced(n,s);
			}
			else if(w==2)
			{
				n.h=ManhatenDistance(n,s);
			}
			n.f=n.g+n.h;
			
		}
		else {
			n=null;
		}
		return n;
	}
	public Node1 up(StringBuffer s,int w,Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		if(i>=Math.sqrt(n.node.length()))
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt((int)(i-Math.sqrt(n.node.length()))));
			n.node.setCharAt((int)(i-Math.sqrt(n.node.length())),tmp );
			n.g=node.g+1;
			if(w==1)
			{
				n.h=missPlaced(n,s);
			}
			else if(w==2)
			{
				n.h=ManhatenDistance(n,s);
			}
			n.f=n.g+n.h;
		}
		else {
			n=null;
		}
		return n;
	}
	public Node1 down(StringBuffer s,int w,Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		if(i<n.node.length()-Math.sqrt(n.node.length()))
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt((int)(i+Math.sqrt(n.node.length()))));
			n.node.setCharAt((int)(i+Math.sqrt(n.node.length())),tmp );
			n.g=node.g+1;
			if(w==1)
			{
				n.h=missPlaced(n,s);
			}
			else if(w==2)
			{
				n.h=ManhatenDistance(n,s);
			}
			n.f=n.g+n.h;
		}
		else {
			n=null;
		}
		return n;
	}
	public Node1 getMinimalNode1()
	{
		
		/*int min;
		int size=open.size();
		Node1 minNode1= new Node1();
		Collections.sort(costs);
		min=costs.get(0);
		costs.remove(0);
		for(int i=0;i<size;i++)
		{
			minNode1=open.poll();
			if(minNode1.f==min)
			{
				break;
			}
			open.add(minNode1);
		}
		return minNode1;*/
		int min;
		int size=ouvert.length;
		Node1 minNode1= new Node1();
		Collections.sort(costs);
		if(costs.size()!=0)
		{
			min=costs.get(0);
			costs.remove(0);
			a:
				for(int i=0;i<size;i++)
				{
					if(ouvert[i][0]!=null)
					{
						for(int j=1;j<=ouvert[i][0].f;j++)
						{
							if(ouvert[i][j]!=null)
							{
								if(ouvert[i][j].f==min)
								{
									minNode1.node=ouvert[i][j].node;
									minNode1.g=ouvert[i][j].g;
									minNode1.f=ouvert[i][j].f;
									Open o = new Open();
									ouvert[i][j]=o;
									break a;
								}
							}
						}
					}
				}
		}
		else {
			textArea.append("Goal not found!!");
		}
		
		return minNode1;
	}
	public Node1 AEtoile(Node1 n, StringBuffer s, int w)
	{
		Antecedent a = new Antecedent();
		a = new Antecedent();
		k=HashFct(Integer.parseInt(n.node.toString()));
		if(antecedent[k][0]==null)
		{
			a.cost_child=0;
			antecedent[k][0]=a;
		}
		a.cost_child=antecedent[k][0].cost_child+1;
		antecedent[k][0]=a;
		a = new Antecedent();
		a.id_parent=null;
		a.cost_parent=-1;
		a.id_child=n.node.toString();
		a.cost_child=n.f;
		
		antecedent[k][antecedent[k][0].cost_child]=a;
		boolean goal=false;
		while(goal==false)
		{
			int k=0;
			expand(s,n,w);
			//System.out.println("/*********************************************************************************/");
			if(n.left!=null)
			{
				addToAntecedent(n.left,n);
			}
			if(n.right!=null)
			{
				addToAntecedent(n.right,n);
			}
			if(n.up!=null)
			{
				addToAntecedent(n.up,n);
			}
			if(n.down!=null)
			{
				addToAntecedent(n.down,n);
			}
			n=getMinimalNode1();
			if(n==null)
			{
				goal=true;
			}
			//System.out.println("Node1 chosen: " +n.node );
			if(Integer.parseInt(n.node.toString())==Integer.parseInt(s.toString()))
			{
				//System.out.println("******************************************************************************************************");
				goal=true;
			}
		}
		
		//System.out.println("Goal Found!!!!!!!!!!!!!! "+ n.f+" "+ antecedent[HashFct(Integer.parseInt(n.node.toString()))][1].cost_child+" ligne "+HashFct(Integer.parseInt(n.node.toString())));
		return n;
	}
	
	
	public int get_action(Antecedent a)
	{
		int i1,i2;
		int action=0;
		i1=a.id_parent.indexOf("0");
		i2=a.id_child.indexOf("0");
		//left
		if(i1==(i2+1)) {
			action = 1;
		}
		//right
		if(i1==(i2-1)) {
			action = 2;
		}
		//up
		if(i1==(i2+Math.sqrt(a.id_parent.length()))) {
			action = 3;
		}
		//down
		if(i1==(i2-Math.sqrt(a.id_parent.length()))) {
			action = 4;
		}
		return action;
	}
	public void get_path(Node1 b,Node1 n)
	{
		int p=0;
		int k= HashFct(Integer.parseInt(b.node.toString()));
		p=look_up(b.node.toString(),b.f);
		boolean found=false;
		while(found==false)
		{
			String parent = new String();
			//System.out.println("Actions: "+ antecedent[k][p].id_parent+ " and "+ antecedent[k][p].id_child);
			//System.out.println("Actions done: "+get_action(antecedent[k][p]));
			Actions.add(get_action(antecedent[k][p]));
			parent=antecedent[k][p].id_parent;
			p=look_up(antecedent[k][p].id_parent,antecedent[k][p].cost_parent);
			k=HashFct(Integer.parseInt(parent));
			if((Integer.parseInt(antecedent[k][p].id_parent)==Integer.parseInt(n.node.toString()))&&(n.f==antecedent[k][p].cost_parent))
			{
				//System.out.println("Actions done: "+get_action(antecedent[k][p]));
				Actions.add(get_action(antecedent[k][p]));
				break;
			}
			
		}
		//System.out.println("Worked");
		System.out.println(Actions);
		System.out.println(Actions.size());
	}
	public void get_path2(Node1 b,Node1 n)
	{
		int p=0;
		p=look_up2(b);
		int k= HashFct(Integer.parseInt(b.node.toString()));
		while(Integer.parseInt(antecedent[k][p].id_child)!=Integer.parseInt(n.node.toString()))
		{
			String parent = new String();
			//System.out.println("achid "+Integer.parseInt(antecedent[k][p].id_child)+" n "+Integer.parseInt(n.node.toString())+" acout "+ antecedent[k][p].cost_child+" nf "+n.f);
			
			//System.out.println("Actions: "+ antecedent[k][p].id_parent+ " and "+ antecedent[k][p].id_child);
			//System.out.println("Actions done: "+get_action(antecedent[k][p]));
			Actions.add(get_action(antecedent[k][p]));
			parent=antecedent[k][p].id_parent;
			p=look_up(antecedent[k][p].id_parent,antecedent[k][p].cost_parent);
			k=HashFct(Integer.parseInt(parent));
			if(Integer.parseInt(antecedent[k][p].id_parent)==Integer.parseInt(n.node.toString()))
			{
				//System.out.println("Actions done: "+get_action(antecedent[k][p]));
				Actions.add(get_action(antecedent[k][p]));
				break;
			}
		}
		//System.out.println("Worked");
		System.out.println(Actions);
		System.out.println(Actions.size());
	}
	public int look_up2(Node1 n){
		int p=0;
		int k= HashFct(Integer.parseInt(n.node.toString()));
		for(int i=1; i<antecedent[k][0].cost_child;i++)
		{
			if(antecedent[k][i].id_child.equalsIgnoreCase(n.node.toString()))
			{
				p=i;
				//System.out.println("an2 "+antecedent[k][i].id_child+" i"+i);
				break;
			}
		}
		return p;
	}
	public int look_up(String n, int f){
		int p=0;
		int k= HashFct(Integer.parseInt(n));
		
		for(int i=1; i<=antecedent[k][0].cost_child;i++)
		{
			if((Integer.parseInt(antecedent[k][i].id_child)==Integer.parseInt(n))&&(f==antecedent[k][i].cost_child))
			{
				p=i;
				break;
			}
		}
		return p;
	}
	/******************************************************************dfs************************************************/
	public void expand_for_dfs(StringBuffer s,Node1 n)
	{
		n.right = new Node1();
		n.left = new Node1();
		n.up = new Node1();
		n.down = new Node1();
		k=HashFct(Integer.parseInt(n.node.toString()));
		closed[k][closed[k][0]]=Integer.parseInt(n.node.toString());
		closed[k][0]=closed[k][0]+1;
		/**************************************************create all nodes**********************************************************************/
		n.left=left(s,0,n);
		n.right=right(s,0,n);
		n.up=up(s,0,n);
		n.down=down(s,0,n);
	
	}
	public void push(Node1 n1,Node1 n2) {
		Antecedent a = new Antecedent();
		a.id_parent=n1.node.toString();
		a.id_child=n2.node.toString();
		antecedent[k][antecedent[k][0].cost_child]=a;
		//System.out.println("a "+antecedent[k][antecedent[k][0].cost_child].id_child+ " "+ antecedent[k][0].cost_child);
		a = new Antecedent();
		a.cost_child=antecedent[k][0].cost_child+1;
		antecedent[k][0]=a;
	    n2.prof=n1.prof;
	    stack.push(n2);
	}
	public Node1 profondeur(Node1 n, StringBuffer s, int p) {
		
		boolean not_found=false;
		
		//System.out.println("Donner la limite de profondeur\n");
		int prof =p;
		Antecedent a =new Antecedent();
		
		
		for(int i=0;i<362897;i++)
		{
			closed[i][0]=1;
		}
		for(int i=0;i<362897;i++)
		{
			a.cost_child=1;
			antecedent[i][0]=a;
		}
		a =new Antecedent();
		k=HashFct(Integer.parseInt(n.node.toString()));
		a.id_parent=null;
		
		a.id_child=n.node.toString();
		antecedent[k][antecedent[k][0].cost_child]=a;
		a = new Antecedent();
		a.cost_child=antecedent[k][0].cost_child+1;
		antecedent[k][0]=a;
		boolean goal=false;
		n.prof=0;
		
		
		while(goal==false && not_found==false){
			
			if(n.prof<prof){
		
		/*System.out.println("avant expand");*/
		expand_for_dfs(s,n);
		/*System.out.println("apres expand");*/
		
		n.prof++;
		
		if(n.right!=null) {
			k=HashFct(Integer.parseInt(n.right.node.toString()));
			boolean existe = false;
			for(int i=1;i<antecedent[k][0].cost_child;i++)
			{
				if(Integer.parseInt(antecedent[k][i].id_child)==Integer.parseInt(n.right.node.toString()))
				{
					existe=true;
				}
			}
			if(existe==false) {
				push(n,n.right);
				//System.out.println("right "+n.right.node);
			
		}}
		if(n.left!=null) {
			k=HashFct(Integer.parseInt(n.left.node.toString()));

			boolean existe = false;
			for(int i=1;i<antecedent[k][0].cost_child;i++)
			{
				if(Integer.parseInt(antecedent[k][i].id_child)==Integer.parseInt(n.left.node.toString()))
				{
					existe=true;
				}
			}
			if(existe==false) {
				push(n,n.left);
				//System.out.println("left "+n.left.node);
			}
		}
		if(n.up!=null) {
			k=HashFct(Integer.parseInt(n.up.node.toString()));
			boolean existe = false;
			for(int i=1;i<antecedent[k][0].cost_child;i++)
			{

				if(Integer.parseInt(antecedent[k][i].id_child)==Integer.parseInt(n.up.node.toString()))
				{
					
					existe=true;
				}
			}	
			if(existe==false) {
				
				push(n,n.up);
				//System.out.println("up "+n.up.node);
		}}
		if(n.down!=null) {
			k=HashFct(Integer.parseInt(n.down.node.toString()));
			boolean existe = false;
			for(int i=1;i<antecedent[k][0].cost_child;i++)
			{
				if(Integer.parseInt(antecedent[k][i].id_child)==Integer.parseInt(n.down.node.toString()))
				{
					existe=true;
				}
			}	
			if(existe==false) {
				push(n,n.down);
				//System.out.println("down "+n.down.node);
		}
			
		}}	
		if(!stack.isEmpty()) {
			n=stack.pop();
			//System.out.println("node choosen :"+n.node);
			}
		
		else {
			textArea.append("Goal not Found!!!!");
			//System.out.println("goal not found with dept = "+prof);
		not_found=true;
		
		}
		if(Integer.parseInt(n.node.toString())==Integer.parseInt(s.toString()))
		{
			k=HashFct(Integer.parseInt(n.node.toString()));
			//System.out.println("Elle eest ajouter "+ antecedent[k][1].id_child);
			//System.out.println("GOAL FOUND******************************************************************************************************");
			goal=true;
		}
		}
		return n;
	}
	/******************************************bfs******************************************************/
	public void setPere(Node1 pere,String move,Node1 n)
	{
		n.pere=pere;
		n.move=move;
	}
	public void expandL(Node1 n)
	{
		right = new Node1();
		left = new Node1();
		up = new Node1();
		down = new Node1();
		n.left=left(n.node,0,n);
		if(n.left!=null)
		{
			setPere(n,"left",n.left);
		}
		
		n.right=right(n.node,0,n);
		if(n.right!=null)
		{
			setPere(n,"right",n.right);
		}
		
		n.up=up(n.node,0,n);
		if(n.up!=null)
		{
			setPere(n,"up",n.up);
		}
		
		n.down=down(n.node,0,n);
		if(n.down!=null)
		{
			setPere(n,"down",n.down);
		}
		
		
	}
	public Node1 insererFilsOuvr(StringBuffer goal)
	{
		
		//int tabP[]=new int[2];
		//ArrayList<int []> solution=new ArrayList<int []>();
		boolean continu=true;
		if (open.isEmpty())
		{
			return null;
		}
		else
		{
			
			while(continu) {
			
			Node1 noeud=new Node1();
			noeud=open.remove();
			int nb=Integer.parseInt(noeud.node.toString());
			
			int h=HashFct(nb);
			
			closed[h][0]++;
			closed[h][closed[h][0]]=nb;
			expandL(noeud);
			//System.out.println("xpnd");
			if(noeud.up!=null && noeud.up.node!=null)
			{
				int nb1=Integer.parseInt(noeud.up.node.toString());
				//System.out.println("up is 1 "+nb1);
				if (nb1!=Integer.parseInt(goal.toString()))
				{				
					int h1=HashFct(nb1);
					if(closed[h1][0]==0)
					{
						open.add(noeud.up);
					}
					else
					{
						boolean exist=false;
						for (int j=0;j<8;j++)
						{
							if (closed[h1][j+1]==nb1){exist=true;}
						}
						if (exist==false) {open.add(noeud.up);}
					}
					
				}
				else 
				{
					continu=false;
					return noeud.up;
				}
			}
			if(noeud.down!=null && noeud.down.node!=null)
			{
				int nb1=Integer.parseInt(noeud.down.node.toString());
				//System.out.println("down is "+ nb1);
				if (nb1!=Integer.parseInt(goal.toString()))
				{
					int h1=HashFct(nb1);
					if(closed[h1][0]==0)
					{
						
						open.add(noeud.down);
					}
					else
					{
						boolean exist=false;
						for (int j=0;j<8;j++)
						{
							if (closed[h1][j+1]==nb1){exist=true;}
						}
						if (exist==false) {open.add(noeud.down);}
					}
				}
				else 
				{
					continu=false;
					return noeud.down;
				}
			}
			if(noeud.left!=null && noeud.left.node!=null)
			{
				int nb1=Integer.parseInt(noeud.left.node.toString());
				//System.out.println("lft is "+nb1);
				if (nb1!=Integer.parseInt(goal.toString()))
				{
					int h1=HashFct(nb1);
					//System.out.println("h de lft"+h1);
					if(closed[h1][0]==0)
					{
						open.add(noeud.left);
					}
					else
					{
						boolean exist=false;
						for (int j=0;j<8;j++)
						{
							if (closed[h1][j+1]==nb1){exist=true;}
						}
						if (exist==false) {open.add(noeud.left);}
					}
				}
				else 
				{
					continu=false;
					return noeud.left;
				}
			}
			if(noeud.right!=null && noeud.right.node!=null)
			{
				int nb1=Integer.parseInt(noeud.right.node.toString());
				//System.out.println("riht is "+nb1);
				if (nb1==Integer.parseInt(goal.toString()))
				{
					continu=false;
					return noeud.right;
				}
				else
				{
					int h1=HashFct(nb1);
					if(closed[h1][0]==0)
					{
						open.add(noeud.right);
					}
					else
					{
						boolean exist=false;
						for (int j=0;j<8;j++)
						{
							if (closed[h1][j+1]==nb1){exist=true;}
						}
						if (exist==false) {open.add(noeud.right);}
					}
				}
				
			}//if noeud right
			
			
		}//while
	 
	  }//else is empty
		return null;
	}
	public Stack chainage(Node1 noeud)
	{
		Stack stack=new Stack();
		while(noeud.pere!=null)
		{
			stack.push(noeud.move);
			stack.push(noeud.pere.node);
			noeud=noeud.pere;
		}
		return stack;
	}
	/*******************************************rest***************************************************/
	public int solvable(String nm, StringBuffer s)
	{
		int k=0;
		boolean done=false;
		/*String sm = new String("");
		for(int i=0;i<nm.length();i++)
		{
			sm=sm+nm.charAt(i);
			if(nm.charAt(i)!='0')
			{
				sm=sm+nm.charAt(i);
			}
		}
		System.out.println(sm);
		for(int i=0;i<sm.length();i++)
		{
			for(int j=i+1;j<sm.length();j++)
			{
				int i1=Character.getNumericValue(sm.charAt(i));
				int j1=Character.getNumericValue(sm.charAt(j));
				if(j1>i1)
				{
					k++;
				}
			}
		}*/
		for(int i=0; i<s.length();i++)
		{
			int j=0;
			if(nm.charAt(i)!='0')
			{
				a:
					while(j<s.length())
					{
						if(nm.charAt(i)!=s.charAt(j))
						{
							if(s.charAt(j)!='0')
							{
								k++;
							}
		 				}
						if(s.charAt(j)==nm.charAt(i)) {
							s.setCharAt(s.indexOf(s.charAt(j)+""),'0');
							System.out.println(s.charAt(j)+" "+ k);
							break a;
						}
						j++;
					}
			}
		}
		return k;
	}
	
	
	
	
	
	public void Population(int n, Node1 node,int popu)
	{
		act = new int[4];
		Population parent = new Population();
		int etat_pred=-1;
		int k=node.node.indexOf("0");
		if(k%3==0)
		{
			act[1]=2;
		}
		if(k%3==1)
		{
			act[0]=1;
			act[1]=1;
		}
		if(k%3==2)
		{
			act[0]=2;
		}
		if((k>=3)&&(k<=5))
		{
			act[2]=1;
			act[3]=1;
		}
		if((k>=6)&&(k<=8))
		{
			act[2]=2;
		}
		if((k>=0)&&(k<=2))
		{
			act[3]=2;
		}
		for(int i=0;i<n;i++)
		{
			parent = new Population();
			String ss = new String();
			int [] act1 = new int[4];
			act1=act.clone();
			/*for(int l=0;l<4;l++)
			{
				act1[l]=act[l];
				//System.out.println(act1[l]);
			}*/
			for(int j=0;j<popu;j++)
			{
				/*for(int l=0;l<4;l++)
				{
					//System.out.println(act1[l]);
				}*/
				Random rand = new Random();
				float rr= rand.nextFloat();
				int r = (int)(rr/0.25);
				
				if(etat_pred==-1)
				{
					if(act1[r]!=0)
					{//System.out.println("n "+rr);
						//System.out.println("etat pred "+etat_pred+" r "+r);
						parent.p[j]=rr;
						ss=ss+", "+Float.toString(parent.p[j]);
						act1[r]=act1[r]-1;
						etat_pred=r;
						if((r+1)%2==0)
						{
							act1[(r-1)]=act1[(r-1)]+1;
						}
						else
						{
							act1[(r+1)]=act1[(r+1)]+1;
						}
					}
					else
					{
						j=j-1;
					}
				}
				else if(((etat_pred+r)!=1)&&((etat_pred+r)!=5))
				{
					if(act1[r]!=0)
					{//System.out.println("n "+rr);
						//System.out.println("etat pred "+etat_pred+" r "+r);
						parent.p[j]=rr;
						ss=ss+", "+Float.toString(parent.p[j]);
						act1[r]=act1[r]-1;
						etat_pred=r;
						if((r+1)%2==0)
						{
							act1[(r-1)]=act1[(r-1)]+1;
						}
						else
						{
							act1[(r+1)]=act1[(r+1)]+1;
						}
					}
					else
					{
						j=j-1;
					}
				}
				else
				{
					j=j-1;
				}
			}
			//System.out.println(ss);
			populations.add(parent);
		}
	}
	public Node1 applySolution(Node1 test, StringBuffer s,float[] p, int popu)
	{
		for(int j=0;j<p.length;j++)
		{
			if((p[j]>=0)&&(p[j]<=0.25))
			{//System.out.println("1 "+test.node);
				test=left1(test);
				//System.out.println("2 "+test.node);
			}
			else if((p[j]>=0.25)&&(p[j]<=0.5))
			{
				//System.out.println("1 "+test.node);
				test=right1(test);
				//System.out.println("2 "+test.node);
			}
			else if((p[j]>=0.5)&&(p[j]<=0.75))
			{//System.out.println("1 "+test.node);
				test=up1(test);
				//System.out.println("2 "+test.node);
			}
			else if((p[j]>=0.75)&&(p[j]<=1))
			{//System.out.println("1 "+test.node);
				test=down1(test);
				//System.out.println("2 "+test.node);
			}
			
			if(test.node.toString().equalsIgnoreCase(s.toString()))
			{
				System.out.println("goal found"+j);
				test.f=17;
				test.g=j;
				return test;
			}
		}
		test.f=missPlaced(test,s);;
		test.g=popu;
		return test;
	}
	public Population Fitness(Node1 n, StringBuffer s)
	{
		costs= new ArrayList<Integer>();
		//System.out.println("/***************debut fitness***********************/");
		int taille=populations.size();
		for(int i=0;i<taille;i++)
		{
			Population parent = new Population();
			parent=populations.remove();
			Node1 test = new Node1();
			test=n;
			test=applySolution(test,s,parent.p,popu);
			if(test.f==17)
			{
				populations.add(parent);
				return parent;
			}
			parent.fitness=missPlaced(test,s);
			costs.add(missPlaced(test,s));
			//System.out.println(test.node+ " f= "+parent.fitness);
			populations.add(parent);
		}
		//System.out.println("apres fitness there is "+populations.size());
		return null;
	}
	public void get_fitess(int popu)
	{
		//System.out.println("99999999999999999999 in get fitesst" + costs.size());
		int min1, min2;
		int size=populations.size();
		Population parent = new Population();
		Collections.sort(costs);
		
		if(costs.size()!=0)
		{
			min1=costs.get(0);
			costs.remove(0);
			min2=costs.get(0);
			costs.remove(0);
			costs.add(min1);
			costs.add(min2);
			int j=0;
			for(int i=0;i<size;i++)
			{
				parent=populations.remove();
				if((parent.fitness==min1)||(parent.fitness==min2))
				{
					theFitesset[j] = parent.p.clone();
					j=j+1;
				}
				populations.add(parent);
				if(j==2)
				{
					break;
				}
			}
		}
		/*for(int i=0;i<2;i++)
		{
			for(int j=0;j<popu;j++)
			{
				//System.out.println(theFitesset[i][j]);
			}
			//System.out.println("###########################################");
		}*/
	}
	public void Crossover(int popu)
	{
		//System.out.println("/***************debut crossover***********************/");
		childs = new float[2][popu];
		Random rand = new Random();
		int r = rand.nextInt(popu);
		//System.out.println(r+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		float tmp;
		
		
		childs[0] = theFitesset[0].clone();
		childs[1] = theFitesset[1].clone();
		for(int i=0;i<r;i++)
		{
			tmp=childs[0][i];
			childs[0][i]=childs[1][i];
			childs[1][i]=tmp;
		}
		/*for(int i=0;i<2;i++)
		{
			for(int j=0;j<popu;j++)
			{
				//System.out.println(childs[i][j]);
			}
			System.out.println("*************************************************");
		}*/
		for(int i=0;i<2;i++)
		{
			int [] act1 = new int[4];
			act1 = act.clone();
			int etat_pred=-1;
			for(int j=0;j<popu;j++)
			{
				int rr = (int)(childs[i][j]/0.25);
				//System.out.println(act1[rr]+"here");
				if(etat_pred==-1)
				{
					if(act1[rr]!=0)
					{
						act1[rr]=act1[rr]-1;
						etat_pred=rr;
						if((rr+1)%2==0)
						{
							act1[(rr-1)]=act1[(rr-1)]+1;
						}
						else
						{
							act1[(rr+1)]=act1[(rr+1)]+1;
						}
					}
					else
					{
						while(true)
						{
							Random ran = new Random();
							float a= rand.nextFloat();
							int a1 = (int)(a/0.25);
							

							if(((etat_pred+a1)!=1)&&((etat_pred+a1)!=5))
							{
								if(act1[a1]!=0)
								{
									act1[a1]=act1[a1]-1;
									etat_pred=a1;
									childs[i][j]=a;
									if((a1+1)%2==0)
									{
										act1[(a1-1)]=act1[(a1-1)]+1;
									}
									else
									{
										act1[(a1+1)]=act1[(a1+1)]+1;
									}
									break;
								}
							}
						}
					}
				}
				else if(((etat_pred+rr)!=1)&&((etat_pred+rr)!=5))
				{
					if(act1[rr]!=0)
					{
						act1[rr]=act1[rr]-1;
						etat_pred=rr;
						if((rr+1)%2==0)
						{
							act1[(rr-1)]=act1[(rr-1)]+1;
						}
						else
						{
							act1[(rr+1)]=act1[(rr+1)]+1;
						}
					}
					else
					{
						while(true)
						{
							Random ran = new Random();
							float a= rand.nextFloat();
							int a1 = (int)(a/0.25);
							

							if(((etat_pred+a1)!=1)&&((etat_pred+a1)!=5))
							{
								if(act1[a1]!=0)
								{
									//System.out.println("dkhel hna " + act1[a1]+ " a "+ a);
									act1[a1]=act1[a1]-1;
									etat_pred=a1;
									childs[i][j]=a;
									if((a1+1)%2==0)
									{
										act1[(a1-1)]=act1[(a1-1)]+1;
									}
									else
									{
										act1[(a1+1)]=act1[(a1+1)]+1;
									}
									break;
								}
							}
						}
					}
				}
				else
				{
					while(true)
					{
						Random ran = new Random();
						float a= rand.nextFloat();
						int a1 = (int)(a/0.25);
						

						if(((etat_pred+a1)!=1)&&((etat_pred+a1)!=5))
						{
							if(act1[a1]!=0)
							{
								//System.out.println("dkhel hna " + act1[a1]+ " a "+ a);
								act1[a1]=act1[a1]-1;
								etat_pred=a1;
								childs[i][j]=a;
								if((a1+1)%2==0)
								{
									act1[(a1-1)]=act1[(a1-1)]+1;
								}
								else
								{
									act1[(a1+1)]=act1[(a1+1)]+1;
								}
								break;
							}
						}
					}
				}
			}
		}
		/*for(int i=0;i<2;i++)
		{
			for(int j=0;j<popu;j++)
			{
				//System.out.println(childs[i][j]);
			}
			System.out.println("*************************************************");
		}*/
	}
	public void mutation(Node1 n, StringBuffer s, int popu)
	{
		//System.out.println("*******************debut mutation********************************");
		Random ran = new Random();
		for(int i=0;i<2;i++)
		{
			//System.out.println("permutation n:"+i);
			while(true)
			{
				childs1=childs[i].clone();
				int i1=0;
				int i2=0;
				do {
					i1=ran.nextInt(popu);
					i2=ran.nextInt(popu);
				}while(i1==i2);
				//System.out.println(i1+" "+ i2);
				float tmp;
				tmp=childs1[i1];
				childs1[i1]=childs1[i2];
				childs1[i2]=tmp;
				int [] act1 = new int[4];
				act1 = act.clone();
				int etat_pred=-1;
				int k=0;
				for(int j=0;j<popu;j++)
				{
					int rr = (int)(childs1[j]/0.25);
					//System.out.println(act1[rr]+"here");
					if(etat_pred==-1)
					{
						if(act1[rr]!=0)
						{
							act1[rr]=act1[rr]-1;
							etat_pred=rr;
							if((rr+1)%2==0)
							{
								act1[(rr-1)]=act1[(rr-1)]+1;
							}
							else
							{
								act1[(rr+1)]=act1[(rr+1)]+1;
							}
						}
						else
						{
							break;
						}
					}
					else if(((etat_pred+rr)!=1)&&((etat_pred+rr)!=5))
					{
						if(act1[rr]!=0)
						{
							act1[rr]=act1[rr]-1;
							etat_pred=rr;
							if((rr+1)%2==0)
							{
								act1[(rr-1)]=act1[(rr-1)]+1;
							}
							else
							{
								act1[(rr+1)]=act1[(rr+1)]+1;
							}
						}
						else
						{
							break;
						}
					}
					else
					{
						//System.out.println("jaz from here");
						break;
					}
					k=k+1;
				}
				if(k==5)
				{
					break;
				}
			}
			childs[i]=childs1.clone();
		}
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<popu;j++)
			{
				//System.out.println(childs[i][j]);
			}
			//System.out.println("*************************************************");
		}
		getLessFitess();
		
		for(int l = 0; l<2; l++)
		{
			Population parent = new Population();
			parent.p=childs[l].clone();
			Node1 test = new Node1();
			test=n;
			test=applySolution(test,s,childs[l],popu);
			parent.fitness=missPlaced(test,s);
			costs.add(parent.fitness);
			//System.out.println("Childs fitness "+parent.fitness);
			populations.add(parent);
			
		}
		
	}
	public void getLessFitess()
	{
		int max1, max2;
		int size=populations.size();
		Population parent = new Population();
		Collections.sort(costs,Collections.reverseOrder());
		
		if(costs.size()!=0)
		{
			//System.out.println("costs size = "+costs.size());
			max1=costs.get(0);
			costs.remove(0);
			max2=costs.get(0);
			costs.remove(0);
			//System.out.println("cost size="+costs.size());
			int j=0;
			//System.out.println("///////////////////////////////////////////////////////"+size);
			for(int i=0;i<size;i++)
			{
				parent=populations.remove();
				//System.out.println("parent fitness "+parent.fitness+ " max1 "+ max1+ " max2 " +max2);
				if((parent.fitness==max1)||(parent.fitness==max2))
				{
					//System.out.println("population size "+ populations.size());
					theFitesset[j] = parent.p.clone();
					j=j+1;
				}
				else 
				{
					populations.add(parent);
				}
				if(j==2)
				{
					break;
				}
			}
			//System.out.println("La taille de pp"+populations.size());
		}
		//System.out.println("Less fitess ***********************************************************");
		/*for(int i=0;i<2;i++)
		{
			for(int j=0;j<popu;j++)
			{
				//System.out.println(theFitesset[i][j]);
			}
			System.out.println("###########################################");
		}*/
	}
	public String GA(Node1 n, StringBuffer s, int num, int popu)
	{
		this.popu=popu;
		Methodes m = new Methodes();
		m.Population(num,n,popu);
		int iteration = 0;
		int g;
		Population solution = new Population();
		while(iteration<10)
		{
			solution = m.Fitness(n,s);
			
			if(solution!=null)
			{
				break;
			}
			m.get_fitess(popu);
			m.Crossover(popu);
			m.mutation(n,s,popu);
			iteration++;
		}
		
		Node1 test = new Node1();
		test=n;
		if(solution!=null)
		{
			test = m.applySolution(test, s,solution.p,popu);
			/*System.out.println("La solution finale est:");
			for(int i=0;i<test.g;i++)
			{
				System.out.println(solution.p[i]);
			}*/
		}
		else
		{
			int size=m.populations.size();
			Population parent = new Population();
			Collections.sort(costs);
			
			if(costs.size()!=0)
			{
				int min1=costs.get(0);
				costs.remove(0);
				int j=0;
				for(int i=0;i<size;i++)
				{
					parent=m.populations.remove();
					//System.out.println("dkhel hnakkkkk"+size+ parent.fitness);
					if(parent.fitness==min1)
					{
						solution = new Population();
						solution.p = parent.p.clone();
						//System.out.println("ffffoad");
						break;
					}
				}
			}
			//System.out.println("aw kemel"+solution.fitness);
			test = m.applySolution(test, s,solution.p,popu);
			//System.out.println("La solution finale est:\n");
			/*for(int i=0;i<test.g;i++)
			{
				System.out.println(solution.p[i]);
			}*/
		}
		System.out.println("node final " + test.node+" "+ test.f);
		//System.out.println(GetMoves(solution.p));
		return GetMoves(solution.p);
	}
	public String GetMoves(float [] k)
	{
		String s = new String("");
		for(int j=0;j<k.length;j++)
		{
			if((k[j]>=0)&&(k[j]<=0.25))
			{
				s = s + "Left ";
			}
			else if((k[j]>=0.25)&&(k[j]<=0.5))
			{
				s = s + "Right ";
			}
			else if((k[j]>=0.5)&&(k[j]<=0.75))
			{
				s = s + "Up ";
			}
			else if((k[j]>=0.75)&&(k[j]<=1))
			{
				s = s + "Down ";
			}
		}
		
		return s;
	}
	public Node1 left1(Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		
		if(i%Math.sqrt(n.node.length())!=0)
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt(i-1) );
			n.node.setCharAt((i-1),tmp );
		}
		return n;
	}
	public Node1 right1(Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		if(i%Math.sqrt(n.node.length())!=Math.sqrt(n.node.length())-1)
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt(i+1) );
			n.node.setCharAt((i+1),tmp );
		}
		return n;
	}
	public Node1 up1(Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		if(i>=Math.sqrt(n.node.length()))
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt((int)(i-Math.sqrt(n.node.length()))));
			n.node.setCharAt((int)(i-Math.sqrt(n.node.length())),tmp );
		}
		return n;
	}
	public Node1 down1(Node1 node)
	{
		char tmp;
		Node1 n = new Node1();
		n.node.append(node.node);
		int i=n.node.indexOf("0");
		if(i<n.node.length()-Math.sqrt(n.node.length()))
		{
			tmp=n.node.charAt(i);
			n.node.setCharAt(i,n.node.charAt((int)(i+Math.sqrt(n.node.length()))));
			n.node.setCharAt((int)(i+Math.sqrt(n.node.length())),tmp );
		}
		return n;
	}
	/********************************************PSO*******************************************************/
	public void Population1(int n, Node1 node,StringBuffer s,int taille)
	{
		int [] act = new int[4];
		Population parent = new Population();
		int etat_pred=-1;
		int k=node.node.indexOf("0");
		if(k%3==0)
		{
			act[1]=2;
		}
		if(k%3==1)
		{
			act[0]=1;
			act[1]=1;
		}
		if(k%3==2)
		{
			act[0]=2;
		}
		if((k>=3)&&(k<=5))
		{
			act[2]=1;
			act[3]=1;
		}
		if((k>=6)&&(k<=8))
		{
			act[2]=2;
		}
		if((k>=0)&&(k<=2))
		{
			act[3]=2;
		}
		for(int i=0;i<taille;i++)
		{
			parent = new Population();
			parent.velocity=1;
			String ss = new String();
			int [] act1 = new int[4];
			for(int l=0;l<4;l++)
			{
				act1[l]=act[l];
				//System.out.println(act1[l]);
			}
			for(int j=0;j<n;j++)
			{
				for(int l=0;l<4;l++)
				{
					//System.out.println(act1[l]);
				}
				Random rand = new Random();
				float rr= rand.nextFloat();
				int r = (int)(rr/0.25);
				if(etat_pred==-1)
				{
					if(act1[r]!=0)
					{//System.out.println("n "+rr);
						parent.p[j]=rr;
						ss=ss+", "+Float.toString(parent.p[j]);
						
						act1[r]=act1[r]-1;
						etat_pred=r;
						if((r+1)%2==0)
						{
							act1[(r-1)]=act1[(r-1)]+1;
						}
						else
						{
							act1[(r+1)]=act1[(r+1)]+1;
						}
					}
					else
					{
						j=j-1;
					}
				}
				else if(((etat_pred+r)!=1)&&((etat_pred+r)!=5))
				{
					if(act1[r]!=0)
					{//System.out.println("n "+rr);
					
						parent.p[j]=rr;
						
						ss=ss+", "+Float.toString(parent.p[j]);
						
						act1[r]=act1[r]-1;
						etat_pred=r;
						if((r+1)%2==0)
						{
							act1[(r-1)]=act1[(r-1)]+1;
						}
						else
						{
							act1[(r+1)]=act1[(r+1)]+1;
						}
					}
					else
					{
						j=j-1;
					}
				}
				else
				{
					j=j-1;
				}
				
			}
			//System.out.println(ss);
			//System.out.println("velocity of the particle"+parent.velocity);
			populations1.add(parent);
			
			
		}
	}
	
	public void Fitness1(Node1 n, StringBuffer s)
	{
		int taille=populations1.size();
		for(int i=0;i<taille;i++)
		{
			Population parent = new Population();
			parent=populations1.get(i);
			Node1 test = new Node1();
			test=n;
			for(int j=0;j<5;j++)
			{
				if((parent.p[j]>=0)&&(parent.p[j]<=0.25))
				{
					test=left1(test);
				
				}
				else if((parent.p[j]>=0.25)&&(parent.p[j]<=0.5))
				{
					
					test=right1(test);
					
				}
				else if((parent.p[j]>=0.5)&&(parent.p[j]<=0.75))
				{
					test=up1(test);
					
				}
				else if((parent.p[j]>=0.75)&&(parent.p[j]<=1))
				{
					test=down1(test);
					
				}
			}
			parent.fitness=ManhatenDistance(test,s);
			parent.pos=ManhatenDistance(test,s);
			//System.out.println(test.node+ " f= "+parent.fitness);
			populations.add(parent);
		}
	}
	
	public Population pso(Node1 n, StringBuffer s,int taille) {
		int p_best=1000;
		int g_best=1000;
		int max_iter=30;
		int iteration=0;
		double w=0.8;
		double c1=0.8;
		double c2=0.7;
		Population t=new Population();
		Population parent = new Population();
		Population1(100, n, s,taille);/*init N particles positions and velocities*/
		Fitness1(n,s);/*evaluate the particle postions*/
	/*for each particle i do Pbesti = xi*/
		for(int i=0;i<populations1.size();i++)
		{
			if(populations1.get(i).fitness<p_best) 
			{
			parent=populations1.get(i);
			p_best=populations1.get(i).fitness;
			}
			
		}
		//System.out.println("solution avec meilleur fitness"+parent.p);
		//System.out.println("sa fitness"+parent.fitness);
		Population best_solution=parent;
		g_best=p_best;
		
		while(iteration<max_iter) 
		{
			//System.out.println("iteration "+iteration);
			for(int i=0;i<populations1.size();i++ ) {
			float r1,r2;
			Population p =new Population();
			p=populations1.get(i);
			int x=p.fitness;
			int v=p.velocity;
		   	Random rand1 = new Random();
			r1= rand1.nextFloat();
			Random rand2 = new Random();
			r2= rand2.nextFloat();
			//System.out.println("ancienne position de la particule"+i+": "+x);
			//System.out.println("ancienne vitesse de la particule"+i+":"+v);
			v=(int) ((w*v)+(c1*r1)*(p_best-x)+(c2*r2)*(g_best-x));
			x=x+v;
			p.fitness=x;
			p.velocity=v;
			//System.out.println("position de la particule"+i+":"+x);
			//System.out.println("vitesse de la particule"+i+":"+v);
			if(x<p_best) {
				p_best=x;/*update p_best*/
				t=populations1.get(i);
			}
			}
			g_best=p_best;
			for(int k=0;k<populations1.size();k++) {
				if(populations1.get(k).velocity==t.velocity && populations1.get(k).fitness==t.fitness) {
					best_solution=populations1.get(k); 
				}
			}
			iteration++;
			
			
		}
		
		//System.out.println("best solution position"+best_solution.pos+"best solution velocity"+best_solution.velocity);																																	
		
		for(int i=0;i<best_solution.p.length;i++) {
			//System.out.println(best_solution.p[i]);
		}
		Node1 res = new Node1();
		res=applySolution(n, s,best_solution.p, best_solution.p.length);
		//System.out.println("le but est: "+res.node);
		return best_solution;
	}
}
