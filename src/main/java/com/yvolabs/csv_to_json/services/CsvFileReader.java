package com.yvolabs.csv_to_json.services;

import com.yvolabs.csv_to_json.interfaces.FileReadable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * Reads text from a csv file.
 * <p>
 * The {@code CsvFileReader} is meant for reading csv file and
 * returns a stream of data. Implements {@code FileReadable}
 *
 * @author Yvonne Nkrunzanzi
 * @see FileReadable
 * @since 1.0
 */
public class CsvFileReader implements FileReadable<String> {
    private final String FILEPATH;

    public CsvFileReader(String FILEPATH) {

        this.FILEPATH = FILEPATH;
    }


    @Override
    public Stream<String> read() throws IOException {

        return readFile(this.FILEPATH);
    }

    /**
     * Method {@code readFile}, given the {@code Filepath} reads the file and returns a Stream of data
     *
     * @param filePath the file
     * @return {@code  Stream<String>}
     * @throws IOException If the file does not exist,
     *                     or is a directory rather than a regular file,
     *                     or for some other reason cannot be opened for
     *                     reading.
     * @since 1.0
     */
    private Stream<String> readFile(String filePath) throws IOException {
        InputStream in = getClass().getResourceAsStream(filePath);

        if (in == null) {
            throw new IOException(filePath + " No such file or directory");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.lines();
    }


}
