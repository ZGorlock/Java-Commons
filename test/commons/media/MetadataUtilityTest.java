/*
 * File:    MetadataUtilityTest.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.List;

import commons.access.Project;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of MetadataUtility.
 *
 * @see MetadataUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MetadataUtility.class})
public class MetadataUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MetadataUtilityTest.class);
    
    
    //Constants
    
    /**
     * The directory containing resources for this test class.
     */
    private static final File testResources = Project.testResourcesDir(MetadataUtility.class);
    
    
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
     * JUnit test of getMetadata.
     *
     * @throws Exception When there is an exception.
     * @see MetadataUtility#getMetadata(File, String)
     * @see MetadataUtility#getMetadata(File)
     */
    @Test
    public void testGetMetadata() throws Exception {
        List<MetadataUtility.MetadataTag> metadata;
        
        //jpg
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"));
        Assert.assertNotNull(metadata);
        Assert.assertEquals(143, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "JPEG");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(8, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "JFIF");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(6, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Exif IFD0");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(13, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Exif SubIFD");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(24, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "GPS");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(7, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Exif Thumbnail");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(6, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "XMP");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(1, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "ICC Profile");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(30, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Photoshop");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(18, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "IPTC");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(18, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Adobe JPEG");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(4, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Huffman");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(1, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "File Type");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(4, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "File");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(3, metadata.size());
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.jpg"), "Missing");
        Assert.assertNotNull(metadata);
        Assert.assertEquals(0, metadata.size());
        
        //png
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.png"));
        Assert.assertNotNull(metadata);
        Assert.assertEquals(16, metadata.size());
        
        //gif
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.gif"));
        Assert.assertNotNull(metadata);
        Assert.assertEquals(27, metadata.size());
        
        //bmp
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.bmp"));
        Assert.assertNotNull(metadata);
        Assert.assertEquals(18, metadata.size());
        
        //ico
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.ico"));
        Assert.assertNotNull(metadata);
        Assert.assertEquals(15, metadata.size());
        
        //text
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.txt"));
        Assert.assertNotNull(metadata);
        Assert.assertEquals(0, metadata.size());
        
        //invalid
        
        metadata = MetadataUtility.getMetadata(new File(testResources, "test.missing"));
        Assert.assertNull(metadata);
        
        metadata = MetadataUtility.getMetadata(null);
        Assert.assertNull(metadata);
    }
    
    /**
     * JUnit test of getMetadataTag.
     *
     * @throws Exception When there is an exception.
     * @see MetadataUtility#getMetadataTag(File, String, String)
     * @see MetadataUtility#getMetadataTag(File, String)
     */
    @Test
    public void testGetMetadataTag() throws Exception {
        MetadataUtility.MetadataTag metadata;
        
        //standard
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "JPEG", "Image Width");
        Assert.assertNotNull(metadata);
        Assert.assertEquals("JPEG", metadata.directory);
        Assert.assertEquals("Image Width", metadata.name);
        Assert.assertEquals("600 pixels", metadata.value);
        Assert.assertEquals(metadata, MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "Image Width"));
        Assert.assertEquals(metadata, MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), null, "Image Width"));
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "GPS", "GPS Latitude");
        Assert.assertNotNull(metadata);
        Assert.assertEquals("GPS", metadata.directory);
        Assert.assertEquals("GPS Latitude", metadata.name);
        Assert.assertEquals("54° 59' 22.8\"", metadata.value);
        Assert.assertEquals(metadata, MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "GPS Latitude"));
        Assert.assertEquals(metadata, MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), null, "GPS Latitude"));
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "IPTC", "Date Created");
        Assert.assertNotNull(metadata);
        Assert.assertEquals("IPTC", metadata.directory);
        Assert.assertEquals("Date Created", metadata.name);
        Assert.assertEquals("2002:06:20", metadata.value);
        Assert.assertEquals(metadata, MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "Date Created"));
        Assert.assertEquals(metadata, MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), null, "Date Created"));
        
        //invalid
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "Missing", "Image Height");
        Assert.assertNull(metadata);
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "JPEG", "Missing");
        Assert.assertNull(metadata);
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), "JPEG", null);
        Assert.assertNull(metadata);
        
        metadata = MetadataUtility.getMetadataTag(new File(testResources, "test.jpg"), null);
        Assert.assertNull(metadata);
        
        TestUtils.assertException(NullPointerException.class, () ->
                MetadataUtility.getMetadataTag(null, "Image Height"));
    }
    
    /**
     * JUnit test of MetadataTag.
     *
     * @throws Exception When there is an exception.
     * @see MetadataUtility.MetadataTag
     */
    @Test
    public void testMetadataTag() throws Exception {
        MetadataUtility.MetadataTag testTag1 = new MetadataUtility.MetadataTag(
                "Test Dir", "Test Tag", "The Value", 3);
        
        MetadataUtility.MetadataTag testTag2 = new MetadataUtility.MetadataTag();
        testTag2.directory = "Test Dir";
        testTag2.name = "Test Tag";
        testTag2.value = "The Value";
        testTag2.type = 3;
        
        //equals
        
        Assert.assertEquals(testTag1, testTag2);
        
        testTag2.directory = "Another Dir";
        Assert.assertNotEquals(testTag1, testTag2);
        testTag2.directory = "Test Dir";
        Assert.assertEquals(testTag1, testTag2);
        
        testTag2.name = "Another Tag";
        Assert.assertNotEquals(testTag1, testTag2);
        testTag2.name = "Test Tag";
        Assert.assertEquals(testTag1, testTag2);
        
        testTag2.value = "Another Value";
        Assert.assertNotEquals(testTag1, testTag2);
        testTag2.value = "The Value";
        Assert.assertEquals(testTag1, testTag2);
        
        testTag2.type = 8;
        Assert.assertNotEquals(testTag1, testTag2);
        testTag2.type = 3;
        Assert.assertEquals(testTag1, testTag2);
        
        //toString
        
        Assert.assertEquals("[Test Dir] Test Tag : The Value", testTag1.toString());
        Assert.assertEquals("[Test Dir] Test Tag : The Value", testTag2.toString());
    }
    
}
