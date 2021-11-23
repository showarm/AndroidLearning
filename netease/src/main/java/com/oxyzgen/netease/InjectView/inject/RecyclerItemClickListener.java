package com.oxyzgen.netease.InjectView.inject;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

  private OnItemClickListener mListener;

  GestureDetector mGestureDetector;

  public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
    mListener = listener;
    mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onSingleTapUp(MotionEvent e) {
        return true;
      }
    });
  }
  public interface OnItemClickListener {
    void onItemClick(View view, int position);
  }

  @Override
  public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
    View childView = view.findChildViewUnder(e.getX(), e.getY());
    if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
      mListener.onItemClick(childView, view.getChildPosition(childView));
      return true;
    }
    return false;
  }

  @Override
  public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

  }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean b) {

  }
}
