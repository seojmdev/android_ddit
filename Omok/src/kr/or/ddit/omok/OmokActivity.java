package kr.or.ddit.omok;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class OmokActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        BoardView bv = new BoardView(this);
        setContentView(bv);
    }
}

class BoardView extends View {
	final int COLUMN_NUM = 15;	 //15*15
	int mColumnWidth = 0;

	int[][] mStoneGrid = null; //돌 정보
	int mStoneColor = Color.BLACK;

	Paint mPnt = null;

	//1. BoardView에 터치가 발생하면 터치된 좌표에 돌이 그려지도록  
	//		터치된 좌표에 해당하는 mStoneGrid[][] 값을 돌색으로 설정
	//2. 터치시 흑색과 백색 돌이 번갈아 그려지도록
	//3. 터치시 기존에 돌이 이미 존재하던 곳에는 그려지지 않도록
	//4. 게임이 끝났는지 검사 (승자 출력 및 재시작/종료)
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mColumnWidth = (w<h?w:h)/COLUMN_NUM; //한칸의 너비 계산 
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int x=0, y=0;
		int midx=0, midy=0;
		canvas.drawColor(Color.YELLOW); //바탕색 칠하기
		mPnt.setColor(Color.BLACK); //선색
		for (y=0;y<COLUMN_NUM;y++){
			midy = mColumnWidth*y+mColumnWidth/2;
			for (x=0;x<COLUMN_NUM;x++){
				midx = mColumnWidth*x+mColumnWidth/2;
				canvas.drawLine(midx, mColumnWidth*y, midx, mColumnWidth*(y+1), mPnt);//세로선 그리기
				canvas.drawLine(mColumnWidth*x, midy, mColumnWidth*(x+1), midy, mPnt);//가로선 그리기
				if (mStoneGrid[x][y]!=0) { 
					mPnt.setColor(mStoneGrid[x][y]); //돌색
					canvas.drawCircle(midx, midy, mColumnWidth/2, mPnt); //돌 그리기
					mPnt.setColor(Color.BLACK); //선색
				}
			}
		}
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mStoneGrid = new int[COLUMN_NUM][COLUMN_NUM];  //돌 정보를 저장할 2차원 배열
		
		mPnt = new Paint(); //선과 돌을 그리기 위한 페인트 생성 및 설정
		mPnt.setStrokeWidth(3);
		mPnt.setStyle(Paint.Style.FILL);
		for (int x=0;x<COLUMN_NUM;x++) //돌 정보를 저장할 2차원 배열 초기화
			for (int y=0;y<COLUMN_NUM;y++)
				mStoneGrid[x][y]=0; //돌이 없는 칸
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

}