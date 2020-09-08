package database;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class IO {

    public IO (String []date_data) {

        FileReader fr;
		try {
			fr = new FileReader("test.txt");
		
        BufferedReader br = new BufferedReader(fr);
        int i=0;
        while (br.ready()) {

           // System.out.println(br.readLine());
            date_data[i]=br.readLine();
            i++;
            
        }
        fr.close();
        }
		catch(IOException e)  {}		
    
    }

}
