package czatGui;

public class WrongNameException extends Exception{
	@Override
	public String toString() {
		return "Nick musi mieć więcej niż 3 znaki";
	}
}
