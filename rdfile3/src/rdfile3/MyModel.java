package rdfile3;

public class MyModel {
	private String date;
	private String name;
	private int rmNombre;
	private double rmMontant;
	private int pfNombre;
	private double pfMontant;
	private int rDABNombre;
	private double rDABMontant;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRmNombre() {
		return rmNombre;
	}
	public void setRmNombre(int rmNombre) {
		this.rmNombre = rmNombre;
	}
	public double getRmMontant() {
		return rmMontant;
	}
	public void setRmMontant(double mPAYMENT_ATB_TTCASH) {
		this.rmMontant = mPAYMENT_ATB_TTCASH;
	}
	public int getPfNombre() {
		return pfNombre;
	}
	public void setPfNombre(int pfNombre) {
		this.pfNombre = pfNombre;
	}
	public double getPfMontant() {
		return pfMontant;
	}
	public void setPfMontant(double mPAYMENT_ATB_PFACT) {
		this.pfMontant = mPAYMENT_ATB_PFACT;
	}
	public int getrDABNombre() {
		return rDABNombre;
	}
	public void setrDABNombre(int rDABNombre) {
		this.rDABNombre = rDABNombre;
	}
	public double getrDABMontant() {
		return rDABMontant;
	}
	public void setrDABMontant(double dAB_ATB_TTCASH) {
		this.rDABMontant = dAB_ATB_TTCASH;
	}
	public MyModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyModel(String date, String name, int rmNombre, double rmMontant, int pfNombre, double pfMontant, int rDABNombre,
			double rDABMontant) {
		super();
		this.date = date;
		this.name = name;
		this.rmNombre = rmNombre;
		this.rmMontant = rmMontant;
		this.pfNombre = pfNombre;
		this.pfMontant = pfMontant;
		this.rDABNombre = rDABNombre;
		this.rDABMontant = rDABMontant;
	}
	
}
