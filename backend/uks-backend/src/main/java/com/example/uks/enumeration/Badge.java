package com.example.uks.enumeration;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Badge {
    DOCKER_OFFICIAL_IMAGE,
    NONE;

    public String getFormattedName() {
        String formattedName = this.name()
                .toLowerCase()
                .replace('_', ' '); // Replace underscores with spaces

        formattedName = Arrays.stream(formattedName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));

        return formattedName;
    }

}
