package MyF;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public Clip clip = null;
	public String NowPlay = "";
	public boolean bNowStop = true;
	
	public boolean BgmPlay(String filename) {
		boolean result = false;
		NowPlay = filename;
		File f = new File(filename);
		
		AudioInputStream ais = null;
		
		if(f.isFile()) {
			try {
				ais = AudioSystem.getAudioInputStream(f);
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.loop(-1);
				clip.start();
				result = true;
				bNowStop = false;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			result = false;
		}		
		return result;
	}
	public void BgmStop(boolean isStop) {
		if(clip != null) {
			if(isStop) {
				clip.stop();
				bNowStop = true;
			} else {
				if(clip.isRunning()) clip.stop();
				clip.start();
				bNowStop = false;
			}
		}
	}
	public void BgmClose() {
		if(clip != null) {
			clip.stop();
			clip.close();
			bNowStop = true;
		}
	}
	
	public boolean SoundPlay(String filename) {
		boolean result = false;
		File f = new File(filename);
		Clip clip = null;
		AudioInputStream ais = null;
		
		if(f.isFile()) {
			try {
				ais = AudioSystem.getAudioInputStream(f);
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();			
				result = true;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			result = false;
		}		
		return result;
	}
	

}
