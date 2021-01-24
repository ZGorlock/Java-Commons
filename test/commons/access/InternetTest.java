/*
 * File:    InternetTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 */

package commons.access;

import java.io.File;

import commons.log.CommonsLogging;
import org.jsoup.nodes.Document;
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

/**
 * JUnit test of Internet.
 *
 * @see Internet
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({Internet.class, CommonsLogging.class})
public class InternetTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(InternetTest.class);
    
    
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
     * @see Internet#URL_ENCODING
     * @see Internet#DEFAULT_LOG_INTERNET
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("UTF-8", Internet.URL_ENCODING);
        Assert.assertFalse(Internet.DEFAULT_LOG_INTERNET);
    }
    
    /**
     * JUnit test of isOnline.
     *
     * @throws Exception When there is an exception.
     * @see Internet#isOnline()
     */
    @Test
    public void testIsOnline() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of encodeUrl.
     *
     * @throws Exception When there is an exception.
     * @see Internet#encodeUrl(String)
     */
    @Test
    public void testEncodeUrl() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHtml.
     *
     * @throws Exception When there is an exception.
     * @see Internet#getHtml(String)
     */
    @Test
    public void testGetHtml() throws Exception {
        //TODO
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
        //TODO
    }
    
    /**
     * JUnit test of parseHtmlJmpFunctions.
     *
     * @throws Exception When there is an exception.
     * @see Internet#parseHtmlJmpFunctions(Document)
     */
    @Test
    public void testParseHtmlJmpFunctions() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of openUrl.
     *
     * @throws Exception When there is an exception.
     * @see Internet#openUrl(String)
     */
    @Test
    public void testOpenUrl() throws Exception {
        //TODO
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
