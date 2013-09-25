package com.tabhost;

import java.util.ArrayList;
import java.util.List;

import com.example.tabhostviewpager.R;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
public class ViewpagerActivity extends Activity {
	
	List<View> listViews;
	Context context =null;
	@SuppressWarnings("deprecation")
	LocalActivityManager manger = null;
	TabHost tabHost = null;
	private ViewPager pager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		
		context = ViewpagerActivity.this;
		pager = (ViewPager)findViewById(R.id.viewpager);
		
		//定放一个放view的list，用于存放viewpager用的的view
		
		listViews = new ArrayList<View>();
		manger  = new LocalActivityManager(this, true);
		manger.dispatchCreate(savedInstanceState);
		
		Intent i1 = new Intent(context,T1Activity.class);
		listViews.add(getView("A",i1));
		Intent i2 = new Intent(context,T2Activity.class);
		listViews.add(getView("B",i2));
		Intent i3 = new Intent(context,T3Activity.class);
		listViews.add(getView("C", i3));
		
		tabHost = (TabHost)findViewById(R.id.tabhost);
		tabHost.setup();
		tabHost.setup(manger);
		
		//这里定义一下tabhost中tab的样式
		RelativeLayout tLayout1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget_view, null);
		TextView view1 = (TextView)tLayout1.findViewById(R.id.tv_title);
		view1.setText("第1页");
		
		RelativeLayout tLayout2 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget_view, null);
		TextView view2 = (TextView)tLayout2.findViewById(R.id.tv_title);
		view2.setText("第2页");
		
		RelativeLayout tLayout3 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget_view, null);
		TextView view3 = (TextView)tLayout3.findViewById(R.id.tv_title);
		view3.setText("第3页");
		
		Intent intent = new Intent(context,EmptyActivity.class);
		//注意这里intent中的activity不能是自身，比如A对应的是T1Activity，后面intent就new T3Activity
		tabHost.addTab(tabHost.newTabSpec("A").setIndicator(tLayout1).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator(tLayout2).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("c").setIndicator(tLayout3).setContent(intent));
		
		
		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				tabHost.setCurrentTab(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//点击tabHost中的tab时，要切换下面的viewPager
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if("A".equals(tabId)){
					pager.setCurrentItem(0);
				}
				if("B".equals(tabId)){
					pager.setCurrentItem(1);
				}
				if("c".equals(tabId)){
					pager.setCurrentItem(2);
				}
			}
		});
	}
	private View getView(String string, Intent i1) {
		// TODO Auto-generated method stub
		return manger.startActivity(string,i1).getDecorView();
	}
	
	
	public class MyPageAdapter extends PagerAdapter{
		
		private List<View> list;
		private MyPageAdapter(List<View> list){
			
			this.list = list;
		}
		
		@Override
		public void destroyItem(View view, int position, Object object) {
			// TODO Auto-generated method stub
			ViewPager pViewPager = ((ViewPager)view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View container) {
			// TODO Auto-generated method stub
			super.finishUpdate(container);
		}
		
		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			ViewPager viewPager = ((ViewPager) container);
			viewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
	}
}





















