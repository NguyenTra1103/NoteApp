package com.example.allnotes;

import static com.example.allnotes.DrawActivity.paint_brush;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import static com.example.allnotes.DrawActivity.paint_brush;
import static com.example.allnotes.DrawActivity.path;

public class PaintView extends View {
    public static ArrayList<Path> pathList = new ArrayList<>();
    public static ArrayList<Integer> colorLits = new ArrayList<>();
    public ViewGroup.LayoutParams params;
    public static int current_brush = Color.BLACK;
    public PaintView(Context context) {
        super(context);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        paint_brush.setAntiAlias(true);
        paint_brush.setColor(current_brush);
        paint_brush.setStyle(Paint.Style.STROKE);
        paint_brush.setStrokeCap(Paint.Cap.ROUND);
        paint_brush.setStrokeJoin(Paint.Join.ROUND);
        paint_brush.setStrokeWidth(8f);
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                pathList.add(path);
                colorLits.add(current_brush);
                invalidate();
                return true;
            default:
                return false;
        }
    }
    @Override
    protected void onDraw(Canvas canvas){
        for (int i = 0; i < pathList.size(); i++) {
            paint_brush.setColor(colorLits.get(i));
            canvas.drawPath(pathList.get(i),paint_brush);
            invalidate();
        }
    }
}
