package io.shatru.libarary.management.dto;

public record BookResponse(Long id, String title, String author, int publicationYear, String isbn, String language, boolean availability) {}
