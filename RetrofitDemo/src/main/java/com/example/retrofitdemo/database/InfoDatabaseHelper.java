package com.example.retrofitdemo.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.retrofitdemo.base.HistoricalData;

import static com.example.retrofitdemo.base.HistoricalData.CONTENT;

public class InfoDatabaseHelper extends SQLiteOpenHelper {
   
	private static final String dataBaseName="ContactsInfo.db";
	private static final int version=1;
	
	private static final String DB_CREATE_CONTACTS="create table if not exists "
			            + HistoricalData.PERSON_INFO_TABLE+"("+"_id INTEGER primary key autoincrement,"
						+ HistoricalData.CONTENT+" TEXT UNIQUE,"
						+ HistoricalData.RESULT+" TEXT ,"
						+ HistoricalData.DATE+" TEXT )";
	
	@SuppressLint("NewApi")
	public InfoDatabaseHelper(Context context) {
		super(context, dataBaseName, null, version, null);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE_CONTACTS);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	/**
	 * query()方法查询
	 * 一些查询用法
	 */
	public Cursor queryPersonData(String s) {

		Cursor cursor = null;
		String rawQuerySql = null;

		//查询全部数据
		rawQuerySql =  "select * from "+HistoricalData.PERSON_INFO_TABLE;
		//查询_id = 1 的数据  select * from person where _id = 1
		rawQuerySql = "select * from "+HistoricalData.PERSON_INFO_TABLE+" where "+CONTENT +" = s";
		return cursor;
	}
}
