package org.dfernandez.lastminute.com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class AbstractCsvReader<T> {

    private final static Logger LOG = Logger.getLogger(AbstractCsvReader.class.getName());

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    public List<T> readCsvFile(File fileName) {
       // BufferedReader fileReader = null;
        List<T> entities = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            //Read the file line by line starting from the second line
            while (line != null && !line.trim().equals("")) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                T element  = unmarshall(tokens);
                entities.add(element);
                line = reader.readLine();
            }


        } catch (IOException e) {
            LOG.warning (String.format("File %s Not Found. Error message %s ", fileName.getAbsolutePath(), e));
        }

        return entities;

    }

    abstract T unmarshall(String[] tokens);

}
