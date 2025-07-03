package data;

import model.book;
import exception.DataAccessException;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import static sun.nio.ch.IOUtil.load;

public class FileLibraryRepository implements BookRepository {
    private final Path filePath;
    private final List<book> books = new ArrayList<>();

    public FileLibraryRepository(String filePath) {
        this.filePath = Paths.get(filePath);
        load();
    }
}