import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class Animation{
    public static synchronized void playSound(final String url) {
	new Thread(new Runnable() {
		// The wrapper thread is unnecessary, unless it blocks on the
		// Clip finishing; see comments.
		public void run() {
		    try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
										       MainClass.class.getResourceAsStream("/path/to/sounds/" + url));
			clip.open(inputStream);
			clip.start(); 
		    } catch (Exception e) {
			System.err.println(e.getMessage());
		    }
		}
	    }).start();
    }
    public static void main(String[] args){
	playSound("https://www.youtube.com/watch?v=0MKLBh0fBbg");
    }
	
}
