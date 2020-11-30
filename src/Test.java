import java.io.*;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		InputStream is = getClass().getClassLoader().getResourceAsStream("foo.txt");
	}
}
