package com.xiang.suspendproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {
    private TextView mBuyLayout;
    private TextView mTopBuyLayout;
    private MyScrollView myScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
         mBuyLayout = (TextView) findViewById(R.id.buy);
        mTopBuyLayout = (TextView) findViewById(R.id.top_buy_layout);
        myScrollView.setOnScrollListener(this);
//当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }

}
