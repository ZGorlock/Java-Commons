/*
 * File:    FilesystemTest.java
 * Package: access
 * Author:  Zachary Gill
 */

package access;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import string.StringUtility;

/**
 * JUnit test of Filesystem.
 *
 * @see Filesystem
 */
@SuppressWarnings("SpellCheckingInspection")
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({Filesystem.class, FileUtils.class})
public class FilesystemTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilesystemTest.class);
    
    
    //Constants
    
    /**
     * The temporary directory to use during testing.
     */
    private static final File tmpDir = new File("tmp");
    
    
    //Static Fields
    
    /**
     * An example file for Filesystem testing.
     */
    private static File testFile = new File(tmpDir, "test.txt");
    
    /**
     * An example file for Filesystem testing.
     */
    private static File test2File = new File(tmpDir, "test2.txt");
    
    /**
     * An example directory for Filesystem testing.
     */
    private static File testDir = new File(tmpDir, "testDir");
    
    /**
     * An example directory for Filesystem testing.
     */
    private static File test2Dir = new File(tmpDir, "test2Dir");
    
    /**
     * An example directory for Filesystem testing.
     */
    private static File testDir2 = new File(testDir, "testDir2");
    
    /**
     * An example directory for Filesystem testing.
     */
    private static File test2Dir2 = new File(testDir, "test2Dir2");
    
    /**
     * An example directory for Filesystem testing.
     */
    private static File testDir3 = new File(testDir2, "testDir3");
    
    /**
     * An example directory for Filesystem testing.
     */
    private static File test2Dir3 = new File(testDir2, "test2Dir3");
    
    /**
     * An example file for Filesystem testing.
     */
    private static File testFile2 = new File(testDir, "test.txt");
    
    /**
     * An example file for Filesystem testing.
     */
    private static File test2File2 = new File(testDir, "test2.txt");
    
    /**
     * An example file for Filesystem testing.
     */
    private static File testFile3 = new File(testDir3, "test.txt");
    
    /**
     * An example file for Filesystem testing.
     */
    private static File test2File3 = new File(testDir3, "test2.txt");
    
    /**
     * An example nested directory for Filesystem testing.
     */
    private static File nestedDir = null;
    
    /**
     * An example internal file for Filesystem testing.
     */
    private static File internalFile = null;
    
    /**
     * An example internal directory for Filesystem testing.
     */
    private static File internalDir = null;
    
    /**
     * An example internal file for Filesystem testing.
     */
    private static File internalFile2 = null;
    
    /**
     * An example nested internal file for Filesystem testing.
     */
    private static File nestedInternalFile = null;
    
    /**
     * An example nested internal directory for Filesystem testing.
     */
    private static File nestedInternalDir = null;
    
    /**
     * An example nested internal file for Filesystem testing.
     */
    private static File nestedInternalFile2 = null;
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @BeforeClass
    public static void setupClass() throws Exception {
        PowerMockito.spy(Filesystem.class);
        PowerMockito.when(Filesystem.class, "logFilesystem").thenReturn(false);
        
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
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
    @After
    public void cleanup() throws Exception {
        if ((nestedInternalFile2 != null) && nestedInternalFile2.exists()) {
            Assert.assertTrue(nestedInternalFile2.delete());
        }
        if ((nestedInternalDir != null) && nestedInternalDir.exists()) {
            Assert.assertTrue(nestedInternalDir.delete());
        }
        if ((nestedInternalFile != null) && nestedInternalFile.exists()) {
            Assert.assertTrue(nestedInternalFile.delete());
        }
        if ((nestedDir != null) && nestedDir.exists()) {
            Assert.assertTrue(nestedDir.delete());
        }
        if ((internalFile2 != null) && internalFile2.exists()) {
            Assert.assertTrue(internalFile2.delete());
        }
        if ((internalDir != null) && internalDir.exists()) {
            Assert.assertTrue(internalDir.delete());
        }
        if ((internalFile != null) && internalFile.exists()) {
            Assert.assertTrue(internalFile.delete());
        }
        if ((testFile != null) && testFile.exists()) {
            Assert.assertTrue(testFile.delete());
        }
        if ((test2File != null) && test2File.exists()) {
            Assert.assertTrue(test2File.delete());
        }
        if ((testFile2 != null) && testFile2.exists()) {
            Assert.assertTrue(testFile2.delete());
        }
        if ((test2File2 != null) && test2File2.exists()) {
            Assert.assertTrue(test2File2.delete());
        }
        if ((testFile3 != null) && testFile3.exists()) {
            Assert.assertTrue(testFile3.delete());
        }
        if ((test2File3 != null) && test2File3.exists()) {
            Assert.assertTrue(test2File3.delete());
        }
        if ((testDir3 != null) && testDir3.exists()) {
            Assert.assertTrue(testDir3.delete());
        }
        if ((test2Dir3 != null) && test2Dir3.exists()) {
            Assert.assertTrue(test2Dir3.delete());
        }
        if ((testDir2 != null) && testDir2.exists()) {
            Assert.assertTrue(testDir2.delete());
        }
        if ((test2Dir2 != null) && test2Dir2.exists()) {
            Assert.assertTrue(test2Dir2.delete());
        }
        if ((testDir != null) && testDir.exists()) {
            Assert.assertTrue(testDir.delete());
        }
        if ((test2Dir != null) && test2Dir.exists()) {
            Assert.assertTrue(test2Dir.delete());
        }
    }
    
    
    //Tests
    
    /**
     * JUnit test of createFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#createFile(File)
     */
    @Test
    public void testCreateFile() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //already exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of createDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#createDirectory(File)
     */
    @Test
    public void testCreateDirectory() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //already exists
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //file
        Assert.assertTrue(Filesystem.createDirectory(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of deleteFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#deleteFile(File)
     */
    @Test
    public void testDeleteFile() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.deleteFile(testFile));
        Assert.assertFalse(testFile.exists());
        
        //already deleted
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(Filesystem.deleteFile(testFile));
        Assert.assertFalse(testFile.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(Filesystem.deleteFile(testFile3));
        Assert.assertFalse(testFile3.exists());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.deleteFile(testDir)); //should call deleteDirectory
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of deleteDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#deleteDirectory(File)
     */
    @Test
    public void testDeleteDirectory() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.deleteDirectory(testDir));
        Assert.assertFalse(testDir.exists());
        
        //already deleted
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(Filesystem.deleteDirectory(testDir));
        Assert.assertFalse(testDir.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(Filesystem.deleteDirectory(testDir));
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.deleteDirectory(testFile)); //should call deleteFile
        Assert.assertFalse(testFile.exists());
    }
    
    /**
     * JUnit test of delete.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#delete(File)
     */
    @Test
    public void testDelete() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.delete(testFile)); //should call deleteFile
        Assert.assertFalse(testFile.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.delete(testDir)); //should call deleteDirectory
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of renameFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#renameFile(File, File)
     */
    @Test
    public void testRenameFile() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.renameFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //already renamed
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.renameFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.renameFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.renameFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.renameFile(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertTrue(Filesystem.renameFile(testFile, testFile3));
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.renameFile(testDir, test2Dir)); //should call renameDirectory
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of renameDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#renameDirectory(File, File)
     */
    @Test
    public void testRenameDirectory() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.renameDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //already renamed
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.renameDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.renameDirectory(test2Dir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.renameFile(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.renameDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testFile3.exists());
        Assert.assertTrue(Filesystem.renameDirectory(test2Dir, testDir3));
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testFile3.exists());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.renameDirectory(test2Dir, testDir3));
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.renameDirectory(testFile, test2File)); //should call renameFile
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
    }
    
    /**
     * JUnit test of rename.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#rename(File, File)
     */
    @Test
    public void testRename() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.rename(testFile, test2File)); //should call renameFile
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.rename(testDir, test2Dir)); //should call renameDirectory
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of copyFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#copyFile(File, File, boolean)
     * @see #testCopyFileStandard()
     * @see #testCopyFileOverwriteOff()
     * @see #testCopyFileOverwriteOn()
     */
    @Test
    public void testCopyFile() throws Exception {
        testCopyFileStandard();
        testCopyFileOverwriteOff();
        testCopyFileOverwriteOn();
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyFile(testDir, test2Dir)); //should call copyDirectory
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * Helper method for JUnit test of copyFile for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyFileStandard() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.copyFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //already copied
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.copyFile(test2File, test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.copyFile(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, testFile3));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, destination exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertFalse(Filesystem.copyFile(testFile, testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is a directory, multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir3));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of copyFile for cases with overwrite off.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyFileOverwriteOff() throws Exception {
        //standard, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist, overwrite off
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.copyFile(testFile, test2File, false));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //already copied, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.copyFile(test2File, test2File, false));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.copyFile(testFile, test2File, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, testFile3, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, destination exists, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertFalse(Filesystem.copyFile(testFile, testDir, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is a directory, multiple levels, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir3, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of copyFile for cases with overwrite on.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyFileOverwriteOn() throws Exception {
        //standard, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist, overwrite on
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.copyFile(testFile, test2File, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //already copied, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.copyFile(test2File, test2File, true));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, test2File, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, testFile3, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, destination exists, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is a directory, multiple levels, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        Assert.assertTrue(Filesystem.copyFile(testFile, testDir3, true));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of copyDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#copyDirectory(File, File, boolean, boolean)
     * @see #testCopyDirectoryStandard()
     * @see #testCopyDirectoryOverwriteOff()
     * @see #testCopyDirectoryOverwriteOn()
     * @see #testCopyDirectoryOverwriteOffInsertOff()
     * @see #testCopyDirectoryOverwriteOffInsertOn()
     * @see #testCopyDirectoryOverwriteOnInsertOff()
     * @see #testCopyDirectoryOverwriteOnInsertOn()
     */
    @Test
    public void testCopyDirectory() throws Exception {
        testCopyDirectoryStandard();
        testCopyDirectoryOverwriteOff();
        testCopyDirectoryOverwriteOn();
        testCopyDirectoryOverwriteOffInsertOff();
        testCopyDirectoryOverwriteOffInsertOn();
        testCopyDirectoryOverwriteOnInsertOff();
        testCopyDirectoryOverwriteOnInsertOn();
        
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(testFile, test2File)); //should call copyFile
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryStandard() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for cases with overwrite off.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryOverwriteOff() throws Exception {
        //standard, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for cases with overwrite on.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryOverwriteOn() throws Exception {
        //standard, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, testFile, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedInternalFile = new File(testFile, testFile.getName());
        nestedInternalDir = new File(testFile, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, testFile, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for cases with overwrite off and insert off.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryOverwriteOffInsertOff() throws Exception {
        //standard, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite off, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, false, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, false, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite off, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, false, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, false, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for cases with overwrite off and insert on.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryOverwriteOffInsertOn() throws Exception {
        //standard, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite off, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, false, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(testDir3, test2Dir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, false, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        
        //standard, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite off, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, testFile, false, true));
        Assert.assertFalse(nestedDir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, false, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.createDirectory(nestedDir));
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedDir.isDirectory());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, false, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for cases with overwrite on and insert off.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryOverwriteOnInsertOff() throws Exception {
        //standard, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite on, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, testFile, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, true, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, true, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite on, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedInternalFile = new File(testFile, testFile.getName());
        nestedInternalDir = new File(testFile, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, testFile, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, true, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, true, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of copyDirectory for cases with overwrite on and insert on.
     *
     * @throws Exception When there is an exception.
     */
    private void testCopyDirectoryOverwriteOnInsertOn() throws Exception {
        //standard, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite on, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, testFile, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, true, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(testDir3, test2Dir.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, true, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        
        //standard, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite on, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, testFile, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already copied, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, test2Dir, true, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.createDirectory(nestedDir));
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedDir.isDirectory());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(testDir, test2Dir, true, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.copyDirectory(test2Dir, testDir3, true, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#copy(File, File, boolean)
     */
    @Test
    public void testCopy() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.copy(testFile, test2File)); //should call copyFile
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.copy(testFile, test2File, false)); //should call copyFile
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.copy(testFile, test2File, true)); //should call copyFile
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.copy(testDir, test2Dir)); //should call copyDirectory
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.copy(testDir, test2Dir, false)); //should call copyDirectory
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.copy(testDir, test2Dir, true)); //should call copyDirectory
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of moveFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#moveFile(File, File, boolean)
     * @see #testMoveFileStandard()
     * @see #testMoveFileOverwriteOff()
     * @see #testMoveFileOverwriteOn()
     */
    @Test
    public void testMoveFile() throws Exception {
        testMoveFileStandard();
        testMoveFileOverwriteOff();
        testMoveFileOverwriteOn();
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveFile(testDir, test2Dir)); //should call moveDirectory
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * Helper method for JUnit test of moveFile for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveFileStandard() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.moveFile(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //already moved
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.moveFile(test2File, test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.moveFile(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, testFile3));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, destination exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertFalse(Filesystem.moveFile(testFile, testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is a directory, multiple levels
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir3));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of moveFile for cases with overwrite off.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveFileOverwriteOff() throws Exception {
        //standard, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File, false));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist, overwrite off
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.moveFile(testFile, test2File, false));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //already moved, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File, false));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.moveFile(test2File, test2File, false));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.moveFile(testFile, test2File, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, testFile3, false));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir, false));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, destination exists, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertFalse(Filesystem.moveFile(testFile, testDir, false));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is a directory, multiple levels, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir3, false));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of moveFile for cases with overwrite on.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveFileOverwriteOn() throws Exception {
        //standard, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //source doesn't exist, overwrite on
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.moveFile(testFile, test2File, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //already moved, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File, true));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.moveFile(test2File, test2File, true));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //destination already exists, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, test2File, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //multiple levels, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, testFile3, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is directory, destination exists, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //destination is a directory, multiple levels, overwrite on
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        Assert.assertTrue(Filesystem.moveFile(testFile, testDir3, true));
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of moveDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#moveDirectory(File, File, boolean, boolean)
     * @see #testMoveDirectoryStandard()
     * @see #testMoveDirectoryOverwriteOff()
     * @see #testMoveDirectoryOverwriteOn()
     * @see #testMoveDirectoryOverwriteOffInsertOff()
     * @see #testMoveDirectoryOverwriteOffInsertOn()
     * @see #testMoveDirectoryOverwriteOnInsertOff()
     * @see #testMoveDirectoryOverwriteOnInsertOn()
     */
    @Test
    public void testMoveDirectory() throws Exception {
        testMoveDirectoryStandard();
        testMoveDirectoryOverwriteOff();
        testMoveDirectoryOverwriteOn();
        testMoveDirectoryOverwriteOffInsertOff();
        testMoveDirectoryOverwriteOffInsertOn();
        testMoveDirectoryOverwriteOnInsertOff();
        testMoveDirectoryOverwriteOnInsertOn();
        
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(testFile, test2File)); //should call moveFile
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryStandard() throws Exception {
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for cases with overwrite off.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryOverwriteOff() throws Exception {
        //standard, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, false));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, false));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for cases with overwrite on.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryOverwriteOn() throws Exception {
        //standard, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, testFile, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //already moved, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, true));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedInternalFile = new File(testFile, testFile.getName());
        nestedInternalDir = new File(testFile, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, testFile, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, true));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for cases with overwrite off and insert off.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryOverwriteOffInsertOff() throws Exception {
        //standard, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite off, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, false, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, false, false));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite off, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, false, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, false));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite off, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, false, false));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for cases with overwrite off and insert on.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryOverwriteOffInsertOn() throws Exception {
        //standard, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite off, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, false, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(testDir3, test2Dir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, false, true));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        
        //standard, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite off, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, testFile, false, true));
        Assert.assertFalse(nestedDir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, false, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.createDirectory(nestedDir));
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedDir.isDirectory());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, false, true));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite off, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, false, true));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for cases with overwrite on and insert off.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryOverwriteOnInsertOff() throws Exception {
        //standard, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite on, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, testFile, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //already moved, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, true, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, true, false));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        
        //standard, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite on, insert off
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedInternalFile = new File(testFile, testFile.getName());
        nestedInternalDir = new File(testFile, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, testFile, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, true, false));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedInternalFile = new File(test2Dir, testFile.getName());
        nestedInternalDir = new File(test2Dir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, false));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite on, insert off
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedInternalFile = new File(testDir3, testFile.getName());
        nestedInternalDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, true, false));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * Helper method for JUnit test of moveDirectory for cases with overwrite on and insert on.
     *
     * @throws Exception When there is an exception.
     */
    private void testMoveDirectoryOverwriteOnInsertOn() throws Exception {
        //standard, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, overwrite on, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, testFile, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, true, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(testDir3, test2Dir.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, true, true));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        
        //standard, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //source doesn't exist, nested files, overwrite on, insert on
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination is file, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        nestedDir = new File(testFile, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, testFile, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testFile.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //already moved, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(test2Dir, testDir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, test2Dir, true, true));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //destination already exists, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        nestedDir = new File(test2Dir, testDir.getName());
        Assert.assertTrue(Filesystem.createDirectory(nestedDir));
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedDir.isDirectory());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(testDir, test2Dir, true, true));
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //multiple levels, nested files, overwrite on, insert on
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        internalDir = new File(test2Dir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        nestedDir = new File(testDir3, test2Dir.getName());
        nestedInternalFile = new File(nestedDir, testFile.getName());
        nestedInternalDir = new File(nestedDir, test2Dir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, testFile.getName());
        Assert.assertTrue(Filesystem.moveDirectory(test2Dir, testDir3, true, true));
        Assert.assertFalse(test2Dir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(nestedDir.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(nestedDir.isDirectory());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(nestedDir.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(nestedDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.delete());
    }
    
    /**
     * JUnit test of move.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#move(File, File, boolean)
     */
    @Test
    public void testMove() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.move(testFile, test2File)); //should call moveFile
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //file, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.move(testFile, test2File, false)); //should call moveFile
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file, overwrite off
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.move(testFile, test2File, true)); //should call moveFile
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(test2File.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.move(testDir, test2Dir)); //should call moveDirectory
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, overwrite off
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.move(testDir, test2Dir, false)); //should call moveDirectory
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, overwrite on
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(Filesystem.move(testDir, test2Dir, true)); //should call moveDirectory
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of clearDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#clearDirectory(File)
     */
    @Test
    public void testClearDirectory() throws Exception {
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertFalse(testDir.delete());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.clearDirectory(testDir));
        Assert.assertFalse(internalFile.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.clearDirectory(testDir));
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, testFile.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.clearDirectory(testDir));
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of listFiles.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#listFiles(File, FileFilter)
     */
    @Test
    public void testListFiles() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.listFiles(testDir, x -> true);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.listFiles(testDir, x -> true);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, x -> true);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, x -> true);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir3, x -> true);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.listFiles(testDir, File::isFile);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.listFiles(testDir, File::isFile);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, File::isFile);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, File::isFile);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir3, File::isFile);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.listFiles(testDir, File::isDirectory);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.listFiles(testDir, File::isDirectory);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, File::isDirectory);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, File::isDirectory);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir3, File::isDirectory);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.listFiles(testDir, x -> x.getName().matches("test[^\\d]+"));
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.listFiles(testDir, x -> x.getName().matches("test[^\\d]+"));
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, x -> x.getName().matches("test[^\\d]+"));
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir, x -> x.getName().matches("test[^\\d]+"));
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.listFiles(testDir3, x -> x.getName().matches("test[^\\d]+"));
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of getFilesRecursively.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getFilesRecursively(File, String, String)
     * @see #testGetFilesRecursivelyStandard()
     * @see #testGetFilesRecursivelyFileFilter()
     * @see #testGetFilesRecursivelyDirectoryFilter()
     * @see #testGetFilesRecursivelyFileAndDirectoryFilter()
     */
    @Test
    public void testGetFilesRecursively() throws Exception {
        testGetFilesRecursivelyStandard();
        testGetFilesRecursivelyFileFilter();
        testGetFilesRecursivelyDirectoryFilter();
        testGetFilesRecursivelyFileAndDirectoryFilter();
    }
    
    /**
     * Helper method for JUnit test of getFilesRecursively for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesRecursivelyStandard() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesRecursively(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesRecursively(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir3);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getFilesRecursively for cases with a file filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesRecursivelyFileFilter() throws Exception {
        List<File> list;
        
        //base line, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesRecursively(testDir, "^.*$");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir3, "^.*$");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir3, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getFilesRecursively for cases with a directory filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesRecursivelyDirectoryFilter() throws Exception {
        List<File> list;
        
        //base line, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir3, "^.*$", "^.*$");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "^.*$", "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir3, "^.*$", "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getFilesRecursively for cases with a file filter and a directory filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesRecursivelyFileAndDirectoryFilter() throws Exception {
        List<File> list;
        
        //base line, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesRecursively(testDir3, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of getDirsRecursively.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getDirsRecursively(File, String)
     * @see #testGetDirsRecursivelyStandard()
     * @see #testGetDirsRecursivelyDirectoryFilter()
     */
    @Test
    public void testGetDirsRecursively() throws Exception {
        testGetDirsRecursivelyStandard();
        testGetDirsRecursivelyDirectoryFilter();
    }
    
    /**
     * Helper method for JUnit test of getDirsRecursively for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetDirsRecursivelyStandard() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getDirsRecursively(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getDirsRecursively(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir);
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir3);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getDirsRecursively for cases with a directory filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetDirsRecursivelyDirectoryFilter() throws Exception {
        List<File> list;
        
        //base line, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir3, "^.*$");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getDirsRecursively(testDir, "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getDirsRecursively(testDir, "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir, "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir, "test(Dir)?\\d+.*");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirsRecursively(testDir3, "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of getFilesAndDirsRecursively.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getFilesAndDirsRecursively(File, String, String)
     * @see #testGetFilesAndDirsRecursivelyStandard()
     * @see #testGetFilesAndDirsRecursivelyFileFilter()
     * @see #testGetFilesAndDirsRecursivelyDirectoryFilter()
     * @see #testGetFilesAndDirsRecursivelyFileAndDirectoryFilter()
     */
    @Test
    public void testGetFilesAndDirsRecursively() throws Exception {
        testGetFilesAndDirsRecursivelyStandard();
        testGetFilesAndDirsRecursivelyFileFilter();
        testGetFilesAndDirsRecursivelyDirectoryFilter();
        testGetFilesAndDirsRecursivelyFileAndDirectoryFilter();
    }
    
    /**
     * Helper method for JUnit test of getFilesAndDirsRecursively for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesAndDirsRecursivelyStandard() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirsRecursively(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir);
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir);
        Assert.assertEquals(5, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir3);
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getFilesAndDirsRecursively for cases with a file filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesAndDirsRecursivelyFileFilter() throws Exception {
        List<File> list;
        
        //base line, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$");
        Assert.assertEquals(5, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir3, "^.*$");
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test[^\\d]+");
        Assert.assertEquals(4, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir3, "test[^\\d]+");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getFilesAndDirsRecursively for cases with a directory filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesAndDirsRecursivelyDirectoryFilter() throws Exception {
        List<File> list;
        
        //base line, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "^.*$");
        Assert.assertEquals(5, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default file filter, default directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir3, "^.*$", "^.*$");
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(list.contains(internalFile2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "testDir\\d+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "testDir\\d+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "^.*$", "testDir\\d+");
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, default file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir3, "^.*$", "testDir\\d+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * Helper method for JUnit test of getFilesAndDirsRecursively for cases with a file filter and a directory filter.
     *
     * @throws Exception When there is an exception.
     */
    private void testGetFilesAndDirsRecursivelyFileAndDirectoryFilter() throws Exception {
        List<File> list;
        
        //base line, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(4, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(list.contains(testDir3));
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file filter, directory filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirsRecursively(testDir3, "test\\d+.*", "test(Dir)?\\d+.*");
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of getFiles.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getFiles(File, String)
     */
    @Test
    public void testGetFiles() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFiles(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFiles(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFiles(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFiles(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFiles(testDir3);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFiles(testDir, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFiles(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFiles(testDir, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFiles(testDir, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFiles(testDir3, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of getDirs.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getDirs(File, String)
     */
    @Test
    public void testGetDirs() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getDirs(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getDirs(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirs(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirs(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirs(testDir3);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getDirs(testDir, "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getDirs(testDir, "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirs(testDir, "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, test2Dir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirs(testDir, "testDir\\d+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getDirs(testDir3, "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of getFilesAndDirs.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getFilesAndDirs(File, String, String)
     */
    @Test
    public void testGetFilesAndDirs() throws Exception {
        List<File> list;
        
        //base line
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirs(testDir);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirs(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir3);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, file name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir3, "test[^\\d]+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //base line, file name filter, directory name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+", "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //standard, file name filter, directory name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+", "testDir\\d+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(internalFile));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //multiple files, file name filter, directory name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+", "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, file name filter, directory name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir, "test[^\\d]+", "testDir\\d+");
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(testDir2));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //nested files, internal search, file name filter, directory name filter
        Assert.assertTrue(Filesystem.createDirectory(testDir3));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir3.isDirectory());
        internalFile = new File(testDir3, test2File.getName());
        internalDir = new File(testDir3, testDir.getName());
        internalFile2 = new File(internalDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        list = Filesystem.getFilesAndDirs(testDir3, "test[^\\d]+", "testDir\\d+");
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of contentEquals.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#contentEquals(File, File)
     */
    @Test
    public void testContentEquals() throws Exception {
        //empty
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.contentEquals(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //equals
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a sample text"));
        Assert.assertTrue(Filesystem.contentEquals(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //one empty
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a sample text"));
        Assert.assertFalse(Filesystem.contentEquals(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //not equals
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a different sample text"));
        Assert.assertFalse(Filesystem.contentEquals(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file and directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertFalse(Filesystem.contentEquals(testFile, testDir));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //directories
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.contentEquals(testDir, test2Dir));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of sizeOfFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#sizeOfFile(File)
     */
    @Test
    public void testSizeOfFile() throws Exception {
        //empty
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertEquals(0, Filesystem.sizeOfFile(testFile));
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //size
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertEquals(13, Filesystem.sizeOfFile(testFile));
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //doesn't exist
        Assert.assertFalse(testFile.exists());
        Assert.assertEquals(0, Filesystem.sizeOfFile(testFile));
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertEquals(13, Filesystem.sizeOfFile(testDir)); //should call sizeOfDirectory
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of sizeOfDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#sizeOfDirectory(File)
     */
    @Test
    public void testSizeOfDirectory() throws Exception {
        //empty
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertEquals(0, Filesystem.sizeOfDirectory(testDir));
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //size
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertEquals(13, Filesystem.sizeOfDirectory(testDir));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //size, multiple
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, "a different sample text"));
        Assert.assertEquals(36, Filesystem.sizeOfDirectory(testDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //doesn't exist
        Assert.assertFalse(testDir.exists());
        Assert.assertEquals(0, Filesystem.sizeOfDirectory(testDir));
        
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertEquals(13, Filesystem.sizeOfDirectory(testFile)); //should call sizeOfFile
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
    }
    
    /**
     * JUnit test of sizeOf.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#sizeOf(File)
     */
    @Test
    public void testSizeOf() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertEquals(13, Filesystem.sizeOf(testFile)); //should call sizeOfFile
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertEquals(13, Filesystem.sizeOfFile(testDir)); //should call sizeOfDirectory
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of fileIsEmpty.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#fileIsEmpty(File)
     */
    @Test
    public void testFileIsEmpty() throws Exception {
        //empty
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.fileIsEmpty(testFile));
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //not empty
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertFalse(Filesystem.fileIsEmpty(testFile));
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //doesn't exist
        Assert.assertFalse(testFile.exists());
        Assert.assertTrue(Filesystem.fileIsEmpty(testFile));
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertFalse(Filesystem.fileIsEmpty(testDir)); //should call directoryIsEmpty
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of directoryIsEmpty.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#directoryIsEmpty(File)
     */
    @Test
    public void testDirectoryIsEmpty() throws Exception {
        //empty
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.directoryIsEmpty(testDir));
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
        
        //not empty
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertFalse(Filesystem.directoryIsEmpty(testDir));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //not empty, with text
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertFalse(Filesystem.directoryIsEmpty(testDir));
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //size, multiple
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, testDir.getName());
        internalFile2 = new File(internalDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, "a different sample text"));
        Assert.assertFalse(Filesystem.directoryIsEmpty(testDir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //doesn't exist
        Assert.assertFalse(testDir.exists());
        Assert.assertTrue(Filesystem.directoryIsEmpty(testDir));
        
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertFalse(Filesystem.directoryIsEmpty(testFile)); //should call fileIsEmpty
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
    }
    
    /**
     * JUnit test of isEmpty.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#isEmpty(File)
     */
    @Test
    public void testIsEmpty() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertFalse(Filesystem.isEmpty(testFile)); //should call fileIsEmpty
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertFalse(Filesystem.isEmpty(testDir)); //should call directoryIsEmpty
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of sizeCompare.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#sizeCompare(File, File)
     */
    @Test
    public void testSizeCompare() throws Exception {
        //empty, file, file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertEquals(0, Filesystem.sizeCompare(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //equal, file, file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a sample text"));
        Assert.assertEquals(0, Filesystem.sizeCompare(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //not equal, file, file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a different sample text"));
        Assert.assertEquals(-1, Filesystem.sizeCompare(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //not equal, file, file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a sample text"));
        Assert.assertEquals(1, Filesystem.sizeCompare(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //empty, directory, directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertEquals(0, Filesystem.sizeCompare(testDir, test2Dir));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //equal, directory, directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, test2File.getName());
        nestedInternalFile = new File(test2Dir, test2File.getName());
        nestedInternalDir = new File(test2Dir, testDir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile2, "a sample text"));
        Assert.assertEquals(0, Filesystem.sizeCompare(testDir, test2Dir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //not equal, directory, directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, test2File.getName());
        nestedInternalFile = new File(test2Dir, test2File.getName());
        nestedInternalDir = new File(test2Dir, testDir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile2, "a sample text"));
        Assert.assertEquals(1, Filesystem.sizeCompare(testDir, test2Dir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //not equal, directory, directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        internalDir = new File(testDir, test2Dir.getName());
        internalFile2 = new File(internalDir, test2File.getName());
        nestedInternalFile = new File(test2Dir, test2File.getName());
        nestedInternalDir = new File(test2Dir, testDir.getName());
        nestedInternalFile2 = new File(nestedInternalDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalDir.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(nestedInternalDir.exists());
        Assert.assertTrue(nestedInternalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalDir.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(nestedInternalDir.isDirectory());
        Assert.assertTrue(nestedInternalFile2.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile2, "a different sample text"));
        Assert.assertEquals(-1, Filesystem.sizeCompare(testDir, test2Dir));
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalDir.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(nestedInternalFile2.delete());
        Assert.assertTrue(nestedInternalDir.delete());
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalDir.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(nestedInternalFile2.exists());
        Assert.assertFalse(nestedInternalDir.exists());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //empty, file, directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertEquals(0, Filesystem.sizeCompare(testFile, testDir));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //equal, file, directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertEquals(0, Filesystem.sizeCompare(testFile, testDir));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //not equal, file, directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a different sample text"));
        Assert.assertEquals(-1, Filesystem.sizeCompare(testFile, testDir));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //not equal, file, directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(testDir.isDirectory());
        internalFile = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a different sample text"));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        Assert.assertEquals(1, Filesystem.sizeCompare(testFile, testDir));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of dateCompare.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#dateCompare(File, File)
     */
    @Test
    public void testDateCompare() throws Exception {
        int delay = 50;
        
        //file, file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Thread.sleep(delay);
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertEquals(-1, Filesystem.dateCompare(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file, file
        Assert.assertTrue(Filesystem.createFile(test2File));
        Thread.sleep(delay);
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertEquals(1, Filesystem.dateCompare(testFile, test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //directory, directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Thread.sleep(delay);
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertEquals(-1, Filesystem.dateCompare(testDir, test2Dir));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, directory
        Assert.assertTrue(Filesystem.createDirectory(test2Dir));
        Thread.sleep(delay);
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertEquals(1, Filesystem.dateCompare(testDir, test2Dir));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Thread.sleep(delay);
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertEquals(-1, Filesystem.dateCompare(testDir, testFile));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
        
        //directory, directory
        Assert.assertTrue(Filesystem.createFile(testFile));
        Thread.sleep(delay);
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile.isFile());
        Assert.assertEquals(1, Filesystem.dateCompare(testDir, testFile));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(testFile.exists());
    }
    
    /**
     * JUnit test of openInputStream.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#openInputStream(File)
     */
    @Test
    public void testOpenInputStream() throws Exception {
        FileInputStream fis;
        int read;
        byte[] data;
        String dataString;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text\nanother sample text"));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(33, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(-1, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNull(fis);
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            fis = Filesystem.openInputStream(testDir);
            Assert.assertNull(fis);
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of openOutputStream.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#openOutputStream(File, boolean)
     */
    @Test
    public void testOpenOutputStream() throws Exception {
        FileOutputStream fos;
        String dataString;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text\nanother sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample text\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text".getBytes());
            fos.close();
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("another sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("another sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.close();
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            fos = Filesystem.openOutputStream(testDir);
            Assert.assertNull(fos);
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //standard, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile, true);
            Assert.assertNotNull(fos);
            fos.write("a sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile, true);
            Assert.assertNotNull(fos);
            fos.write("a sample text\nanother sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample text\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile, true);
            Assert.assertNotNull(fos);
            fos.write("a sample text".getBytes());
            fos.close();
            fos = Filesystem.openOutputStream(testFile, true);
            Assert.assertNotNull(fos);
            fos.write("another sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample textanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile, true);
            Assert.assertNotNull(fos);
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist, append
        try {
            Assert.assertFalse(testFile.exists());
            fos = Filesystem.openOutputStream(testFile, true);
            Assert.assertNotNull(fos);
            fos.close();
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory, append
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            fos = Filesystem.openOutputStream(testDir, true);
            Assert.assertNull(fos);
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of readFileToString.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#readFileToString(File)
     */
    @Test
    public void testReadFileToString() throws Exception {
        FileOutputStream fos;
        String dataString;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text\nanother sample text".getBytes());
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("a sample text\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.close();
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            dataString = Filesystem.readFileToString(testFile);
            Assert.assertEquals("", dataString);
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            dataString = Filesystem.readFileToString(testDir);
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of readFileToByteArray.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#readFileToByteArray(File)
     */
    @Test
    public void testReadFileToByteArray() throws Exception {
        FileOutputStream fos;
        byte[] dataArray;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            byte[] a = "a sample text".getBytes();
            fos.write(a);
            fos.close();
            dataArray = Filesystem.readFileToByteArray(testFile);
            Assert.assertArrayEquals("a sample text".getBytes(), dataArray);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text\nanother sample text".getBytes());
            fos.close();
            dataArray = Filesystem.readFileToByteArray(testFile);
            Assert.assertArrayEquals("a sample text\nanother sample text".getBytes(), dataArray);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.close();
            dataArray = Filesystem.readFileToByteArray(testFile);
            Assert.assertArrayEquals("".getBytes(), dataArray);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            dataArray = Filesystem.readFileToByteArray(testFile);
            Assert.assertArrayEquals("".getBytes(), dataArray);
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            dataArray = Filesystem.readFileToByteArray(testDir);
            Assert.assertArrayEquals("".getBytes(), dataArray);
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of readLines.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#readLines(File)
     */
    @Test
    public void testReadLines() throws Exception {
        FileOutputStream fos;
        List<String> dataStrings;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text".getBytes());
            fos.close();
            dataStrings = Filesystem.readLines(testFile);
            Assert.assertArrayEquals(new String[] {"a sample text"}, dataStrings.toArray());
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.write("a sample text\nanother sample text\n\n\none more sample text".getBytes());
            fos.close();
            dataStrings = Filesystem.readLines(testFile);
            Assert.assertArrayEquals(new String[] {"a sample text", "another sample text", "", "", "one more sample text"}, dataStrings.toArray());
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            fos = Filesystem.openOutputStream(testFile);
            Assert.assertNotNull(fos);
            fos.close();
            dataStrings = Filesystem.readLines(testFile);
            Assert.assertArrayEquals(new String[] {}, dataStrings.toArray());
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            dataStrings = Filesystem.readLines(testFile);
            Assert.assertArrayEquals(new String[] {}, dataStrings.toArray());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            dataStrings = Filesystem.readLines(testDir);
            Assert.assertArrayEquals(new String[] {}, dataStrings.toArray());
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of writeStringToFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#writeStringToFile(File, String, boolean)
     */
    @Test
    public void testWriteStringToFile() throws Exception {
        FileInputStream fis;
        int read;
        byte[] data;
        String dataString;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text\nanother sample text"));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(33, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "another sample text"));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(19, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("another sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, ""));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(-1, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            Assert.assertFalse(Filesystem.writeStringToFile(testDir, "a sample text"));
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //standard, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text", true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text\nanother sample text", true));
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "\n\n\none more sample text", true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(56, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\nanother sample text\n\n\none more sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text", true));
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "another sample text", true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(32, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample textanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "", true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(-1, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist, append
        try {
            Assert.assertFalse(testFile.exists());
            Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text", true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory, append
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            Assert.assertFalse(Filesystem.writeStringToFile(testDir, "a sample text", true));
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of writeByteArrayToFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#writeByteArrayToFile(File, byte[], boolean)
     */
    @Test
    public void testWriteByteArrayToFile() throws Exception {
        FileInputStream fis;
        int read;
        byte[] data;
        String dataString;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text".getBytes()));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text\nanother sample text".getBytes()));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(33, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text".getBytes()));
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "another sample text".getBytes()));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(19, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("another sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "".getBytes()));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(-1, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text".getBytes()));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            Assert.assertFalse(Filesystem.writeByteArrayToFile(testDir, "a sample text".getBytes()));
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //standard, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text".getBytes(), true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text\nanother sample text".getBytes(), true));
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "\n\n\none more sample text".getBytes(), true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(56, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\nanother sample text\n\n\none more sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text".getBytes(), true));
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "another sample text".getBytes(), true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(32, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample textanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "".getBytes(), true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(-1, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist, append
        try {
            Assert.assertFalse(testFile.exists());
            Assert.assertTrue(Filesystem.writeByteArrayToFile(testFile, "a sample text".getBytes(), true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(13, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory, append
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            Assert.assertFalse(Filesystem.writeByteArrayToFile(testDir, "a sample text".getBytes(), true));
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of writeLines.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#writeByteArrayToFile(File, byte[], boolean)
     */
    @Test
    public void testWriteLines() throws Exception {
        FileInputStream fis;
        int read;
        byte[] data;
        List<String> lines;
        String dataString;
        
        //standard
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(15, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("a sample text");
            lines.add("another sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(36, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\r\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines));
            lines = new ArrayList<>();
            lines.add("another sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(21, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("another sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(2, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist
        try {
            Assert.assertFalse(testFile.exists());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(15, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertFalse(Filesystem.writeLines(testDir, lines));
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //standard, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines, true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(15, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //multi-line, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("a sample text");
            lines.add("another sample text");
            lines.add("");
            lines.add("");
            lines.add("one more sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines, true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(62, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\r\nanother sample text\r\n\r\n\r\none more sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //overwrite, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines, true));
            lines = new ArrayList<>();
            lines.add("another sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines, true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(36, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text\r\nanother sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //empty, append
        try {
            Assert.assertTrue(Filesystem.createFile(testFile));
            Assert.assertTrue(testFile.exists());
            Assert.assertTrue(testFile.isFile());
            lines = new ArrayList<>();
            lines.add("");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines, true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(2, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //does not exist, append
        try {
            Assert.assertFalse(testFile.exists());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertTrue(Filesystem.writeLines(testFile, lines, true));
            fis = Filesystem.openInputStream(testFile);
            Assert.assertNotNull(fis);
            data = new byte[1024];
            read = fis.read(data);
            fis.close();
            Assert.assertEquals(15, read);
            dataString = StringUtility.trim(new String(data));
            Assert.assertEquals("a sample text", dataString);
            Assert.assertTrue(testFile.delete());
            Assert.assertFalse(testFile.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
        
        //directory, append
        try {
            Assert.assertTrue(Filesystem.createDirectory(testDir));
            Assert.assertTrue(testDir.exists());
            Assert.assertTrue(testDir.isDirectory());
            lines = new ArrayList<>();
            lines.add("a sample text");
            Assert.assertFalse(Filesystem.writeLines(testDir, lines, true));
            Assert.assertTrue(testDir.delete());
            Assert.assertFalse(testDir.exists());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getTempDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getTempDirectory()
     */
    @Test
    public void testGetTempDirectory() throws Exception {
        File tmpDirectory = Filesystem.getTempDirectory();
        Assert.assertNotNull(tmpDirectory);
        Assert.assertTrue(tmpDirectory.exists());
    }
    
    /**
     * JUnit test of getUserDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getUserDirectory()
     */
    @Test
    public void testGetUserDirectory() throws Exception {
        File tmpDirectory = Filesystem.getUserDirectory();
        Assert.assertNotNull(tmpDirectory);
        Assert.assertTrue(tmpDirectory.exists());
    }
    
    /**
     * JUnit test of createSymbolicLink.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#createSymbolicLink(File, File)
     */
    @Test
    public void testCreateSymbolicLink() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createSymbolicLink(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createSymbolicLink(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //target does not exist
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(Filesystem.createSymbolicLink(testFile, test2File));
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //link already exists
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(Filesystem.createFile(test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.createSymbolicLink(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file, modify target
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createSymbolicLink(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        Assert.assertEquals("a sample text", Filesystem.readFileToString(test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //file, modify link
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createSymbolicLink(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(test2File, "a sample text"));
        Assert.assertEquals("a sample text", Filesystem.readFileToString(testFile));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //directory, modify target
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createSymbolicLink(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        internalFile2 = new File(test2Dir, testFile.getName());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
        
        //directory, modify link
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createSymbolicLink(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        internalFile = new File(test2Dir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile.isFile());
        internalFile2 = new File(testDir, testFile.getName());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(internalFile.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of isSymbolicLink.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#isSymbolicLink(File)
     */
    @Test
    public void testIsSymbolicLink() throws Exception {
        //file
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.createSymbolicLink(testFile, test2File));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(test2File.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(test2File.isFile());
        Assert.assertFalse(Filesystem.isSymbolicLink(testFile));
        Assert.assertTrue(Filesystem.isSymbolicLink(test2File));
        Assert.assertTrue(testFile.delete());
        Assert.assertTrue(test2File.delete());
        Assert.assertFalse(testFile.exists());
        Assert.assertFalse(test2File.exists());
        
        //directory
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(Filesystem.createSymbolicLink(testDir, test2Dir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(test2Dir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(test2Dir.isDirectory());
        Assert.assertFalse(Filesystem.isSymbolicLink(testDir));
        Assert.assertTrue(Filesystem.isSymbolicLink(test2Dir));
        Assert.assertTrue(testDir.delete());
        Assert.assertTrue(test2Dir.delete());
        Assert.assertFalse(testDir.exists());
        Assert.assertFalse(test2Dir.exists());
    }
    
    /**
     * JUnit test of checksum.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#checksum(File)
     */
    @Test
    public void testChecksum() throws Exception {
        long checksum;
        long checksum2;
        
        //standard
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "a sample text"));
        checksum = Filesystem.checksum(testFile);
        Assert.assertNotEquals(0, checksum);
        Assert.assertTrue(Filesystem.writeStringToFile(testFile, "another sample text"));
        checksum2 = Filesystem.checksum(testFile);
        Assert.assertNotEquals(0, checksum2);
        Assert.assertNotEquals(checksum2, checksum);
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //empty
        Assert.assertTrue(Filesystem.createFile(testFile));
        Assert.assertTrue(testFile.exists());
        Assert.assertTrue(testFile.isFile());
        Assert.assertEquals(0, Filesystem.checksum(testFile));
        Assert.assertTrue(testFile.delete());
        Assert.assertFalse(testFile.exists());
        
        //does not exist
        Assert.assertFalse(testFile.exists());
        Assert.assertEquals(0, Filesystem.checksum(testFile));
        Assert.assertFalse(testFile.exists());
        
        //directory
        internalFile = new File(testDir, testFile.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        checksum = Filesystem.checksum(testDir);
        Assert.assertNotEquals(0, checksum);
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //directory, multiple files
        internalFile = new File(testDir, testFile.getName());
        internalFile2 = new File(testDir, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, "a sample text"));
        checksum = Filesystem.checksum(testDir);
        Assert.assertNotEquals(0, checksum);
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, "another sample text"));
        checksum2 = Filesystem.checksum(testDir);
        Assert.assertNotEquals(0, checksum2);
        Assert.assertNotEquals(checksum2, checksum);
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testDir.exists());
        
        //directory, empty
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertEquals(0, Filesystem.checksum(testDir));
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of checksumDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#checksumDirectory(File)
     */
    @Test
    public void testChecksumDirectory() throws Exception {
        String checksumListing;
        
        //standard
        
        internalFile = new File(testDir, test2File.getName());
        internalFile2 = new File(testDir2, testFile.getName());
        nestedInternalFile = new File(testDir, "sync");
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(Filesystem.createFile(testFile2));
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile2.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile2.isFile());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        
        Assert.assertTrue(Filesystem.writeStringToFile(testFile2, testFile2.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, internalFile.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, internalFile2.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(testFile3, testFile3.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile, nestedInternalFile.getName()));
        
        checksumListing = Filesystem.checksumDirectory(testDir);
        Assert.assertNotEquals("", checksumListing);
        Assert.assertTrue(checksumListing.matches("^\\{\"checksums\":\\[.+]}$"));
        
        Pattern listingPattern = Pattern.compile("\\{\"file\":\"(?<file>.*?)\",\"checksum\":(?<checksum>\\d*?)}");
        Matcher listingMatcher = listingPattern.matcher(checksumListing);
        Map<String, Long> listings = new HashMap<>();
        while (listingMatcher.find()) {
            String file = listingMatcher.group("file").replace("\\/", "/");
            Long checksum = Long.parseLong(listingMatcher.group("checksum"));
            listings.put(file, checksum);
        }
        
        int prefixLength = (tmpDir.getName() + '/' + testDir.getName() + '/').length();
        Assert.assertTrue(listings.containsKey(StringUtility.lShear(testFile2.getPath().replace("\\", "/"), prefixLength)));
        Assert.assertNotEquals(0L, listings.get(StringUtility.lShear(testFile2.getPath().replace("\\", "/"), prefixLength)).longValue());
        Assert.assertTrue(listings.containsKey(StringUtility.lShear(internalFile.getPath().replace("\\", "/"), prefixLength)));
        Assert.assertNotEquals(0L, listings.get(StringUtility.lShear(internalFile.getPath().replace("\\", "/"), prefixLength)).longValue());
        Assert.assertTrue(listings.containsKey(StringUtility.lShear(internalFile2.getPath().replace("\\", "/"), prefixLength)));
        Assert.assertNotEquals(0L, listings.get(StringUtility.lShear(internalFile2.getPath().replace("\\", "/"), prefixLength)).longValue());
        Assert.assertTrue(listings.containsKey(StringUtility.lShear(testFile3.getPath().replace("\\", "/"), prefixLength)));
        Assert.assertNotEquals(0L, listings.get(StringUtility.lShear(testFile3.getPath().replace("\\", "/"), prefixLength)).longValue());
        Assert.assertFalse(listings.containsKey(StringUtility.lShear(nestedInternalFile.getPath().replace("\\", "/"), prefixLength)));
        
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testFile2.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testFile2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //empty
        
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        checksumListing = Filesystem.checksumDirectory(testDir);
        Assert.assertEquals("{\"checksums\":[]}", checksumListing);
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of compareChecksumDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#compareChecksumDirectory(File, String)
     */
    @Test
    public void testCompareChecksumDirectory() throws Exception {
        String checksumListing;
        String checksumComparison;
        
        //standard
        
        internalFile = new File(testDir, test2File.getName());
        internalFile2 = new File(testDir2, testFile.getName());
        nestedInternalFile = new File(testDir, "sync");
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(Filesystem.createFile(testFile2));
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(Filesystem.createFile(nestedInternalFile));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile2.exists());
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(nestedInternalFile.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile2.isFile());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(nestedInternalFile.isFile());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(internalFile2.isFile());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testFile3.isFile());
        
        Assert.assertTrue(Filesystem.writeStringToFile(testFile2, testFile2.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile, internalFile.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(internalFile2, internalFile2.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(testFile3, testFile3.getName()));
        Assert.assertTrue(Filesystem.writeStringToFile(nestedInternalFile, nestedInternalFile.getName()));
        
        checksumListing = Filesystem.checksumDirectory(testDir);
        Assert.assertNotEquals("", checksumListing);
        Assert.assertTrue(checksumListing.matches("^\\{\"checksums\":\\[.*]}$"));
        
        Assert.assertTrue(Filesystem.writeStringToFile(testFile2, testFile2.getName(), true)); //modification
        
        Assert.assertTrue(Filesystem.deleteFile(internalFile)); //deletion
        Assert.assertTrue(Filesystem.deleteFile(internalFile2));
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(internalFile2.exists());
        String oldInternalFilePath = internalFile.getPath().replace("\\", "/");
        String oldInternalFile2Path = internalFile2.getPath().replace("\\", "/");
        
        internalFile = new File(testDir3, test2File.getName()); //addition
        internalFile2 = new File(testDir2, test2File.getName());
        Assert.assertTrue(Filesystem.createFile(internalFile));
        Assert.assertTrue(Filesystem.createFile(internalFile2));
        Assert.assertTrue(internalFile.exists());
        Assert.assertTrue(internalFile2.exists());
        Assert.assertTrue(internalFile.isFile());
        Assert.assertTrue(internalFile2.isFile());
        
        checksumComparison = Filesystem.compareChecksumDirectory(testDir, checksumListing);
        Assert.assertNotEquals("", checksumListing);
        Pattern checksumComparisonPattern = Pattern.compile("^\\{\"additions\":\\[(?<additions>.*?)],\"deletions\":\\[(?<deletions>.*?)],\"modifications\":\\[(?<modifications>.*?)]}$");
        Matcher checksumComparisonMatcher = checksumComparisonPattern.matcher(checksumComparison);
        Assert.assertTrue(checksumComparisonMatcher.matches());
        String comparisonAdditions = checksumComparisonMatcher.group("additions");
        String comparisonModifications = checksumComparisonMatcher.group("modifications");
        String comparisonDeletions = checksumComparisonMatcher.group("deletions");
        
        Pattern listingPattern = Pattern.compile("\"(?<file>.*?)\"");
        Matcher additionListingMatcher = listingPattern.matcher(comparisonAdditions);
        Matcher modificationListingMatcher = listingPattern.matcher(comparisonModifications);
        Matcher deletionListingMatcher = listingPattern.matcher(comparisonDeletions);
        List<String> additions = new ArrayList<>();
        List<String> modifications = new ArrayList<>();
        List<String> deletions = new ArrayList<>();
        while (additionListingMatcher.find()) {
            String file = additionListingMatcher.group("file").replace("\\/", "/");
            additions.add(file);
        }
        while (modificationListingMatcher.find()) {
            String file = modificationListingMatcher.group("file").replace("\\/", "/");
            modifications.add(file);
        }
        while (deletionListingMatcher.find()) {
            String file = deletionListingMatcher.group("file").replace("\\/", "/");
            deletions.add(file);
        }
        
        int prefixLength = (tmpDir.getName() + '/' + testDir.getName() + '/').length();
        Assert.assertTrue(modifications.contains(StringUtility.lShear(testFile2.getPath().replace("\\", "/"), prefixLength)));
        Assert.assertTrue(deletions.contains(StringUtility.lShear(oldInternalFilePath, prefixLength)));
        Assert.assertTrue(deletions.contains(StringUtility.lShear(oldInternalFile2Path, prefixLength)));
        Assert.assertTrue(additions.contains(StringUtility.lShear(internalFile.getPath().replace("\\", "/"), prefixLength)));
        Assert.assertTrue(additions.contains(StringUtility.lShear(internalFile2.getPath().replace("\\", "/"), prefixLength)));
        
        Assert.assertTrue(nestedInternalFile.delete());
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(internalFile2.delete());
        Assert.assertTrue(internalFile.delete());
        Assert.assertTrue(testFile2.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(nestedInternalFile.exists());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(internalFile2.exists());
        Assert.assertFalse(internalFile.exists());
        Assert.assertFalse(testFile2.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //empty
        
        Assert.assertTrue(Filesystem.createDirectory(testDir));
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testDir.isDirectory());
        checksumComparison = Filesystem.compareChecksumDirectory(testDir, Filesystem.checksumDirectory(testDir));
        Assert.assertEquals("{\"additions\":[],\"deletions\":[],\"modifications\":[]}", checksumComparison);
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testDir.exists());
    }
    
    /**
     * JUnit test of generatePath.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#generatePath(boolean, String...)
     */
    @Test
    public void testGeneratePath() throws Exception {
        //standard
        String path = Filesystem.generatePath("testDir", "testDir2", "testDir3", "test.txt");
        String expectedPath = "testDir/testDir2/testDir3/test.txt";
        Assert.assertEquals(expectedPath, path);
        Assert.assertTrue(Filesystem.createFile(testFile3));
        Assert.assertTrue(testFile3.exists());
        Assert.assertTrue(testDir3.exists());
        Assert.assertTrue(testDir2.exists());
        Assert.assertTrue(testDir.exists());
        Assert.assertTrue(testFile3.isFile());
        Assert.assertTrue(testDir3.isDirectory());
        Assert.assertTrue(testDir2.isDirectory());
        Assert.assertTrue(testDir.isDirectory());
        Assert.assertTrue(testFile3.getAbsolutePath().replace("\\", "/").endsWith(expectedPath));
        Assert.assertTrue(testFile3.delete());
        Assert.assertTrue(testDir3.delete());
        Assert.assertTrue(testDir2.delete());
        Assert.assertTrue(testDir.delete());
        Assert.assertFalse(testFile3.exists());
        Assert.assertFalse(testDir3.exists());
        Assert.assertFalse(testDir2.exists());
        Assert.assertFalse(testDir.exists());
        
        //ending slash
        path = Filesystem.generatePath(false, "testDir", "testDir2", "testDir3", "test.txt");
        expectedPath = "testDir/testDir2/testDir3/test.txt";
        Assert.assertEquals(expectedPath, path);
        path = Filesystem.generatePath(true, "testDir", "testDir2", "testDir3");
        expectedPath = "testDir/testDir2/testDir3/";
        Assert.assertEquals(expectedPath, path);
        
        //empty
        Assert.assertEquals("", Filesystem.generatePath());
    }
    
    /**
     * JUnit test of createTemporaryFile.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#createTemporaryFile(String)
     */
    @Test
    public void testCreateTemporaryFile() throws Exception {
        File tmpFile;
        
        //standard
        tmpFile = Filesystem.createTemporaryFile();
        Assert.assertTrue(tmpFile.exists());
        Assert.assertTrue(tmpFile.isFile());
        Assert.assertTrue(tmpFile.getParentFile().getAbsolutePath().equalsIgnoreCase(tmpDir.getAbsolutePath()));
        Assert.assertTrue(tmpFile.delete());
        Assert.assertFalse(tmpFile.exists());
        
        //extension, empty
        tmpFile = Filesystem.createTemporaryFile("");
        Assert.assertTrue(tmpFile.exists());
        Assert.assertTrue(tmpFile.isFile());
        Assert.assertTrue(tmpFile.getParentFile().getAbsolutePath().equalsIgnoreCase(tmpDir.getAbsolutePath()));
        Assert.assertTrue(tmpFile.delete());
        Assert.assertFalse(tmpFile.exists());
        
        //extension, not empty
        tmpFile = Filesystem.createTemporaryFile(".file");
        Assert.assertTrue(tmpFile.exists());
        Assert.assertTrue(tmpFile.isFile());
        Assert.assertTrue(tmpFile.getName().endsWith(".file"));
        Assert.assertTrue(tmpFile.getParentFile().getAbsolutePath().equalsIgnoreCase(tmpDir.getAbsolutePath()));
        Assert.assertTrue(tmpFile.delete());
        Assert.assertFalse(tmpFile.exists());
    }
    
    /**
     * JUnit test of createTemporaryDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#createTemporaryDirectory()
     */
    @Test
    public void testCreateTemporaryDirectory() throws Exception {
        //standard
        File tmpDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(tmpDir.exists());
        Assert.assertTrue(tmpDir.isDirectory());
        Assert.assertTrue(tmpDir.getParentFile().getAbsolutePath().equalsIgnoreCase(FilesystemTest.tmpDir.getAbsolutePath()));
        Assert.assertTrue(tmpDir.delete());
        Assert.assertFalse(tmpDir.exists());
    }
    
    /**
     * JUnit test of getTemporaryFilePathLength.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getTemporaryFilePathLength(String)
     */
    @Test
    public void testGetTemporaryFilePathLength() throws Exception {
        //standard
        Assert.assertEquals(40, Filesystem.getTemporaryFilePathLength());
        
        //extension, empty
        Assert.assertEquals(40, Filesystem.getTemporaryFilePathLength(""));
        
        //extension, not empty
        Assert.assertEquals(45, Filesystem.getTemporaryFilePathLength(".file"));
    }
    
    /**
     * JUnit test of getTemporaryDirectoryPathLength.
     *
     * @throws Exception When there is an exception.
     * @see Filesystem#getTemporaryDirectoryPathLength()
     */
    @Test
    public void testGetTemporaryDirectoryPathLength() throws Exception {
        //standard
        Assert.assertEquals(40, Filesystem.getTemporaryDirectoryPathLength());
    }
    
}