/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zelda.java2d;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This manager is responsible for the playing, stopping and caching of sounds within Spark.  You would
 * use this manager when you wish to play audio without adding too much memory overhead.
 */
public class SoundManager {

    private final Map<String,AudioClip> clipMap = new HashMap<>();
    private final Map<URL,AudioClip> fileMap = new HashMap<>();

    /**
     * Default constructor
     */
    public SoundManager() {
    }

    /**
     * Plays an audio clip of local clips deployed with Spark.
     *
     * @param clip the properties value found in la.properties.
     * @return the AudioClip found. If no audio clip was found, returns null.
     */
    public AudioClip getClip(String clip) {
        if (!clipMap.containsKey(clip)) {
            // Add new clip
            final AudioClip newClip = loadClipForURL(clip);
            if (newClip != null) {
                clipMap.put(clip, newClip);
            }
        }

        return clipMap.get(clip);
    }

    /**
     * Plays an AudioClip.
     *
     * @param clip the audioclip to play.
     */
    public void playClip(final AudioClip clip) {

        final Runnable playThread = () -> {
            try {
                clip.play();
            }
            catch (Exception ex) {
                System.err.println("Unable to load sound file");
            }
        };

    }

    /**
     * Plays an AudioClip.
     *
     * @param clipToPlay the properties value found in la.properties.
     * @return 
     */
    public AudioClip playClip(String clipToPlay) {
        AudioClip clip = getClip(clipToPlay);
        try {
            clip.play();
        }
        catch (Exception ex) {
            System.err.println("Unable to load sound file");
        }
        return clip;
    }

    /**
     * Plays a sound file.
     *
     * @param soundFile the File object representing the wav file.
     */
    public void playClip(final File soundFile) {
        final Runnable playThread = () -> {
            try {
                final URL url = soundFile.toURI().toURL();
                AudioClip ac = fileMap.get(url);
                if (ac == null) {
                    ac = Applet.newAudioClip(url);
                    fileMap.put(url, ac);
                }
                ac.play();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        };

    }
    
    public static  URL getURL(String name) {
        return SoundManager.class.getResource("/" + name);
    }

    /**
     * Creates an AudioClip from a URL.
     *
     * @param clipOfURL the url of the AudioClip to play. We only support .wav files at the moment.
     * @return the AudioFile found. If no audio file  was found,returns null.
     */
    private AudioClip loadClipForURL(String clipOfURL) {
        final URL url = getURL(clipOfURL);
        AudioClip clip = null;

        try {
            clip = Applet.newAudioClip(url);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return clip;
    }


}