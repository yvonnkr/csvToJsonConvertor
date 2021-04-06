package com.yvolabs.csv_to_json.services;

import com.yvolabs.csv_to_json.interfaces.MapDataToObject;
import com.yvolabs.csv_to_json.models.Vacancy;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code MapDataToVacancy} Maps data to an {@code Vacancy} Object.
 * Implements {@code MapDataToObject}
 * <p>
 * @author Yvonne Nkrunzanzi
 * @see MapDataToObject
 * @since 1.0
 */
public class MapDataToVacancy implements MapDataToObject<Vacancy> {
    private final Stream<String> data;
    private final Pattern pattern;


    public MapDataToVacancy(Stream<String> data, String separator) {
        this.data = data;
        this.pattern = Pattern.compile(separator + Constants.REGEX_IGNORE_COMMA);
    }

    /** {@code map} Main method to run
     * @return {@code List<Vacancy>}
     */
    @Override
    public List<Vacancy> map() {
        return mapVacancy();
    }

    /** Maps each line to {@code Vacancy}
     * @return {@code List<Vacancy>}
     * @throws NullPointerException
     */
    private List<Vacancy> mapVacancy() throws NullPointerException {
        return Objects.requireNonNull(this.data)
                .skip(1)
                .map(line -> {
                    String[] str = this.pattern.split(line);

                    if (str.length > 5) { // hardcoded
                        Vacancy vacancy = new Vacancy();

                        vacancy.setCreator(UUID.fromString(str[0]));
                        vacancy.setBreakLength(Integer.parseInt(str[1]));
                        vacancy.setDescription(str[2]);
                        vacancy.setStart(str[3]);
                        vacancy.setEnd(str[4]);
                        vacancy.setShiftType(str[5]);

                        return vacancy;
                    } else {
                        return null;
                    }
                }).collect(Collectors.toList());
    }

}
