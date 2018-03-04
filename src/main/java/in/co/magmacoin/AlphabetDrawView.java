package in.co.magmacoin;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

/**
 * A set of alphabets to draw and allow finger paint them.
 * 
 * @author deepak
 *
 */
public class AlphabetDrawView extends View implements OnTouchListener{
    private static final String TAG = "AlphabetDrawView";
    private static final float TOUCH_TOLERANCE = 4;
    
    
    private Paint mPaint;
    private Paint dPaint;
	private AlphabetBean alphabets;
	private int index;    
    private Path    mPath;
    private float 	mX, mY,tx,ty;
    private Bitmap  mBitmap;
    private Canvas  mCanvas;
    private Paint   mBitmapPaint;
    private Speaker speaker;
	
	public AlphabetDrawView(Context context) {
        this(context, null);
	}

	public AlphabetDrawView(Context context, AttributeSet attributes) {

        this(context, AlphabetFactory.getAlphabetSet("english"),attributes);
	}
	
	public AlphabetDrawView(Context context,AlphabetBean alphabets,AttributeSet attributes) {
		super(context,attributes);

        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		this.alphabets = alphabets;
		index = 0;
	    dPaint = new Paint();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaint = new Paint();

        mBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

		dPaint.setAntiAlias(true);
        dPaint.setDither(true);
        dPaint.setColor(Color.WHITE);
        dPaint.setPathEffect(new DashPathEffect(new float[] {2,8}, 0));
        dPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        dPaint.setStrokeWidth(5);
        if(alphabets.getFontAssetName() != null) {
        	Typeface customFont = Typeface.createFromAsset(context.getAssets(), alphabets.getFontAssetName());
        	dPaint.setTypeface(customFont);
        }

        Bitmap fillBMP = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.chalk); 
        Shader fillBMPshader = new BitmapShader(fillBMP, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(fillBMPshader);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPath = new Path();
        

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

	}

	
	public AlphabetBean getAlphabets() {
		return this.alphabets;
	}
	
	public void setAlphabets(AlphabetBean alphabets) {
		this.alphabets = alphabets;
		index = 0;
		if(alphabets.getFontAssetName() != null) {
        	Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), alphabets.getFontAssetName());
        	dPaint.setTypeface(customFont);
        }
		setCharToDraw(0);
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Navigation	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public boolean hasNext() {
		return index != alphabets.getAlphabets().length-1;
	}
	
	public void next() {
		if(hasNext()) {
			pendingNavAction = new NavigationAction(index+1);
			Util.animateViewToRight(this);
		}
	}
	
	public boolean hasPre() {
		return index != 0;
	}
	
	public void pre() {
		if(hasPre()) {
			pendingNavAction = new NavigationAction(index-1);
			Util.animateViewToLeft(this);
		}
	}
	
	public void reload() {
		pendingNavAction = new NavigationAction(index);
		Util.animateViewToDown(this);

	}
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   Navigation	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Constants.GREEN_BOARD);

        Bitmap pic = Util.getBitmapFromAsset(getContext(), alphabets.getImageAssetName()[index]);
		if(pic != null)
			mCanvas.drawBitmap(pic, null, new Rect(getWidth()/2, getHeight()/2, getWidth(), getHeight()), mPaint);

        dPaint.setTextSize(getHeight()*.5F);
        tx = getWidth()*.2F;
        ty = getHeight()*.5F;
        
        speaker.speak(alphabets.getAlphabets()[index]);
    }
    
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
        canvas.drawText(alphabets.getAlphabets()[index] , tx, ty, dPaint);
    }

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

	void setCharToDraw(int toDrawCharIndex) {
		//GOT new char to draw.
		mBitmap.eraseColor(android.graphics.Color.TRANSPARENT);
		mCanvas.drawColor(Constants.GREEN_BOARD);
		
		Bitmap pic = Util.getBitmapFromAsset(getContext(), alphabets.getImageAssetName()[index]);
		if(pic != null)
			mCanvas.drawBitmap(pic, null, new Rect(getWidth()/2, getHeight()/2, getWidth(), getHeight()), mPaint);
		mPath.reset();
		invalidate();
		
		speaker.speak(alphabets.getAlphabets()[index]);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        
        return true;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void onAnimationEnd() {
		super.onAnimationEnd();
//		Toast.makeText(getContext(), "onAnimationEnd", Toast.LENGTH_SHORT).show();
		if(pendingNavAction != null) {
			pendingNavAction.navigate();
			pendingNavAction = null;
		}
		
	}
	
	

	private NavigationAction pendingNavAction;
	
	class NavigationAction {
		private int nextIndex;

		NavigationAction(int index) {
			nextIndex = index;
		}

		void navigate() {
			index = nextIndex;
			mPaint.setXfermode(null);
			mPaint.setAlpha(0xFF);
			setCharToDraw(index);
		}
	}
	

}