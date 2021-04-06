package com.yvolabs.csv_to_json.interfaces;

import java.util.List;

/**
 * A {@code MapDataToObject} is a source for maping data to object.
 * The {@code map} method returns a List.
 *
 * @author Yvonne Nkrunzanzi
 * @since 1.0
 */
public interface MapDataToObject<T> {
    List<T> map();
}
