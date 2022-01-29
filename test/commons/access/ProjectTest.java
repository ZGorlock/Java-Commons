/*
 * File:    ProjectTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.File;

import commons.io.speech.SpeechSynthesizer;
import commons.math.component.handler.math.BigComponentMathHandler;
import commons.object.string.StringUtility;
import commons.security.CryptoUtility;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Project.
 *
 * @see Project
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Project.class, Filesystem.class, TestUtils.AssertWrapper.class})
public class ProjectTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProjectTest.class);
    
    
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
     * @see Project#SOURCE_DIR
     * @see Project#TEST_DIR
     * @see Project#DATA_DIR
     * @see Project#RESOURCES_DIR
     * @see Project#TEST_RESOURCES_DIR
     * @see Project#OUTPUT_DIR
     * @see Project#SOURCE_CLASSES_DIR
     * @see Project#TEST_CLASSES_DIR
     * @see Project#LOG_DIR
     * @see Project#TMP_DIR
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("src", StringUtility.fileString(Project.SOURCE_DIR, false));
        Assert.assertEquals("test", StringUtility.fileString(Project.TEST_DIR, false));
        Assert.assertEquals("data", StringUtility.fileString(Project.DATA_DIR, false));
        Assert.assertEquals("resources", StringUtility.fileString(Project.RESOURCES_DIR, false));
        Assert.assertEquals("test-resources", StringUtility.fileString(Project.TEST_RESOURCES_DIR, false));
        Assert.assertEquals("bin", StringUtility.fileString(Project.OUTPUT_DIR, false));
        Assert.assertEquals("bin/classes", StringUtility.fileString(Project.SOURCE_CLASSES_DIR, false));
        Assert.assertEquals("bin/test-classes", StringUtility.fileString(Project.TEST_CLASSES_DIR, false));
        Assert.assertEquals("log", StringUtility.fileString(Project.LOG_DIR, false));
        Assert.assertEquals("tmp", StringUtility.fileString(Project.TMP_DIR, false));
    }
    
    /**
     * JUnit test of initializeProjectDirectories.
     *
     * @throws Exception When there is an exception.
     * @see Project#initializeProjectDirectories()
     */
    @Test
    public void testInitializeProjectDirectories() throws Exception {
        PowerMockito.mockStatic(Filesystem.class);
        
        //standard
        PowerMockito.when(Filesystem.class, "exists", ArgumentMatchers.any(File.class)).thenReturn(true);
        Project.initializeProjectDirectories();
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(10));
        Filesystem.exists(ArgumentMatchers.any(File.class));
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(1));
        Filesystem.clearDirectory(ArgumentMatchers.eq(Project.TMP_DIR));
        
        //clean
        PowerMockito.when(Filesystem.class, "exists", ArgumentMatchers.any(File.class)).thenReturn(false);
        PowerMockito.when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class)).thenReturn(true);
        Project.initializeProjectDirectories();
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(20));
        Filesystem.exists(ArgumentMatchers.any(File.class));
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(10));
        Filesystem.createDirectory(ArgumentMatchers.any(File.class));
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(2));
        Filesystem.clearDirectory(ArgumentMatchers.eq(Project.TMP_DIR));
        
        //failure
        PowerMockito.when(Filesystem.class, "exists", ArgumentMatchers.any(File.class)).thenReturn(false);
        PowerMockito.when(Filesystem.class, "createDirectory", ArgumentMatchers.any(File.class)).thenReturn(false);
        Project.initializeProjectDirectories();
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(21));
        Filesystem.exists(ArgumentMatchers.any(File.class));
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(11));
        Filesystem.createDirectory(ArgumentMatchers.any(File.class));
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.noMoreInteractions());
        Filesystem.clearDirectory(ArgumentMatchers.eq(Project.TMP_DIR));
    }
    
    /**
     * JUnit test of sourceDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#sourceDir(Class)
     */
    @Test
    public void testSourceDir() throws Exception {
        //standard
        Assert.assertEquals("src/commons/access",
                StringUtility.fileString(Project.sourceDir(Project.class), false));
        Assert.assertEquals("src/commons/security",
                StringUtility.fileString(Project.sourceDir(CryptoUtility.class), false));
        Assert.assertEquals("src/commons/io/speech",
                StringUtility.fileString(Project.sourceDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("src/commons/math/component/handler/math",
                StringUtility.fileString(Project.sourceDir(BigComponentMathHandler.class), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.sourceDir(null));
    }
    
    /**
     * JUnit test of testDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#testDir(Class)
     */
    @Test
    public void testTestDir() throws Exception {
        //standard
        Assert.assertEquals("test/commons/access",
                StringUtility.fileString(Project.testDir(Project.class), false));
        Assert.assertEquals("test/commons/security",
                StringUtility.fileString(Project.testDir(CryptoUtility.class), false));
        Assert.assertEquals("test/commons/io/speech",
                StringUtility.fileString(Project.testDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("test/commons/math/component/handler/math",
                StringUtility.fileString(Project.testDir(BigComponentMathHandler.class), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.testDir(null));
    }
    
    /**
     * JUnit test of dataDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#dataDir(Class, String)
     * @see Project#dataDir(Class)
     */
    @Test
    public void testDataDir() throws Exception {
        //standard
        Assert.assertEquals("data/commons/access/Project",
                StringUtility.fileString(Project.dataDir(Project.class), false));
        Assert.assertEquals("data/commons/security/CryptoUtility",
                StringUtility.fileString(Project.dataDir(CryptoUtility.class), false));
        Assert.assertEquals("data/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.dataDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("data/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.dataDir(BigComponentMathHandler.class), false));
        
        //prefix
        Assert.assertEquals("data/test/dir1/dir2/commons/access/Project",
                StringUtility.fileString(Project.dataDir(Project.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("data/test/dir1/dir2/commons/security/CryptoUtility",
                StringUtility.fileString(Project.dataDir(CryptoUtility.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("data/test/dir1/dir2/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.dataDir(SpeechSynthesizer.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("data/test/dir1/dir2/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.dataDir(BigComponentMathHandler.class, "test\\dir1/dir2"), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.dataDir(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.dataDir(Project.class, null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.dataDir(null, null));
    }
    
    /**
     * JUnit test of resourcesDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#resourcesDir(Class, String)
     * @see Project#resourcesDir(Class)
     */
    @Test
    public void testResourcesDir() throws Exception {
        //standard
        Assert.assertEquals("resources/commons/access/Project",
                StringUtility.fileString(Project.resourcesDir(Project.class), false));
        Assert.assertEquals("resources/commons/security/CryptoUtility",
                StringUtility.fileString(Project.resourcesDir(CryptoUtility.class), false));
        Assert.assertEquals("resources/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.resourcesDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("resources/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.resourcesDir(BigComponentMathHandler.class), false));
        
        //prefix
        Assert.assertEquals("resources/test/dir1/dir2/commons/access/Project",
                StringUtility.fileString(Project.resourcesDir(Project.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("resources/test/dir1/dir2/commons/security/CryptoUtility",
                StringUtility.fileString(Project.resourcesDir(CryptoUtility.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("resources/test/dir1/dir2/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.resourcesDir(SpeechSynthesizer.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("resources/test/dir1/dir2/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.resourcesDir(BigComponentMathHandler.class, "test\\dir1/dir2"), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.resourcesDir(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.resourcesDir(Project.class, null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.resourcesDir(null, null));
    }
    
    /**
     * JUnit test of testResourcesDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#testResourcesDir(Class, String)
     * @see Project#testResourcesDir(Class)
     */
    @Test
    public void testTestResourcesDir() throws Exception {
        //standard
        Assert.assertEquals("test-resources/commons/access/Project",
                StringUtility.fileString(Project.testResourcesDir(Project.class), false));
        Assert.assertEquals("test-resources/commons/security/CryptoUtility",
                StringUtility.fileString(Project.testResourcesDir(CryptoUtility.class), false));
        Assert.assertEquals("test-resources/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.testResourcesDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("test-resources/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.testResourcesDir(BigComponentMathHandler.class), false));
        
        //prefix
        Assert.assertEquals("test-resources/test/dir1/dir2/commons/access/Project",
                StringUtility.fileString(Project.testResourcesDir(Project.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("test-resources/test/dir1/dir2/commons/security/CryptoUtility",
                StringUtility.fileString(Project.testResourcesDir(CryptoUtility.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("test-resources/test/dir1/dir2/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.testResourcesDir(SpeechSynthesizer.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("test-resources/test/dir1/dir2/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.testResourcesDir(BigComponentMathHandler.class, "test\\dir1/dir2"), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.testResourcesDir(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.testResourcesDir(Project.class, null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.testResourcesDir(null, null));
    }
    
    /**
     * JUnit test of sourceClassesDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#sourceClassesDir(Class)
     */
    @Test
    public void testSourceClassesDir() throws Exception {
        //standard
        Assert.assertEquals("bin/classes/commons/access",
                StringUtility.fileString(Project.sourceClassesDir(Project.class), false));
        Assert.assertEquals("bin/classes/commons/security",
                StringUtility.fileString(Project.sourceClassesDir(CryptoUtility.class), false));
        Assert.assertEquals("bin/classes/commons/io/speech",
                StringUtility.fileString(Project.sourceClassesDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("bin/classes/commons/math/component/handler/math",
                StringUtility.fileString(Project.sourceClassesDir(BigComponentMathHandler.class), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.sourceClassesDir(null));
    }
    
    /**
     * JUnit test of testClassesDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#testClassesDir(Class)
     */
    @Test
    public void testTestClassesDir() throws Exception {
        //standard
        Assert.assertEquals("bin/test-classes/commons/access",
                StringUtility.fileString(Project.testClassesDir(Project.class), false));
        Assert.assertEquals("bin/test-classes/commons/security",
                StringUtility.fileString(Project.testClassesDir(CryptoUtility.class), false));
        Assert.assertEquals("bin/test-classes/commons/io/speech",
                StringUtility.fileString(Project.testClassesDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("bin/test-classes/commons/math/component/handler/math",
                StringUtility.fileString(Project.testClassesDir(BigComponentMathHandler.class), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.testClassesDir(null));
    }
    
    /**
     * JUnit test of logDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#logDir(Class, String)
     * @see Project#logDir(Class)
     */
    @Test
    public void testLogDir() throws Exception {
        //standard
        Assert.assertEquals("log/commons/access/Project",
                StringUtility.fileString(Project.logDir(Project.class), false));
        Assert.assertEquals("log/commons/security/CryptoUtility",
                StringUtility.fileString(Project.logDir(CryptoUtility.class), false));
        Assert.assertEquals("log/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.logDir(SpeechSynthesizer.class), false));
        Assert.assertEquals("log/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.logDir(BigComponentMathHandler.class), false));
        
        //prefix
        Assert.assertEquals("log/test/dir1/dir2/commons/access/Project",
                StringUtility.fileString(Project.logDir(Project.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("log/test/dir1/dir2/commons/security/CryptoUtility",
                StringUtility.fileString(Project.logDir(CryptoUtility.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("log/test/dir1/dir2/commons/io/speech/SpeechSynthesizer",
                StringUtility.fileString(Project.logDir(SpeechSynthesizer.class, "test\\dir1/dir2"), false));
        Assert.assertEquals("log/test/dir1/dir2/commons/math/component/handler/math/BigComponentMathHandler",
                StringUtility.fileString(Project.logDir(BigComponentMathHandler.class, "test\\dir1/dir2"), false));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Project.logDir(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.logDir(Project.class, null));
        TestUtils.assertException(NullPointerException.class, () ->
                Project.logDir(null, null));
    }
    
    /**
     * JUnit test of classDir.
     *
     * @throws Exception When there is an exception.
     * @see Project#classDir(File, String, Class, boolean)
     */
    @Test
    public void testClassDir() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        //standard
        Assert.assertEquals(String.join(File.separator, "src", "commons", "access", "Project"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.SOURCE_DIR, "", Project.class, true).toString());
        Assert.assertEquals(String.join(File.separator, "test", "commons", "access"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.TEST_DIR, "", Project.class, false).toString());
        Assert.assertEquals(String.join(File.separator, "data", "commons", "security", "CryptoUtility"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.DATA_DIR, "", CryptoUtility.class, true).toString());
        Assert.assertEquals(String.join(File.separator, "resources", "test", "dir1", "dir2", "commons", "security", "CryptoUtility"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.RESOURCES_DIR, "test\\dir1/dir2", CryptoUtility.class, true).toString());
        Assert.assertEquals(String.join(File.separator, "test-resources", "test", "dir1", "dir2", "commons", "security"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.TEST_RESOURCES_DIR, "test\\dir1/dir2", CryptoUtility.class, false).toString());
        Assert.assertEquals(String.join(File.separator, "bin", "commons", "io", "speech"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.OUTPUT_DIR, "", SpeechSynthesizer.class, false).toString());
        Assert.assertEquals(String.join(File.separator, "bin", "classes", "test", "dir1", "dir2", "commons", "io", "speech", "SpeechSynthesizer"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.SOURCE_CLASSES_DIR, "test\\dir1/dir2", SpeechSynthesizer.class, true).toString());
        Assert.assertEquals(String.join(File.separator, "bin", "test-classes", "test", "dir1", "dir2", "commons", "io", "speech"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.TEST_CLASSES_DIR, "test\\dir1/dir2", SpeechSynthesizer.class, false).toString());
        Assert.assertEquals(String.join(File.separator, "log", "commons", "math", "component", "handler", "math", "BigComponentMathHandler"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.LOG_DIR, "", BigComponentMathHandler.class, true).toString());
        Assert.assertEquals(String.join(File.separator, "tmp", "commons", "math", "component", "handler", "math"),
                TestUtils.invokeMethod(Project.class, "classDir", Project.TMP_DIR, "", BigComponentMathHandler.class, false).toString());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(Project.class, "classDir", null, "", Project.class, true));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(Project.class, "classDir", Project.SOURCE_DIR, null, Project.class, true));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(Project.class, "classDir", Project.SOURCE_DIR, "", null, true));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(Project.class, "classDir", null, null, null, true));
    }
    
}
