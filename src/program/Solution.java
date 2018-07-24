package program;

import java.util.Vector;

public class Solution {
	private GlobalData gd = new GlobalData();
	private GaussElimination elimination = new GaussElimination();
	private CompleteElement cEl = gd.completeEl;
	private int nN = gd.nN;                    		//liczba wezlow po wysokosci przekroju
	private int nM = gd.nM;                    		//liczba wezlow po szerokosci przekroju
	private double N = gd.N;                   		//wysokoœæ przekroju
	private double M = gd.M;                   		//szerokoœæ przekroju
	private int w[][]={{1,1},{1,1},{1,1},{1,1}};    //wagi
	private double egdeNlength = N/(nN-1);          //d³ugosc krawedzi po wysokoœci
	private double egdeMlength = M/(nM-1);          //d³ugosc krawedzi po szerokoœci
	
	private Mesh mesh;
	private Jacobian jcb = new Jacobian();
	private double globalH[][]=new double[nN*nM][nN*nM];
	private double globalC[][]=new double[nN*nM][nN*nM];
	private double globalP[]=new double[nN*nM];
	private double localH[][]=new double[4][4];
	private double localC[][]=new double[4][4];
	private double localP[]=new double[4];
	private double A[][]=new double[nN*nM][nN*nM];
	private double B[]=new double[nN*nM];
	private Vector<Double> vB = new Vector<>();
	private Vector<Double> t1 = new Vector<>();
	private Vector<Double> t0 = new Vector<>();
	private int index=0;
	
	public Solution(Mesh mesh){
		this.mesh=mesh;
	}
	
	public void compute(){
		//dla ka¿dego elementu w siatce
		for(int i=0; i<nN-1; i++){
			for(int j=0; j<nM-1; j++){
				
				jcb.computeJacobian(mesh.elements[i][j]);
				for(int k=0; k<4; k++){
					for(int l=0; l<4; l++){
						localH[k][l]+=gd.K*jcb.dNdx[0][k]*jcb.dNdx[0][l]*jcb.detJ[0]*w[0][0]*w[0][1];
						localH[k][l]+=gd.K*jcb.dNdy[0][k]*jcb.dNdy[0][l]*jcb.detJ[0]*w[0][0]*w[0][1];
						
						localH[k][l]+=gd.K*jcb.dNdx[1][k]*jcb.dNdx[1][l]*jcb.detJ[1]*w[1][0]*w[1][1];
						localH[k][l]+=gd.K*jcb.dNdy[1][k]*jcb.dNdy[1][l]*jcb.detJ[1]*w[1][0]*w[1][1];
						
						localH[k][l]+=gd.K*jcb.dNdx[2][k]*jcb.dNdx[2][l]*jcb.detJ[2]*w[2][0]*w[2][1];
						localH[k][l]+=gd.K*jcb.dNdy[2][k]*jcb.dNdy[2][l]*jcb.detJ[2]*w[2][0]*w[2][1];
						
						localH[k][l]+=gd.K*jcb.dNdx[3][k]*jcb.dNdx[3][l]*jcb.detJ[3]*w[3][0]*w[3][1];
						localH[k][l]+=gd.K*jcb.dNdy[3][k]*jcb.dNdy[3][l]*jcb.detJ[3]*w[3][0]*w[3][1];
						
						localC[k][l]+=gd.C*gd.R*cEl.N[0][k]*cEl.N[0][l]*jcb.detJ[0]*w[0][0]*w[0][1];
						localC[k][l]+=gd.C*gd.R*cEl.N[1][k]*cEl.N[1][l]*jcb.detJ[1]*w[1][0]*w[1][1];
						localC[k][l]+=gd.C*gd.R*cEl.N[2][k]*cEl.N[2][l]*jcb.detJ[2]*w[2][0]*w[2][1];
						localC[k][l]+=gd.C*gd.R*cEl.N[3][k]*cEl.N[3][l]*jcb.detJ[3]*w[3][0]*w[3][1];	
					}
				}
					
					//sprawdzenie czy element ma krawêdzie kontaktowe
					if(!(mesh.elements[i][j].contactEdges.isEmpty())){
						for(int idx=0; idx<mesh.elements[i][j].contactEdges.size(); idx++){
							switch (mesh.elements[i][j].contactEdges.get(idx)) {
						    case 1:
						    	for(int m=0; m<4;m++){
						    		for(int n=0; n<4; n++){
						    			localH[m][n]+=gd.alfa*cEl.Npow1[0][m]*cEl.Npow1[0][n]*0.5*egdeNlength;
						    			localH[m][n]+=gd.alfa*cEl.Npow1[1][m]*cEl.Npow1[1][n]*0.5*egdeNlength;
						    		}
						    	}
						      break;
						    case 2:
						    	for(int m=0; m<4;m++){
						    		for(int n=0; n<4; n++){
						    			localH[m][n]+=gd.alfa*cEl.Npow2[0][m]*cEl.Npow2[0][n]*0.5*egdeMlength;
						    			localH[m][n]+=gd.alfa*cEl.Npow2[1][m]*cEl.Npow2[1][n]*0.5*egdeMlength;
						    		}
						    	}
						      break;
						    case 3:
						    	for(int m=0; m<4;m++){
						    		for(int n=0; n<4; n++){
						    			localH[m][n]+=gd.alfa*cEl.Npow3[0][m]*cEl.Npow3[0][n]*0.5*egdeNlength;
						    			localH[m][n]+=gd.alfa*cEl.Npow3[1][m]*cEl.Npow3[1][n]*0.5*egdeNlength;
						    		}
						    	}
							  break;
						    case 4:
						    	for(int m=0; m<4;m++){
						    		for(int n=0; n<4; n++){
						    			localH[m][n]+=gd.alfa*cEl.Npow4[0][m]*cEl.Npow4[0][n]*0.5*egdeMlength;
						    			localH[m][n]+=gd.alfa*cEl.Npow4[1][m]*cEl.Npow4[1][n]*0.5*egdeMlength;
						    		}
						    	}
							   break;
							}
						}	
					}
					
					//wyliczenie wektora P dla elementu
					//sprawdzenie czy element ma krawêdzie kontaktowe
					if(!(mesh.elements[i][j].contactEdges.isEmpty())){
						
						for(int idx=0; idx<mesh.elements[i][j].contactEdges.size(); idx++){
							switch (mesh.elements[i][j].contactEdges.get(idx)) {
						    case 1:
						    	for(int m=0; m<4;m++){
						    		localP[m]+=-gd.alfa*cEl.Npow1[0][m]*gd.tEnv*0.5*egdeNlength;
					    			localP[m]+=-gd.alfa*cEl.Npow1[1][m]*gd.tEnv*0.5*egdeNlength;
						    	}
						      break;
						    case 2:
						    	for(int m=0; m<4;m++){
						    		localP[m]+=-gd.alfa*cEl.Npow2[0][m]*gd.tEnv*0.5*egdeMlength;
					    			localP[m]+=-gd.alfa*cEl.Npow2[1][m]*gd.tEnv*0.5*egdeMlength;
						    	}
						      break;
						    case 3:
						    	for(int m=0; m<4;m++){
						    		localP[m]+=-gd.alfa*cEl.Npow3[0][m]*gd.tEnv*0.5*egdeNlength;
					    			localP[m]+=-gd.alfa*cEl.Npow3[1][m]*gd.tEnv*0.5*egdeNlength;
						    	}
							  break;
						    case 4:
						    	for(int m=0; m<4;m++){
						    		localP[m]+=-gd.alfa*cEl.Npow4[0][m]*gd.tEnv*0.5*egdeMlength;
					    			localP[m]+=-gd.alfa*cEl.Npow4[1][m]*gd.tEnv*0.5*egdeMlength;
						    	}
							   break;
							}
						}
					}
					jcb.clearJacobian();
					this.aggregation(i,j);
					this.clearLocal();
			}
		}
	}
	
