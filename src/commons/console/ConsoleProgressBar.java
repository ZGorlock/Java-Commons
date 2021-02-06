/*
 * File:    ConsoleProgressBar.java
 * Package: commons.console
 * Author:  Zachary Gill
 */

package commons.console;

import java.util.concurrent.TimeUnit;

import commons.math.BoundUtility;
import commons.string.StringUtility;
import commons.time.DateTimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A progress bar for the console.
 */
public class ConsoleProgressBar {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ConsoleProgressBar.class);
    
    
    //Constants
    
    /**
     * The default width of the progress bar in characters.
     */
    public static final int DEFAULT_PROGRESS_BAR_WIDTH = 32;
    
    /**
     * The default value of the flag indicating whether or not to automatically print the progress bar after an update.
     */
    public static final boolean DEFAULT_PROGRESS_BAR_AUTO_PRINT = true;
    
    /**
     * The minimum number of milliseconds that must pass before an update can occur.
     */
    public static final long PROGRESS_BAR_MINIMUM_UPDATE_DELAY = 200;
    
    
    //Fields
    
    /**
     * The title to display for the progress bar.
     */
    private String title;
    
    /**
     * The total progress of the progress bar.
     */
    private long total;
    
    /**
     * The current progress of the progress bar.
     */
    private long progress = 0;
    
    /**
     * The currently completed progress of the progress bar.
     */
    private long current = 0;
    
    /**
     * The completed progress of the progress bar at the time of the last update.
     */
    private long previous = 0;
    
    /**
     * The initial progress of the progress bar.
     */
    private long initialProgress = 0;
    
    /**
     * The initial duration of the progress bar in seconds.
     */
    private long initialDuration = 0;
    
    /**
     * The time of the current update of the progress bar.
     */
    private long currentUpdate = 0;
    
    /**
     * The time of the previous update of the progress bar.
     */
    private long previousUpdate = 0;
    
    /**
     * The time the progress bar was updated for the firstUpdate time.
     */
    private long firstUpdate = 0;
    
    /**
     * The width of the bar in the progress bar.
     */
    private int width;
    
    /**
     * The units of the progress bar.
     */
    private String units;
    
    /**
     * A flag indicating whether or not to automatically print the progress bar after an update.
     */
    private boolean autoPrint;
    
    /**
     * The current progress bar.
     */
    private String progressBar = "";
    
    /**
     * A flag indicating whether there was an update to the progress bar or not.
     */
    private boolean update = false;
    
    
    //Constructors
    
    /**
     * Creates a new ConsoleProgressBar object.
     *
     * @param title     The title to display for the progress bar.
     * @param total     The total size of the progress bar.
     * @param width     The with of the bar in the progress bar.
     * @param units     The units of the progress bar.
     * @param autoPrint Whether or not to automatically print the progress bar after an update.
     */
    public ConsoleProgressBar(String title, long total, int width, String units, boolean autoPrint) {
        this.title = title;
        this.total = total;
        this.width = width;
        this.units = units;
        this.autoPrint = autoPrint;
    }
    
    /**
     * Creates a new ConsoleProgressBar object.
     *
     * @param title The title to display for the progress bar.
     * @param total The total size of the progress bar.
     * @param width The with of the bar in the progress bar.
     * @param units The units of the progress bar.
     * @see #ConsoleProgressBar(String, long, int, String, boolean)
     */
    public ConsoleProgressBar(String title, long total, int width, String units) {
        this(title, total, width, units, DEFAULT_PROGRESS_BAR_AUTO_PRINT);
    }
    
    /**
     * Creates a new ConsoleProgressBar object.
     *
     * @param title The title to display for the progress bar.
     * @param total The total size of the progress bar.
     * @param units The units of the progress bar.
     * @see #ConsoleProgressBar(String, long, int, String)
     */
    public ConsoleProgressBar(String title, long total, String units) {
        this(title, total, DEFAULT_PROGRESS_BAR_WIDTH, units);
    }
    
    /**
     * Creates a new ConsoleProgressBar object.
     *
     * @param title The title to display for the progress bar.
     * @param total The total size of the progress bar.
     * @param width The with of the bar in the progress bar.
     * @see #ConsoleProgressBar(String, long, int, String)
     */
    public ConsoleProgressBar(String title, long total, int width) {
        this(title, total, width, "");
    }
    
    /**
     * Creates a new ConsoleProgressBar object.
     *
     * @param title The title to display for the progress bar.
     * @param total The total size of the progress bar.
     * @see #ConsoleProgressBar(String, long, int, String)
     */
    public ConsoleProgressBar(String title, long total) {
        this(title, total, DEFAULT_PROGRESS_BAR_WIDTH, "");
    }
    
    /**
     * Private constructor for a new ConsoleProgressBar object.
     */
    private ConsoleProgressBar() {
    }
    
    
    //Methods
    
    /**
     * Builds the progress bar.<br>
     * This must be displayed with print(), not println().
     *
     * @return The progress bar.
     * @see #getPercentageString()
     * @see #getBarString()
     * @see #getRatioString()
     * @see #getTimeRemainingString()
     */
    @SuppressWarnings("HardcodedLineSeparator")
    public String get() {
        if (update) {
            progressBar = '\r' + StringUtility.spaces(width + ((total + units).length() * 2) + 30) + '\r' +
                    getPercentageString() + ' ' +
                    getBarString() + ' ' +
                    getRatioString() + " - " +
                    getTimeRemainingString();
        }
        
        return progressBar;
    }
    
    /**
     * Updates the progress bar.<br>
     * If the time between updates is less than PROGRESS_BAR_MINIMUM_UPDATE_DELAY then the update will not take place until called again after the delay.
     *
     * @param newProgress The new progress of the progress bar.
     * @param autoPrint   Whether or not to automatically print the progress bar after an update.
     * @return Whether the progress bar was updated or not.
     */
    private synchronized boolean update(long newProgress, boolean autoPrint) {
        if (isComplete()) {
            return false;
        }
        
        if (firstUpdate == 0) {
            if (!title.isEmpty()) {
                System.out.println(getTitleString());
                System.out.flush();
                System.err.flush();
            }
            firstUpdate = System.nanoTime();
        }
        
        progress = BoundUtility.truncateNum(newProgress, 0, total).longValue();
        
        boolean needsUpdate = (Math.abs(System.nanoTime() - currentUpdate) >= TimeUnit.MILLISECONDS.toNanos(PROGRESS_BAR_MINIMUM_UPDATE_DELAY));
        if (needsUpdate || (progress == total)) {
            previous = current;
            current = progress;
            
            previousUpdate = currentUpdate;
            currentUpdate = System.nanoTime();
            
            update = true;
        }
        
        if (update && autoPrint) {
            print();
        }
        return update;
    }
    
    /**
     * Updates the progress bar.<br>
     * If the time between updates is less than PROGRESS_BAR_MINIMUM_UPDATE_DELAY then the update will not take place until called again after the delay.
     *
     * @param newProgress The new progress of the progress bar.
     * @return Whether the progress bar was updated or not.
     * @see #update(long, boolean)
     */
    public synchronized boolean update(long newProgress) {
        return update(newProgress, autoPrint);
    }
    
    /**
     * Adds one to the current progress.
     *
     * @return Whether the progress bar was updated or not.
     * @see #update(long)
     */
    public synchronized boolean addOne() {
        return update(progress + 1);
    }
    
    /**
     * Prints the progress bar to the console.
     *
     * @see #get()
     */
    public synchronized void print() {
        String bar = get();
        System.out.print(bar);
        System.out.flush();
        System.err.flush();
        update = false;
    }
    
    /**
     * Calculates the ratio of the progress bar.
     *
     * @return The ratio of the progress bar.
     */
    public double getRatio() {
        return ((total <= 0) || (current > total)) ? 1 :
               (current < 0) ? 0 :
               (double) current / total;
    }
    
    /**
     * Calculates the percentage of the progress bar.
     *
     * @return The percentage of the progress bar.
     * @see #getRatio()
     */
    public int getPercentage() {
        return (int) (getRatio() * 100);
    }
    
    /**
     * Calculates the last recorded speed of the progress bar.
     *
     * @return The last recorded speed of the progress bar in units per second.
     */
    public double getLastSpeed() {
        double recentTime = (double) Math.max((currentUpdate - previousUpdate), 0) / TimeUnit.SECONDS.toNanos(1);
        long recentProgress = Math.max((current - previous), 0);
        
        return ((recentTime == 0) || (recentProgress == 0) || (current < 0) || (previous < 0) || (previousUpdate <= 0) || (currentUpdate <= 0)) ? 0 :
               (recentProgress / recentTime);
    }
    
    /**
     * Calculates the average speed of the progress bar.
     *
     * @return The average speed of the progress bar in units per second.
     */
    public double getAverageSpeed() {
        double totalTime = (double) Math.max((currentUpdate - firstUpdate), 0) / TimeUnit.SECONDS.toNanos(1);
        
        return ((totalTime == 0) || (current <= 0) || (firstUpdate < 0) || (currentUpdate <= 0)) ? 0 :
               (current / totalTime);
    }
    
    /**
     * Calculates the total duration of the progress bar.
     *
     * @return The total duration of the progress bar in nanoseconds.
     */
    public long getTotalDuration() {
        long totalDuration = Math.max((currentUpdate - firstUpdate), 0) +
                (Math.max(initialDuration, 0) * TimeUnit.SECONDS.toNanos(1));
        
        return ((currentUpdate <= 0) || (firstUpdate < 0)) ? 0 :
               totalDuration;
    }
    
    /**
     * Estimates the time remaining in seconds.
     *
     * @return The estimated time remaining in seconds.
     */
    public long getTimeRemaining() {
        long remainingProgress = Math.max((total - current), 0);
        long totalProgress = Math.max((current - Math.max(initialProgress, 0)), 0);
        long totalTime = Math.max((currentUpdate - firstUpdate), 0);
        
        return ((totalProgress == 0) || (totalTime == 0) || (current <= 0) || (currentUpdate <= 0) || (firstUpdate < 0)) ? Long.MAX_VALUE :
               TimeUnit.NANOSECONDS.toSeconds((long) (((double) remainingProgress / totalProgress) * totalTime));
    }
    
    /**
     * Determines if the progress bar is complete or not.
     *
     * @return Whether the progress bar is complete or not.
     */
    public boolean isComplete() {
        return (current >= total);
    }
    
    /**
     * Completes the progress bar.
     *
     * @param printTime      Whether or not to print the final time after the progress bar.
     * @param additionalInfo Additional info to print at the end of the progress bar.
     * @see #get()
     */
    public void complete(boolean printTime, String additionalInfo) {
        update(total, false);
        String completeProgressBar = get();
        
        if (printTime) {
            long duration = TimeUnit.NANOSECONDS.toMillis(getTotalDuration());
            String durationString = DateTimeUtility.durationToDurationString(duration, false, false, true);
            completeProgressBar += " (" + durationString + ')';
        }
        if (!additionalInfo.isEmpty()) {
            completeProgressBar += " - " + additionalInfo;
        }
        
        System.out.println(completeProgressBar);
        System.out.flush();
        System.err.flush();
        update = false;
    }
    
    /**
     * Completes the progress bar.
     *
     * @param printTime Whether or not to print the final time after the progress bar.
     * @see #complete(boolean, String)
     */
    public void complete(boolean printTime) {
        complete(printTime, "");
    }
    
    /**
     * Completes the progress bar.
     *
     * @see #complete(boolean)
     */
    public void complete() {
        complete(true);
    }
    
    /**
     * Builds the title string for the progress bar.
     *
     * @return The title string.
     * @see #getTitle()
     */
    public String getTitleString() {
        return Console.ConsoleEffect.CYAN.apply(getTitle() + ": ");
    }
    
    /**
     * Builds the percentage string for the progress bar.
     *
     * @return The percentage string.
     * @see #getPercentage()
     */
    public String getPercentageString() {
        int percentage = getPercentage();
        String percentageString = StringUtility.padLeft(String.valueOf(percentage), 3);
        Console.ConsoleEffect color = ((percentage == 100) ? Console.ConsoleEffect.CYAN : Console.ConsoleEffect.GREEN);
        
        return color.apply(percentageString) + '%';
    }
    
    /**
     * Builds the progress bar string for the progress bar.
     *
     * @return The progress bar string.
     * @see #getRatio()
     */
    public String getBarString() {
        double ratio = getRatio();
        int completed = Math.max((int) ((double) width * ratio), 0);
        int remaining = Math.max((width - completed - 1), 0);
        Console.ConsoleEffect color = ((completed == width) ? Console.ConsoleEffect.CYAN : Console.ConsoleEffect.GREEN);
        
        String bar = "=".repeat(completed) + ((completed != width) ? '>' : "") + " ".repeat(remaining);
        return '[' + color.apply(bar) + ']';
    }
    
    /**
     * Builds the ratio string for the progress bar.
     *
     * @return The ratio string.
     */
    public String getRatioString() {
        String formattedCurrent = StringUtility.padLeft(String.valueOf(Math.max(Math.min(current, total), 0)), String.valueOf(total).length());
        Console.ConsoleEffect color = ((current >= total) ? Console.ConsoleEffect.CYAN : Console.ConsoleEffect.GREEN);
        
        return color.apply(formattedCurrent) + units + '/' +
                Console.ConsoleEffect.CYAN.apply(String.valueOf(total)) + units;
    }
    
    /**
     * Builds the time remaining string for the progress bar.
     *
     * @return The time remaining string.
     * @see #getTimeRemaining()
     */
    public String getTimeRemainingString() {
        long time = getTimeRemaining();
        String durationStamp = DateTimeUtility.durationToDurationStamp(TimeUnit.SECONDS.toMillis(time), false, false);
        
        return (current >= total) ? Console.ConsoleEffect.CYAN.apply("Complete") :
               (time == Long.MAX_VALUE) ? "ETA: --:--:--" :
               "ETA: " + durationStamp;
    }
    
    
    //Getters
    
    /**
     * Returns the title of the progress bar.
     *
     * @return The title of the progress bar.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Returns the total progress of the progress bar.
     *
     * @return The total progress of the progress bar.
     */
    public long getTotal() {
        return total;
    }
    
    /**
     * Returns the progress of the progress bar.
     *
     * @return The progress of the progress bar.
     */
    public long getProgress() {
        return progress;
    }
    
    /**
     * Returns the currently completed progress of the progress bar.
     *
     * @return The currently completed progress of the progress bar.
     */
    public long getCurrent() {
        return current;
    }
    
    /**
     * Returns the completed progress of the progress bar at the time of the last update.
     *
     * @return The completed progress of the progress bar at the time of the last update.
     */
    public long getPrevious() {
        return previous;
    }
    
    /**
     * Returns the initial progress of the progress bar.
     *
     * @return The initial progress of the progress bar.
     */
    public long getInitialProgress() {
        return initialProgress;
    }
    
    /**
     * Returns the initial duration of the progress bar in seconds.
     *
     * @return The initial duration of the progress bar in seconds.
     */
    public long getInitialDuration() {
        return initialDuration;
    }
    
    /**
     * Returns the time of the current update of the progress bar.
     *
     * @return The time of the current update of the progress bar.
     */
    public long getCurrentUpdate() {
        return currentUpdate;
    }
    
    /**
     * Returns the time of the previous update of the progress bar.
     *
     * @return The time of the previous update of the progress bar.
     */
    public long getPreviousUpdate() {
        return previousUpdate;
    }
    
    /**
     * Returns the time the progress bar was updated for the firstUpdate time.
     *
     * @return The time the progress bar was updated for the firstUpdate time.
     */
    public long getFirstUpdate() {
        return firstUpdate;
    }
    
    /**
     * Returns the width of the bar in the progress bar.
     *
     * @return The width of the bar in the progress bar.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the units of the progress bar.
     *
     * @return The units of the progress bar.
     */
    public String getUnits() {
        return units;
    }
    
    /**
     * Returns the flag indicating whether or not to automatically print the progress bar after an update.
     *
     * @return The flag indicating whether or not to automatically print the progress bar after an update.
     */
    public boolean getAutoPrint() {
        return autoPrint;
    }
    
    
    //Setters
    
    /**
     * Sets the initial progress of the progress bar.
     *
     * @param initialProgress The initial progress of the progress bar.
     */
    public void setInitialProgress(long initialProgress) {
        this.initialProgress = initialProgress;
    }
    
    /**
     * Sets the initial duration of the progress bar in seconds.
     *
     * @param initialDuration The initial duration of the progress bar in seconds.
     */
    public void setInitialDuration(long initialDuration) {
        this.initialDuration = initialDuration;
    }
    
    /**
     * Sets the flag indicating whether or not to automatically print the progress bar after an update.
     *
     * @param autoPrint The flag indicating whether or not to automatically print the progress bar after an update.
     */
    public void setAutoPrint(boolean autoPrint) {
        this.autoPrint = autoPrint;
    }
    
}
