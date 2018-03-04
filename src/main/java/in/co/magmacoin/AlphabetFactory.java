package in.co.magmacoin;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author deepak
 *
 */
class AlphabetFactory {
	static Map<String,AlphabetBean> sets = new HashMap<String,AlphabetBean>();
	static {

		String englishKey = "english"; 
		sets.put(englishKey,
		new AlphabetBean("English", englishKey, new String[] { "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
				"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
				"z" },
				"BirdCherryLite.ttf",
				new String[] { "apple.png", "ball.png",
				"car.png", "dog.png", "elephant.png", "fish.png",
				"gun.png", "horse.png", "icecream.png", "jug.png",
				"key.png", "lion.png", "mango.png", "nest.png",
				"orange.png", "pigeon.png", "queen.png", "rabbit.png",
				"spoon.png", "tiger.png", "umbrella.png", "van.png",
				"watermalon.png", "xmas.png", "yak.png", "zoo.png",
				"apple.png", "ball.png", "camel.png", "dog.png",
				"elephant.png", "fish.png", "gun.png", "horse.png",
				"icecream.png", "jug.png", "key.png", "lion.png",
				"mango.png", "nest.png", "orange.png", "pigeon.png",
				"queen.png", "rabbit.png", "spoon.png", "tiger.png",
				"umbrella.png", "van.png", "watermalon.png",
				"xmas.png", "yak.png", "zoo.png", }
				)
				);
			

		
		
		//Unicode treats them as a "jodakshar" combination characters as ?,? for ??? ?and ?, ? for ???. 
		//Search: Issues in Indic Language Collation 
		String hindiKey = "हिंदी";
		sets.put(hindiKey, new AlphabetBean("हिंदी", hindiKey, new String[] {
				"अ","आ","इ","ई","उ","ऊ","ऋ","ए","ऐ","ओ","औ",
				"अं","अः",
				"क","ख","ग","घ","ङ",
				"च","छ","ज","झ","ञ",
				"ट","ठ","ड","ढ","ण",
				"त","थ","द","ध","न",
				"प","फ","ब","भ","म",
				"य","र","ल","व",
				"श","ष","स","ह"
//				,"क्ष","ज्ञ","त्र"
				//TODO Add above three letters to font file.
				}, "Gurumaa-thin.ttf",
				new String[] { "anaar.png", "mango.png", "tamarind.png", "sugarcane.png",
						"owl.png", "camel.png", "rishi.png", "ankle.png", "eyeglasses.png",
						"okhli.png", "aurat.png",
						"grapes.png","",
						"pigeon.png", "rabbit.png", "cow.png", "home.png", "",
						"spoon.png", "umbrella.png", "jug.png", "flag.png", "",
						"tomato.png", "thatera.gif", "damroo.png", "dhakkan.png", "", 
						"watermalon.png", "than.png", "inkpot.png", "bow.png", "tap.png",
						"kite.png", "fruits.png", "goat.png", "bear.png","fish.png",
						"yagya.png", "chariot.png", "top.png", "crane.png",
						"shalgam.png", "hexagon.png", "snakecharmer.png", "elephant.png"
//						,"","",""
				}
				));
		
		//TODO Gujrati Bangali etc Gujrati:http://kids.baps.org/gujarati/vowels/index.htm, http://kids.baps.org/gujarati/consonants/index.htm
		
		}
	
		
		//how to dynamically put alphabet sets here? Fetch from server
		
		public static AlphabetBean getAlphabetSet(String key){
			AlphabetBean result = sets.get(key);

			if(result != null)
				return result;
			else
				throw new IllegalArgumentException("Alphabets for " + key + " not found.");
		}
}