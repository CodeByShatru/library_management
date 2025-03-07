package io.shatru.libarary.management.dto;

public record BookRequest(String title,String author, int publicationYear, String isbn, String language, boolean availability) {
}
