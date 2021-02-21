/*
 * File:    AudioUtility.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

/**
 * Handles audio operations.
 */
public final class AudioUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(AudioUtility.class);
    
    
    //Functions
    
    //TODO
    
    /**
     * Plays an mp3 file and waits until it is done playing.
     *
     * @param mp3 The mp3 file to play.
     * @return Whether the mp3 file was successfully played or not.
     */
    public static boolean playMp3(File mp3) {
        AtomicBoolean success = new AtomicBoolean(true);
        AtomicBoolean done = new AtomicBoolean(false);
        
        AudioPlayerComponent component = new AudioPlayerComponent();
        MediaPlayer player = component.mediaPlayer();
        player.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                done.set(true);
            }
            
            @Override
            public void error(MediaPlayer mediaPlayer) {
                success.set(false);
                done.set(true);
            }
            
        });
        
        player.audio().setVolume(100);
        player.media().play(mp3.getAbsolutePath());
        
        while (!done.get()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
        
        player.submit(player::release);
        return success.get();
    }
    
}