	//agregacja macierzy H, C oraz wektora P dla jednego elementu
	public void aggregation(int n, int m){
		for(int i=0; i<4; i++){
			for(int j=0; j<4;j++){
				globalH[mesh.elements[n][m].nodes.get(i).id-1][mesh.elements[n][m].nodes.get(j).id-1]+=localH[i][j];
				globalC[mesh.elements[n][m].nodes.get(i).id-1][mesh.elements[n][m].nodes.get(j).id-1]+=localC[i][j]; 
			}
			globalP[mesh.elements[n][m].nodes.get(i).id-1]+=localP[i];
		}
	}
	
	public void process(){
		
		for(int i=0; i<nN; i++){
			for(int j=0; j<nM; j++){
				t0.add(mesh.nodes[i][j].temp);
			}
		}
		
		for(int i=0; i<nN*nM; i++){
			for(int j=0; j<nN*nM;j++){
				A[i][j]=globalH[i][j]+globalC[i][j]/gd.timeStep;
				B[i]+=-globalC[i][j]/gd.timeStep*t0.get(j);
			}
			B[i]+=globalP[i];
			vB.add(-B[i]);
		}
		
		t1 = elimination.gaussElimination(nN*nM, A, vB);
		
		index=0;
		for(int i=0; i<nN; i++){
			for(int j=0; j<nM; j++){
				mesh.nodes[i][j].temp=t1.get(index);
				index++;
			}
		}
	}
	
	public void clearLocal(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4;j++){
				localH[i][j]=0;
				localC[i][j]=0;
			}
			localP[i]=0;
		}
	}
	
	public void clearGlobal(){
		for(int i=0; i<nN*nM; i++){
			for(int j=0; j<nN*nM; j++){
				A[i][j]=0;
				globalH[i][j]=0;
				globalC[i][j]=0;
			}
			B[i]=0;
			globalP[i]=0;
		}
		vB.removeAllElements();
		t0.removeAllElements();
		t1.removeAllElements();
	}

	public Vector<Double> getT1() {
		return t1;
	}
}
