package in.co.magmacoin;


import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;

//Change Launch Icon
public class ConnectTheDotsActivity extends Activity {
    private Speaker speaker;
	private int layoutId;

	@Override
	public void setContentView(int layoutResID) {
		this.layoutId = layoutResID;
		super.setContentView(layoutResID);
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	speaker = new Speaker();
    	speaker.setTtsEngine(new TextToSpeech(this, speaker));

        setLaunchMenu();

    }

    private void setLaunchMenu()
    {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.launch_menu);


        ImageButton btnAlphaDraw = (ImageButton) findViewById(R.id.btnAlphaDrawEng);
        btnAlphaDraw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.main);
                final AlphabetDrawView drawView = (AlphabetDrawView) findViewById(R.id.alphabetView);

                String lang = "english";
                drawView.setSpeaker(speaker);
                drawView.setAlphabets(AlphabetFactory.getAlphabetSet(lang));
                ImageButton btnLeft = (ImageButton) findViewById(R.id.btnLeft);
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(drawView.hasPre()) {
                            drawView.pre();
                        }
                    }
                });

                final ImageButton btnReload = (ImageButton) findViewById(R.id.btnReload);
                btnReload.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        drawView.reload();
                    }
                });

                ImageButton btnNext = (ImageButton) findViewById(R.id.btnNext);
                btnNext.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(drawView.hasNext()) {
                            drawView.next();
                        }
                    }
                });
            }
        });


        ImageButton btnAlphaDrawHindi = (ImageButton) findViewById(R.id.btnAlphaDrawHin);
        btnAlphaDrawHindi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.main);
                final AlphabetDrawView drawView = (AlphabetDrawView) findViewById(R.id.alphabetView);

                String lang = "हिंदी";
                drawView.setSpeaker(speaker);
                drawView.setAlphabets(AlphabetFactory.getAlphabetSet(lang));
                ImageButton btnLeft = (ImageButton) findViewById(R.id.btnLeft);
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(drawView.hasPre()) {
                            drawView.pre();
                        }
                    }
                });

                final ImageButton btnReload = (ImageButton) findViewById(R.id.btnReload);
                btnReload.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        drawView.reload();
                    }
                });

                ImageButton btnNext = (ImageButton) findViewById(R.id.btnNext);
                btnNext.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(drawView.hasNext()) {
                            drawView.next();
                        }
                    }
                });
            }
        });
    }
    
    @Override
    public void onDestroy() {
        // must shutdown tts!
        if (speaker != null && speaker.getTtsEngine() != null) {
        	speaker.getTtsEngine().stop();
        	speaker.getTtsEngine().shutdown();
        }
        super.onDestroy();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	String prefLang = sharedPrefs.getString("learnLanguage", "english");

    	final AlphabetDrawView drawView = (AlphabetDrawView) findViewById(R.id.alphabetView);
		if (drawView != null) {
			String currentLang = drawView.getAlphabets().getKey();
			if (!currentLang.equals(prefLang)) {
				drawView.setAlphabets(AlphabetFactory.getAlphabetSet(prefLang));
			}
		}
    }
    
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    
	@Override
	public void onBackPressed() {
		if(this.layoutId == R.layout.main)
		{
            setLaunchMenu();
		}
		else
		{
			super.onBackPressed();
		}
	}
}
