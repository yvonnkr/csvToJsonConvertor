package com.yvolabs.csv_to_json.utils;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;

/**
 * {@code CustomCharacterEscapes}  is Custom Character Escape
 * Extends {@code CharacterEscapes}
 *
 * @author Yvonne Nkrunzanzi
 * @since 1.0
 */
public class CustomCharacterEscapes extends CharacterEscapes {
    private final int[] asciiEscapes;

    public CustomCharacterEscapes() {
        asciiEscapes = standardAsciiEscapesForJSON();
        asciiEscapes['"'] = CharacterEscapes.ESCAPE_NONE;
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int i) {
        return null;
    }
}
