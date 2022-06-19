package application;

import java.lang.Thread.State;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Node extends Thread implements Initializable {
	
	/****************************************************partie declaration****************************************************************/
	StringBuffer node = new StringBuffer();
	StringBuffer result = new StringBuffer();
	Node1 right;
	Node1 left;
	Node1 up;
	Node1 down;
	@FXML
	private TextField t1;
	@FXML
	private TextField t2;
	@FXML
	private TextField t3;
	@FXML
	private TextField t4;
	@FXML
	private TextField t5;
	@FXML
	private TextField t6;
	@FXML
	private TextField t7;
	@FXML
	private TextField t8;
	@FXML
	private TextField t9;
	@FXML
	private TextField t10;
	@FXML
	private TextField t11;
	@FXML
	private TextField t12;
	@FXML
	private TextField t13;
	@FXML
	private TextField t14;
	@FXML
	private TextField t15;
	@FXML
	private TextField t16;
	@FXML
	private TextField t17;
	@FXML
	private TextField t18;
	@FXML
	private TextField proff;
	@FXML
	TextArea textArea = new TextArea();
	String[] c = {"A* missplassed","A* Manhaten distance", "BFS", "DFS"};
	String c1 = new String();
	@FXML
	ChoiceBox<String> choice;
	@FXML
	Button button = new Button();
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		choice.getItems().addAll(c);
		choice.setOnAction(this::get_choice);
		proff.setDisable(true);
		button.setDisable(true);
	}
	
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
	Open[][] ouvert = new Open[362897][10];
	/***********************************************************partie methods*************************************************************/
	public void fillInitialMatrix(ActionEvent event3) {
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
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() { 

			private int i = Actions.size()-1; 
		    @Override
		    public void handle(ActionEvent event) {
		    	//node.replace(MIN_PRIORITY, MAX_PRIORITY, "013745276");
		    	setMatrix(node);
				StringBuffer but = new StringBuffer("123804765");
				setMatrix2(but);
		   }
		}));
		timeline.setCycleCount(1);
		timeline.play();
		
	}

	public void Clear(ActionEvent event4)
	{
		t1.clear();
		t2.clear();
		t3.clear();
		t4.clear();
		t5.clear();
		t6.clear();
		t7.clear();
		t8.clear();
		t9.clear();
		t10.clear();
		t11.clear();
		t12.clear();
		t13.clear();
		t14.clear();
		t15.clear();
		t16.clear();
		t17.clear();
		t18.clear();
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
	public int HashFct(int n)
	{
		int hash;
		hash=n%362897;
		return hash;
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
			textArea.setText("Goal not found!!");
		}
		
		return minNode1;
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
	public void interfaces(ActionEvent event)
	{
		Actions.clear();
		right = new Node1();
		left = new Node1();
		StringBuffer s = new StringBuffer();
		if(!t1.getText().isEmpty()&&!t2.getText().isEmpty()&&!t3.getText().isEmpty()&&!t4.getText().isEmpty()&&!t5.getText().isEmpty()&&!t6.getText().isEmpty()&&!t7.getText().isEmpty()&&!t8.getText().isEmpty()&&!t9.getText().isEmpty()&&!t10.getText().isEmpty()&&!t11.getText().isEmpty()&&!t12.getText().isEmpty()&&!t13.getText().isEmpty()&&!t14.getText().isEmpty()&&!t15.getText().isEmpty()&&!t16.getText().isEmpty()&&!t17.getText().isEmpty()&&!t18.getText().isEmpty())
		{
			right.node.append(t1.getText());
			right.node.append(t2.getText());
			right.node.append(t3.getText());
			right.node.append(t4.getText());
			right.node.append(t5.getText());
			right.node.append(t6.getText());
			right.node.append(t7.getText());
			right.node.append(t8.getText());
			right.node.append(t9.getText());
			s.append(t10.getText());
			s.append(t11.getText());
			s.append(t12.getText());
			s.append(t13.getText());
			s.append(t14.getText());
			s.append(t15.getText());
			s.append(t16.getText());
			s.append(t17.getText());
			s.append(t18.getText());
			StringBuffer s1 = new StringBuffer();
			if(solvable(right.node.toString(),s1)%2!=0)
			{
				textArea.setText("Puzzle not solvable");
				//System.out.println(solvable(right.node.toString(),s));
			}
			else if(c1=="A* missplassed")
			{
				long startTime = System.nanoTime();
				right.h=missPlaced(right,s);
				right.g=0;
				right.f=right.g+right.h;
				//System.out.println("this is missplaced"+solvable(right.node.toString()));
				left=AEtoile(right, s,1);
				long endTime = System.nanoTime();
				long timeElapsed = endTime - startTime;
				System.out.println("Temp d'execution: "+timeElapsed);
				get_path(left,right);
				button.setDisable(false);
				
			}
			else if(c1=="A* Manhaten distance")
			{
				long startTime = System.nanoTime();
				right.h=ManhatenDistance(right,s);
				right.g=0;
				right.f=right.g+right.h;
				//System.out.println("this is manhaten");
				left=AEtoile(right, s,2);
				long endTime = System.nanoTime();
				long timeElapsed = endTime - startTime;
				button.setDisable(false);
				get_path(left,right);
				System.out.println("Temp d'execution: "+timeElapsed);
			}
			else if(c1=="BFS")
			{
				long startTime = System.nanoTime();
				//System.out.println("this is bfs");
				n1=right;
				//right.init(right.node);
				open.add(n1);
				left=insererFilsOuvr(s);
				long endTime = System.nanoTime();
				long timeElapsed = endTime - startTime;
				System.out.println("Temp d'execution: "+timeElapsed);
				Stack st = new Stack();
				st=chainage(left);
				int i=0;
				while(!st.isEmpty())
				{
					Object nd=st.pop();
					if (i%2==1) {
						//System.out.println(nd);
						if(nd=="left")
						{
							Actions.add(1);
						}
						if(nd=="right")
						{
							Actions.add(2);
						}
						if(nd=="up")
						{
							Actions.add(3);
						}
						if(nd=="down")
						{
							Actions.add(4);
						}
					}
					i++;
				}
				Collections.reverse(Actions);
				System.out.println(Actions);
				button.setDisable(false);
				
			}
			else if(c1=="DFS")
			{
				long startTime = System.nanoTime();
				//System.out.println("this is dfs");
				left=profondeur(right,s,Integer.parseInt(proff.getText()));
				long endTime = System.nanoTime();
				long timeElapsed = endTime - startTime;
				//System.out.println("here stops dfs");
				get_path2(left,right);
				button.setDisable(false);
				
				System.out.println("Temp d'execution: "+timeElapsed);
			}
			
		}
	}
	public void get_choice(ActionEvent e)
	{
		c1= choice.getValue();
		if(c1=="A* missplassed")
		{
			proff.setDisable(true);
		}
		else if(c1=="A* Manhaten distance")
		{
			proff.setDisable(true);
		}
		else if(c1=="BFS")
		{
			proff.setDisable(true);
		}
		else if(c1=="DFS")
		{
			proff.setDisable(false);
		}
	}
	public void swap(ActionEvent event2) throws Exception
	{
		Node1 txt = new Node1();
		txt=right;
		if(c1=="BFS")
		{
			right=n1;
			txt=n1;
		}
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() { 

			private int i = Actions.size()-1; 
		    @Override
		    public void handle(ActionEvent event) {
		    	String s = new String("");
		    	int i1=right.node.indexOf("0");
				if(Actions.get(i)==1)
				{
					//s=s+"Left-";
					char tmp = right.node.charAt(i1-1);
					right.node.setCharAt( (i1-1), '0');
					right.node.setCharAt( (i1), tmp);
				}
				if(Actions.get(i)==2)
				{
					//s=s+"right-";
					char tmp = right.node.charAt(i1+1);
					right.node.setCharAt( (i1+1), '0');
					right.node.setCharAt( (i1), tmp);
				}
				if(Actions.get(i)==3)
				{
					//s=s+"up-";
					char tmp = right.node.charAt((int)(i1-Math.sqrt(right.node.length())));
					right.node.setCharAt( (int)(i1-Math.sqrt(right.node.length())), '0');
					right.node.setCharAt( (i1), tmp);
				}
				if(Actions.get(i)==4)
				{
					//s=s+"down-";
					char tmp = right.node.charAt((int)(i1+Math.sqrt(right.node.length())));
					right.node.setCharAt( (int)(i1+Math.sqrt(right.node.length())), '0');
					right.node.setCharAt( (i1), tmp);
				}
				setMatrix(right.node);
				i--;
		   }
		}));
		timeline.setCycleCount(Actions.size());
		timeline.play();
		String s = new String("");
		StringBuffer node = new StringBuffer("012345678");
		
		for(int i = Actions.size()-1;i>=0;i--)
		{
			if(Actions.get(i)==1)
			{
				s=s+txt.node.toString()+" action applyed is left "+left(node,0,txt).node.toString()+"\n";
				txt=left(node,0,txt);
			}
			if(Actions.get(i)==2)
			{
				s=s+txt.node.toString()+" action applyed is right "+right(node,0,txt).node.toString()+"\n";
				txt=right(node,0,txt);
			}
			if(Actions.get(i)==3)
			{
				//System.out.println(right.node+ " ffffffffffff "+ node);
				s=s+txt.node.toString()+" action applyed is up "+up(node,0,txt).node.toString()+"\n";
				txt=up(node,0,txt);
			}
			if(Actions.get(i)==4)
			{
				s=s+txt.node.toString()+" action applyed is down "+down(node,0,txt).node.toString()+"\n";
				txt=down(node,0,txt);
			}
		}
		textArea.setText(s);
	}
	public void setMatrix(StringBuffer s)
	{
		t1.clear();
		if(s.charAt(0)=='0') {
			t1.setStyle("-fx-text-fill: white;");
			t1.setText(s.charAt(0)+"");
		}
		else
		{
			t1.setStyle("-fx-text-fill: black;");
			t1.setText(s.charAt(0)+"");
		}
		t2.clear();
		if(s.charAt(1)=='0') {
			t2.setStyle("-fx-text-fill: white;");
			t2.setText(s.charAt(1)+"");
		}
		else
		{
			t2.setStyle("-fx-text-fill: black;");
			t2.setText(s.charAt(1)+"");
		}
		t3.clear();
		if(s.charAt(2)=='0') {
			t3.setStyle("-fx-text-fill: white;");
			t3.setText(s.charAt(2)+"");
		}
		else
		{
			t3.setStyle("-fx-text-fill: black;");
			t3.setText(s.charAt(2)+"");
		}
		t4.clear();
		if(s.charAt(3)=='0') {
			t4.setStyle("-fx-text-fill: white;");
			t4.setText(s.charAt(3)+"");
		}
		else
		{
			t4.setStyle("-fx-text-fill: black;");
			t4.setText(s.charAt(3)+"");
		}
		t5.clear();
		if(s.charAt(4)=='0') {
			t5.setStyle("-fx-text-fill: white;");
			t5.setText(s.charAt(4)+"");
		}
		else
		{
			t5.setStyle("-fx-text-fill: black;");
			t5.setText(s.charAt(4)+"");
		}
		t6.clear();
		if(s.charAt(5)=='0') {
			t6.setStyle("-fx-text-fill: white;");
			t6.setText(s.charAt(5)+"");
		}
		else
		{
			t6.setStyle("-fx-text-fill: black;");
			t6.setText(s.charAt(5)+"");
		}
		t7.clear();
		t7.setText(s.charAt(6)+"");
		if(s.charAt(6)=='0') {
			t7.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t7.setStyle("-fx-text-fill: black;");
		}
		t8.clear();
		t8.setText(s.charAt(7)+"");
		if(s.charAt(7)=='0') {
			t8.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t8.setStyle("-fx-text-fill: black;");
		}
		t9.clear();
		t9.setText(s.charAt(8)+"");
		if(s.charAt(8)=='0') {
			t9.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t9.setStyle("-fx-text-fill: black;");
		}
	}
	public void setMatrix2(StringBuffer s)
	{
		t10.clear();
		t10.setText(s.charAt(0)+"");
		if(s.charAt(0)=='0') {
			t10.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t10.setStyle("-fx-text-fill: black;");
		}
		t11.clear();
		t11.setText(s.charAt(1)+"");
		if(s.charAt(1)=='0') {
			t11.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t11.setStyle("-fx-text-fill: black;");
		}
		t12.clear();
		t12.setText(s.charAt(2)+"");
		if(s.charAt(2)=='0') {
			t12.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t12.setStyle("-fx-text-fill: black;");
		}
		t13.clear();
		t13.setText(s.charAt(3)+"");
		if(s.charAt(3)=='0') {
			t13.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t13.setStyle("-fx-text-fill: black;");
		}
		t14.clear();
		t14.setText(s.charAt(4)+"");
		if(s.charAt(4)=='0') {
			t14.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t14.setStyle("-fx-text-fill: black;");
		}
		t15.clear();
		t15.setText(s.charAt(5)+"");
		if(s.charAt(5)=='0') {
			t15.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t15.setStyle("-fx-text-fill: black;");
		}
		t16.clear();
		t16.setText(s.charAt(6)+"");
		if(s.charAt(6)=='0') {
			t16.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t16.setStyle("-fx-text-fill: black;");
		}
		t17.clear();
		t17.setText(s.charAt(7)+"");
		if(s.charAt(7)=='0') {
			t17.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t17.setStyle("-fx-text-fill: black;");
		}
		t18.clear();
		t18.setText(s.charAt(8)+"");
		if(s.charAt(8)=='0') {
			t18.setStyle("-fx-text-fill: white;");
		}
		else
		{
			t18.setStyle("-fx-text-fill: black;");
		}
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
			textArea.setText("Goal not Found!!!!");
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
	/***************************largeur****************************/
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
	public void setPere(Node1 pere,String move,Node1 n)
	{
		n.pere=pere;
		n.move=move;
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
					{System.out.println("hello");
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
	
	public void Population(int n, Node1 node)
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
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<50;j++)
			{
				Random rand = new Random();
				if((etat_pred>=0)&&(etat_pred<=0.25))
				{
					if(rand.nextFloat()>0.25)
					{
						
					}
					parent.p[j]=rand.nextFloat();
				}
			}
		}
	}
	
}
