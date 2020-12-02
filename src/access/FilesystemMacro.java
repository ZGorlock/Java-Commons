/*
 * File:    FilesystemMacro.java
 * Package: access
 * Author:  Zachary Gill
 */

package access;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import string.StringUtility;

/**
 * A resource class that provides filesystem macros.
 */
public final class FilesystemMacro {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilesystemMacro.class);
    
    
    //Functions
    
    /**
     * Macro to delete empty directories within the requested directory.
     *
     * @param dir       The directory to search.
     * @param recursive Flag to recursively search sub-directories or not.
     * @return Whether the operation was successful or not.
     */
    public static boolean deleteEmptyDirectoriesInDirectory(File dir, boolean recursive) {
        boolean success = true;
        
        Collection<File> cf;
        cf = Filesystem.getDirs(dir);
        
        for (File f : cf) {
            if (Filesystem.directoryIsEmpty(f)) {
                success &= Filesystem.deleteDirectory(f);
            } else if (recursive) {
                success &= deleteEmptyDirectoriesInDirectory(f, true);
                if (Filesystem.directoryIsEmpty(f)) {
                    success &= Filesystem.deleteDirectory(f);
                }
            }
        }
        
        return success;
    }
    
    /**
     * Macro to delete empty directories within the requested directory.
     *
     * @param dir The directory to search.
     * @return Whether the operation was successful or not.
     * @see #deleteEmptyDirectoriesInDirectory(File, boolean)
     */
    public static boolean deleteEmptyDirectoriesInDirectory(File dir) {
        return deleteEmptyDirectoriesInDirectory(dir, false);
    }
    
    /**
     * Macro to delete all files listed within the requested file.
     *
     * @param file The file to read the file list from.
     * @return Whether the operation was successful or not.
     */
    public static boolean deleteListOfFilesFromFile(File file) {
        boolean success = true;
        List<String> fileList = Filesystem.readLines(file);
        for (String listed : fileList) {
            if (!Filesystem.delete(new File(listed))) {
                success = false;
            }
        }
        return success;
    }
    
    /**
     * Macro to delete directories within the requested directory that do not contain a particular file type.
     *
     * @param dir       The directory to search.
     * @param fileType  The file type to look for.
     * @param recursive Flag to recursively search sub-directories or not.
     * @return Whether the operation was successful or not.
     */
    public static boolean deleteDirectoriesInDirectoryThatDoNotContainFileType(File dir, String fileType, boolean recursive) {
        boolean success = true;
        
        Collection<File> cf;
        cf = Filesystem.getDirs(dir);
        
        for (File f : cf) {
            if (!directoryContainsFileType(f, fileType, recursive)) {
                success &= Filesystem.deleteDirectory(f);
            } else if (recursive) {
                success &= deleteDirectoriesInDirectoryThatDoNotContainFileType(f, fileType, true);
            }
        }
        
        return success;
    }
    
    /**
     * Macro to delete directories within the requested directory that do not contain a particular file type.
     *
     * @param dir      The directory to search.
     * @param fileType The file type to look for.
     * @return Whether the operation was successful or not.
     * @see #deleteDirectoriesInDirectoryThatDoNotContainFileType(File, String, boolean)
     */
    public static boolean deleteDirectoriesInDirectoryThatDoNotContainFileType(File dir, String fileType) {
        return deleteDirectoriesInDirectoryThatDoNotContainFileType(dir, fileType, true);
    }
    
    /**
     * Determines if a directory contains a file of a particular file type.
     *
     * @param dir       The directory to search.
     * @param fileType  The file type to look for.
     * @param recursive Flag to recursively search sub-directories or not.
     * @return Whether a file of the specified file type exists in the directory or not.
     */
    public static boolean directoryContainsFileType(File dir, String fileType, boolean recursive) {
        Collection<File> cf;
        cf = Filesystem.getFilesAndDirs(dir);
        
        for (File f : cf) {
            if (f.isDirectory() && recursive) {
                if (directoryContainsFileType(f, fileType, true)) {
                    return true;
                }
            } else {
                if (f.getName().endsWith(fileType)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Prepends all of the lines of a file with the prefix.
     *
     * @param file   The file to prepend lines for.
     * @param prefix The prefix to prepend.
     * @param check  Whether or not to check if the prefix is already prepended to the line.
     * @return Whether the operation was successful or not.
     */
    public static boolean prependLinesInFile(File file, String prefix, boolean check) {
        List<String> fileLines = Filesystem.readLines(file);
        List<String> newFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (!check || !line.startsWith(prefix)) {
                line = prefix + line;
            }
            newFileLines.add(line);
        }
        return Filesystem.writeLines(file, newFileLines);
    }
    
    /**
     * Prepends all of the lines of a file with the prefix if the prefix is not already the start of the line.
     *
     * @param file   The file to prepend lines for.
     * @param prefix The prefix to prepend.
     * @return Whether the operation was successful or not.
     * @see #prependLinesInFile(File, String, boolean)
     */
    public static boolean prependLinesInFile(File file, String prefix) {
        return prependLinesInFile(file, prefix, true);
    }
    
    /**
     * Un-prepends all of the lines of a file with the prefix.
     *
     * @param file   The file to prepend lines for.
     * @param prefix The prefix to prepend.
     * @return Whether the operation was successful or not.
     */
    public static boolean unprependLinesInFile(File file, String prefix) {
        List<String> fileLines = Filesystem.readLines(file);
        List<String> newFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (line.startsWith(prefix)) {
                line = StringUtility.lShear(line, prefix.length());
            }
            newFileLines.add(line);
        }
        return Filesystem.writeLines(file, newFileLines);
    }
    
    /**
     * Macro to copy all the songs listed in a playlist to a specified directory.
     * The songs listed in the playlist are expected to be in the form: "pathPrefix\artist\song.*".
     * The songs will be copied to the directory in the form: "directory\artist\song.*".
     *
     * @param playlist   The playlist to read the list of songs from.
     * @param directory  The directory to copy the songs to.
     * @param pathPrefix The pathPrefix of the location for music.
     * @return Whether the operation was successful or not.
     */
    public static boolean copyPlaylistToDirectory(File playlist, File directory, String pathPrefix) {
        boolean success = true;
        List<String> fileList = Filesystem.readLines(playlist);
        for (String listed : fileList) {
            File song = new File(listed);
            if (song.exists()) {
                String outputDirectory = directory.getAbsolutePath();
                if (!outputDirectory.endsWith("\\")) {
                    outputDirectory = outputDirectory + '\\';
                }
                
                File output = new File(song.getAbsolutePath().replace(pathPrefix, outputDirectory));
                success &= Filesystem.copyFile(song, output, true);
            }
        }
        return success;
    }
    
    /**
     * Recursively searches for jars in a specified directory and returns a subset of them that contain a specified folder in their top level.
     *
     * @param directory  The directory to search for jars in.
     * @param folderName The folder name to search for within the jars.
     * @return A list of jars that contain the specified folder in their top level.
     */
    public static List<File> findJarsInDirectoryThatContainFolder(File directory, String folderName) {
        List<File> jarList = Filesystem.getFilesRecursively(directory, ".*\\.jar");
        List<File> found = new ArrayList<>();
        File tmpDir = new File("tmp");
        for (File jar : jarList) {
            File extractDir = new File(tmpDir, UUID.randomUUID().toString());
            Archive.extract(jar, extractDir);
            File finding = new File(extractDir, folderName);
            if (finding.exists()) {
                found.add(jar);
            }
            Filesystem.deleteDirectory(extractDir);
        }
        return found;
    }
    
}
