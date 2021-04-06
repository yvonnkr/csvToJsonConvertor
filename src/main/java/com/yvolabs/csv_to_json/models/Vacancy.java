package com.yvolabs.csv_to_json.models;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A {@code Vacancy} is POJO.
 *
 * @author Yvonne Nkrunzanzi
 * @since 1.0
 */

@JsonFilter("customFilter")
public class Vacancy {
    private UUID creator;

    @JsonProperty("break")
    private Integer breakLength;

    @JsonRawValue(value = false)
    @JsonSerialize()
    private String description;

    private String start;
    private String end;

    @JsonInclude(Include.NON_NULL)
    private String shiftType;

    public Vacancy() {
    }

    public Vacancy(UUID creator, Integer breakLength, String description, String start, String end, String shiftType) {
        this.creator = creator;
        this.breakLength = breakLength;
        this.description = description;
        this.start = start;
        this.end = end;
        this.shiftType = shiftType;
    }


    public UUID getCreator() {
        return creator;
    }

    public void setCreator(UUID creator) {
        this.creator = creator;
    }

    public Integer getBreakLength() {
        return breakLength;
    }

    public void setBreakLength(Integer breakLength) {
        this.breakLength = breakLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "creator=" + creator +
                ", breakInterval=" + breakLength +
                ", description='" + description + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", shiftType='" + shiftType + '\'' +
                '}';
    }
}
