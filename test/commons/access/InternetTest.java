/*
 * File:    InternetTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.regex.Pattern;

import commons.log.CommonsLogging;
import commons.math.BoundUtility;
import commons.string.StringUtility;
import commons.test.TestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Internet.
 *
 * @see Internet
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Internet.class, CommonsLogging.class, Desktop.class})
public class InternetTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(InternetTest.class);
    
    
    //Constants
    
    /**
     * The test resources directory for this class.
     */
    private static final File testResources = new File("test-resources/commons/access/Internet");
    
    
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
     * @see Internet#DEFAULT_TEST_HOST
     * @see Internet#URL_ENCODING
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("google.com", Internet.DEFAULT_TEST_HOST);
        Assert.assertEquals("UTF-8", Internet.URL_ENCODING);
    }
    
    /**
     * JUnit test of isOnline.
     *
     * @throws Exception When there is an exception.
     * @see Internet#isOnline()
     */
    @Test
    public void testIsOnline() throws Exception {
        long startTime;
        long duration;
        
        //connected
        Assert.assertEquals(Internet.DEFAULT_TEST_HOST, TestUtils.getField(Internet.class, "testHost"));
        startTime = System.currentTimeMillis();
        Assert.assertTrue(Internet.isOnline());
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 0, (200 * 1.01)));
        
        //"not connected"
        TestUtils.setField(Internet.class, "testHost", "n0t4w3bs1t3.com");
        Assert.assertEquals("n0t4w3bs1t3.com", TestUtils.getField(Internet.class, "testHost"));
        startTime = System.currentTimeMillis();
        Assert.assertFalse(Internet.isOnline());
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 0, (200 * 1.01)));
        TestUtils.setField(Internet.class, "testHost", Internet.DEFAULT_TEST_HOST);
        Assert.assertEquals(Internet.DEFAULT_TEST_HOST, TestUtils.getField(Internet.class, "testHost"));
    }
    
    /**
     * JUnit test of encodeUrl.
     *
     * @throws Exception When there is an exception.
     * @see Internet#encodeUrl(String)
     */
    @Test
    public void testEncodeUrl() throws Exception {
        //standard
        Assert.assertEquals("test", Internet.encodeUrl("test"));
        Assert.assertEquals("another+test", Internet.encodeUrl("another test"));
        Assert.assertEquals("another-test%3F", Internet.encodeUrl("another-test?"));
        Assert.assertEquals("dogs+%2B+cats+%3D+pets%21", Internet.encodeUrl("dogs + cats = pets!"));
        Assert.assertEquals("%3B%2F%3F%3A%40%26%3D%2B%24%2C", Internet.encodeUrl(";/?:@&=+$,"));
        
        //invalid
        Assert.assertEquals("", Internet.encodeUrl(""));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertEquals("", Internet.encodeUrl(null)));
    }
    
    /**
     * JUnit test of getHtml.
     *
     * @throws Exception When there is an exception.
     * @see Internet#getHtml(String)
     */
    @Test
    public void testGetHtml() throws Exception {
        Document html;
        long startTime;
        long duration;
        
        //standard
        
        startTime = System.currentTimeMillis();
        html = Internet.getHtml("https://www.google.com/search?num=1&q=define+run");
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 1, (5000 * 1.01)));
        Assert.assertNotNull(html);
        Assert.assertEquals("https://www.google.com/search?num=1&q=define+run", html.location());
        Assert.assertEquals(2, html.childNodeSize());
        Assert.assertEquals("https://www.google.com/search?num=1&q=define+run", html.baseUri());
        Assert.assertNull(html.parentNode());
        
        //invalid
        
        startTime = System.currentTimeMillis();
        html = Internet.getHtml("https://www.n0t4w3bs1t3.com");
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 1, (5000 * 1.01)));
        Assert.assertNull(html);
        
        TestUtils.assertException(IllegalArgumentException.class, "Must supply a valid URL", () ->
                Internet.getHtml(""));
        
        TestUtils.assertException(IllegalArgumentException.class, "Must supply a valid URL", () ->
                Internet.getHtml(null));
    }
    
    /**
     * JUnit test of downloadFile.
     *
     * @throws Exception When there is an exception.
     * @see Internet#downloadFile(String, File)
     * @see Internet#downloadFile(String)
     */
    @Test
    public void testDownloadFile() throws Exception {
        File downloadDir;
        File download;
        long startTime;
        long duration;
        
        //standard
        
        startTime = System.currentTimeMillis();
        download = Internet.downloadFile("https://github.com/ytdl-org/youtube-dl/releases/download/2021.05.16/youtube-dl.exe");
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration > 0);
        Assert.assertNotNull(download);
        Assert.assertTrue(download.exists());
        Assert.assertTrue(download.getName().endsWith(".download"));
        Assert.assertEquals(8172589, download.length());
        Filesystem.deleteFile(download);
        
        //location
        
        downloadDir = Filesystem.createTemporaryDirectory();
        download = new File(downloadDir, "youtube-dl.exe");
        startTime = System.currentTimeMillis();
        download = Internet.downloadFile("https://github.com/ytdl-org/youtube-dl/releases/download/2021.05.16/youtube-dl.exe", download);
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration > 0);
        Assert.assertNotNull(download);
        Assert.assertTrue(download.exists());
        Assert.assertEquals("youtube-dl.exe", download.getName());
        Assert.assertEquals(8172589, download.length());
        Filesystem.deleteDirectory(downloadDir);
        
        //"not connected"
        
        TestUtils.setField(Internet.class, "testHost", "n0t4w3bs1t3.com");
        Assert.assertEquals("n0t4w3bs1t3.com", TestUtils.getField(Internet.class, "testHost"));
        startTime = System.currentTimeMillis();
        download = Internet.downloadFile("https://github.com/ytdl-org/youtube-dl/releases/download/2021.05.16/youtube-dl.exe");
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration < (200 * 1.01));
        Assert.assertNull(download);
        TestUtils.setField(Internet.class, "testHost", Internet.DEFAULT_TEST_HOST);
        Assert.assertEquals(Internet.DEFAULT_TEST_HOST, TestUtils.getField(Internet.class, "testHost"));
        
        //invalid
        
        startTime = System.currentTimeMillis();
        download = Internet.downloadFile("n0t4w3bs1t3.com/download/file.txt");
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration < (200 * 1.01));
        Assert.assertNull(download);
        
        startTime = System.currentTimeMillis();
        download = Internet.downloadFile("");
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration < (200 * 1.01));
        Assert.assertNull(download);
        
        startTime = System.currentTimeMillis();
        download = Internet.downloadFile(null);
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration < (200 * 1.01));
        Assert.assertNull(download);
    }
    
    /**
     * JUnit test of parseHtmlJmpFunctions.
     *
     * @throws Exception When there is an exception.
     * @see Internet#parseHtmlJmpFunctions(Document)
     */
    @Test
    public void testParseHtmlJmpFunctions() throws Exception {
        Document html;
        Document parsed;
        
        //standard
        
        html = Jsoup.parse(Filesystem.readFileToString(new File(testResources, "test.html")));
        Assert.assertNotNull(html);
        parsed = Internet.parseHtmlJmpFunctions(html);
        Assert.assertNotNull(parsed);
        Assert.assertNotEquals(html.toString(), parsed.toString());
        
        final Pattern jslDhFunctionPattern = Pattern.compile("window\\.jsl\\.dh\\('(?<id>[^']+)',\\s*'(?<content>[^']+)'");
        final Pattern expandedPattern = Pattern.compile("<div\\sclass=\"xpdxpnd\"\\sdata-mh=\"-?\\d+\"\\sid=\"(?<id>[^\"]+)\"></div>");
        Assert.assertEquals(79, jslDhFunctionPattern.matcher(html.toString()).results().count());
        Assert.assertEquals(79, jslDhFunctionPattern.matcher(parsed.toString()).results().count());
        Assert.assertEquals(23, expandedPattern.matcher(html.toString()).results().count());
        Assert.assertEquals(0, expandedPattern.matcher(parsed.toString()).results().count());
        
        //invalid
        
        html = Jsoup.parse("");
        parsed = Internet.parseHtmlJmpFunctions(html);
        Assert.assertNotNull(parsed);
        Assert.assertEquals(StringUtility.removeWhiteSpace(html.toString()), StringUtility.removeWhiteSpace(parsed.toString()));
        
        TestUtils.assertException(NullPointerException.class, () ->
                Internet.parseHtmlJmpFunctions(null));
    }
    
    /**
     * JUnit test of openUrl.
     *
     * @throws Exception When there is an exception.
     * @see Internet#openUrl(String)
     */
    @Test
    public void testOpenUrl() throws Exception {
        Desktop mockDesktop = Mockito.mock(Desktop.class);
        PowerMockito.mockStatic(Desktop.class);
        PowerMockito.when(Desktop.isDesktopSupported()).thenReturn(true);
        PowerMockito.when(Desktop.getDesktop()).thenReturn(mockDesktop);
        
        //standard
        Internet.openUrl("https://www.google.com/search?q=cute+cats");
        Mockito.verify(mockDesktop).browse(ArgumentMatchers.eq(URI.create("https://www.google.com/search?q=cute+cats")));
        
        //unsupported
        PowerMockito.when(Desktop.isDesktopSupported()).thenReturn(false);
        Internet.openUrl("https://www.google.com/search?q=cute+cats");
        Mockito.verifyNoMoreInteractions(mockDesktop);
    }
    
    /**
     * JUnit test of logInternet.
     *
     * @throws Exception When there is an exception.
     * @see Internet#logInternet()
     */
    @Test
    public void testLogInternet() throws Exception {
        PowerMockito.mockStatic(CommonsLogging.class);
        Internet.logInternet();
        PowerMockito.verifyStatic(CommonsLogging.class);
        CommonsLogging.logInternet();
    }
    
}
