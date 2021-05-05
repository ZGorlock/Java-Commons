/*
 * File:    FilesystemMacroTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of FilesystemMacro.
 *
 * @see FilesystemMacro
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({FilesystemMacro.class})
public class FilesystemMacroTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilesystemMacroTest.class);
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @BeforeClass
    public static void setupClass() throws Exception {
    }
    
    /**
     * The JUnit class cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @AfterClass
    public static void cleanupClass() throws Exception {
    }
    
    /**
     * The JUnit setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Before
    public void setup() throws Exception {
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @After
    public void cleanup() throws Exception {
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of deleteEmptyDirectoriesInDirectory.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#deleteEmptyDirectoriesInDirectory(File, boolean)
     * @see FilesystemMacro#deleteEmptyDirectoriesInDirectory(File)
     */
    @Test
    public void testDeleteEmptyDirectoriesInDirectory() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of deleteListOfFilesFromFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#deleteListOfFilesFromFile(File)
     */
    @Test
    public void testDeleteListOfFilesFromFile() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of deleteDirectoriesInDirectoryThatDoNotContainFileType.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#deleteDirectoriesInDirectoryThatDoNotContainFileType(File, String, boolean)
     * @see FilesystemMacro#deleteDirectoriesInDirectoryThatDoNotContainFileType(File, String)
     */
    @Test
    public void testDeleteDirectoriesInDirectoryThatDoNotContainFileType() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of directoryContainsFileType.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#directoryContainsFileType(File, String, boolean)
     */
    @Test
    public void testDirectoryContainsFileType() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of prependLinesInFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#prependLinesInFile(File, String, boolean)
     * @see FilesystemMacro#prependLinesInFile(File, String)
     */
    @Test
    public void testPrependLinesInFile() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of unprependLinesInFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#unprependLinesInFile(File, String)
     */
    @Test
    public void testUnprependLinesInFile() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyPlaylistToDirectory.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#copyPlaylistToDirectory(File, File, String)
     */
    @Test
    public void testCopyPlaylistToDirectory() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of findJarsInDirectoryThatContainFolder.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#findJarsInDirectoryThatContainFolder(File, String)
     */
    @Test
    public void testFindJarsInDirectoryThatContainFolder() throws Exception {
        //TODO
    }
    
}
