package com.rd.callcar;

import android.app.Application;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.baidu.mapapi.*;

public class App extends Application {

	static App mDemoApp;

	// �ٶ�MapAPI�Ĺ�����
	public BMapManager mBMapMan = null;

	// ��ȨKey
	// TODO: ����������Key,
	// �����ַ��http://dev.baidu.com/wiki/static/imap/key/
	public String mStrKey = "E3041FEDFA4A24627A4B76539E07658B0FE44A5D";
	boolean m_bKeyRight = true; // ��ȨKey��ȷ����֤ͨ��

	// �����¼���������������ͨ�������������Ȩ��֤�����
	public static class MyGeneralListener implements MKGeneralListener {
		@Override
		public void onGetNetworkState(int iError) {
//			Toast.makeText(App.mDemoApp.getApplicationContext(), "���������������",
//					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// ��ȨKey����
				Toast.makeText(App.mDemoApp.getApplicationContext(),
						"����BMapApiDemoApp.java�ļ�������ȷ����ȨKey��", Toast.LENGTH_LONG)
						.show();
				App.mDemoApp.m_bKeyRight = false;
			}
		}
	}

	@Override
	public void onCreate() {
		mDemoApp = this;
		mBMapMan = new BMapManager(this);
		mBMapMan.init(this.mStrKey, new MyGeneralListener());
		mBMapMan.getLocationManager().setNotifyInternal(10, 5);
		// if (mBMapMan != null) {
		// mBMapMan.destroy();
		// mBMapMan = null;
		// }

		super.onCreate();
	}

	@Override
	// ��������app���˳�֮ǰ����mapadpi��destroy()�����������ظ���ʼ��������ʱ������
	public void onTerminate() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onTerminate();
	}

	public void SaveBegin(String name, String begin) {
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.edit().putString(name, begin).commit();
	}

	public String getUSerid() {
		return PreferenceManager.getDefaultSharedPreferences(
				getApplicationContext()).getString("userid", "");
	}

	public void SaveLogin(boolean begin) {
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.edit().putBoolean("islogin", begin).commit();
	}

	public boolean getLogin() {
		return PreferenceManager.getDefaultSharedPreferences(
				getApplicationContext()).getBoolean("islogin", false);
	}

	public void ShowToast(int msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void ShowToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
