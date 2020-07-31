import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.*;
class MusicTest {

	@Test
	void test() throws IOException {
		BufferedReader Out=new BufferedReader (new FileReader ("CaoDuongPlaylist.txt")); 
		BufferedReader In=new BufferedReader (new FileReader ("Target2.txt"));
		String expectedLine;
		
		while ((expectedLine = In.readLine ()) != null) {
			String actualLine = Out.readLine ();
			assertEquals (expectedLine, actualLine);
		}
		Out.close();
		In.close();
	}
}

