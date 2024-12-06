package com.example.uks.enumeration;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Category {
    API_MANAGMENT,
    CONTENT_MANAGMENT_SYSTEM,
    DATA_SCIENCE,
    DATABASE_AND_STORAGE,
    DEVOPS,
    DOCKER_EXTENSIONS,
    LANGUAGE_AND_FRAMEWORKS,
    INTEGRATION_AND_DELIVERY,
    FRONTEND,
    INTERNET_OF_THINGS,
    MACHINE_LEARNING_AND_AI,
    MESSAGE_QUEUES,
    MENTORING_AND_OBSERVABILITY,
    NETWORKING,
    OPERATING_SYSTEM,
    OBSERVABILITY,
    SECURITY,
    WEB_SERVERS,
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
