package de.gowlr.allcar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import de.gowlr.allcar.config.StorageProperties;
import de.gowlr.allcar.exceptions.FileNotFoundException;
import de.gowlr.allcar.exceptions.StorageException;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Storage konnte nicht initialisiert werden: ", e);
        }
    }

    @Override
    public String store(MultipartFile file, Integer id) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path subFolder = Path.of(rootLocation.toString(), id.toString());
        //Sicherstellen, dass solch ein Ordner nicht doppelt angelegt wir
        try {
            // deleteDirectory(subFolder.toFile());
            Files.createDirectories(subFolder);
        } catch (IOException e) {
            throw new StorageException("Storage konnte nicht initialisiert werden: ", e);
        }

        try {
            if (file.isEmpty()) {
                throw new StorageException("Konnte leere Datei nicht speichern: " + filename);
            }
            if (filename.contains("..")) {
                // Security, um Zugriff aufs ausserhalb des Projektverzeichnisses liegende
                // Dateisystem zu verhindern
                throw new StorageException(
                        "Datei konnte aufgrund von relativem Pfad ausserhalb der aktuellen Directory nicht gespeichert werden:  "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(id.toString() + "/" + filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Konnte nicht gespeichert werden: " + filename, e);
        }

        return filename;
    }

    private void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Dateien konnte nicht gelesen werden: ", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Datei konnte nicht gelesen werden: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Datei konnte nicht gelesen werden: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}