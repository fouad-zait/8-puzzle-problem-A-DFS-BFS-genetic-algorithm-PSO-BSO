package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main2.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("8 PUZZLE SOLVER");
			//primaryStage.getIcons().add(new Image("C:\\Users\\LENOVO IP330\\eclipse-workspace\\MAE2\\src\\application\\IMG.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*Node n = new Node();
		Node1 m = new Node1();
		m.node.append("283164705");
		n.Population(1, m);*/
		/*Node1 m = new Node1();
		Node1 b = new Node1();
		m.node.append("283164705");
		StringBuffer s = new StringBuffer("123804765");
		System.out.println(n.solvable(m.node.toString(), s));*/
		/*b=n.AEtoile(m, s,1);
		n.get_path(b,m);*/
		/*StringBuffer s = new StringBuffer("123804765");
		Node n = new Node();
		Node b = new Node();
		n.node.append("283164705");
		//n.fillInitialMatrix();
		b=n.AEtoile(n, s);
		System.out.println("Goal found is:"+b.node+" " +b.f);
		n.get_path(b);
		//System.out.println(n.desplayPath());*/
		launch(args);
		
	}
}
