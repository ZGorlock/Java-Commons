/*
 * File:    ArchiveUtilityTest.java
 * Package: commons.file
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.file;

import java.io.File;
import java.util.UUID;
import java.util.zip.ZipEntry;

import commons.access.Filesystem;
import commons.access.Project;
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
 * JUnit test of ArchiveUtility.
 *
 * @see ArchiveUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ArchiveUtility.class, Filesystem.class})
public class ArchiveUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArchiveUtilityTest.class);
    
    
    //Constants
    
    /**
     * The test resources directory for this class.
     */
    private static final File testResources = Project.testResourcesDir(ArchiveUtility.class);
    
    
    //Static Fields
    
    /**
     * An example zip archive for testing.
     */
    private static File testZip = new File(testResources, "test.zip");
    
    /**
     * An example jar archive for testing.
     */
    private static File testJar = new File(testResources, "test.jar");
    
    /**
     * An example zip archive for testing.
     */
    private static File testZip2 = new File(testResources, "test2.zip");
    
    /**
     * An example jar archive for testing.
     */
    private static File testJar2 = new File(testResources, "test2.jar");
    
    /**
     * An example zip archive for testing.
     */
    private static File testZip3 = new File(testResources, "test3.zip");
    
    /**
     * An example jar archive for testing.
     */
    private static File testJar3 = new File(testResources, "test3.jar");
    
    
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
     * @see ArchiveUtility#ARCHIVE_PATH_SEPARATOR
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("/", ArchiveUtility.ARCHIVE_PATH_SEPARATOR);
    }
    
    /**
     * JUnit test of ArchiveType.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility.ArchiveType
     */
    @Test
    public void testArchiveType() throws Exception {
        Assert.assertEquals(2, ArchiveUtility.ArchiveType.values().length);
        Assert.assertEquals(ArchiveUtility.ArchiveType.ZIP, ArchiveUtility.ArchiveType.values()[0]);
        Assert.assertEquals(ArchiveUtility.ArchiveType.JAR, ArchiveUtility.ArchiveType.values()[1]);
        
        //toString
        Assert.assertEquals("zip", ArchiveUtility.ArchiveType.ZIP.toString());
        Assert.assertEquals("jar", ArchiveUtility.ArchiveType.JAR.toString());
    }
    
    /**
     * JUnit test of CompressionMethod.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility.CompressionMethod
     */
    @Test
    public void testCompressionMethod() throws Exception {
        Assert.assertEquals(2, ArchiveUtility.CompressionMethod.values().length);
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.CompressionMethod.values()[0]);
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.CompressionMethod.values()[1]);
        
        //getLevel
        Assert.assertEquals(ZipEntry.STORED, ArchiveUtility.CompressionMethod.STORE.getLevel());
        Assert.assertEquals(ZipEntry.DEFLATED, ArchiveUtility.CompressionMethod.COMPRESS.getLevel());
    }
    
    /**
     * JUnit test of extractResource.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#extractResource(File, String, File)
     */
    @Test
    public void testExtractResource() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        
        //standard
        
        Assert.assertTrue(ArchiveUtility.extractResource(testZip, "data.txt", testDir));
        Assert.assertTrue(new File(testDir, "data.txt").exists());
        
        Assert.assertTrue(ArchiveUtility.extractResource(testJar, "info.txt", testDir));
        Assert.assertTrue(new File(testDir, "info.txt").exists());
        
        //archive does not exist
        
        Assert.assertFalse(ArchiveUtility.extractResource(new File("another.zip"), "data.txt", testDir));
        Assert.assertFalse(ArchiveUtility.extractResource(new File("another.jar"), "data.txt", testDir));
        
        //unsupported archive type
        
        Filesystem.copyFile(testZip, new File(testDir, "testArchive.archive"));
        Assert.assertFalse(ArchiveUtility.extractResource(new File(testDir, "testArchive.archive"), "data.txt", testDir));
        
        //resource does not exist
        
        Assert.assertFalse(ArchiveUtility.extractResource(new File(testDir, "another.zip"), "testFile.txt", testDir));
        Assert.assertFalse(ArchiveUtility.extractResource(new File(testDir, "another.jar"), "testFile.txt", testDir));
        
        //cannot create output directory
        
        File testDir2 = Filesystem.createTemporaryDirectory();
        Filesystem.deleteDirectory(testDir2);
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(ArchiveUtility.extractResource(testZip, "data.txt", testDir2));
        Assert.assertFalse(ArchiveUtility.extractResource(testJar, "info.txt", testDir2));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of extractDirectory.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#extractDirectory(File, String, File)
     */
    @Test
    public void testExtractDirectory() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        
        //standard
        
        Assert.assertTrue(ArchiveUtility.extractDirectory(testZip, "packages", testDir));
        Assert.assertTrue(new File(testDir, "packages").exists());
        Assert.assertEquals(3, Filesystem.getFiles(new File(testDir, "packages")).size());
        
        Assert.assertTrue(ArchiveUtility.extractDirectory(testJar, "logs", testDir));
        Assert.assertTrue(new File(testDir, "logs").exists());
        Assert.assertEquals(5, Filesystem.getFiles(new File(testDir, "logs")).size());
        
        //archive does not exist
        
        Assert.assertFalse(ArchiveUtility.extractDirectory(new File("another.zip"), "packages", testDir));
        Assert.assertFalse(ArchiveUtility.extractDirectory(new File("another.jar"), "logs", testDir));
        
        //unsupported archive type
        
        Filesystem.copyFile(testZip, new File(testDir, "testArchive.archive"));
        Assert.assertFalse(ArchiveUtility.extractDirectory(new File(testDir, "testArchive.archive"), "packages", testDir));
        
        //resource does not exist
        
        Assert.assertFalse(ArchiveUtility.extractDirectory(new File(testDir, "another.zip"), "testDir", testDir));
        Assert.assertFalse(ArchiveUtility.extractDirectory(new File(testDir, "another.jar"), "testDir", testDir));
        
        //cannot create output directory
        
        File testDir2 = Filesystem.createTemporaryDirectory();
        Filesystem.deleteDirectory(testDir2);
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(ArchiveUtility.extractDirectory(testZip, "packages", testDir2));
        Assert.assertFalse(ArchiveUtility.extractDirectory(testJar, "logs", testDir2));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of extract.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#extract(File, File)
     */
    @Test
    public void testExtract() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        File outputDir;
        
        //standard
        
        outputDir = new File(testDir, "zip");
        Assert.assertTrue(ArchiveUtility.extract(testZip, outputDir));
        Assert.assertTrue(outputDir.exists());
        Assert.assertEquals(12, Filesystem.getFilesAndDirsRecursively(outputDir).size());
        
        outputDir = new File(testDir, "jar");
        Assert.assertTrue(ArchiveUtility.extract(testJar, outputDir));
        Assert.assertTrue(outputDir.exists());
        Assert.assertEquals(13, Filesystem.getFilesAndDirsRecursively(outputDir).size());
        
        outputDir = new File(testDir, "zip3");
        Assert.assertTrue(ArchiveUtility.extract(testZip3, outputDir));
        Assert.assertTrue(outputDir.exists());
        Assert.assertEquals(2, Filesystem.getFilesAndDirsRecursively(outputDir).size());
        
        outputDir = new File(testDir, "jar3");
        Assert.assertTrue(ArchiveUtility.extract(testJar3, outputDir));
        Assert.assertTrue(outputDir.exists());
        Assert.assertEquals(3, Filesystem.getFilesAndDirsRecursively(outputDir).size());
        
        //archive does not exist
        
        Assert.assertFalse(ArchiveUtility.extract(new File(testDir, "another.zip"), new File(testDir, "archive")));
        Assert.assertFalse(ArchiveUtility.extract(new File(testDir, "another.jar"), new File(testDir, "archive")));
        
        //unsupported archive type
        
        Filesystem.copyFile(testZip, new File(testDir, "testArchive.archive"));
        Assert.assertFalse(ArchiveUtility.extract(new File(testDir, "testArchive.archive"), new File(testDir, "archive")));
        
        //cannot create output directory
        
        File testDir2 = Filesystem.createTemporaryDirectory();
        Filesystem.deleteDirectory(testDir2);
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(ArchiveUtility.extract(testZip, testDir2));
        Assert.assertFalse(ArchiveUtility.extract(testJar, testDir2));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compileFile.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#compileFile(File, ArchiveUtility.CompressionMethod, File)
     * @see ArchiveUtility#compileFile(File, File)
     */
    @Test
    public void testCompileFile() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(ArchiveUtility.extract(testJar, new File(testDir, "jar")));
        File archive;
        
        //standard
        
        archive = new File(testDir, "archive_store.zip");
        Assert.assertTrue(ArchiveUtility.compileFile(archive, ArchiveUtility.CompressionMethod.STORE, new File(testDir, "zip/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(3478, archive.length());
        File zipStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipStore));
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(zipStore).size());
        Assert.assertTrue(new File(zipStore, "data.txt").exists());
        
        archive = new File(testDir, "archive_compress.zip");
        Assert.assertTrue(ArchiveUtility.compileFile(archive, ArchiveUtility.CompressionMethod.COMPRESS, new File(testDir, "zip/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(555, archive.length());
        File zipCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipCompress));
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(zipCompress).size());
        Assert.assertTrue(new File(zipCompress, "data.txt").exists());
        
        archive = new File(testDir, "archive_store.jar");
        Assert.assertTrue(ArchiveUtility.compileFile(archive, ArchiveUtility.CompressionMethod.STORE, new File(testDir, "jar/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(3486, archive.length());
        File jarStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarStore));
        Assert.assertEquals(2, Filesystem.getFilesAndDirsRecursively(jarStore).size());
        Assert.assertTrue(new File(jarStore, "data.txt").exists());
        
        archive = new File(testDir, "archive_compress.jar");
        Assert.assertTrue(ArchiveUtility.compileFile(archive, ArchiveUtility.CompressionMethod.COMPRESS, new File(testDir, "jar/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(563, archive.length());
        File jarCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarCompress));
        Assert.assertEquals(2, Filesystem.getFilesAndDirsRecursively(jarCompress).size());
        Assert.assertTrue(new File(jarCompress, "data.txt").exists());
        
        //default compression mode
        
        Assert.assertTrue(ArchiveUtility.compileFile(new File(testDir, "archive_default.zip"), new File(testDir, "zip/data.txt")));
        Assert.assertTrue(new File(testDir, "archive_default.zip").exists());
        Assert.assertEquals(3478, new File(testDir, "archive_default.zip").length());
        Assert.assertTrue(ArchiveUtility.compileFile(new File(testDir, "archive_default.jar"), new File(testDir, "jar/data.txt")));
        Assert.assertTrue(new File(testDir, "archive_default.jar").exists());
        Assert.assertEquals(3486, new File(testDir, "archive_default.jar").length());
        
        //file does not exist
        
        Assert.assertFalse(ArchiveUtility.compileFile(new File(testDir, "another.zip"), new File(testDir, "zip/test.txt")));
        Assert.assertFalse(ArchiveUtility.compileFile(new File(testDir, "another.jar"), new File(testDir, "jar/test.txt")));
        
        //unsupported archive type
        
        Assert.assertFalse(ArchiveUtility.compileFile(new File(testDir, "testArchive.archive"), new File(testDir, "zip/data.txt")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compileFiles.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#compileFiles(File, ArchiveUtility.CompressionMethod, File...)
     * @see ArchiveUtility#compileFiles(File, File...)
     */
    @Test
    public void testCompileFiles() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(ArchiveUtility.extract(testJar, new File(testDir, "jar")));
        File archive;
        
        //standard
        
        archive = new File(testDir, "archive_store.zip");
        Assert.assertTrue(ArchiveUtility.compileFiles(archive, ArchiveUtility.CompressionMethod.STORE,
                new File(testDir, "zip/data.txt"), new File(testDir, "zip/info.txt"), new File(testDir, "zip/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(7033, archive.length());
        File zipStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipStore));
        Assert.assertEquals(3, Filesystem.getFilesAndDirsRecursively(zipStore).size());
        Assert.assertTrue(new File(zipStore, "data.txt").exists());
        Assert.assertTrue(new File(zipStore, "info.txt").exists());
        Assert.assertTrue(new File(zipStore, "log1.log").exists());
        
        archive = new File(testDir, "archive_compress.zip");
        Assert.assertTrue(ArchiveUtility.compileFiles(archive, ArchiveUtility.CompressionMethod.COMPRESS,
                new File(testDir, "zip/data.txt"), new File(testDir, "zip/info.txt"), new File(testDir, "zip/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(1194, archive.length());
        File zipCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipCompress));
        Assert.assertEquals(3, Filesystem.getFilesAndDirsRecursively(zipCompress).size());
        Assert.assertTrue(new File(zipCompress, "data.txt").exists());
        Assert.assertTrue(new File(zipCompress, "info.txt").exists());
        Assert.assertTrue(new File(zipCompress, "log1.log").exists());
        
        archive = new File(testDir, "archive_store.jar");
        Assert.assertTrue(ArchiveUtility.compileFiles(archive, ArchiveUtility.CompressionMethod.STORE,
                new File(testDir, "jar/data.txt"), new File(testDir, "jar/info.txt"), new File(testDir, "jar/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(7041, archive.length());
        File jarStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarStore));
        Assert.assertEquals(4, Filesystem.getFilesAndDirsRecursively(jarStore).size());
        Assert.assertTrue(new File(jarStore, "data.txt").exists());
        Assert.assertTrue(new File(jarStore, "info.txt").exists());
        Assert.assertTrue(new File(jarStore, "log1.log").exists());
        
        archive = new File(testDir, "archive_compress.jar");
        Assert.assertTrue(ArchiveUtility.compileFiles(archive, ArchiveUtility.CompressionMethod.COMPRESS,
                new File(testDir, "jar/data.txt"), new File(testDir, "jar/info.txt"), new File(testDir, "jar/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(1202, archive.length());
        File jarCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarCompress));
        Assert.assertEquals(4, Filesystem.getFilesAndDirsRecursively(jarCompress).size());
        Assert.assertTrue(new File(jarCompress, "data.txt").exists());
        Assert.assertTrue(new File(jarCompress, "info.txt").exists());
        Assert.assertTrue(new File(jarCompress, "log1.log").exists());
        
        //default compression mode
        
        Assert.assertTrue(ArchiveUtility.compileFiles(new File(testDir, "archive_default.zip"),
                new File(testDir, "zip/data.txt"), new File(testDir, "zip/info.txt"), new File(testDir, "zip/logs/log1.log")));
        Assert.assertTrue(new File(testDir, "archive_default.zip").exists());
        Assert.assertEquals(7033, new File(testDir, "archive_default.zip").length());
        Assert.assertTrue(ArchiveUtility.compileFiles(new File(testDir, "archive_default.jar"),
                new File(testDir, "jar/data.txt"), new File(testDir, "jar/info.txt"), new File(testDir, "jar/logs/log1.log")));
        Assert.assertTrue(new File(testDir, "archive_default.jar").exists());
        Assert.assertEquals(7041, new File(testDir, "archive_default.jar").length());
        
        //file does not exist
        
        Assert.assertFalse(ArchiveUtility.compileFiles(new File(testDir, "another.zip"), new File(testDir, "zip/data.txt"), new File(testDir, "zip/test.txt")));
        Assert.assertFalse(ArchiveUtility.compileFiles(new File(testDir, "another.jar"), new File(testDir, "jar/data.txt"), new File(testDir, "jar/test.txt")));
        
        //unsupported archive type
        
        Assert.assertFalse(ArchiveUtility.compileFiles(new File(testDir, "testArchive.archive"), new File(testDir, "zip/data.txt")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compile.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#compile(File, ArchiveUtility.CompressionMethod, File, boolean)
     * @see ArchiveUtility#compile(File, ArchiveUtility.CompressionMethod, File)
     * @see ArchiveUtility#compile(File, File, boolean)
     * @see ArchiveUtility#compile(File, File)
     */
    @Test
    public void testCompile() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(ArchiveUtility.extract(testJar, new File(testDir, "jar")));
        File archive;
        
        //standard
        
        archive = new File(testDir, "archive_store.zip");
        Assert.assertTrue(ArchiveUtility.compile(archive, ArchiveUtility.CompressionMethod.STORE, new File(testDir, "zip/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17108, archive.length());
        File zipStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipStore));
        Assert.assertEquals(5, Filesystem.getFilesAndDirsRecursively(zipStore).size());
        Assert.assertTrue(new File(zipStore, "log0.log").exists());
        Assert.assertTrue(new File(zipStore, "log1.log").exists());
        Assert.assertTrue(new File(zipStore, "log2.log").exists());
        Assert.assertTrue(new File(zipStore, "log3.log").exists());
        Assert.assertTrue(new File(zipStore, "log4.log").exists());
        
        archive = new File(testDir, "archive_compress.zip");
        Assert.assertTrue(ArchiveUtility.compile(archive, ArchiveUtility.CompressionMethod.COMPRESS, new File(testDir, "zip/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(2503, archive.length());
        File zipCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipCompress));
        Assert.assertEquals(5, Filesystem.getFilesAndDirsRecursively(zipCompress).size());
        Assert.assertTrue(new File(zipCompress, "log0.log").exists());
        Assert.assertTrue(new File(zipCompress, "log1.log").exists());
        Assert.assertTrue(new File(zipCompress, "log2.log").exists());
        Assert.assertTrue(new File(zipCompress, "log3.log").exists());
        Assert.assertTrue(new File(zipCompress, "log4.log").exists());
        
        archive = new File(testDir, "archive_store.jar");
        Assert.assertTrue(ArchiveUtility.compile(archive, ArchiveUtility.CompressionMethod.STORE, new File(testDir, "jar/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17116, archive.length());
        File jarStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarStore));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(jarStore).size());
        Assert.assertTrue(new File(jarStore, "log0.log").exists());
        Assert.assertTrue(new File(jarStore, "log1.log").exists());
        Assert.assertTrue(new File(jarStore, "log2.log").exists());
        Assert.assertTrue(new File(jarStore, "log3.log").exists());
        Assert.assertTrue(new File(jarStore, "log4.log").exists());
        
        archive = new File(testDir, "archive_compress.jar");
        Assert.assertTrue(ArchiveUtility.compile(archive, ArchiveUtility.CompressionMethod.COMPRESS, new File(testDir, "jar/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(2511, archive.length());
        File jarCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarCompress));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(jarCompress).size());
        Assert.assertTrue(new File(jarCompress, "log0.log").exists());
        Assert.assertTrue(new File(jarCompress, "log1.log").exists());
        Assert.assertTrue(new File(jarCompress, "log2.log").exists());
        Assert.assertTrue(new File(jarCompress, "log3.log").exists());
        Assert.assertTrue(new File(jarCompress, "log4.log").exists());
        
        //directory structure
        
        archive = new File(testDir, "archive_full.zip");
        Assert.assertTrue(ArchiveUtility.compile(archive, ArchiveUtility.CompressionMethod.STORE, new File(testDir, "zip")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        File zipFull = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipFull));
        Assert.assertEquals(12, Filesystem.getFilesAndDirsRecursively(zipFull).size());
        
        archive = new File(testDir, "archive_full.jar");
        Assert.assertTrue(ArchiveUtility.compile(archive, ArchiveUtility.CompressionMethod.STORE, new File(testDir, "jar")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21556, archive.length());
        File jarFull = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarFull));
        Assert.assertEquals(13, Filesystem.getFilesAndDirsRecursively(jarFull).size());
        
        //default compression mode, default include dir
        
        archive = new File(testDir, "archive_default.zip");
        Assert.assertTrue(ArchiveUtility.compile(archive, new File(testDir, "zip/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17108, archive.length());
        File zipDefault = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipDefault));
        Assert.assertEquals(5, Filesystem.getFilesAndDirsRecursively(zipDefault).size());
        Assert.assertFalse(new File(zipDefault, "logs").exists());
        
        archive = new File(testDir, "archive_default.jar");
        Assert.assertTrue(ArchiveUtility.compile(archive, new File(testDir, "jar/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17116, archive.length());
        File jarDefault = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarDefault));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(jarDefault).size());
        Assert.assertFalse(new File(jarDefault, "logs").exists());
        
        //include dir
        
        archive = new File(testDir, "archive_dir.zip");
        Assert.assertTrue(ArchiveUtility.compile(archive, new File(testDir, "zip/logs"), true));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17270, archive.length());
        File zipDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, zipDir));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(zipDir).size());
        Assert.assertTrue(new File(zipDir, "logs").exists());
        
        archive = new File(testDir, "archive_dir.jar");
        Assert.assertTrue(ArchiveUtility.compile(archive, new File(testDir, "jar/logs"), true));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17278, archive.length());
        File jarDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(ArchiveUtility.extract(archive, jarDir));
        Assert.assertEquals(7, Filesystem.getFilesAndDirsRecursively(jarDir).size());
        Assert.assertTrue(new File(jarDir, "logs").exists());
        
        //directory does not exist
        
        Assert.assertFalse(ArchiveUtility.compile(new File(testDir, "another.zip"), new File(testDir, "zip/testDir")));
        Assert.assertFalse(ArchiveUtility.compile(new File(testDir, "another.jar"), new File(testDir, "jar/testDir")));
        
        //unsupported archive type
        
        Assert.assertFalse(ArchiveUtility.compile(new File(testDir, "testArchive.archive"), new File(testDir, "zip/data.txt")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compress.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#compress(File)
     */
    @Test
    public void testCompress() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //archive does not exist
        
        Assert.assertFalse(ArchiveUtility.compress(new File(testDir, "another.zip")));
        Assert.assertFalse(ArchiveUtility.compress(new File(testDir, "another.jar")));
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        Assert.assertFalse(ArchiveUtility.compress(archive));
        
        //already compressed
        
        archive = new File(testDir, testZip.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //cannot create output directory
        
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(ArchiveUtility.compress(new File(testDir, testZip.getName())));
        Assert.assertFalse(ArchiveUtility.compress(new File(testDir, testJar.getName())));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of decompress.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#decompress(File)
     */
    @Test
    public void testDecompress() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21556, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //archive does not exist
        
        Assert.assertFalse(ArchiveUtility.decompress(new File(testDir, "another.zip")));
        Assert.assertFalse(ArchiveUtility.decompress(new File(testDir, "another.jar")));
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        Assert.assertFalse(ArchiveUtility.decompress(archive));
        
        //already decompressed
        
        archive = new File(testDir, testZip.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Assert.assertTrue(ArchiveUtility.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, ArchiveUtility.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //cannot create output directory
        
        Assert.assertTrue(ArchiveUtility.compress(new File(testDir, testZip.getName())));
        Assert.assertTrue(ArchiveUtility.compress(new File(testDir, testJar.getName())));
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(new File(UUID.randomUUID().toString())).when(Filesystem.class, "createTemporaryDirectory");
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(ArchiveUtility.decompress(new File(testDir, testZip.getName())));
        Assert.assertFalse(ArchiveUtility.decompress(new File(testDir, testJar.getName())));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of isCompressed.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#isCompressed(File)
     */
    @Test
    public void testIsCompressed() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        Boolean isCompressed;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        isCompressed = ArchiveUtility.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertFalse(isCompressed);
        Assert.assertTrue(ArchiveUtility.compress(archive));
        isCompressed = ArchiveUtility.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertTrue(isCompressed);
        
        archive = new File(testDir, testJar.getName());
        isCompressed = ArchiveUtility.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertFalse(isCompressed);
        Assert.assertTrue(ArchiveUtility.compress(archive));
        isCompressed = ArchiveUtility.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertTrue(isCompressed);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        isCompressed = ArchiveUtility.isCompressed(archive);
        Assert.assertNull(isCompressed);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of isDecompressed.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#isDecompressed(File)
     */
    @Test
    public void testIsDecompressed() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        Boolean isDecompressed;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        isDecompressed = ArchiveUtility.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertTrue(isDecompressed);
        Assert.assertTrue(ArchiveUtility.compress(archive));
        isDecompressed = ArchiveUtility.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertFalse(isDecompressed);
        
        archive = new File(testDir, testJar.getName());
        isDecompressed = ArchiveUtility.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertTrue(isDecompressed);
        Assert.assertTrue(ArchiveUtility.compress(archive));
        isDecompressed = ArchiveUtility.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertFalse(isDecompressed);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        isDecompressed = ArchiveUtility.isDecompressed(archive);
        Assert.assertNull(isDecompressed);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of getCompressionMethod.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#getCompressionMethod(File)
     */
    @Test
    public void testGetCompressionMethod() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        ArchiveUtility.CompressionMethod compressionMethod;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        compressionMethod = ArchiveUtility.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, compressionMethod);
        Assert.assertTrue(ArchiveUtility.compress(archive));
        compressionMethod = ArchiveUtility.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, compressionMethod);
        
        archive = new File(testDir, testJar.getName());
        compressionMethod = ArchiveUtility.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(ArchiveUtility.CompressionMethod.STORE, compressionMethod);
        Assert.assertTrue(ArchiveUtility.compress(archive));
        compressionMethod = ArchiveUtility.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(ArchiveUtility.CompressionMethod.COMPRESS, compressionMethod);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        compressionMethod = ArchiveUtility.getCompressionMethod(archive);
        Assert.assertNull(compressionMethod);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of getArchiveType.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#getArchiveType(File)
     */
    @Test
    public void testGetArchiveType() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        ArchiveUtility.ArchiveType archiveType;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        archiveType = ArchiveUtility.getArchiveType(archive);
        Assert.assertNotNull(archiveType);
        Assert.assertEquals(ArchiveUtility.ArchiveType.ZIP, archiveType);
        
        archive = new File(testDir, testJar.getName());
        archiveType = ArchiveUtility.getArchiveType(archive);
        Assert.assertNotNull(archiveType);
        Assert.assertEquals(ArchiveUtility.ArchiveType.JAR, archiveType);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        archiveType = ArchiveUtility.getArchiveType(archive);
        Assert.assertNull(archiveType);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of createDiffArchive.
     *
     * @throws Exception When there is an exception.
     * @see ArchiveUtility#createDiffArchive(File, File, File, ArchiveUtility.CompressionMethod)
     * @see ArchiveUtility#createDiffArchive(File, File, File)
     */
    @Test
    public void testCreateDiffArchive() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        Filesystem.copyFile(testZip2, testDir);
        Filesystem.copyFile(testJar2, testDir);
        File source;
        File target;
        File diff;
        File diffExtract;
        
        //standard
        
        source = new File(testDir, "test.zip");
        target = new File(testDir, "test2.zip");
        diff = new File(testDir, "diff.zip");
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21428, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22350, target.length());
        Assert.assertFalse(diff.exists());
        Assert.assertTrue(ArchiveUtility.createDiffArchive(source, target, diff, ArchiveUtility.CompressionMethod.STORE));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21428, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22350, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8721, diff.length());
        diffExtract = new File(testDir, "diffZip");
        Assert.assertTrue(ArchiveUtility.extract(diff, diffExtract));
        Assert.assertEquals(9, Filesystem.getFilesAndDirsRecursively(diffExtract).size());
        Assert.assertTrue(new File(diffExtract, "data.txt").exists());
        Assert.assertTrue(new File(diffExtract, "info.txt~").exists());
        Assert.assertTrue(new File(diffExtract, "manifest.txt").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log0.log~").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log5.log").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test1.package").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test3.package").exists());
        
        diff = new File(testDir, "diff_compress.zip");
        Assert.assertTrue(ArchiveUtility.createDiffArchive(source, target, diff, ArchiveUtility.CompressionMethod.COMPRESS));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21428, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22350, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(2493, diff.length());
        diffExtract = new File(testDir, "diffCompressZip");
        Assert.assertTrue(ArchiveUtility.extract(diff, diffExtract));
        Assert.assertEquals(9, Filesystem.getFilesAndDirsRecursively(diffExtract).size());
        Assert.assertTrue(new File(diffExtract, "data.txt").exists());
        Assert.assertTrue(new File(diffExtract, "info.txt~").exists());
        Assert.assertTrue(new File(diffExtract, "manifest.txt").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log0.log~").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log5.log").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test1.package").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test3.package").exists());
        
        source = new File(testDir, "test.jar");
        target = new File(testDir, "test2.jar");
        diff = new File(testDir, "diff.jar");
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21436, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22358, target.length());
        Assert.assertFalse(diff.exists());
        Assert.assertTrue(ArchiveUtility.createDiffArchive(source, target, diff, ArchiveUtility.CompressionMethod.STORE));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21436, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22358, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8729, diff.length());
        diffExtract = new File(testDir, "diffJar");
        Assert.assertTrue(ArchiveUtility.extract(diff, diffExtract));
        Assert.assertEquals(10, Filesystem.getFilesAndDirsRecursively(diffExtract).size());
        Assert.assertTrue(new File(diffExtract, "data.txt").exists());
        Assert.assertTrue(new File(diffExtract, "info.txt~").exists());
        Assert.assertTrue(new File(diffExtract, "manifest.txt").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log0.log~").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log5.log").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test1.package").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test3.package").exists());
        Assert.assertTrue(new File(diffExtract, "META-INF").exists());
        
        diff = new File(testDir, "diff_compress.jar");
        Assert.assertTrue(ArchiveUtility.createDiffArchive(source, target, diff, ArchiveUtility.CompressionMethod.COMPRESS));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21436, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22358, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(2501, diff.length());
        diffExtract = new File(testDir, "diffCompressJar");
        Assert.assertTrue(ArchiveUtility.extract(diff, diffExtract));
        Assert.assertEquals(10, Filesystem.getFilesAndDirsRecursively(diffExtract).size());
        Assert.assertTrue(new File(diffExtract, "data.txt").exists());
        Assert.assertTrue(new File(diffExtract, "info.txt~").exists());
        Assert.assertTrue(new File(diffExtract, "manifest.txt").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log0.log~").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log5.log").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test1.package").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test3.package").exists());
        Assert.assertTrue(new File(diffExtract, "META-INF").exists());
        
        //default compression
        
        source = new File(testDir, "test.zip");
        target = new File(testDir, "test2.zip");
        diff = new File(testDir, "diff2.zip");
        Assert.assertFalse(diff.exists());
        Assert.assertTrue(ArchiveUtility.createDiffArchive(source, target, diff));
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8721, diff.length());
        
        source = new File(testDir, "test.jar");
        target = new File(testDir, "test2.jar");
        diff = new File(testDir, "diff2.jar");
        Assert.assertFalse(diff.exists());
        Assert.assertTrue(ArchiveUtility.createDiffArchive(source, target, diff));
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8729, diff.length());
        
        //source does not exist
        
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, "source.zip"), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, "source.jar"), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        
        //target does not exist
        
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, "target.zip"), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, "target.jar"), new File(testDir, "diff.jar")));
        
        //unsupported source archive type
        
        Filesystem.copyFile(new File(testDir, testZip.getName()), new File(testDir, "sourceZip.archive"));
        Filesystem.copyFile(new File(testDir, testJar.getName()), new File(testDir, "sourceJar.archive"));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, "sourceZip.archive"), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, "sourceJar.archive"), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        
        //unsupported target archive type
        
        Filesystem.copyFile(new File(testDir, testZip.getName()), new File(testDir, "targetZip.archive"));
        Filesystem.copyFile(new File(testDir, testJar.getName()), new File(testDir, "targetJar.archive"));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, "targetZip.archive"), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, "targetJar.archive"), new File(testDir, "diff.jar")));
        
        //unsupported diff archive type
        
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diffZip.archive")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diffJar.archive")));
        
        //archive types differ
        
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.archive")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.archive")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.jar")));
        
        //cannot create output directories
        
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(ArchiveUtility.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
}
