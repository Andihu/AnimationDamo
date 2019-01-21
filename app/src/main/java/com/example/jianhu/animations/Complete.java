package com.example.jianhu.animations;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;


public class Complete extends View {

    private Path mCircle;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Path  mDstPath;


    float mRadius=100;
    float mCenntX=0;
    float mCentY=0;
    float mCurrentValue=-1;

    private boolean isNext=false;
    private ValueAnimator animator;


    public Complete(Context context) {
        super(context);
        init();
    }


    public Complete(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Complete(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void startAnimation(){



        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentValue= (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2500);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }



    private void init() {

        //这是开发分支

        mCenntX=(this.getX()-this.getLeft())/2;
        mCentY=(this.getY()-this.getTop())/2;

        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);

        mDstPath =new Path();
        mCircle=new Path();

        mCircle.addCircle(mCenntX,mCentY,mRadius, Path.Direction.CW);

        mCircle.moveTo(mCenntX -mRadius/2,mCentY);
        mCircle.lineTo(mCenntX,mCentY+mRadius/2);
        mCircle.lineTo(mCenntX+mRadius/2,mCentY-mRadius/3);

        mPathMeasure=new PathMeasure(mCircle,false);

        animator = ValueAnimator.ofFloat(0,2);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (mCurrentValue<1){
            float stop=mPathMeasure.getLength()*mCurrentValue;
            mPathMeasure.getSegment(0,stop,mDstPath,true);
            canvas.drawPath(mDstPath,mPaint);
        }else {
            if (!isNext){
            isNext=true;
            mPathMeasure.getSegment(0,mPathMeasure.getLength(),mDstPath,true);
            mPathMeasure.nextContour();}
            float stop=mPathMeasure.getLength()*(mCurrentValue-1);
            mPathMeasure.getSegment(0,stop,mDstPath,true);
            canvas.drawPath(mDstPath,mPaint);
        }

    }

}
