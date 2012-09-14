package com.example.helloworld2;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MyCustomButton extends Button {

	// private int coordinateX,coordinateY;
	// private Gallery.LayoutParams galleryLayoutParams;

	private Handler mHandler;
	private int imageResId;
	private LinearLayout.LayoutParams layoutParams;
	private ImageView i;
	private Rect rectangle;
	private int originalScreenWidth;
	private String txtButtonText;
	private int screenWidth, screenHeight;
	private Bitmap image;
	private boolean btnIsPressed;
	private int btnLeftRight;
	private int btnMaxX, btnMaxY;
	private int x2, y2;
	private int currentX, currentY;
	private Gridd gridd;
	private LinearLayout myCustomButtonLayout;
	private TextView textView;
	private int WIDTH_DIVIDER = 4;
	private int HEIGHT_DIVIDER = 10;
	private Context context;
	private int index;

	public MyCustomButton(Context context, String text, int resImage,
			int screenWidth, int screenHeight, int btnLeftRight, Gridd gridd,
			int originalScreenWidth,int index) {
		super(context);
		this.context = context;
		this.originalScreenWidth = originalScreenWidth;
		this.txtButtonText = text;
		this.gridd = gridd;
		this.imageResId = resImage;
		this.btnLeftRight = btnLeftRight;
		this.index = index;
		this.image = BitmapFactory.decodeResource(context.getResources(),
				imageResId);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.btnIsPressed = false;
		// Bitmap bitmap = Bitmap.createBitmap(this.getWidth(),
		// this.getHeight(),
		// Bitmap.Config.ARGB_8888);
		// Drawable d = context.getResources().getDrawable(imageResId);
		// setBackgroundDrawable(new
		// BitmapDrawable(bitmap).getResources().getDrawable(imageResId));
		// setBackgroundResource(resImage);

		textView = new TextView(context);
		textView.setText(text);
		// this.coordinateX = coordinateX;
		// this.coordinateY = coordinateY;
		i = new ImageView(context);
		setBackgroundDrawable(context.getResources().getDrawable(resImage));
		//i.setImageBitmap(this.image);
		//i.setAdjustViewBounds(true); // set the ImageView bounds to match the
		//i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.MATCH_PARENT,
			//	LayoutParams.MATCH_PARENT));
		setButtonsMax();
		// layoutParams = new LayoutParams(originalScreenWidth / 2,
		// LayoutParams.MATCH_PARENT);
		// Drawable draw = context.getResources().getDrawable(resImage);
		// setBackgroundDrawable(draw);
		layoutParams = new LayoutParams((int)(originalScreenWidth / 1.5),
				LayoutParams.MATCH_PARENT);
		// setLayoutParams(layoutParams);
		setBackgroundDrawable(context.getResources().getDrawable(imageResId));
		setText(txtButtonText);
		setTextSize(15);
		if (btnLeftRight == 0) {
			setGravity(Gravity.CENTER);
		} else {
			setGravity(Gravity.CENTER);
		}
		//setGravity(Gravity.CENTER);
		setTextColor(Color.WHITE);
		// setBackgroundColor(Color.WHITE);
		System.out.println("Obje Yaratildi");
		// this.gridd.notifyGridd();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			currentX = (int) event.getRawX();
			currentY = (int) event.getRawY();
			gridd.notifyGridd(index, btnLeftRight);
			System.out.println("Mouse Down");
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			x2 = (int) event.getRawX();
			y2 = (int) event.getRawY();

			System.out.println("X2: " + x2);
			System.out.println(originalScreenWidth);
			if (btnLeftRight == 0 && x2 < ((int) (originalScreenWidth / 1.5))) {

				System.out.println("Left Button Move");
				layoutParams.setMargins(x2 - (int)(originalScreenWidth / 1.5) - 1
						/ screenWidth, 0, 0, 0);
				this.setLayoutParams(layoutParams);
			}

			else if (btnLeftRight == 1 && x2 > ((int) originalScreenWidth / 2.5)) {

				System.out.println("Right Button Move");
				layoutParams.setMargins((x2 - (int) (originalScreenWidth / 2.5)),
						0, 0, 0);
				// layoutParams.setMargins(left, top, right, bottom);
				this.setLayoutParams(layoutParams);
			}

			break;
		}
		case MotionEvent.ACTION_UP: {
			if (x2 >= originalScreenWidth / 1.5 && btnLeftRight == 0) {
				gridd.notifyGriddToStartIntent(txtButtonText);
				
			} else if (x2 <= originalScreenWidth / 2.5 && btnLeftRight == 1) {
				gridd.notifyGriddToStartIntent(txtButtonText);
			}
			//setButtonMargin();
			setButtonMargin();
			//gridd.notifyGriddToTurnBack(index);
			break;
		}
		case MotionEvent.ACTION_OUTSIDE: {
			setButtonMargin();
			break;
		}
		}
		return true;
		// return super.onTouchEvent(event);
	}

	public void setButtonMargin() {
		if (btnLeftRight == 0) {
			layoutParams.setMargins(
					0 - (int)(originalScreenWidth / WIDTH_DIVIDER *2), 0, 0, 0);
			this.setLayoutParams(layoutParams);
		} else {
			layoutParams.setMargins(originalScreenWidth
					- ((int)(originalScreenWidth / WIDTH_DIVIDER *2.24)), 0, 0, 0);
			this.setLayoutParams(layoutParams);
		}
	}

	private void setButtonsMax() {
		btnMaxX = (-(int) (screenWidth / 2));
		btnMaxY = (int) (originalScreenWidth - 285);
	}

	private void openThread() {
		mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			public void run() {
				while (true) {

					setButtonMarginWithTimer();
				}

			}
		}, 3);
	}

	public void setButtonMarginWithTimer() {
		if (btnLeftRight == 0) {
			if (btnMaxX > x2) {
				x2 -= 10;
				layoutParams.setMargins(x2, layoutParams.MATCH_PARENT, 0, 0);
				this.setLayoutParams(layoutParams);
			} else if (btnMaxX <= x2) {
				x2 = btnMaxX;
				layoutParams.setMargins(btnMaxX, layoutParams.MATCH_PARENT, 0,
						0);
				this.setLayoutParams(layoutParams);
			}

		} else {
			layoutParams.setMargins((int) (originalScreenWidth - 285),
					LayoutParams.MATCH_PARENT, 0, 0);
			this.setLayoutParams(layoutParams);
		}
	}

	public int isBtnLeftRight() {
		return btnLeftRight;
	}

	protected void onDraw(Canvas canvas) {
		Paint imgPaint = new Paint();
		imgPaint.setFilterBitmap(true);
		imgPaint.setStyle(Style.FILL);
		imgPaint.clearShadowLayer();
		canvas.drawBitmap(image, (int)(originalScreenWidth/1.5),
				LayoutParams.MATCH_PARENT, imgPaint);
		super.onDraw(canvas);
	}

	public void setButtonIsNotPressed() {
		btnIsPressed = false;
	}

	public int getImageResId() {
		return imageResId;
	}

	public void setImageResId(int imageResId) {
		this.imageResId = imageResId;
	}

	public String getTxtButtonText() {
		return txtButtonText;
	}

	public void setTxtButtonText(String txtButtonText) {
		this.txtButtonText = txtButtonText;
	}

	public boolean isBtnIsPressed() {
		return btnIsPressed;
	}

	public void setBtnIsPressed(boolean btnIsPressed) {
		this.btnIsPressed = btnIsPressed;
	}

	public Bitmap getImage() {
		return image;
	}

}
