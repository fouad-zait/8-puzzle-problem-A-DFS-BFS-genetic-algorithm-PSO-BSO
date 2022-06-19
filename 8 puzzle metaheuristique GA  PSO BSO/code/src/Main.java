import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Main extends JFrame implements ActionListener {

	private JFrame frame;
	private JTextField t0;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JTextField t6;
	private JTextField t7;
	private JTextField t8;
	private JTextField t9;
	private JTextField t10;
	private JTextField t11;
	private JTextField t12;
	private JTextField t13;
	private JTextField t14;
	private JTextField t15;
	private JTextField t16;
	private JTextField t17;
	private JTextField t18;
	JTextArea textarea;
	private JButton b1;
	private JButton b2;
	String choice;
	JComboBox comboBox;
	Methodes m = new Methodes();
	String road;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		/*StringBuffer s = new StringBuffer("123804765");
		Node1 n = new Node1();
		n.node.append("283164705");
		Methodes m = new Methodes();
		*/
		        
		StringBuffer s1 = new StringBuffer("283164705");
		initialize(s1);
	}
	
	@Override
	public void actionPerformed (ActionEvent e){
	    if(e.getSource()==comboBox){
	        choice=(String) comboBox.getSelectedItem();
			if(choice=="A* missplassed")
			{
				t18.setEnabled(false);
			}
			else if(choice=="A* Manhaten distance")
			{
				t18.setEnabled(false);
			}
			else if(choice=="BFS")
			{
				t18.setEnabled(false);
			}
			else if(choice=="DFS")
			{
				t18.setEnabled(true);
			}
			else if(choice=="GA")
			{
				t18.setEnabled(true);
			}
	    }
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(StringBuffer s1) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 702);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		t0 = new JTextField();
		t0.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t0.setBounds(90, 168, 80, 80);
		frame.getContentPane().add(t0);
		t0.setColumns(10);
		
		t1 = new JTextField();
		t1.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t1.setColumns(10);
		t1.setBounds(170, 168, 80, 80);
		frame.getContentPane().add(t1);
		
		t2 = new JTextField();
		t2.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t2.setColumns(10);
		t2.setBounds(250, 168, 80, 80);
		frame.getContentPane().add(t2);
		
		t3 = new JTextField();
		t3.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t3.setColumns(10);
		t3.setBounds(90, 248, 80, 80);
		frame.getContentPane().add(t3);
		
		t4 = new JTextField();
		t4.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t4.setColumns(10);
		t4.setBounds(170, 248, 80, 80);
		frame.getContentPane().add(t4);
		
		t5 = new JTextField();
		t5.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t5.setColumns(10);
		t5.setBounds(250, 248, 80, 80);
		frame.getContentPane().add(t5);
		
		t6 = new JTextField();
		t6.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t6.setColumns(10);
		t6.setBounds(90, 328, 80, 80);
		frame.getContentPane().add(t6);
		
		t7 = new JTextField();
		t7.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t7.setColumns(10);
		t7.setBounds(170, 328, 80, 80);
		frame.getContentPane().add(t7);
		
		t8 = new JTextField();
		t8.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t8.setColumns(10);
		t8.setBounds(250, 328, 80, 80);
		frame.getContentPane().add(t8);
		
		t9 = new JTextField();
		t9.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t9.setColumns(10);
		t9.setBounds(660, 168, 80, 80);
		frame.getContentPane().add(t9);
		
		t10 = new JTextField();
		t10.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t10.setColumns(10);
		t10.setBounds(740, 168, 80, 80);
		frame.getContentPane().add(t10);
		
		t11 = new JTextField();
		t11.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t11.setColumns(10);
		t11.setBounds(820, 168, 80, 80);
		frame.getContentPane().add(t11);
		
		t12 = new JTextField();
		t12.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t12.setColumns(10);
		t12.setBounds(660, 248, 80, 80);
		frame.getContentPane().add(t12);
		
		t13 = new JTextField();
		t13.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t13.setColumns(10);
		t13.setBounds(740, 248, 80, 80);
		frame.getContentPane().add(t13);
		
		t14 = new JTextField();
		t14.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t14.setColumns(10);
		t14.setBounds(820, 248, 80, 80);
		frame.getContentPane().add(t14);
		
		t15 = new JTextField();
		t15.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t15.setColumns(10);
		t15.setBounds(660, 328, 80, 80);
		frame.getContentPane().add(t15);
		
		t16 = new JTextField();
		t16.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t16.setColumns(10);
		t16.setBounds(740, 328, 80, 80);
		frame.getContentPane().add(t16);
		
		t17 = new JTextField();
		t17.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t17.setColumns(10);
		t17.setBounds(820, 328, 80, 80);
		frame.getContentPane().add(t17);
		
		JLabel lblNewLabel = new JLabel("Puzzle Solver!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(397, 10, 188, 121);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Generate Matrix");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer s = new StringBuffer("123804765");
				StringBuffer s1 = new StringBuffer("283164705");
				setMatrix(s1);
				setMatrix2(s);
			}
		});
		btnNewButton.setBounds(397, 436, 198, 55);
		frame.getContentPane().add(btnNewButton);
		
		String[] c = {"Les methodes","A* missplassed","A* Manhaten distance", "BFS", "DFS","GA","PSO","BSO"};
		comboBox = new JComboBox(c); 
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox.setBounds(403, 141, 182, 51);
		comboBox.setSelectedItem("text has changed");
		comboBox.addActionListener(this);
		frame.getContentPane().add(comboBox);
		
		t18 = new JTextField();
		t18.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		t18.setBounds(433, 212, 119, 36);
		frame.getContentPane().add(t18);
		t18.setColumns(10);
		
		textarea = new JTextArea(10,15);
		textarea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textarea.setBounds(0, 564, 991, 101);
		frame.getContentPane().add(textarea);
		
		
		//JScrollPane sp=new JScrollPane (textarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//frame.add(sp);
		
		b2 = new JButton("Show steps");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swap(choice);
			}
		});
		b2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		b2.setBounds(425, 348, 136, 36);
		b2.setEnabled(false);
		frame.getContentPane().add(b2);
		
		b1 = new JButton("Start");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				m.Actions.clear();
				m.right = new Node1();
				m.left = new Node1();
				StringBuffer s = new StringBuffer();
				if(!t0.getText().equals("")&&!t2.getText().equals("")&&!t3.getText().equals("")&&!t4.getText().equals("")&&!t5.getText().equals("")&&!t6.getText().equals("")&&!t7.getText().equals("")&&!t8.getText().equals("")&&!t9.getText().equals("")&&!t10.getText().equals("")&&!t11.getText().equals("")&&!t12.getText().equals("")&&!t13.getText().equals("")&&!t14.getText().equals("")&&!t15.getText().equals("")&&!t16.getText().equals("")&&!t17.getText().equals("")&&!t1.getText().equals(""))
				{
					m.right.node.append(t0.getText());
					m.right.node.append(t1.getText());
					m.right.node.append(t2.getText());
					m.right.node.append(t3.getText());
					m.right.node.append(t4.getText());
					m.right.node.append(t5.getText());
					m.right.node.append(t6.getText());
					m.right.node.append(t7.getText());
					m.right.node.append(t8.getText());
					s.append(t9.getText());
					s.append(t10.getText());
					s.append(t11.getText());
					s.append(t12.getText());
					s.append(t13.getText());
					s.append(t14.getText());
					s.append(t15.getText());
					s.append(t16.getText());
					s.append(t17.getText());
					StringBuffer s1 = new StringBuffer();
					//System.out.println("helloooo " + m.right.node + " kkkkk " + s);
					if(m.solvable(m.right.node.toString(),s1)%2!=0)
					{
						textarea.setText("Puzzle not solvable");
						//System.out.println(solvable(right.node.toString(),s));
					}
					else if(choice=="A* missplassed")
					{
						long startTime = System.nanoTime();
						m.right.h=m.missPlaced(m.right,s);
						m.right.g=0;
						m.right.f=m.right.g+m.right.h;
						//System.out.println("this is missplaced"+solvable(right.node.toString()));
						m.left=m.AEtoile(m.right, s,1);
						long endTime = System.nanoTime();
						long timeElapsed = endTime - startTime;
						System.out.println("Temp d'execution: "+timeElapsed);
						m.get_path(m.left,m.right);
						b2.setEnabled(true);
					}
					else if(choice=="A* Manhaten distance")
					{
						long startTime = System.nanoTime();
						m.right.h=m.ManhatenDistance(m.right,s);
						m.right.g=0;
						m.right.f=m.right.g+m.right.h;
						//System.out.println("this is manhaten");
						m.left=m.AEtoile(m.right, s,2);
						long endTime = System.nanoTime();
						long timeElapsed = endTime - startTime;
						b2.setEnabled(true);
						m.get_path(m.left,m.right);
						System.out.println("Temp d'execution: "+timeElapsed);
					}
					else if(choice=="BFS")
					{
						long startTime = System.nanoTime();
						//System.out.println("this is bfs");
						m.n1=m.right;
						//right.init(right.node);
						m.open.add(m.n1);
						m.left=m.insererFilsOuvr(s);
						long endTime = System.nanoTime();
						long timeElapsed = endTime - startTime;
						System.out.println("Temp d'execution: "+timeElapsed);
						Stack st = new Stack();
						st=m.chainage(m.left);
						int i=0;
						while(!st.isEmpty())
						{
							Object nd=st.pop();
							if (i%2==1) {
								//System.out.println(nd);
								if(nd=="left")
								{
									m.Actions.add(1);
								}
								if(nd=="right")
								{
									m.Actions.add(2);
								}
								if(nd=="up")
								{
									m.Actions.add(3);
								}
								if(nd=="down")
								{
									m.Actions.add(4);
								}
							}
							i++;
						}
						Collections.reverse(m.Actions);
						System.out.println(m.Actions);
						b2.setEnabled(true);
						
					}
					else if(choice=="DFS")
					{
						long startTime = System.nanoTime();
						//System.out.println("this is dfs");
						m.left=m.profondeur(m.right,s,Integer.parseInt(t18.getText()));
						long endTime = System.nanoTime();
						long timeElapsed = endTime - startTime;
						System.out.println("Temp d'execution: "+timeElapsed);
						//System.out.println("here stops dfs");
						m.get_path2(m.left,m.right);
						b2.setEnabled(true);
						
						
					}
					else if(choice=="GA")
					{
						int taillePopulation = Integer.parseInt(t18.getText());
						long startTime = System.nanoTime();
						road = m.GA(m.right,s,taillePopulation,100);
						long endTime = System.nanoTime();
						b2.setEnabled(true);
						textarea.setText(road);
						long timeElapsed = endTime - startTime;
						System.out.println("Temp d'execution: "+timeElapsed);
						System.out.println(road);
					}
					else if(choice=="PSO")
					{
						Population parent = new Population();
						long startTime = System.nanoTime();
						parent=m.pso(m.right, s,1000);
						String res = m.GetMoves(parent.p);
						long endTime = System.nanoTime();
						b2.setEnabled(true);
						long timeElapsed = endTime - startTime;
						System.out.println("Temp d'execution: "+timeElapsed);
						textarea.setText(res);
						System.out.println(res);
					}
					else if(choice=="BSO")
					{
						String[] etat_init = new String[9];
				        String[] etat_fin = new String[9];
				        for(int i=0;i<9;i++)
				        {
				        	System.out.println(m.right.node.toString().charAt(i));
				        	etat_init[i]=""+m.right.node.toString().charAt(i);
				        	etat_fin[i]=""+s.toString().charAt(i);
				        	
				        }
				        for(int i=0;i<9;i++)
				        {
				        	System.out.println(etat_init[i]);
				        	
				        }
				        Boolean result;
				        //INITIALISE LES PREMIER COMBINAISONS
				        Combinaison Comb_init = new Combinaison(etat_init);
				        Combinaison Comb_fin = new Combinaison(etat_fin);
				        
				        
				        //INITIALSE LE BSO
				        BSO A = new BSO(Comb_init,Comb_fin);
				        
				        
				        //RESULTAS DE RECHERCHE BSO
				        long startTime = System.nanoTime();
				        result = A.Chercher();
				        long endTime = System.nanoTime();
				        
				        
				        //SOLUTION TROUVE
				        if (result == true)
				        {
				            List<Combinaison> Solution = A.Solution();
				            Info info_solution = A.Info_Solution();
				            String res = new String("");
				            for(int j= Solution.size()-1;j>=0;j--) {
				            	if(j!=(Solution.size()-1))
				            	{
				            		res=res + Solution.get(j).Get_direction()+" ";
				            	}
				                    
				            }
				            textarea.setText(res);
				            System.out.println("le path is:"+res);
				            System.out.println("\n********** Info sur la Solution *****************\n"+
				                    "Nombre etapes de Solution Obtenue: "+(Solution.size()-1)+
				                    "\nTemps pris pour la recherche (approxiamtive): "+ info_solution.getTime()+ " s"
				            );
				            long timeElapsed = endTime - startTime;
							System.out.println("Temp d'execution: "+timeElapsed);
				        }
				        
				        //SOLUTION NON TROUVE        
				        else
				        {
				            System.out.println("Puzzle is not Solvable");
				        }    
					}
				}
			}
		});
		b1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		b1.setBounds(445, 268, 97, 36);
		frame.getContentPane().add(b1);
		
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Steps:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		lblNewLabel_1.setBounds(10, 524, 139, 30);
		frame.getContentPane().add(lblNewLabel_1);
	}
	public void setMatrix(StringBuffer s)
	{
		t0.setText("");
		t0.setText(s.charAt(0)+"");
		t1.setText("");
		t1.setText(s.charAt(1)+"");
		t2.setText("");
		t2.setText(s.charAt(2)+"");
		t3.setText("");
		t3.setText(s.charAt(3)+"");
		t4.setText("");
		t4.setText(s.charAt(4)+"");
		t5.setText("");
		t5.setText(s.charAt(5)+"");
		t6.setText("");
		t6.setText(s.charAt(6)+"");
		t7.setText("");
		t7.setText(s.charAt(7)+"");
		t8.setText("");
		t8.setText(s.charAt(8)+"");
	}
	
	public void setMatrix2(StringBuffer s)
	{
		t9.setText("");
		t9.setText(s.charAt(0)+"");
		t10.setText("");
		t10.setText(s.charAt(1)+"");
		t11.setText("");
		t11.setText(s.charAt(2)+"");
		t12.setText("");
		t12.setText(s.charAt(3)+"");
		t13.setText("");
		t13.setText(s.charAt(4)+"");
		t14.setText("");
		t14.setText(s.charAt(5)+"");
		t15.setText("");
		t15.setText(s.charAt(6)+"");
		t16.setText("");
		t16.setText(s.charAt(7)+"");
		t17.setText("");
		t17.setText(s.charAt(8)+"");
	}
	
	
	public void swap(String choice)
	{
		Node1 txt = new Node1();
		txt=m.right;
		if(choice=="BFS")
		{
			m.right=m.n1;
			txt=m.n1;
		}
		String s = new String("");
		StringBuffer node = new StringBuffer("012345678");
		
		for(int i = m.Actions.size()-1;i>=0;i--)
		{
			if(m.Actions.get(i)==1)
			{
				s=s+txt.node.toString()+" action applyed is left "+m.left(node,0,txt).node.toString()+"\n";
				txt=m.left(node,0,txt);
			}
			if(m.Actions.get(i)==2)
			{
				s=s+txt.node.toString()+" action applyed is right "+m.right(node,0,txt).node.toString()+"\n";
				txt=m.right(node,0,txt);
			}
			if(m.Actions.get(i)==3)
			{
				//System.out.println(right.node+ " ffffffffffff "+ node);
				s=s+txt.node.toString()+" action applyed is up "+m.up(node,0,txt).node.toString()+"\n";
				txt=m.up(node,0,txt);
			}
			if(m.Actions.get(i)==4)
			{
				s=s+txt.node.toString()+" action applyed is down "+m.down(node,0,txt).node.toString()+"\n";
				txt=m.down(node,0,txt);
			}
		}
		textarea.setText(s);
	}
}
