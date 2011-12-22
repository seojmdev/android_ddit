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

	int[][] mStoneGrid = null; //�� ����
	int mStoneColor = Color.BLACK;

	Paint mPnt = null;

	//1. BoardView�� ��ġ�� �߻��ϸ� ��ġ�� ��ǥ�� ���� �׷�������  
	//		��ġ�� ��ǥ�� �ش��ϴ� mStoneGrid[][] ���� �������� ����
	//2. ��ġ�� ����� ��� ���� ������ �׷�������
	//3. ��ġ�� ������ ���� �̹� �����ϴ� ������ �׷����� �ʵ���
	//4. ������ �������� �˻� (���� ��� �� �����/����)
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mColumnWidth = (w<h?w:h)/COLUMN_NUM; //��ĭ�� �ʺ� ��� 
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int x=0, y=0;
		int midx=0, midy=0;
		canvas.drawColor(Color.YELLOW); //������ ĥ�ϱ�
		mPnt.setColor(Color.BLACK); //����
		for (y=0;y<COLUMN_NUM;y++){
			midy = mColumnWidth*y+mColumnWidth/2;
			for (x=0;x<COLUMN_NUM;x++){
				midx = mColumnWidth*x+mColumnWidth/2;
				canvas.drawLine(midx, mColumnWidth*y, midx, mColumnWidth*(y+1), mPnt);//���μ� �׸���
				canvas.drawLine(mColumnWidth*x, midy, mColumnWidth*(x+1), midy, mPnt);//���μ� �׸���
				if (mStoneGrid[x][y]!=0) { 
					mPnt.setColor(mStoneGrid[x][y]); //����
					canvas.drawCircle(midx, midy, mColumnWidth/2, mPnt); //�� �׸���
					mPnt.setColor(Color.BLACK); //����
				}
			}
		}
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mStoneGrid = new int[COLUMN_NUM][COLUMN_NUM];  //�� ������ ������ 2���� �迭
		
		mPnt = new Paint(); //���� ���� �׸��� ���� ����Ʈ ���� �� ����
		mPnt.setStrokeWidth(3);
		mPnt.setStyle(Paint.Style.FILL);
		for (int x=0;x<COLUMN_NUM;x++) //�� ������ ������ 2���� �迭 �ʱ�ȭ
			for (int y=0;y<COLUMN_NUM;y++)
				mStoneGrid[x][y]=0; //���� ���� ĭ
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