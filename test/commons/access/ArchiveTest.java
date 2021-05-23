/*
 * File:    ArchiveTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.File;
import java.util.UUID;
import java.util.zip.ZipEntry;

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
 * JUnit test of Archive.
 *
 * @see Archive
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Archive.class, Filesystem.class})
public class ArchiveTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArchiveTest.class);
    
    
    //Constants
    
    /**
     * The test resources directory for this class.
     */
    private static final File testResources = new File("test-resources/commons/access/Archive");
    
    
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
     * @see Archive#ARCHIVE_PATH_SEPARATOR
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("/", Archive.ARCHIVE_PATH_SEPARATOR);
    }
    
    /**
     * JUnit test of ArchiveType.
     *
     * @throws Exception When there is an exception.
     * @see Archive.ArchiveType
     */
    @Test
    public void testArchiveType() throws Exception {
        Assert.assertEquals(2, Archive.ArchiveType.values().length);
        Assert.assertEquals(Archive.ArchiveType.ZIP, Archive.ArchiveType.values()[0]);
        Assert.assertEquals(Archive.ArchiveType.JAR, Archive.ArchiveType.values()[1]);
        
        //toString
        Assert.assertEquals("zip", Archive.ArchiveType.ZIP.toString());
        Assert.assertEquals("jar", Archive.ArchiveType.JAR.toString());
    }
    
    /**
     * JUnit test of CompressionMethod.
     *
     * @throws Exception When there is an exception.
     * @see Archive.CompressionMethod
     */
    @Test
    public void testCompressionMethod() throws Exception {
        Assert.assertEquals(2, Archive.CompressionMethod.values().length);
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.CompressionMethod.values()[0]);
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.CompressionMethod.values()[1]);
        
        //getLevel
        Assert.assertEquals(ZipEntry.STORED, Archive.CompressionMethod.STORE.getLevel());
        Assert.assertEquals(ZipEntry.DEFLATED, Archive.CompressionMethod.COMPRESS.getLevel());
    }
    
    /**
     * JUnit test of extractResource.
     *
     * @throws Exception When there is an exception.
     * @see Archive#extractResource(File, String, File)
     */
    @Test
    public void testExtractResource() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        
        //standard
        
        Assert.assertTrue(Archive.extractResource(testZip, "data.txt", testDir));
        Assert.assertTrue(new File(testDir, "data.txt").exists());
        
        Assert.assertTrue(Archive.extractResource(testJar, "info.txt", testDir));
        Assert.assertTrue(new File(testDir, "info.txt").exists());
        
        //archive does not exist
        
        Assert.assertFalse(Archive.extractResource(new File("another.zip"), "data.txt", testDir));
        Assert.assertFalse(Archive.extractResource(new File("another.jar"), "data.txt", testDir));
        
        //unsupported archive type
        
        Filesystem.copyFile(testZip, new File(testDir, "testArchive.archive"));
        Assert.assertFalse(Archive.extractResource(new File(testDir, "testArchive.archive"), "data.txt", testDir));
        
        //resource does not exist
        
        Assert.assertFalse(Archive.extractResource(new File(testDir, "another.zip"), "testFile.txt", testDir));
        Assert.assertFalse(Archive.extractResource(new File(testDir, "another.jar"), "testFile.txt", testDir));
        
        //cannot create output directory
        
        File testDir2 = Filesystem.createTemporaryDirectory();
        Filesystem.deleteDirectory(testDir2);
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(Archive.extractResource(testZip, "data.txt", testDir2));
        Assert.assertFalse(Archive.extractResource(testJar, "info.txt", testDir2));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of extractDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Archive#extractDirectory(File, String, File)
     */
    @Test
    public void testExtractDirectory() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        
        //standard
        
        Assert.assertTrue(Archive.extractDirectory(testZip, "packages", testDir));
        Assert.assertTrue(new File(testDir, "packages").exists());
        Assert.assertEquals(3, Filesystem.getFiles(new File(testDir, "packages")).size());
        
        Assert.assertTrue(Archive.extractDirectory(testJar, "logs", testDir));
        Assert.assertTrue(new File(testDir, "logs").exists());
        Assert.assertEquals(5, Filesystem.getFiles(new File(testDir, "logs")).size());
        
        //archive does not exist
        
        Assert.assertFalse(Archive.extractDirectory(new File("another.zip"), "packages", testDir));
        Assert.assertFalse(Archive.extractDirectory(new File("another.jar"), "logs", testDir));
        
        //unsupported archive type
        
        Filesystem.copyFile(testZip, new File(testDir, "testArchive.archive"));
        Assert.assertFalse(Archive.extractDirectory(new File(testDir, "testArchive.archive"), "packages", testDir));
        
        //resource does not exist
        
        Assert.assertFalse(Archive.extractDirectory(new File(testDir, "another.zip"), "testDir", testDir));
        Assert.assertFalse(Archive.extractDirectory(new File(testDir, "another.jar"), "testDir", testDir));
        
        //cannot create output directory
        
        File testDir2 = Filesystem.createTemporaryDirectory();
        Filesystem.deleteDirectory(testDir2);
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(Archive.extractDirectory(testZip, "packages", testDir2));
        Assert.assertFalse(Archive.extractDirectory(testJar, "logs", testDir2));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of extract.
     *
     * @throws Exception When there is an exception.
     * @see Archive#extract(File, File)
     */
    @Test
    public void testExtract() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        
        //standard
        
        Assert.assertTrue(Archive.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(new File(testDir, "zip").exists());
        Assert.assertEquals(12, Filesystem.getFilesAndDirsRecursively(new File(testDir, "zip")).size());
        
        Assert.assertTrue(Archive.extract(testJar, new File(testDir, "jar")));
        Assert.assertTrue(new File(testDir, "jar").exists());
        Assert.assertEquals(13, Filesystem.getFilesAndDirsRecursively(new File(testDir, "jar")).size());
        
        //archive does not exist
        
        Assert.assertFalse(Archive.extract(new File(testDir, "another.zip"), new File(testDir, "archive")));
        Assert.assertFalse(Archive.extract(new File(testDir, "another.jar"), new File(testDir, "archive")));
        
        //unsupported archive type
        
        Filesystem.copyFile(testZip, new File(testDir, "testArchive.archive"));
        Assert.assertFalse(Archive.extract(new File(testDir, "testArchive.archive"), new File(testDir, "archive")));
        
        //cannot create output directory
        
        File testDir2 = Filesystem.createTemporaryDirectory();
        Filesystem.deleteDirectory(testDir2);
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(Archive.extract(testZip, testDir2));
        Assert.assertFalse(Archive.extract(testJar, testDir2));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compileFile.
     *
     * @throws Exception When there is an exception.
     * @see Archive#compileFile(File, Archive.CompressionMethod, File)
     * @see Archive#compileFile(File, File)
     */
    @Test
    public void testCompileFile() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(Archive.extract(testJar, new File(testDir, "jar")));
        File archive;
        
        //standard
        
        archive = new File(testDir, "archive_store.zip");
        Assert.assertTrue(Archive.compileFile(archive, Archive.CompressionMethod.STORE, new File(testDir, "zip/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(3478, archive.length());
        File zipStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipStore));
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(zipStore).size());
        Assert.assertTrue(new File(zipStore, "data.txt").exists());
        
        archive = new File(testDir, "archive_compress.zip");
        Assert.assertTrue(Archive.compileFile(archive, Archive.CompressionMethod.COMPRESS, new File(testDir, "zip/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(555, archive.length());
        File zipCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipCompress));
        Assert.assertEquals(1, Filesystem.getFilesAndDirsRecursively(zipCompress).size());
        Assert.assertTrue(new File(zipCompress, "data.txt").exists());
        
        archive = new File(testDir, "archive_store.jar");
        Assert.assertTrue(Archive.compileFile(archive, Archive.CompressionMethod.STORE, new File(testDir, "jar/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(3486, archive.length());
        File jarStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarStore));
        Assert.assertEquals(2, Filesystem.getFilesAndDirsRecursively(jarStore).size());
        Assert.assertTrue(new File(jarStore, "data.txt").exists());
        
        archive = new File(testDir, "archive_compress.jar");
        Assert.assertTrue(Archive.compileFile(archive, Archive.CompressionMethod.COMPRESS, new File(testDir, "jar/data.txt")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(563, archive.length());
        File jarCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarCompress));
        Assert.assertEquals(2, Filesystem.getFilesAndDirsRecursively(jarCompress).size());
        Assert.assertTrue(new File(jarCompress, "data.txt").exists());
        
        //default compression mode
        
        Assert.assertTrue(Archive.compileFile(new File(testDir, "archive_default.zip"), new File(testDir, "zip/data.txt")));
        Assert.assertTrue(new File(testDir, "archive_default.zip").exists());
        Assert.assertEquals(3478, new File(testDir, "archive_default.zip").length());
        Assert.assertTrue(Archive.compileFile(new File(testDir, "archive_default.jar"), new File(testDir, "jar/data.txt")));
        Assert.assertTrue(new File(testDir, "archive_default.jar").exists());
        Assert.assertEquals(3486, new File(testDir, "archive_default.jar").length());
        
        //file does not exist
        
        Assert.assertFalse(Archive.compileFile(new File(testDir, "another.zip"), new File(testDir, "zip/test.txt")));
        Assert.assertFalse(Archive.compileFile(new File(testDir, "another.jar"), new File(testDir, "jar/test.txt")));
        
        //unsupported archive type
        
        Assert.assertFalse(Archive.compileFile(new File(testDir, "testArchive.archive"), new File(testDir, "zip/data.txt")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compileFiles.
     *
     * @throws Exception When there is an exception.
     * @see Archive#compileFiles(File, Archive.CompressionMethod, File...)
     * @see Archive#compileFiles(File, File...)
     */
    @Test
    public void testCompileFiles() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(Archive.extract(testJar, new File(testDir, "jar")));
        File archive;
        
        //standard
        
        archive = new File(testDir, "archive_store.zip");
        Assert.assertTrue(Archive.compileFiles(archive, Archive.CompressionMethod.STORE,
                new File(testDir, "zip/data.txt"), new File(testDir, "zip/info.txt"), new File(testDir, "zip/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(7033, archive.length());
        File zipStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipStore));
        Assert.assertEquals(3, Filesystem.getFilesAndDirsRecursively(zipStore).size());
        Assert.assertTrue(new File(zipStore, "data.txt").exists());
        Assert.assertTrue(new File(zipStore, "info.txt").exists());
        Assert.assertTrue(new File(zipStore, "log1.log").exists());
        
        archive = new File(testDir, "archive_compress.zip");
        Assert.assertTrue(Archive.compileFiles(archive, Archive.CompressionMethod.COMPRESS,
                new File(testDir, "zip/data.txt"), new File(testDir, "zip/info.txt"), new File(testDir, "zip/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(1194, archive.length());
        File zipCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipCompress));
        Assert.assertEquals(3, Filesystem.getFilesAndDirsRecursively(zipCompress).size());
        Assert.assertTrue(new File(zipCompress, "data.txt").exists());
        Assert.assertTrue(new File(zipCompress, "info.txt").exists());
        Assert.assertTrue(new File(zipCompress, "log1.log").exists());
        
        archive = new File(testDir, "archive_store.jar");
        Assert.assertTrue(Archive.compileFiles(archive, Archive.CompressionMethod.STORE,
                new File(testDir, "jar/data.txt"), new File(testDir, "jar/info.txt"), new File(testDir, "jar/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(7041, archive.length());
        File jarStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarStore));
        Assert.assertEquals(4, Filesystem.getFilesAndDirsRecursively(jarStore).size());
        Assert.assertTrue(new File(jarStore, "data.txt").exists());
        Assert.assertTrue(new File(jarStore, "info.txt").exists());
        Assert.assertTrue(new File(jarStore, "log1.log").exists());
        
        archive = new File(testDir, "archive_compress.jar");
        Assert.assertTrue(Archive.compileFiles(archive, Archive.CompressionMethod.COMPRESS,
                new File(testDir, "jar/data.txt"), new File(testDir, "jar/info.txt"), new File(testDir, "jar/logs/log1.log")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(1202, archive.length());
        File jarCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarCompress));
        Assert.assertEquals(4, Filesystem.getFilesAndDirsRecursively(jarCompress).size());
        Assert.assertTrue(new File(jarCompress, "data.txt").exists());
        Assert.assertTrue(new File(jarCompress, "info.txt").exists());
        Assert.assertTrue(new File(jarCompress, "log1.log").exists());
        
        //default compression mode
        
        Assert.assertTrue(Archive.compileFiles(new File(testDir, "archive_default.zip"),
                new File(testDir, "zip/data.txt"), new File(testDir, "zip/info.txt"), new File(testDir, "zip/logs/log1.log")));
        Assert.assertTrue(new File(testDir, "archive_default.zip").exists());
        Assert.assertEquals(7033, new File(testDir, "archive_default.zip").length());
        Assert.assertTrue(Archive.compileFiles(new File(testDir, "archive_default.jar"),
                new File(testDir, "jar/data.txt"), new File(testDir, "jar/info.txt"), new File(testDir, "jar/logs/log1.log")));
        Assert.assertTrue(new File(testDir, "archive_default.jar").exists());
        Assert.assertEquals(7041, new File(testDir, "archive_default.jar").length());
        
        //file does not exist
        
        Assert.assertFalse(Archive.compileFiles(new File(testDir, "another.zip"), new File(testDir, "zip/data.txt"), new File(testDir, "zip/test.txt")));
        Assert.assertFalse(Archive.compileFiles(new File(testDir, "another.jar"), new File(testDir, "jar/data.txt"), new File(testDir, "jar/test.txt")));
        
        //unsupported archive type
        
        Assert.assertFalse(Archive.compileFiles(new File(testDir, "testArchive.archive"), new File(testDir, "zip/data.txt")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compile.
     *
     * @throws Exception When there is an exception.
     * @see Archive#compile(File, Archive.CompressionMethod, File, boolean)
     * @see Archive#compile(File, Archive.CompressionMethod, File)
     * @see Archive#compile(File, File, boolean)
     * @see Archive#compile(File, File)
     */
    @Test
    public void testCompile() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(testZip, new File(testDir, "zip")));
        Assert.assertTrue(Archive.extract(testJar, new File(testDir, "jar")));
        File archive;
        
        //standard
        
        archive = new File(testDir, "archive_store.zip");
        Assert.assertTrue(Archive.compile(archive, Archive.CompressionMethod.STORE, new File(testDir, "zip/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17108, archive.length());
        File zipStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipStore));
        Assert.assertEquals(5, Filesystem.getFilesAndDirsRecursively(zipStore).size());
        Assert.assertTrue(new File(zipStore, "log0.log").exists());
        Assert.assertTrue(new File(zipStore, "log1.log").exists());
        Assert.assertTrue(new File(zipStore, "log2.log").exists());
        Assert.assertTrue(new File(zipStore, "log3.log").exists());
        Assert.assertTrue(new File(zipStore, "log4.log").exists());
        
        archive = new File(testDir, "archive_compress.zip");
        Assert.assertTrue(Archive.compile(archive, Archive.CompressionMethod.COMPRESS, new File(testDir, "zip/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(2503, archive.length());
        File zipCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipCompress));
        Assert.assertEquals(5, Filesystem.getFilesAndDirsRecursively(zipCompress).size());
        Assert.assertTrue(new File(zipCompress, "log0.log").exists());
        Assert.assertTrue(new File(zipCompress, "log1.log").exists());
        Assert.assertTrue(new File(zipCompress, "log2.log").exists());
        Assert.assertTrue(new File(zipCompress, "log3.log").exists());
        Assert.assertTrue(new File(zipCompress, "log4.log").exists());
        
        archive = new File(testDir, "archive_store.jar");
        Assert.assertTrue(Archive.compile(archive, Archive.CompressionMethod.STORE, new File(testDir, "jar/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17116, archive.length());
        File jarStore = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarStore));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(jarStore).size());
        Assert.assertTrue(new File(jarStore, "log0.log").exists());
        Assert.assertTrue(new File(jarStore, "log1.log").exists());
        Assert.assertTrue(new File(jarStore, "log2.log").exists());
        Assert.assertTrue(new File(jarStore, "log3.log").exists());
        Assert.assertTrue(new File(jarStore, "log4.log").exists());
        
        archive = new File(testDir, "archive_compress.jar");
        Assert.assertTrue(Archive.compile(archive, Archive.CompressionMethod.COMPRESS, new File(testDir, "jar/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(2511, archive.length());
        File jarCompress = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarCompress));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(jarCompress).size());
        Assert.assertTrue(new File(jarCompress, "log0.log").exists());
        Assert.assertTrue(new File(jarCompress, "log1.log").exists());
        Assert.assertTrue(new File(jarCompress, "log2.log").exists());
        Assert.assertTrue(new File(jarCompress, "log3.log").exists());
        Assert.assertTrue(new File(jarCompress, "log4.log").exists());
        
        //directory structure
        
        archive = new File(testDir, "archive_full.zip");
        Assert.assertTrue(Archive.compile(archive, Archive.CompressionMethod.STORE, new File(testDir, "zip")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        File zipFull = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipFull));
        Assert.assertEquals(12, Filesystem.getFilesAndDirsRecursively(zipFull).size());
        
        archive = new File(testDir, "archive_full.jar");
        Assert.assertTrue(Archive.compile(archive, Archive.CompressionMethod.STORE, new File(testDir, "jar")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21556, archive.length());
        File jarFull = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarFull));
        Assert.assertEquals(13, Filesystem.getFilesAndDirsRecursively(jarFull).size());
        
        //default compression mode, default include dir
        
        archive = new File(testDir, "archive_default.zip");
        Assert.assertTrue(Archive.compile(archive, new File(testDir, "zip/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17108, archive.length());
        File zipDefault = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipDefault));
        Assert.assertEquals(5, Filesystem.getFilesAndDirsRecursively(zipDefault).size());
        Assert.assertFalse(new File(zipDefault, "logs").exists());
        
        archive = new File(testDir, "archive_default.jar");
        Assert.assertTrue(Archive.compile(archive, new File(testDir, "jar/logs")));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17116, archive.length());
        File jarDefault = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarDefault));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(jarDefault).size());
        Assert.assertFalse(new File(jarDefault, "logs").exists());
        
        //include dir
        
        archive = new File(testDir, "archive_dir.zip");
        Assert.assertTrue(Archive.compile(archive, new File(testDir, "zip/logs"), true));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17270, archive.length());
        File zipDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, zipDir));
        Assert.assertEquals(6, Filesystem.getFilesAndDirsRecursively(zipDir).size());
        Assert.assertTrue(new File(zipDir, "logs").exists());
        
        archive = new File(testDir, "archive_dir.jar");
        Assert.assertTrue(Archive.compile(archive, new File(testDir, "jar/logs"), true));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(17278, archive.length());
        File jarDir = Filesystem.createTemporaryDirectory();
        Assert.assertTrue(Archive.extract(archive, jarDir));
        Assert.assertEquals(7, Filesystem.getFilesAndDirsRecursively(jarDir).size());
        Assert.assertTrue(new File(jarDir, "logs").exists());
        
        //directory does not exist
        
        Assert.assertFalse(Archive.compile(new File(testDir, "another.zip"), new File(testDir, "zip/testDir")));
        Assert.assertFalse(Archive.compile(new File(testDir, "another.jar"), new File(testDir, "jar/testDir")));
        
        //unsupported archive type
        
        Assert.assertFalse(Archive.compile(new File(testDir, "testArchive.archive"), new File(testDir, "zip/data.txt")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of compress.
     *
     * @throws Exception When there is an exception.
     * @see Archive#compress(File)
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
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //archive does not exist
        
        Assert.assertFalse(Archive.compress(new File(testDir, "another.zip")));
        Assert.assertFalse(Archive.compress(new File(testDir, "another.jar")));
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        Assert.assertFalse(Archive.compress(archive));
        
        //already compressed
        
        archive = new File(testDir, testZip.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //cannot create output directory
        
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(Archive.compress(new File(testDir, testZip.getName())));
        Assert.assertFalse(Archive.compress(new File(testDir, testJar.getName())));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of decompress.
     *
     * @throws Exception When there is an exception.
     * @see Archive#decompress(File)
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
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4008, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.compress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(4154, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21556, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //archive does not exist
        
        Assert.assertFalse(Archive.decompress(new File(testDir, "another.zip")));
        Assert.assertFalse(Archive.decompress(new File(testDir, "another.jar")));
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        Assert.assertFalse(Archive.decompress(archive));
        
        //already decompressed
        
        archive = new File(testDir, testZip.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21428, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testZip, testDir);
        Assert.assertTrue(archive.exists());
        
        archive = new File(testDir, testJar.getName());
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Assert.assertTrue(Archive.decompress(archive));
        Assert.assertTrue(archive.exists());
        Assert.assertEquals(21436, archive.length());
        Assert.assertEquals(Archive.CompressionMethod.STORE, Archive.getCompressionMethod(archive));
        Filesystem.delete(archive);
        Assert.assertFalse(archive.exists());
        Filesystem.copyFile(testJar, testDir);
        Assert.assertTrue(archive.exists());
        
        //cannot create output directory
        
        Assert.assertTrue(Archive.compress(new File(testDir, testZip.getName())));
        Assert.assertTrue(Archive.compress(new File(testDir, testJar.getName())));
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(new File(UUID.randomUUID().toString())).when(Filesystem.class, "createTemporaryDirectory");
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(Archive.decompress(new File(testDir, testZip.getName())));
        Assert.assertFalse(Archive.decompress(new File(testDir, testJar.getName())));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of isCompressed.
     *
     * @throws Exception When there is an exception.
     * @see Archive#isCompressed(File)
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
        isCompressed = Archive.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertFalse(isCompressed);
        Assert.assertTrue(Archive.compress(archive));
        isCompressed = Archive.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertTrue(isCompressed);
        
        archive = new File(testDir, testJar.getName());
        isCompressed = Archive.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertFalse(isCompressed);
        Assert.assertTrue(Archive.compress(archive));
        isCompressed = Archive.isCompressed(archive);
        Assert.assertNotNull(isCompressed);
        Assert.assertTrue(isCompressed);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        isCompressed = Archive.isCompressed(archive);
        Assert.assertNull(isCompressed);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of isDecompressed.
     *
     * @throws Exception When there is an exception.
     * @see Archive#isDecompressed(File)
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
        isDecompressed = Archive.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertTrue(isDecompressed);
        Assert.assertTrue(Archive.compress(archive));
        isDecompressed = Archive.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertFalse(isDecompressed);
        
        archive = new File(testDir, testJar.getName());
        isDecompressed = Archive.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertTrue(isDecompressed);
        Assert.assertTrue(Archive.compress(archive));
        isDecompressed = Archive.isDecompressed(archive);
        Assert.assertNotNull(isDecompressed);
        Assert.assertFalse(isDecompressed);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        isDecompressed = Archive.isDecompressed(archive);
        Assert.assertNull(isDecompressed);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of getCompressionMethod.
     *
     * @throws Exception When there is an exception.
     * @see Archive#getCompressionMethod(File)
     */
    @Test
    public void testGetCompressionMethod() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        Archive.CompressionMethod compressionMethod;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        compressionMethod = Archive.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(Archive.CompressionMethod.STORE, compressionMethod);
        Assert.assertTrue(Archive.compress(archive));
        compressionMethod = Archive.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, compressionMethod);
        
        archive = new File(testDir, testJar.getName());
        compressionMethod = Archive.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(Archive.CompressionMethod.STORE, compressionMethod);
        Assert.assertTrue(Archive.compress(archive));
        compressionMethod = Archive.getCompressionMethod(archive);
        Assert.assertNotNull(compressionMethod);
        Assert.assertEquals(Archive.CompressionMethod.COMPRESS, compressionMethod);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        compressionMethod = Archive.getCompressionMethod(archive);
        Assert.assertNull(compressionMethod);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of getArchiveType.
     *
     * @throws Exception When there is an exception.
     * @see Archive#getArchiveType(File)
     */
    @Test
    public void testGetArchiveType() throws Exception {
        File testDir = Filesystem.createTemporaryDirectory();
        Filesystem.copyFile(testZip, testDir);
        Filesystem.copyFile(testJar, testDir);
        File archive;
        Archive.ArchiveType archiveType;
        
        //standard
        
        archive = new File(testDir, testZip.getName());
        archiveType = Archive.getArchiveType(archive);
        Assert.assertNotNull(archiveType);
        Assert.assertEquals(Archive.ArchiveType.ZIP, archiveType);
        
        archive = new File(testDir, testJar.getName());
        archiveType = Archive.getArchiveType(archive);
        Assert.assertNotNull(archiveType);
        Assert.assertEquals(Archive.ArchiveType.JAR, archiveType);
        
        //unsupported archive type
        
        archive = new File(testDir, "testArchive.archive");
        Filesystem.copyFile(new File(testDir, testZip.getName()), archive);
        Assert.assertTrue(archive.exists());
        archiveType = Archive.getArchiveType(archive);
        Assert.assertNull(archiveType);
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
    /**
     * JUnit test of createDiffArchive.
     *
     * @throws Exception When there is an exception.
     * @see Archive#createDiffArchive(File, File, File, Archive.CompressionMethod)
     * @see Archive#createDiffArchive(File, File, File)
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
        Assert.assertTrue(Archive.createDiffArchive(source, target, diff, Archive.CompressionMethod.STORE));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21428, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22350, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8721, diff.length());
        diffExtract = new File(testDir, "diffZip");
        Assert.assertTrue(Archive.extract(diff, diffExtract));
        Assert.assertEquals(9, Filesystem.getFilesAndDirsRecursively(diffExtract).size());
        Assert.assertTrue(new File(diffExtract, "data.txt").exists());
        Assert.assertTrue(new File(diffExtract, "info.txt~").exists());
        Assert.assertTrue(new File(diffExtract, "manifest.txt").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log0.log~").exists());
        Assert.assertTrue(new File(diffExtract, "logs/log5.log").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test1.package").exists());
        Assert.assertTrue(new File(diffExtract, "packages/test3.package").exists());
        
        diff = new File(testDir, "diff_compress.zip");
        Assert.assertTrue(Archive.createDiffArchive(source, target, diff, Archive.CompressionMethod.COMPRESS));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21428, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22350, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(2493, diff.length());
        diffExtract = new File(testDir, "diffCompressZip");
        Assert.assertTrue(Archive.extract(diff, diffExtract));
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
        Assert.assertTrue(Archive.createDiffArchive(source, target, diff, Archive.CompressionMethod.STORE));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21436, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22358, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8729, diff.length());
        diffExtract = new File(testDir, "diffJar");
        Assert.assertTrue(Archive.extract(diff, diffExtract));
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
        Assert.assertTrue(Archive.createDiffArchive(source, target, diff, Archive.CompressionMethod.COMPRESS));
        Assert.assertTrue(source.exists());
        Assert.assertEquals(21436, source.length());
        Assert.assertTrue(target.exists());
        Assert.assertEquals(22358, target.length());
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(2501, diff.length());
        diffExtract = new File(testDir, "diffCompressJar");
        Assert.assertTrue(Archive.extract(diff, diffExtract));
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
        Assert.assertTrue(Archive.createDiffArchive(source, target, diff));
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8721, diff.length());
        
        source = new File(testDir, "test.jar");
        target = new File(testDir, "test2.jar");
        diff = new File(testDir, "diff2.jar");
        Assert.assertFalse(diff.exists());
        Assert.assertTrue(Archive.createDiffArchive(source, target, diff));
        Assert.assertTrue(diff.exists());
        Assert.assertEquals(8729, diff.length());
        
        //source does not exist
        
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, "source.zip"), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, "source.jar"), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        
        //target does not exist
        
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, "target.zip"), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, "target.jar"), new File(testDir, "diff.jar")));
        
        //unsupported source archive type
        
        Filesystem.copyFile(new File(testDir, testZip.getName()), new File(testDir, "sourceZip.archive"));
        Filesystem.copyFile(new File(testDir, testJar.getName()), new File(testDir, "sourceJar.archive"));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, "sourceZip.archive"), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, "sourceJar.archive"), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        
        //unsupported target archive type
        
        Filesystem.copyFile(new File(testDir, testZip.getName()), new File(testDir, "targetZip.archive"));
        Filesystem.copyFile(new File(testDir, testJar.getName()), new File(testDir, "targetJar.archive"));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, "targetZip.archive"), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, "targetJar.archive"), new File(testDir, "diff.jar")));
        
        //unsupported diff archive type
        
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diffZip.archive")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diffJar.archive")));
        
        //archive types differ
        
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.archive")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.archive")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.jar")));
        
        //cannot create output directories
        
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(false).when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testZip.getName()), new File(testDir, testZip2.getName()), new File(testDir, "diff.zip")));
        Assert.assertFalse(Archive.createDiffArchive(new File(testDir, testJar.getName()), new File(testDir, testJar2.getName()), new File(testDir, "diff.jar")));
        
        //cleanup
        
        Filesystem.deleteDirectory(testDir);
    }
    
}
