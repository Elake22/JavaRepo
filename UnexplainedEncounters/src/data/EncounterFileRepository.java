package data;

import domain.Encounter;
import domain.EncounterType;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EncounterFileRepository {
    private final String filePath;

    public EncounterFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Encounter> findAll() {
        List<Encounter> encounters = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { // Skip CSV header
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length == 4) {
                    int id = Integer.parseInt(fields[0]);
                    EncounterType type = EncounterType.valueOf(fields[1].toUpperCase());
                    LocalDate date = LocalDate.parse(fields[2]);
                    String description = fields[3];

                    encounters.add(new Encounter(id, description, date, type));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading encounters file: " + e.getMessage());
        }

        return encounters;
    }

    public Encounter findById(int id) {
        return findAll().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Encounter> findByType(EncounterType type) {
        List<Encounter> result = new ArrayList<>();
        for (Encounter e : findAll()) {
            if (e.getType() == type) {
                result.add(e);
            }
        }
        return result;
    }
    public boolean update(Encounter updated) {
        List<Encounter> all = findAll();
        boolean found = false;

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == updated.getId()) {
                all.set(i, updated);
                found = true;
                break;
            }
        }

        if (!found) return false;

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filePath)))) {
            // Write CSV header
            writer.println("encounterId,type,when,description");

            for (Encounter e : all) {
                writer.printf("%d,%s,%s,%s%n",
                        e.getId(),
                        e.getType(),
                        e.getDate(),
                        e.getDescription().replace(",", " "));
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteById(int id) {
        List<Encounter> all = findAll();
        boolean removed = all.removeIf(e -> e.getId() == id);

        if (!removed) return false;

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filePath)))) {
            writer.println("encounterId,type,when,description");

            for (Encounter e : all) {
                writer.printf("%d,%s,%s,%s%n",
                        e.getId(),
                        e.getType(),
                        e.getDate(),
                        e.getDescription().replace(",", " "));
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    public boolean add(Encounter encounter) {
        List<Encounter> all = findAll();

        int nextId = all.stream()
                .mapToInt(Encounter::getId)
                .max()
                .orElse(0) + 1;

        encounter.setId(nextId);
        all.add(encounter);

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filePath)))) {
            writer.println("encounterId,type,when,description");

            for (Encounter e : all) {
                writer.printf("%d,%s,%s,%s%n",
                        e.getId(),
                        e.getType(),
                        e.getDate(),
                        e.getDescription().replace(",", " "));
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }



}
