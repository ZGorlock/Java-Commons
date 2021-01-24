/*
 * File:    ArchiveTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 */

package commons.access;

import java.io.File;
import java.util.zip.ZipEntry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Archive.
 *
 * @see Archive
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({Archive.class})
public class ArchiveTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArchiveTest.class);
    
    
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
        //TODO
    }
    
    /**
     * JUnit test of extractDirectory.
     *
     * @throws Exception When there is an exception.
     * @see Archive#extractDirectory(File, String, File)
     */
    @Test
    public void testExtractDirectory() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of extract.
     *
     * @throws Exception When there is an exception.
     * @see Archive#extract(File, File)
     */
    @Test
    public void testExtract() throws Exception {
        //TODO
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
        //TODO
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
        //TODO
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
        //TODO
    }
    
    /**
     * JUnit test of compress.
     *
     * @throws Exception When there is an exception.
     * @see Archive#compress(File)
     */
    @Test
    public void testCompress() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of decompress.
     *
     * @throws Exception When there is an exception.
     * @see Archive#decompress(File)
     */
    @Test
    public void testDecompress() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isCompressed.
     *
     * @throws Exception When there is an exception.
     * @see Archive#isCompressed(File)
     */
    @Test
    public void testIsCompressed() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isDecompressed.
     *
     * @throws Exception When there is an exception.
     * @see Archive#isDecompressed(File)
     */
    @Test
    public void testIsDecompressed() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getCompressionMethod.
     *
     * @throws Exception When there is an exception.
     * @see Archive#getCompressionMethod(File)
     */
    @Test
    public void testGetCompressionMethod() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getArchiveType.
     *
     * @throws Exception When there is an exception.
     * @see Archive#getArchiveType(File)
     */
    @Test
    public void testGetArchiveType() throws Exception {
        //TODO
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
        //TODO
    }
    
}
