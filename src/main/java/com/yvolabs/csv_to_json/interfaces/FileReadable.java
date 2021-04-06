package com.yvolabs.csv_to_json.interfaces;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * A {@code FileReadable} is a source for reading any file.
 * The {@code read} method returns a Stream of data.
 *
 * @author Yvonne Nkrunzanzi
 * @since 1.0
 */
public interface FileReadable<T>  {
    Stream<T> read() throws IOException;
}