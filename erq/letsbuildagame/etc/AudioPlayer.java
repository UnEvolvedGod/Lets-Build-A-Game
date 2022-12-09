package erq.letsbuildagame.etc;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	//Stores all sounds and songs
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			
			musicMap.put("battle_music", new Music("res/Winter-Vivaldi-_COPYRIGHT-FREE_.ogg"));
			
			musicMap.put("background_music", new Music("res/mixkit-tech-house-vibes-130.ogg"));
			
			soundMap.put("menu_sound", new Sound("res/mixkit-short-laser-gun-shot-1670.ogg"));
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	
	
}
