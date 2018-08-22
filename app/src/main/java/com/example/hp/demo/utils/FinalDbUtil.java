package com.example.hp.demo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalDb.DbUpdateListener;

/**
 * 封装FinalDb
 */
public class FinalDbUtil {

	public static final String DB_NAME = "znsk";
	public static final int DB_VERSION = 1;

	private static FinalDbUtil instance;
	
	private FinalDb db;
	
	private FinalDbUtil(Context context) {
		db = FinalDb.create(context, DB_NAME, true, DB_VERSION, UpdateListener.getInstance());
	}
	
	public synchronized static FinalDbUtil getInstance(Context context) {
		if (instance == null) {
			instance = new FinalDbUtil(context);
		}
		return instance;
	}
	
	public FinalDb getFinalDb() {
		return db;
	}
	
	static class UpdateListener implements DbUpdateListener {
		
		static UpdateListener listener = null;
		
		public static UpdateListener getInstance(){
			if (listener == null) {
				listener = new UpdateListener();
			}
			return listener;
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		}
	}
}
