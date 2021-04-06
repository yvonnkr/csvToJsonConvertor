package com.yvolabs.csv_to_json.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class CsvFileReaderTest {
    private CsvFileReader mockCsvFileReader;
    private String filePath;

    @BeforeEach
    void setUp() {
        this.filePath = "/inputTest.csv";
        this.mockCsvFileReader = spy(new CsvFileReader(this.filePath));
    }

    @AfterEach
    void tearDown() {
        this.filePath = "/inputTest.csv";
        this.mockCsvFileReader = spy(new CsvFileReader(this.filePath));

    }

    @Test
    @DisplayName("Does not throws when valid filepath")
    void whenValidFilePath_DoesNotThrow() {

        assertDoesNotThrow(mockCsvFileReader::read);
    }

    @Test
    @DisplayName("Throws when invalid filepath")
    void whenInvalidFilePath_ThrowsIOException() {
        filePath = "/invalidFile.csv";
        mockCsvFileReader = spy(new CsvFileReader(filePath));

        IOException ioException = assertThrows(IOException.class, mockCsvFileReader::read);
        String expectedMsg = "No such file or ";
        String actualMsg = ioException.getMessage();

        assertTrue(actualMsg.contains(expectedMsg));
    }


    @Test
    void read_returns_Stream() throws IOException {
        Stream<String> stream = mockCsvFileReader.read();
        assertNotNull(stream);

    }

}