package program;

public class Jacobian {
	CompleteElement cEl = new CompleteElement();
	double[] detJ = new double[4];
	double dNdx[][] = new double[4][4];	       //pochodne funkcji kszta³tu po x
	double dNdy[][] = new double[4][4];	   	   //pochoden funkcji kszta³tu po y	
	double[] dxdKsi = new double[4];
	double[] dxdEta = new double[4];
	double[] dydKsi = new double[4];
	double[] dydEta = new double[4];
	
	public void computeJacobian(Element el){
		for(int i=0; i<4; i++){
			dxdKsi[i]=cEl.dNdKsi[i][0]*el.getNodes().get(0).x+cEl.dNdKsi[i][1]*el.getNodes().get(1).x+cEl.dNdKsi[i][2]*el.getNodes().get(2).x+cEl.dNdKsi[i][3]*el.getNodes().get(3).x;
			dxdEta[i]=cEl.dNdEta[i][0]*el.getNodes().get(0).x+cEl.dNdEta[i][1]*el.getNodes().get(1).x+cEl.dNdEta[i][2]*el.getNodes().get(2).x+cEl.dNdEta[i][3]*el.getNodes().get(3).x;
			dydKsi[i]=cEl.dNdKsi[i][0]*el.getNodes().get(0).y+cEl.dNdKsi[i][1]*el.getNodes().get(1).y+cEl.dNdKsi[i][2]*el.getNodes().get(2).y+cEl.dNdKsi[i][3]*el.getNodes().get(3).y;
			dydEta[i]=cEl.dNdEta[i][0]*el.getNodes().get(0).y+cEl.dNdEta[i][1]*el.getNodes().get(1).y+cEl.dNdEta[i][2]*el.getNodes().get(2).y+cEl.dNdEta[i][3]*el.getNodes().get(3).y;
			detJ[i]=dxdKsi[i]*dydEta[i]-dxdEta[i]*dydKsi[i];
		}
		
		for(int i=0; i<4; i++){
			dNdx[i][0]=1/detJ[i]*(dydEta[i]*cEl.dNdKsi[i][0]+(-dydKsi[i]*cEl.dNdEta[i][0]));
			dNdx[i][1]=1/detJ[i]*(dydEta[i]*cEl.dNdKsi[i][1]+(-dydKsi[i]*cEl.dNdEta[i][1]));
			dNdx[i][2]=1/detJ[i]*(dydEta[i]*cEl.dNdKsi[i][2]+(-dydKsi[i]*cEl.dNdEta[i][2]));
			dNdx[i][3]=1/detJ[i]*(dydEta[i]*cEl.dNdKsi[i][3]+(-dydKsi[i]*cEl.dNdEta[i][3]));
			
			dNdy[i][0]=1/detJ[i]*((-dxdEta[i]*cEl.dNdKsi[i][0])+dxdKsi[i]*cEl.dNdEta[i][0]);
			dNdy[i][1]=1/detJ[i]*((-dxdEta[i]*cEl.dNdKsi[i][1])+dxdKsi[i]*cEl.dNdEta[i][1]);
			dNdy[i][2]=1/detJ[i]*((-dxdEta[i]*cEl.dNdKsi[i][2])+dxdKsi[i]*cEl.dNdEta[i][2]);
			dNdy[i][3]=1/detJ[i]*((-dxdEta[i]*cEl.dNdKsi[i][3])+dxdKsi[i]*cEl.dNdEta[i][3]);
		}
	}
	
	public void clearJacobian(){
		for(int i=0; i<4; i++){
			dxdKsi[i]=0;
			dxdEta[i]=0;
			dydKsi[i]=0;
			dydEta[i]=0;
			detJ[i]=0;
			dNdx[i][0]=0;
			dNdx[i][1]=0;
			dNdx[i][2]=0;
			dNdx[i][3]=0;
			dNdy[i][0]=0;
			dNdy[i][1]=0;
			dNdy[i][2]=0;
			dNdy[i][3]=0;
		}
	}
	
}
