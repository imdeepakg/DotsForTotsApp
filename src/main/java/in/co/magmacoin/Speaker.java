package in.co.magmacoin;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * A helper class for Text To Speech. 
 * 
 * @author deepak
 *
 */
public class Speaker implements TextToSpeech.OnInitListener {
	
	private TextToSpeech ttsEngine;
	private Queue<String> initQ;
	private boolean inited;
	Speaker() {
		initQ = new LinkedList<String>();
	}
	
	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			setupLocale(Locale.US);
			addHindi();
		} else {
			Log.e("Speaker", "Initilization Failed!");
		}
		
	}
	
	private void setupLocale(Locale locale) {
		int result = ttsEngine.setLanguage(locale);

		if (result == TextToSpeech.LANG_MISSING_DATA
				|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
			Log.e("Speaker", "This Language is not supported");
		} else {

		}
		
		inited=true;
		if(ttsEngine != null) {
			while(initQ.size() > 0)
				ttsEngine.speak(initQ.poll() , TextToSpeech.QUEUE_FLUSH, null);
		}
	}
	
	public TextToSpeech getTtsEngine() {
		return ttsEngine;
	}


	public void setTtsEngine(TextToSpeech ttsEngine) {
		this.ttsEngine = ttsEngine;
	}


	public void speak(String text) {
		if(ttsEngine != null && inited)
			ttsEngine.speak(text , TextToSpeech.QUEUE_FLUSH, null);
		else 
			initQ.add(text);
	}
	
	void addHindi() {
		int success = ttsEngine.addSpeech("अ", "in.co.magmacoin", R.raw.a);
		success &= ttsEngine.addSpeech("आ", "in.co.magmacoin", R.raw.aa);
		success &= ttsEngine.addSpeech("इ", "in.co.magmacoin", R.raw.e);
		success &= ttsEngine.addSpeech("ई", "in.co.magmacoin", R.raw.ee);
		success &= ttsEngine.addSpeech("उ", "in.co.magmacoin", R.raw.u);
		success &= ttsEngine.addSpeech("ऊ", "in.co.magmacoin", R.raw.uu);
//		success &= ttsEngine.addSpeech("ऋ", "in.co.magmacoin", R.raw.a);
		success &= ttsEngine.addSpeech("ए", "in.co.magmacoin", R.raw.ay);
		success &= ttsEngine.addSpeech("ऐ", "in.co.magmacoin", R.raw.aay);
		success &= ttsEngine.addSpeech("ओ", "in.co.magmacoin", R.raw.o);
		success &= ttsEngine.addSpeech("औ", "in.co.magmacoin", R.raw.oo);
		success &= ttsEngine.addSpeech("अं", "in.co.magmacoin", R.raw.ang);
		success &= ttsEngine.addSpeech("अः", "in.co.magmacoin", R.raw.aha);
		success &= ttsEngine.addSpeech("क", "in.co.magmacoin", R.raw.kabotar);
		success &= ttsEngine.addSpeech("ख", "in.co.magmacoin", R.raw.khargosh);
		success &= ttsEngine.addSpeech("ग", "in.co.magmacoin", R.raw.gaay);
		success &= ttsEngine.addSpeech("घ", "in.co.magmacoin", R.raw.ghar);
		success &= ttsEngine.addSpeech("ङ", "in.co.magmacoin", R.raw.adakhali);
		success &= ttsEngine.addSpeech("च", "in.co.magmacoin", R.raw.chammach);
		success &= ttsEngine.addSpeech("छ", "in.co.magmacoin", R.raw.chhata);
		success &= ttsEngine.addSpeech("ज", "in.co.magmacoin", R.raw.jug);
		success &= ttsEngine.addSpeech("झ", "in.co.magmacoin", R.raw.zhanda);
		success &= ttsEngine.addSpeech("ञ", "in.co.magmacoin", R.raw.ayan_khali);
		success &= ttsEngine.addSpeech("ट", "in.co.magmacoin", R.raw.tamatar);
		success &= ttsEngine.addSpeech("ठ", "in.co.magmacoin", R.raw.thatera);
		success &= ttsEngine.addSpeech("ड", "in.co.magmacoin", R.raw.damroo);
		success &= ttsEngine.addSpeech("ढ", "in.co.magmacoin", R.raw.dhakkan);
		success &= ttsEngine.addSpeech("ण", "in.co.magmacoin", R.raw.anna_khali);
		success &= ttsEngine.addSpeech("त", "in.co.magmacoin", R.raw.taarbooj);
		success &= ttsEngine.addSpeech("थ", "in.co.magmacoin", R.raw.thhan);
		success &= ttsEngine.addSpeech("द", "in.co.magmacoin", R.raw.dawaat);
		success &= ttsEngine.addSpeech("ध", "in.co.magmacoin", R.raw.dhanoosh);
		success &= ttsEngine.addSpeech("न", "in.co.magmacoin", R.raw.nal);
		success &= ttsEngine.addSpeech("प", "in.co.magmacoin", R.raw.patang);
		success &= ttsEngine.addSpeech("फ", "in.co.magmacoin", R.raw.phal);
		success &= ttsEngine.addSpeech("ब", "in.co.magmacoin", R.raw.bakri);
		success &= ttsEngine.addSpeech("भ", "in.co.magmacoin", R.raw.bhaalo);
		success &= ttsEngine.addSpeech("म", "in.co.magmacoin", R.raw.machli);
		success &= ttsEngine.addSpeech("य", "in.co.magmacoin", R.raw.yagya);
		success &= ttsEngine.addSpeech("र", "in.co.magmacoin", R.raw.rath);
		success &= ttsEngine.addSpeech("ल", "in.co.magmacoin", R.raw.latto);
		success &= ttsEngine.addSpeech("व", "in.co.magmacoin", R.raw.vak);
		success &= ttsEngine.addSpeech("श", "in.co.magmacoin", R.raw.shalgam);
		success &= ttsEngine.addSpeech("ष", "in.co.magmacoin", R.raw.shatkon);
		success &= ttsEngine.addSpeech("स", "in.co.magmacoin", R.raw.sapera);
		success &= ttsEngine.addSpeech("ह", "in.co.magmacoin", R.raw.haathi);

		if(success == TextToSpeech.SUCCESS){
			Log.d(Constants.APP_NAME, "All hindi added to tts engine");
		} else {
			Log.d(Constants.APP_NAME, "Failed to add some of hindo to TTS engine");
		}
	}	
	void userAdd(String text, String fileName) {
		//let user add support for a character.
		//addSpeech(String text, String filename)
	}
	
	
}
