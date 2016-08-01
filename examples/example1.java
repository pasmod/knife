public class Example {
	int counter = 5;
	public static void main(String[] args) {
		System.out.println(add(2, 3));
	}

	public static int add(final int a, final int b) {
		return a + b;
	}

}

public class SecondClass {

	public void print(int a) {
		System.out.println(a);
	}
}
