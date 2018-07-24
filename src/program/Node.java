package program;

public class Node {
	GlobalData gd = new GlobalData();
	double x, y; 
    boolean status; 
    double temp;
    int id;

	public Node(double x, double y){
    	this.x=x;
    	this.y=y;	
    	
    	if(x==0||y==0||x==gd.M||y==gd.N){
    		this.status=true;
    	}else{
    		this.status=false;
    	}
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
}
