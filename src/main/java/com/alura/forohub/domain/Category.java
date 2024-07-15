package com.alura.forohub.domain;

public enum Category {
    TECHNOLOGY,
    VIDEO_GAMES,
    MUSIC,
    COURSES,
    BOOKS;

    // Método para obtener la instancia del enum a partir de una cadena
    public static Category fromString(String urlCategory) {
        for (Category category : values()) {
            if (category.name().equalsIgnoreCase(urlCategory)) {
                return category;
            }
        }
        throw new IllegalArgumentException("La categoría es incorrecta: " + Category.class.getCanonicalName() + "." + urlCategory);
    }
    }

