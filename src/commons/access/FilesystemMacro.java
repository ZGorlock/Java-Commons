/*
 * File:    FilesystemMacro.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import commons.string.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        
        for (File subDir : Filesystem.getDirs(dir)) {
            if (Filesystem.directoryIsEmpty(subDir)) {
                success &= Filesystem.deleteDirectory(subDir);
            } else if (recursive) {
                success &= deleteEmptyDirectoriesInDirectory(subDir, true);
                if (Filesystem.directoryIsEmpty(subDir)) {
                    success &= Filesystem.deleteDirectory(subDir);
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
        
        for (String listed : Filesystem.readLines(file)) {
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
        
        for (File subDir : Filesystem.getDirs(dir)) {
            if (!directoryContainsFileType(subDir, fileType, recursive)) {
                success &= Filesystem.deleteDirectory(subDir);
            } else if (recursive) {
                success &= deleteDirectoriesInDirectoryThatDoNotContainFileType(subDir, fileType, true);
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
        for (File file : Filesystem.getFilesAndDirs(dir)) {
            if (file.isDirectory() && recursive) {
                if (directoryContainsFileType(file, fileType, true)) {
                    return true;
                }
            } else {
                if (file.getName().endsWith(fileType)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Determines if a directory contains a file of a particular file type.
     *
     * @param dir      The directory to search.
     * @param fileType The file type to look for.
     * @return Whether a file of the specified file type exists in the directory or not.
     * @see #directoryContainsFileType(File, String, boolean)
     */
    public static boolean directoryContainsFileType(File dir, String fileType) {
        return directoryContainsFileType(dir, fileType, true);
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
        List<String> newLines = new ArrayList<>();
        
        for (String line : Filesystem.readLines(file)) {
            if (!check || !line.startsWith(prefix)) {
                line = prefix + line;
            }
            newLines.add(line);
        }
        
        return Filesystem.writeLines(file, newLines);
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
        List<String> newLines = new ArrayList<>();
        
        for (String line : Filesystem.readLines(file)) {
            if (line.startsWith(prefix)) {
                line = StringUtility.lShear(line, prefix.length());
            }
            newLines.add(line);
        }
        
        return Filesystem.writeLines(file, newLines);
    }
    
    /**
     * Replaces all instances of a string in a file.
     *
     * @param file    The file to replace in.
     * @param search  The string to search for.
     * @param replace The string to replace with.
     * @return Whether the operation was successful or not.
     */
    public static boolean replaceInFile(File file, String search, String replace) {
        List<String> newLines = new ArrayList<>();
        
        for (String line : Filesystem.readLines(file)) {
            newLines.add(line.replace(search, replace));
        }
        
        return Filesystem.writeLines(file, newLines);
    }
    
    /**
     * Replaces all instances of a regex pattern in a file.
     *
     * @param file        The file to replace in.
     * @param regexSearch The regex pattern to search for.
     * @param replace     The string to replace with.
     * @return Whether the operation was successful or not.
     */
    public static boolean regexReplaceInFile(File file, String regexSearch, String replace) {
        List<String> newLines = new ArrayList<>();
        
        for (String line : Filesystem.readLines(file)) {
            newLines.add(line.replaceAll(regexSearch, replace));
        }
        
        return Filesystem.writeLines(file, newLines);
    }
    
    /**
     * Macro to copy all the songs listed in a playlist to a specified directory.
     * The songs listed in the playlist are expected to be in the form: "pathPrefix\artist\song.*".
     * The songs will be copied to the directory in the form: "directory\artist\song.*".
     *
     * @param playlist   The playlist to read the list of songs from.
     * @param directory  The directory to copy the songs to.
     * @param pathPrefix The path prefix of the location for music.
     * @return Whether the operation was successful or not.
     */
    public static boolean copyPlaylistToDirectory(File playlist, File directory, String pathPrefix) {
        boolean success = true;
        
        for (String playlistEntry : Filesystem.readLines(playlist)) {
            File song = new File(playlistEntry.replace("\\", "/"));
            if (song.exists()) {
                String outputDirectory = directory.getAbsolutePath().replace("\\", "/");
                if (!outputDirectory.endsWith("/")) {
                    outputDirectory = outputDirectory + '/';
                }
                
                File output = new File(song.getAbsolutePath().replace("\\", "/")
                        .replace(pathPrefix, outputDirectory));
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
        List<File> found = new ArrayList<>();
        
        for (File jar : Filesystem.getFilesRecursively(directory, ".*\\.jar")) {
            File extractDir = Filesystem.createTemporaryDirectory();
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
