package program;

public class Mesh {
	GlobalData gd = new GlobalData();
	int nN = gd.nN;                    //liczba wezlow po wysokosci przekroju
	int nM = gd.nM;                    //liczba wezlow po szerokosci przekroju
	double N = gd.N;                   //wysokoœæ przekroju
	double M = gd.M;                   //szerokoœæ przekroju
	double dx = M / (nM - 1);          //odleglosci miedzy wezlami x
	double dy = N / (nN - 1);          //odleglosci miedzy wezlami y
	private int idCounter;
	
	Element elements[][] = new Element[nN-1][nM-1];
	Node nodes[][] = new Node[nN][nM];
	
	public void initMesh(){
		idCounter=0;
		for(int i=0; i<nN; i++){
			for(int j=0; j<nM; j++){
				idCounter++;
				nodes[i][j]=new Node(i * dx, j * dy);
				nodes[i][j].setId(idCounter);
				nodes[i][j].setTemp(gd.tBegin);
			}
		}
		idCounter=0;
		for(int i=0; i<nN-1; i++){
			for(int j=0; j<nM-1; j++){
				idCounter++;
				elements[i][j]=new Element(idCounter);
				elements[i][j].addNode(nodes[i][j]);
				elements[i][j].addNode(nodes[i+1][j]);
				elements[i][j].addNode(nodes[i+1][j+1]);
				elements[i][j].addNode(nodes[i][j+1]);
				elements[i][j].checkContactEdges();
			}
		}
	}
}
