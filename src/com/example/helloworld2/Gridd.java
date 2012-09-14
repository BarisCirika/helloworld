package com.example.helloworld2;

//package testt.manju.com;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Gridd extends Activity implements OnGestureListener {
	private LinearLayout main;
	private LinearLayout row;
	private LinearLayout rowDummy;
	private LinearLayout rowChild;
	private ArrayList<LinearLayout> childRowArray, rowArray;
	private TextView viewA;
	private ArrayList<MyCustomButton> buttonArray;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private static int WIDTH_DIVIDER = 4;
	private static int HEIGHT_DIVIDER = 10;
	Timer timer;
	private LinearLayout.LayoutParams layoutParams;
	private TextView empty;
	private Button btnGestrue;
	private int currentX = 0;
	private int currentY = 0;
	private int screenWidth, screenHeight;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private MyCustomButton tempCustomButton = null;
	private GestureDetector gestureScanner;
	private String[] titles = { "Büyük ﬁehir", "Konya", "Gezilecek Yerler",
			"Hz. Mevlana", "Restoranlar", "Kültür Vadisi", "Sa€l›k",
			"Konaklama", "Gerekli", "E€lence", "Acil Durum", "Al›ﬂ-veriﬂ" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		buttonArray = new ArrayList<MyCustomButton>();
		getDensity();
		getScreenSize();
		gestureScanner = new GestureDetector(this);
		main = new LinearLayout(this);
		main.setBackgroundColor(Color.GRAY);
		main.setLayoutParams(new LinearLayout.LayoutParams(screenWidth,
				screenHeight));
		main.setOrientation(LinearLayout.VERTICAL);
		// main.setBackgroundColor(Color.WHITE);
		childRowArray = new ArrayList<LinearLayout>();
		rowArray = new ArrayList<LinearLayout>();
		int titleCount = 0;
		row = new LinearLayout(main.getContext());
		row.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, (int)(screenHeight / 28)));
		main.addView(row);
		for (int i = 0; i < titles.length / 2; i++) {
			row = new LinearLayout(main.getContext());
			row.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,(int) (screenHeight / 6.5)));

			for (int j = 0; j < 2; j++) {
				rowChild = new LinearLayout(row.getContext());
				if(j==0)
				 rowChild.setBackgroundColor(Color.YELLOW);
				else
					rowChild.setBackgroundColor(Color.CYAN);
				rowChild.setLayoutParams(new LinearLayout.LayoutParams(
						(int) (screenWidth / 1.5), screenHeight / 8));
				// LayoutParams.WRAP_CONTENT, screenHeight / 10));
				System.out.println(i + ". Screen Width: " + screenWidth
						+ " Screen Height: " + screenHeight);
				MyCustomButton myCustomButton = new MyCustomButton(this,
						titles[titleCount], ImageResource.CATEGORY_IMAGES[j],
						(int) screenWidth / WIDTH_DIVIDER, (int) screenHeight
								/ HEIGHT_DIVIDER, j, this, screenWidth,
						titleCount);
				layoutParams = new LayoutParams((int) (screenWidth / 1.5),
						LayoutParams.FILL_PARENT);
				// layoutParams = new LayoutParams()
				/*
				 * if (j % 2 == 0) layoutParams.setMargins((-(int) (185)),
				 * layoutParams.MATCH_PARENT, 0, 0); else
				 * layoutParams.setMargins( (int) (screenWidth - 285),
				 * LayoutParams.MATCH_PARENT, 0, 0);
				 */

				if (j % 2 == 0) {
					layoutParams.setMargins(
							0 - (screenWidth / WIDTH_DIVIDER * 2), 0, 0, 0);
				} else {
					layoutParams.setMargins(screenWidth
							- ((int) (screenWidth / WIDTH_DIVIDER * 3.3)), 0,
							0, 0);
				}
				myCustomButton.setLayoutParams(layoutParams);
				buttonArray.add(myCustomButton);

				rowChild.addView(myCustomButton);
				row.addView(rowChild);
				childRowArray.add(rowChild);
				titleCount++;
			}
			rowArray.add(row);
			main.addView(row);
			// main.addView(rowDummy);
			coordinateY += screenHeight / 8;
			System.out.println(i + ". Coordinate Y:" + coordinateY);
		}
		/*
		 * btnGestrue = new Button(this); layoutParams = new
		 * LinearLayout.LayoutParams( 200,
		 * LinearLayout.LayoutParams.WRAP_CONTENT);
		 * btnGestrue.setLayoutParams(layoutParams);
		 * layoutParams.setMargins(-150, 0, 0, 0);
		 */
		// btnGestrue.setClickable(false);
		// empty = new TextView(this);
		// empty.setLayoutParams(new LinearLayout.LayoutParams(40, 30));

		// viewA = new TextView(this);
		// viewA.setBackgroundColor(Color.YELLOW);
		// viewA.setTextColor(Color.BLACK);
		// viewA.setTextSize(16);
		// viewA.setLayoutParams(new LinearLayout.LayoutParams(320, 480));
		// main.addView(empty);
		// main.addView(btnGestrue);
		// main.addView(viewA);
		setContentView(main);
		// btnGestrue.setHorizontallyScrolling(true);
	}

	/*
	 * public void setButtonsPosition(){ layoutParams = new
	 * LayoutParams(screenWidth / 5, screenHeight / 10); if (j % 2 == 0)
	 * layoutParams.setMargins((-(int) (screenWidth / 10)), coordinateY, 0, 0);
	 * else layoutParams.setMargins( (int) (screenWidth - screenWidth / 5),
	 * coordinateY, 0, 0); myCustomButton.setLayoutParams(layoutParams);
	 * main.addView(myCustomButton); buttonArray.add(myCustomButton);
	 * titleCount++; }
	 */
	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) { switch
	 * (event.getAction()) { case MotionEvent.ACTION_DOWN: { currentX = (int)
	 * event.getRawX(); currentY = (int) event.getRawY(); break; }
	 * 
	 * case MotionEvent.ACTION_MOVE: { int x2 = (int) event.getRawX(); int y2 =
	 * (int) event.getRawY(); System.out.println("X2: " + x2 + " Y2:" + y2);
	 * 
	 * LinearLayout ll = new LinearLayout(this);
	 * ll.setOrientation(LinearLayout.HORIZONTAL); for (MyCustomButton
	 * myCustomButton : buttonArray) { System.out.println("Button bulma array");
	 * if (myCustomButton.isBtnIsPressed()) { tempCustomButton = myCustomButton;
	 * break; } }
	 * 
	 * // int buttonHeight = (int)screenHeight/10; if (tempCustomButton != null)
	 * { System.out.println("oynatma"); if (tempCustomButton.isBtnLeftRight() ==
	 * 0) { int buttonMargin = x2 - (int) (screenWidth / 6); if (x2 <
	 * screenWidth / 2 && x2 > 20) { // layoutParams.setMargins(buttonMargin, //
	 * tempCustomButton.getHeight(), 0, 0);
	 * layoutParams.setMargins(buttonMargin, tempCustomButton.getHeight(), 0,
	 * 0); tempCustomButton.setLayoutParams(layoutParams); } } else { if (x2 >=
	 * screenWidth / 2 && x2 > screenWidth - 20) { layoutParams.setMargins((int)
	 * (x2 - screenWidth / 6), 0, 0, 0);
	 * tempCustomButton.setLayoutParams(layoutParams); } } } /* if (currentX <
	 * x2) { LinearLayout ll = new LinearLayout(this);
	 * ll.setOrientation(LinearLayout.HORIZONTAL);
	 * 
	 * layoutParams = new LinearLayout.LayoutParams( 200,
	 * LinearLayout.LayoutParams.WRAP_CONTENT);
	 * 
	 * layoutParams.setMargins(x2 - 200, 0, 0, 0); //
	 * layoutParams.setMargins(left, top, right, bottom)
	 * btnGestrue.setLayoutParams(layoutParams); currentX = x2; currentY = y2; }
	 * 
	 * 
	 * break; } case MotionEvent.ACTION_UP: { if(tempCustomButton!=null)
	 * tempCustomButton.setButtonIsNotPressed(); //
	 * layoutParams.setMargins(-150, 0, 0, 0); //
	 * btnGestrue.setLayoutParams(layoutParams); } } return
	 * gestureScanner.onTouchEvent(event); }
	 */
	private void getDensity() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		switch (metrics.densityDpi) {
		case DisplayMetrics.DENSITY_LOW:

			break;
		case DisplayMetrics.DENSITY_MEDIUM:

			break;
		case DisplayMetrics.DENSITY_HIGH:

			break;
		}

	}

	private void getScreenSize() {
		Display display = getWindowManager().getDefaultDisplay();
		screenHeight = display.getHeight();
		screenWidth = display.getWidth();
		System.out.println("Screen Height: " + screenHeight);
	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		// btnGestrue.scrollBy((int)e1.getX(), (int)e1.getY());
		// btnGestrue.scrollTo((int)e2.getX(),(int)e2.getY());
		return true;
	}

	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void notifyGridd(int index, int btnLeftOrRight) {
		// tempCustomButton = myCustomButton;

		LinearLayout childRow1, childRow2;
		if (btnLeftOrRight == 0) {

			childRow1 = childRowArray.get(index);
			childRow1.setLayoutParams(new LinearLayout.LayoutParams(
			// LayoutParams.WRAP_CONTENT, screenHeight / 10));
					(int) (screenWidth / 1.5), screenHeight / 8));
			MyCustomButton tempButton = buttonArray.get(index+1);
			LayoutParams params = new LayoutParams((int) (screenWidth / 1.5), screenHeight / 8);
			params.setMargins((screenWidth
					- ((int) (screenWidth / WIDTH_DIVIDER * 3.3))), 0, 0, 0);
			childRow2 = childRowArray.get(index + 1);
			childRow2.setLayoutParams(new LinearLayout.LayoutParams(
					(int) (screenWidth / 2.5), screenHeight / 8));
			tempButton.setLayoutParams(params);

		} else {
			childRow2 = childRowArray.get(index - 1);
			childRow2.setLayoutParams(new LinearLayout.LayoutParams(
					(int) (screenWidth / 2.5), screenHeight / 8));
			childRow1 = childRowArray.get(index);
			childRow1.setLayoutParams(new LinearLayout.LayoutParams(
			// LayoutParams.WRAP_CONTENT, screenHeight / 10));
					(int) (screenWidth / 1.5), screenHeight / 8));


		}
	}

	public void notifyGriddToTurnBack(int index) {
		int rowNumber = 0;
		int tempCount;

		if (index % 2 == 0) {
			tempCount = index;
		} else
			tempCount = index - 1;
		rowNumber = index / 2 + 1;
		row = rowArray.get(rowNumber);
		// row = new LinearLayout(main.getContext());
		// row.setBackgroundColor(Color.BLUE);
		// row.setLayoutParams(new
		// LinearLayout.LayoutParams(screenWidth,screenHeight/10));

		for (int j = 0; j < 2; j++) {
			rowChild = new LinearLayout(row.getContext());
			// rowChild.setBackgroundColor(Color.YELLOW);
			rowChild.setLayoutParams(new LinearLayout.LayoutParams(
					(int) (screenWidth / 1.5), screenHeight / 10));
			// LayoutParams.WRAP_CONTENT, screenHeight / 10));
			// System.out.println(i + ". Screen Width: " + screenWidth
			// + " Screen Height: " + screenHeight);
			MyCustomButton myCustomButton = new MyCustomButton(this,
					titles[tempCount], ImageResource.CATEGORY_IMAGES[j],
					(int) screenWidth / WIDTH_DIVIDER, (int) screenHeight
							/ HEIGHT_DIVIDER, j, this, screenWidth, tempCount);
			layoutParams = new LayoutParams((int) (screenWidth / 1.5),
					LayoutParams.MATCH_PARENT);
			// layoutParams = new LayoutParams()
			/*
			 * if (j % 2 == 0) layoutParams.setMargins((-(int) (185)),
			 * layoutParams.MATCH_PARENT, 0, 0); else layoutParams.setMargins(
			 * (int) (screenWidth - 285), LayoutParams.MATCH_PARENT, 0, 0);
			 */

			if (j % 2 == 0) {
				layoutParams.setMargins(0 - (screenWidth / WIDTH_DIVIDER * 2),
						0, 0, 0);
			} else {
				layoutParams.setMargins(screenWidth
						- ((int) (screenWidth / WIDTH_DIVIDER * 3.4)), 0, 0, 0);
			}
			myCustomButton.setLayoutParams(layoutParams);
			buttonArray.add(myCustomButton);

			rowChild.addView(myCustomButton);
			row.addView(rowChild);
			childRowArray.add(rowChild);
		}

	}

	public void notifyGriddToStartIntent(String category) {
		ApplicationName applicationName = new ApplicationName(category);
		Intent intent = new Intent(getApplicationContext(),
				SecondActivity.class);
		intent.putExtra("appname", applicationName);
		startActivity(intent);
	}

	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
