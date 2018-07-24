package program;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GlobalData {
	double tBegin;                   //temp. poczatkowa, C  
	double time;                     //czas procesu, s
	double timeStep;                 //krok czasowy, s
	double tEnv;                     //temp. otoczenia, C   
	double alfa;                     //wsp. wymiany ciep³a  
	double N;                        //wysokosc przekroju, m
	double M;                        //szerokosc przekroju, m
	int nN;                          //liczba wezlow po wysokosci przekroju
	int nM;                          //liczba wezlow po szerokosci przekroju
	double C;                        //ciep³o w³aœciwe  
	double K;                        //wsp. przewodzenia ciep³a  
	double R;                        //gêstoœæ  
	int nNodes;                      //liczba wezlow
	int nElements;                   //liczba elementow
	
	double data[]=new double[12];    //dane wczytane z pliku
    
    CompleteElement completeEl = new CompleteElement();
    
    public GlobalData(){
    	try {
			readData();
			nNodes = nN * nM;
			nElements = (nN - 1) * (nM - 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void readData() throws IOException{
    	File file = new File("dane.txt");   
    	Scanner sc = new Scanner(file);
    	int i=0;

    	while (sc.hasNextLine()) {
    		if(i!=7 && i!=8){
    			data[i] = Double.parseDouble(sc.nextLine());
    		}else if(i==7){
    			nN = Integer.parseInt(sc.nextLine());
    		}else if(i==8){
    			nM = Integer.parseInt(sc.nextLine());
    		}
    	    i++;
    	}
        
        tBegin=data[0];
        time=data[1];
        timeStep=data[2];
        tEnv=data[3];
        alfa=data[4];
        N=data[5];
        M=data[6];
        C=data[9];
        K=data[10];
        R=data[11];
    }
}
