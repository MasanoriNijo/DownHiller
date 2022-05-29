/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package org.cocos2dx.cpp;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import jp.maru.scorecenter.ScoreCenter;

public class AppActivity extends Cocos2dxActivity {

	// �C������������ static ���\�b�h����Q�Ƃ���Ƃ��ɕK�v
	private static AppActivity me = null;
	// 設定値
	private static final String DEFAULT_ENCORDING = "UTF-8";// デフォルトのエンコード
	private static final int DEFAULT_READ_LENGTH = 8192;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		me = this;

		// 4-2. (1) ������
		ScoreCenter scoreCenter = ScoreCenter.getInstance();
		scoreCenter.initialize(getApplicationContext());

		// �X�R�A�Z���^�[�Ƀ��O�C������Ă��܂���
		// scoreCenter.hello();

		scoreCenter.setLogEnable(true);
		scoreCenter.setKeepViewCacheEnable(true);

	}

	// JNI ����Ăяo����郁�\�b�h
	public static void postHighScore(int score) {
		// UI�X���b�h�Ŏ��s
		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// ����...
			}
		});
	}

	public static void writeDeta(final int index, final int index2, final String detast) {
		// final int data[]
		// String detast;
		// Context cnt=me;
		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				File detaFile = new File(Environment.getExternalStorageDirectory(), "cource_" + index + "_" + index2);

				if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					Toast.makeText(me, "保存用できません！", Toast.LENGTH_SHORT).show();
				}

				try {

					FileOutputStream mOutPut = new FileOutputStream(detaFile, false);
					mOutPut.write(detast.getBytes());
					/*
					 * for(int i=0;i<data.length;i++){ mOutPut.write(data[i]); }
					 */
					// mOutPut.write("def".getBytes());
					mOutPut.close();
					Toast.makeText(me, "cource_" + index + "_" + index2 + "に書き込みました！", Toast.LENGTH_SHORT).show();

				} catch (Exception e) {

					Toast.makeText(me, e.toString(), Toast.LENGTH_SHORT).show();

				}

			}
		});

	}

	public static String readDeta(final int index, final int index2) {
		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				try {

					Toast.makeText(me, "cource_" + index + "_"+index2 +"を読み込みました！", Toast.LENGTH_SHORT).show();

				} catch (Exception e) {

					Toast.makeText(me, e.toString(), Toast.LENGTH_SHORT).show();

				}

			}
		});

		// String s="";
		// s = Environment.getExternalStorageDirectory().getPath();
		// return(s);

		// me.runOnUiThread(new Runnable() {

		// @Override
		// public void run() {

		try {
			// File detaFile = new File(Environment
			// .getExternalStorageDirectory(), "meiro_117" );

			// AssetManager manager= getResources().getAssets();

			// File detaFile = new
			// File(Environment.getExternalStorageDirectory(),
			// "meiro_" + index);
			File detaFile = new File(Environment.getExternalStorageDirectory(), "cource_" + index + "_" + index2);

			FileInputStream mInPut = new FileInputStream(detaFile);

			byte[] data = new byte[mInPut.available()];
			mInPut.read(data);
			mInPut.close();
			// Toast.makeText(me, "meiro_" + index +
			// "を読み込みました！",Toast.LENGTH_SHORT).show();
			return new String(data);

		} catch (Exception e) {

			// Toast.makeText(me, e.toString(), Toast.LENGTH_SHORT).show();

		}
		return "N";

		// }
		// });

	}

	public static final String readDeta2(final int index,final int index2) {

		// アセットファイルから読み出す。

		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				try {

					Toast.makeText(me, "assetFileよりcource__" + index + "_" + index2 + "を読み込みました！", Toast.LENGTH_SHORT).show();

				} catch (Exception e) {

					Toast.makeText(me, e.toString(), Toast.LENGTH_SHORT).show();

				}

			}
		});

		try {

			// return loadTextAsset("meirodeta/meiro_" + index,this);

		} catch (Exception e) {

			// Toast.makeText(me, e.toString(), Toast.LENGTH_SHORT).show();

		}
		return null;

		// }
		// });

	}

	// assets フォルダから、テキストファイルを読み込む(Android 用)
	public static final String loadTextAsset(String fileName, Context context) throws IOException {
		final AssetManager assetManager = context.getAssets();
		InputStream is = assetManager.open(fileName);
		return loadText(is, DEFAULT_ENCORDING);
	}

	// ストリームから読み込み、テキストエンコードして返す
	public static final String loadText(InputStream inputStream, String charsetName)
			throws IOException, UnsupportedEncodingException {
		return new String(readStream(inputStream, DEFAULT_READ_LENGTH), charsetName);
	}

	// ストリームから読み込み、バイト配列で返す
	public static final byte[] readStream(InputStream inputStream, int readLength) throws IOException {
		final ByteArrayOutputStream byteStream = new ByteArrayOutputStream(readLength); // 一時バッファのように使う
		final byte[] bytes = new byte[readLength]; // read() 毎に読み込むバッファ
		final BufferedInputStream bis = new BufferedInputStream(inputStream, readLength);

		try {
			int len = 0;
			while ((len = bis.read(bytes, 0, readLength)) > 0) {
				byteStream.write(bytes, 0, len); // ストリームバッファに溜め込む
			}
			return byteStream.toByteArray(); // byte[] に変換

		} finally {
			try {
				byteStream.reset(); // すべてのデータを破棄
				bis.close(); // ストリームを閉じる
			} catch (Exception e) {
				// IOException
			}
		}
	}

	public static void tweet(final int score) {
		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,
						"坂チャリ下り\r\n"
								+ "URL_https://play.google.com/store/apps/details?id=jp.gr.java_conf.kotokotobokan.downhiller \r\n"
								+ "獲得コイン:" + score);
				me.startActivity(intent);
			}
		});
	}

	public static void openRankPark() {
		// final Context ctx = this;
		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				ScoreCenter.getInstance().show("downhiller_scoreboard1");
			}
		});
	}

	public static void sendRankParkScore(final int score) {
		// final Context ctx = this;
		me.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ScoreCenter.getInstance().postScore("downhiller_scoreboard1", java.lang.String.valueOf(score));
			}
		});
	}

}
