package com.example.myfirstapp;

import java.io.File;
import java.util.ArrayList;

import android.os.Environment;

public class ListaDeMusicas {
	private File sdcardObj = new File(Environment.getExternalStorageDirectory()
			.getAbsolutePath());
	private ArrayList<String> filelist = new ArrayList<String>();

	public ArrayList<String> getList(){
		return this.filelist;
	
	}
	public ListaDeMusicas buscarMusicas(){
		listFiles(this.sdcardObj, this.filelist);
		return this;
	}
	private void listFiles(File sdcard, ArrayList<String> filelist) {
		if (sdcard.isDirectory()) {
			File[] files = sdcard.listFiles();

			try {
				for (File f : files) {
					if (!f.isDirectory()) {
						if (f.getName().endsWith(".mp3")) {
							// Log.d(" FILES",f.getName());
							this.filelist.add(f.getAbsolutePath());

						}
					} else {
						this.listFiles(f, this.filelist);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}
}
