package Master;

public class singleton {
	
	static singleton instance;
	
	public static singleton getInstance(){
		if (instance == null)
			instance = new singleton();
		
		return instance;
	}

}
