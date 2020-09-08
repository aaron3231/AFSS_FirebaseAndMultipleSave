package sound;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/********************************************************/
/* @author yusiang                                      */
/* ?��此物件被�???�出來�?��?�宣??�此?��件�?��?�別?��身�?��?�是 implements Runnable */
/* ?��?��此物件執行�?�使?��run()，確保該?��件�?��?��?�可??�入??��??(TimerTask)�?        */
/* 如�?��?�是，可?��?��play()，然後音樂�?�路徑�?�確�?                                                          */
/********************************************************/
public class AFSSSound extends TimerTask implements LineListener {
	
	private String audioFilePath = "src//bgm//"; //絕�?�路�?
	private int soundID; //?��?��
	private int type; //種�??(??��??/警�??/??�景?���?)
	public Clip audioClip;
	private boolean playCompleted; //?��?��?���?
	
	public AFSSSound(int soundID, int type) {
		this.soundID = soundID;
		this.type = type;
		this.playCompleted = false;
	}
	public AFSSSound() { //空建構�?��?�用來�?�空
		
	}
	public void play() {
		//System.out.println(soundID);
		File audioFile = new File(audioFilePath + "water.wav"); //??�設?��?��
		switch (type) {
			case 1: //??��?�聲?��
				audioFile = new File(audioFilePath + "btnSound//btn" + soundID + ".wav");
				break;
			/*case 2: //警�?�聲?��
				audioFile = new File(audioFilePath + "warningSound//war" + soundID + ".wav");
				break;*/
			case 3: //??�景?���?
				if(soundID == 0) {
					soundID = 31;
				}
				audioFile = new File(audioFilePath + "bgm//bgm" + soundID + ".wav");
				break;
			default: //Bang??�聲?��
				break;
		}
		
		try {
			if(type == 3){ //?��?��??�景?���?
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.addLineListener(this); //註�?��?�件
				
				audioClip.open(audioStream); //??��?�串�?
				audioClip.start();// ??��?�播?��
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			} else { //?���?2�?
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.addLineListener(this); //註�?��?�件
				
				audioClip.open(audioStream); //??��?�串�?
				audioClip.start(); // ??��?�播?��
				while (!playCompleted) { //??��?�播?���?
					try {
						Thread.sleep(100); //等�??0.1�?
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
			//audioClip.close(); //??��?�串�?
		} catch (UnsupportedAudioFileException ex) { //不支?��??�音檔格�?(??�wav�?)
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) { //串�?��?��??
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) { //I/O?���?
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}
		
	}
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		//如�?��?�到??��?�播?��?��樂�?�串流�?�件
		if (type == LineEvent.Type.START) { //??�播
			System.out.println("Playback started.");	
		} else if (type == LineEvent.Type.STOP) { //??�播
			playCompleted = true;
			System.out.println("Playback completed.");
		}
	}

	@Override
	public void run() { //?��?��?��多執行�?��?�?
		play();
	}
	
	public Clip getClip(){ //??�傳clip
		return audioClip;
	}
}
