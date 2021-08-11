/*
 * File:    FilesystemMacroTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of FilesystemMacro.
 *
 * @see FilesystemMacro
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({FilesystemMacro.class})
public class FilesystemMacroTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilesystemMacroTest.class);
    
    
    //Constants
    
    /**
     * The test resources directory for this class.
     */
    private static final File testResources = Project.testResourcesDir(FilesystemMacro.class);
    
    
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
        File testResource = new File(testResources, "deleteEmptyDirectoriesInDirectory.zip");
        File testDir = Filesystem.createTemporaryDirectory("deleteEmptyDirectoriesInDirectory");
        
        //recursion
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(20, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertTrue(FilesystemMacro.deleteEmptyDirectoriesInDirectory(testDir, true));
        Assert.assertEquals(16, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(new File(testDir, "d1").exists());
        Assert.assertFalse(new File(testDir, "d2/d21/d211/d2111").exists());
        Assert.assertFalse(new File(testDir, "d2/d21/d212").exists());
        Assert.assertFalse(new File(testDir, "d2/d22").exists());
        Filesystem.deleteDirectory(testDir);
        
        //no recursion
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(20, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertTrue(FilesystemMacro.deleteEmptyDirectoriesInDirectory(testDir, false));
        Assert.assertEquals(19, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(new File(testDir, "d1").exists());
        Filesystem.deleteDirectory(testDir);
        
        //default recursion
        
        PowerMockito.spy(FilesystemMacro.class);
        PowerMockito.doReturn(true).when(FilesystemMacro.class, "deleteEmptyDirectoriesInDirectory", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean());
        Assert.assertTrue(FilesystemMacro.deleteEmptyDirectoriesInDirectory(Project.TMP_DIR));
        PowerMockito.verifyStatic(FilesystemMacro.class);
        FilesystemMacro.deleteEmptyDirectoriesInDirectory(Project.TMP_DIR, false);
    }
    
    /**
     * JUnit test of deleteListOfFilesFromFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#deleteListOfFilesFromFile(File)
     */
    @Test
    public void testDeleteListOfFilesFromFile() throws Exception {
        File testResource = new File(testResources, "deleteListOfFilesFromFile.zip");
        File testDir = Filesystem.createTemporaryDirectory("deleteListOfFilesFromFile");
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(13, Filesystem.getFilesAndDirsRecursively(testDir).size());
        File list = new File(testDir, "list.txt");
        Assert.assertTrue(FilesystemMacro.prependLinesInFile(list, (testDir.getAbsolutePath().replace("\\", "/") + '/')));
        Filesystem.readLines(list).forEach(e -> Assert.assertTrue(new File(e).exists()));
        Assert.assertTrue(FilesystemMacro.deleteListOfFilesFromFile(list));
        Assert.assertEquals(7, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Filesystem.readLines(list).forEach(e -> Assert.assertFalse(new File(e).exists()));
        Assert.assertEquals(6, Filesystem.readLines(list).size());
        Filesystem.deleteDirectory(testDir);
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
        File testResource = new File(testResources, "deleteDirectoriesInDirectoryThatDoNotContainFileType.zip");
        File testDir = Filesystem.createTemporaryDirectory("deleteDirectoriesInDirectoryThatDoNotContainFileType");
        
        //recursion
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(40, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertTrue(FilesystemMacro.deleteDirectoriesInDirectoryThatDoNotContainFileType(testDir, "docx", true));
        Assert.assertEquals(25, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(new File(testDir, "d1").exists());
        Assert.assertFalse(new File(testDir, "d2/d21/d211/d2112").exists());
        Assert.assertFalse(new File(testDir, "d2/d21/d212").exists());
        Assert.assertFalse(new File(testDir, "d2/d21/d213").exists());
        Assert.assertFalse(new File(testDir, "d2/d22").exists());
        Filesystem.deleteDirectory(testDir);
        
        //no recursion
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(40, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertTrue(FilesystemMacro.deleteDirectoriesInDirectoryThatDoNotContainFileType(testDir, "docx", false));
        Assert.assertEquals(27, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(new File(testDir, "d1").exists());
        Assert.assertFalse(new File(testDir, "d3").exists());
        Filesystem.deleteDirectory(testDir);
        
        //default recursion
        
        PowerMockito.spy(FilesystemMacro.class);
        PowerMockito.doReturn(true).when(FilesystemMacro.class, "deleteDirectoriesInDirectoryThatDoNotContainFileType", ArgumentMatchers.any(File.class), ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        Assert.assertTrue(FilesystemMacro.deleteDirectoriesInDirectoryThatDoNotContainFileType(Project.TMP_DIR, "txt"));
        PowerMockito.verifyStatic(FilesystemMacro.class);
        FilesystemMacro.deleteDirectoriesInDirectoryThatDoNotContainFileType(Project.TMP_DIR, "txt", true);
    }
    
    /**
     * JUnit test of directoryContainsFileType.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#directoryContainsFileType(File, String, boolean)
     * @see FilesystemMacro#directoryContainsFileType(File, String)
     */
    @Test
    public void testDirectoryContainsFileType() throws Exception {
        File testResource = new File(testResources, "directoryContainsFileType.zip");
        File testDir = Filesystem.createTemporaryDirectory("directoryContainsFileType");
        
        //recursion
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(40, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d1"), "docx", true));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2"), "txt", true));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2"), "mp4", true));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2/d21/d211/d2111"), "jpg", true));
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2/d21/d211/d2111"), "png", true));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2/d21/d212"), "jpg", true));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d3/d31"), "docx", true));
        Filesystem.deleteDirectory(testDir);
        
        //no recursion
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(40, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d1"), "docx", false));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2"), "txt", false));
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2"), "mp4", false));
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2/d21/d211/d2111"), "jpg", false));
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2/d21/d211/d2111"), "png", false));
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d2/d21/d212"), "jpg", false));
        Assert.assertFalse(FilesystemMacro.directoryContainsFileType(new File(testDir, "d3/d31"), "docx", false));
        Filesystem.deleteDirectory(testDir);
        
        //default recursion
        
        PowerMockito.spy(FilesystemMacro.class);
        PowerMockito.doReturn(true).when(FilesystemMacro.class, "directoryContainsFileType", ArgumentMatchers.any(File.class), ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        Assert.assertTrue(FilesystemMacro.directoryContainsFileType(Project.TMP_DIR, "txt"));
        PowerMockito.verifyStatic(FilesystemMacro.class);
        FilesystemMacro.directoryContainsFileType(Project.TMP_DIR, "txt", true);
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
        File testResource = new File(testResources, "prependLinesInFile.zip");
        File testDir = Filesystem.createTemporaryDirectory("prependLinesInFile");
        File file = new File(testDir, "f.txt");
        
        //check
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertArrayEquals(new String[] {
                        "test1", "test2", "test3", "tester", "another test",
                        "test file", "file test", "", "test4", "last test"},
                Filesystem.readLines(file).toArray());
        Assert.assertTrue(FilesystemMacro.prependLinesInFile(file, "test", true));
        Assert.assertArrayEquals(new String[] {
                        "test1", "test2", "test3", "tester", "testanother test",
                        "test file", "testfile test", "test", "test4", "testlast test"},
                Filesystem.readLines(file).toArray());
        Filesystem.deleteDirectory(testDir);
        
        //no check
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertArrayEquals(new String[] {
                        "test1", "test2", "test3", "tester", "another test",
                        "test file", "file test", "", "test4", "last test"},
                Filesystem.readLines(file).toArray());
        Assert.assertTrue(FilesystemMacro.prependLinesInFile(file, "test", false));
        Assert.assertArrayEquals(new String[] {
                        "testtest1", "testtest2", "testtest3", "testtester", "testanother test",
                        "testtest file", "testfile test", "test", "testtest4", "testlast test"},
                Filesystem.readLines(file).toArray());
        Filesystem.deleteDirectory(testDir);
        
        //default check
        
        PowerMockito.spy(FilesystemMacro.class);
        PowerMockito.doReturn(true).when(FilesystemMacro.class, "prependLinesInFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        Assert.assertTrue(FilesystemMacro.prependLinesInFile(file, "test"));
        PowerMockito.verifyStatic(FilesystemMacro.class);
        FilesystemMacro.prependLinesInFile(file, "test", true);
    }
    
    /**
     * JUnit test of unprependLinesInFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#unprependLinesInFile(File, String)
     */
    @Test
    public void testUnprependLinesInFile() throws Exception {
        File testResource = new File(testResources, "unprependLinesInFile.zip");
        File testDir = Filesystem.createTemporaryDirectory("unprependLinesInFile");
        File file = new File(testDir, "f.txt");
        
        //check
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertArrayEquals(new String[] {
                        "test1", "test2", "test3", "tester", "another test",
                        "test file", "file test", "", "test4", "last test"},
                Filesystem.readLines(file).toArray());
        Assert.assertTrue(FilesystemMacro.unprependLinesInFile(file, "test"));
        Assert.assertArrayEquals(new String[] {
                        "1", "2", "3", "er", "another test",
                        " file", "file test", "", "4", "last test"},
                Filesystem.readLines(file).toArray());
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of replaceInFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#replaceInFile(File, String, String)
     */
    @Test
    public void testReplaceInFile() throws Exception {
        File testResource = new File(testResources, "replaceInFile.zip");
        File testDir = Filesystem.createTemporaryDirectory("replaceInFile");
        File file = new File(testDir, "f.txt");
        
        //check
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertArrayEquals(new String[] {
                        "test1", "test2", "test3", "tester", "another test",
                        "test file", "file test", "", "test4", "last test", "",
                        "test3 another testing test3 for the tester"},
                Filesystem.readLines(file).toArray());
        Assert.assertTrue(FilesystemMacro.replaceInFile(file, "test", "string"));
        Assert.assertArrayEquals(new String[] {
                        "string1", "string2", "string3", "stringer", "another string",
                        "string file", "file string", "", "string4", "last string", "",
                        "string3 another stringing string3 for the stringer"},
                Filesystem.readLines(file).toArray());
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of regexReplaceInFile.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#regexReplaceInFile(File, String, String)
     */
    @Test
    public void testRegexReplaceInFile() throws Exception {
        File testResource = new File(testResources, "regexReplaceInFile.zip");
        File testDir = Filesystem.createTemporaryDirectory("regexReplaceInFile");
        File file = new File(testDir, "f.txt");
        
        //check
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertArrayEquals(new String[] {
                        "test1", "test2", "test3", "tester", "another test",
                        "test file", "file test", "", "test4", "last test", "",
                        "test3 another testing test3 for the tester"},
                Filesystem.readLines(file).toArray());
        Assert.assertTrue(FilesystemMacro.regexReplaceInFile(file, "test\\d+", "string"));
        Assert.assertArrayEquals(new String[] {
                        "string", "string", "string", "tester", "another test",
                        "test file", "file test", "", "string", "last test", "",
                        "string another testing string for the tester"},
                Filesystem.readLines(file).toArray());
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of copyPlaylistToDirectory.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#copyPlaylistToDirectory(File, File, String)
     */
    @Test
    public void testCopyPlaylistToDirectory() throws Exception {
        File testResource = new File(testResources, "copyPlaylistToDirectory.zip");
        File testDir = Filesystem.createTemporaryDirectory("copyPlaylistToDirectory");
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(277, Filesystem.getFilesAndDirsRecursively(testDir).size());
        File playlist = new File(testDir, "playlist.m3u");
        List<String> originalPlaylist = Filesystem.readLines(playlist);
        Assert.assertTrue(FilesystemMacro.prependLinesInFile(playlist, (testDir.getAbsolutePath().replace("\\", "/") + '/')));
        File outputDir = new File(testDir, "output");
        Assert.assertTrue(Filesystem.isEmpty(outputDir));
        Assert.assertTrue(FilesystemMacro.copyPlaylistToDirectory(playlist, outputDir, (testDir.getAbsolutePath().replace("\\", "/") + "/source")));
        Assert.assertEquals(343, Filesystem.getFilesAndDirsRecursively(testDir).size());
        Assert.assertFalse(Filesystem.isEmpty(outputDir));
        Assert.assertEquals(66, Filesystem.getFilesAndDirsRecursively(outputDir).size());
        Assert.assertEquals(46, originalPlaylist.size());
        File outputPlaylist = new File(outputDir, playlist.getName());
        List<String> newPlaylist = Filesystem.readLines(outputPlaylist);
        Assert.assertTrue(outputPlaylist.exists());
        Assert.assertEquals(44, newPlaylist.size());
        newPlaylist.stream().map(e -> new File(outputDir.getAbsolutePath() + '/' + e))
                .forEach(e -> Assert.assertTrue(e.exists()));
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of findJarsInDirectoryThatContainFolder.
     *
     * @throws Exception When there is an exception.
     * @see FilesystemMacro#findJarsInDirectoryThatContainFolder(File, String)
     */
    @Test
    public void testFindJarsInDirectoryThatContainFolder() throws Exception {
        File testResource = new File(testResources, "findJarsInDirectoryThatContainFolder.zip");
        File testDir = Filesystem.createTemporaryDirectory("findJarsInDirectoryThatContainFolder");
        
        Filesystem.deleteDirectory(testDir);
        Archive.extract(testResource, testDir);
        Assert.assertEquals(16, Filesystem.getFilesAndDirsRecursively(testDir).size());
        List<File> jars = FilesystemMacro.findJarsInDirectoryThatContainFolder(testDir, "testDir");
        Assert.assertEquals(4, jars.size());
        List<String> jarNames = jars.stream().map(File::getName).collect(Collectors.toList());
        Assert.assertTrue(jarNames.contains("jard11.jar"));
        Assert.assertTrue(jarNames.contains("jard211.jar"));
        Assert.assertTrue(jarNames.contains("jar1.jar"));
        Assert.assertTrue(jarNames.contains("jar2.jar"));
        Filesystem.deleteDirectory(testDir);
    }
    
}
