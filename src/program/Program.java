package program;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Program {
	static PrintStream out;
	static PrintStream console = System.out;
	static GlobalData gd = new GlobalData();
	static private Mesh mesh = new Mesh();
	
	public static void main(String args[]){
		try {
			out = new PrintStream("temperatura.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		mesh.initMesh();
		Solution solution = new Solution(mesh);
		
		for(int time=0; time<gd.time; time+=gd.timeStep){
			solution.compute();  
			solution.process();
			show(time+gd.timeStep);
			solution.clearGlobal();
		}
	}
	
	public static void show(double time){
		System.setOut(console);  //out lub console
		System.out.println("Temperatura po czasie: "+time+" s");
		for(int i=0; i<gd.nN; i++){
			for(int j=0; j<gd.nM; j++){
				System.out.printf("%.2f ",mesh.nodes[i][j].temp);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void showElements(){
		int idx=0;
		for(int i=0; i<gd.nN-1; i++){
			for(int j=0; j<gd.nM-1; j++){
				idx++;
				System.out.println();
				System.out.println("Element "+idx);
				for(int k=0; k<4; k++){
					System.out.println(mesh.elements[i][j].nodes.get(k).id+": "+mesh.elements[i][j].nodes.get(k).temp);
				}
			}
		}
		System.out.println();
	}
}
