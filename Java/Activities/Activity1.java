
public class Activity1 {

	public static void main(String[] args) {
		
		Car carName = new Car();
		carName.make = 2014;
		carName.color = "Black";
		carName.transmission = "Manual";
    
        //Using Car class method
		carName.displayCharacterstics();
		carName.accelerate();
		carName.brake();
	}

}
