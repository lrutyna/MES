package program;

public class CompleteElement {

	 final double N[][] = new double[4][4];          //wartosci fukncji ksztaltu 
	 final double dNdKsi[][] = new double[4][4];     //pochodna fukcji ksztaltu po ksi
	 final double dNdEta[][] = new double[4][4];     //pochodna fukcji ksztaltu po eta
	 final double Npow1[][] = new double[2][4];      //wartosci fukncji ksztaltu dla powierzchni pow1
	 final double Npow2[][] = new double[2][4];  	   //wartosci fukncji ksztaltu dla powierzchni pow2
	 final double Npow3[][] = new double[2][4];      //wartosci fukncji ksztaltu dla powierzchni pow3
	 final double Npow4[][] = new double[2][4];      //wartosci fukncji ksztaltu dla powierzchni pow4
	 
	 final double points[][] = {{-0.577,-0.577},
	 					  {0.577,-0.577},
	 					  {0.577,0.577},
	 					  {-0.577,0.577}};     //4 punkty ca³kowania po objetoœci
	 
	 final double pow1points[][] = {{-0.577,-1},    //punkty ca³kowania po powierzchni
	 						  {0.577,-1}};
	 final double pow2points[][] = {{1,-0.577},
			  				  {1,0.577}};
	 final double pow3points[][] = {{0.577,1},
			  				  {-0.577,1}};
	 final double pow4points[][] = {{-1,0.577},
			 				  {-1,-0.577}};
	 
	 
	 public CompleteElement(){
		 for (int i = 0; i < 4; i++) {
			 N[i][0] = 0.25 * (1 - points[i][0]) * (1 - points[i][1]);
	         N[i][1] = 0.25 * (1 + points[i][0]) * (1 - points[i][1]);
	         N[i][2] = 0.25 * (1 + points[i][0]) * (1 + points[i][1]);
	         N[i][3] = 0.25 * (1 - points[i][0]) * (1 + points[i][1]);
	         
	         dNdKsi[i][0] = -0.25 * (1 - points[i][1]);
	         dNdKsi[i][1] = 0.25 * (1 - points[i][1]);
	         dNdKsi[i][2] = 0.25 * (1 + points[i][1]);
	         dNdKsi[i][3] = -0.25 * (1 + points[i][1]);

	         dNdEta[i][0] = -0.25 * (1 - points[i][0]);
	         dNdEta[i][1] = -0.25 * (1 + points[i][0]);
	         dNdEta[i][2] = 0.25 * (1 + points[i][0]);
	         dNdEta[i][3] = 0.25 * (1 - points[i][0]);
		 }
		 
		 for(int i=0; i<2; i++){
        	 Npow1[i][0] = 0.25 * (1 - pow1points[i][0]) * (1 - pow1points[i][1]);
	         Npow1[i][1] = 0.25 * (1 + pow1points[i][0]) * (1 - pow1points[i][1]);
	         Npow1[i][2] = 0.25 * (1 + pow1points[i][0]) * (1 + pow1points[i][1]);
	         Npow1[i][3] = 0.25 * (1 - pow1points[i][0]) * (1 + pow1points[i][1]);
	         
	         Npow2[i][0] = 0.25 * (1 - pow2points[i][0]) * (1 - pow2points[i][1]);
	         Npow2[i][1] = 0.25 * (1 + pow2points[i][0]) * (1 - pow2points[i][1]);
	         Npow2[i][2] = 0.25 * (1 + pow2points[i][0]) * (1 + pow2points[i][1]);
	         Npow2[i][3] = 0.25 * (1 - pow2points[i][0]) * (1 + pow2points[i][1]);
	         
	         Npow3[i][0] = 0.25 * (1 - pow3points[i][0]) * (1 - pow3points[i][1]);
	         Npow3[i][1] = 0.25 * (1 + pow3points[i][0]) * (1 - pow3points[i][1]);
	         Npow3[i][2] = 0.25 * (1 + pow3points[i][0]) * (1 + pow3points[i][1]);
	         Npow3[i][3] = 0.25 * (1 - pow3points[i][0]) * (1 + pow3points[i][1]);
	         
	         Npow4[i][0] = 0.25 * (1 - pow4points[i][0]) * (1 - pow4points[i][1]);
	         Npow4[i][1] = 0.25 * (1 + pow4points[i][0]) * (1 - pow4points[i][1]);
	         Npow4[i][2] = 0.25 * (1 + pow4points[i][0]) * (1 + pow4points[i][1]);
	         Npow4[i][3] = 0.25 * (1 - pow4points[i][0]) * (1 + pow4points[i][1]);
         }
	 }
}
