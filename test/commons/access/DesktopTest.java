/*
 * File:    DesktopTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.File;
import java.net.URI;

import commons.test.TestAccess;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Desktop.
 *
 * @see Desktop
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Desktop.class})
public class DesktopTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DesktopTest.class);
    
    
    //Static Fields
    
    /**
     * The DesktopWrapper class.
     */
    private static Class<?> DesktopWrapper = TestAccess.getClass(Desktop.class, "DesktopWrapper");
    
    
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
    @Before
    public void setup() throws Exception {
        PowerMockito.spy(Desktop.class);
        
        PowerMockito.mockStatic(DesktopWrapper);
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
     * JUnit test of isDesktopSupported.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#isDesktopSupported()
     */
    @Test
    public void testIsDesktopSupported() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.isDesktopSupported());
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.isDesktopSupported());
    }
    
    /**
     * JUnit test of open.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#open(File)
     */
    @Test
    public void testOpen() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        final File mockFile = Mockito.mock(File.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.open(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("open", ArgumentMatchers.eq(mockFile));
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.open(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("open", ArgumentMatchers.any());
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        
        //invalid
        Assert.assertFalse(Desktop.open(null));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("open", ArgumentMatchers.any());
    }
    
    /**
     * JUnit test of edit.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#edit(File)
     */
    @Test
    public void testEdit() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        final File mockFile = Mockito.mock(File.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.edit(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("edit", ArgumentMatchers.eq(mockFile));
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.edit(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("edit", ArgumentMatchers.any());
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        
        //invalid
        Assert.assertFalse(Desktop.edit(null));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("edit", ArgumentMatchers.any());
    }
    
    /**
     * JUnit test of print.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#print(File)
     */
    @Test
    public void testPrint() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        final File mockFile = Mockito.mock(File.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.print(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("print", ArgumentMatchers.eq(mockFile));
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.print(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("print", ArgumentMatchers.any());
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        
        //invalid
        Assert.assertFalse(Desktop.print(null));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("print", ArgumentMatchers.any());
    }
    
    /**
     * JUnit test of browse.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#browse(File)
     */
    @Test
    public void testBrowse() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        final File mockFile = Mockito.mock(File.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.browse(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("browse", ArgumentMatchers.eq(mockFile));
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.browse(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("browse", ArgumentMatchers.any());
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        
        //invalid
        Assert.assertFalse(Desktop.browse(null));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("browse", ArgumentMatchers.any());
    }
    
    /**
     * JUnit test of trash.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#trash(File)
     */
    @Test
    public void testTrash() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        final File mockFile = Mockito.mock(File.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.trash(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("trash", ArgumentMatchers.eq(mockFile));
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.trash(mockFile));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("trash", ArgumentMatchers.any());
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        
        //invalid
        Assert.assertFalse(Desktop.trash(null));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("trash", ArgumentMatchers.any());
    }
    
    /**
     * JUnit test of navigate.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#navigate(URI)
     */
    @Test
    public void testNavigate() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        final URI mockUri = PowerMockito.mock(URI.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.navigate(mockUri));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("navigate", ArgumentMatchers.eq(mockUri));
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.navigate(mockUri));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("navigate", ArgumentMatchers.any());
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        
        //invalid
        Assert.assertFalse(Desktop.navigate(null));
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("navigate", ArgumentMatchers.any());
    }
    
    /**
     * JUnit test of mail.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#mail()
     */
    @Test
    public void testMail() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.mail());
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("mail");
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.mail());
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("mail");
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
    }
    
    /**
     * JUnit test of help.
     *
     * @throws Exception When there is an exception.
     * @see Desktop#help()
     */
    @Test
    public void testHelp() throws Exception {
        final java.awt.Desktop mockDesktop = PowerMockito.mock(java.awt.Desktop.class);
        
        //standard
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
        Assert.assertTrue(Desktop.help());
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.times(1))
                .invoke("help");
        
        //unsupported
        TestAccess.setFieldValue(Desktop.class, "desktop", null);
        Assert.assertFalse(Desktop.help());
        PowerMockito.verifyPrivate(DesktopWrapper, VerificationModeFactory.noMoreInteractions())
                .invoke("help");
        TestAccess.setFieldValue(Desktop.class, "desktop", mockDesktop);
    }
    
    /**
     * JUnit test of DesktopWrapper.
     *
     * @throws Exception When there is an exception.
     * @see Desktop.DesktopWrapper
     */
    @Test
    public void testDesktopWrapper() throws Exception {
        //methods
        TestUtils.assertMethodExists(DesktopWrapper, "open", File.class);
        TestUtils.assertMethodExists(DesktopWrapper, "edit", File.class);
        TestUtils.assertMethodExists(DesktopWrapper, "print", File.class);
        TestUtils.assertMethodExists(DesktopWrapper, "browse", File.class);
        TestUtils.assertMethodExists(DesktopWrapper, "trash", File.class);
        TestUtils.assertMethodExists(DesktopWrapper, "navigate", URI.class);
        TestUtils.assertMethodExists(DesktopWrapper, "mail");
        TestUtils.assertMethodExists(DesktopWrapper, "help");
    }
    
}
