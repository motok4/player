package com.example.myfirstapp;

import java.io.File;
import java.util.ArrayList;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	public MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
            //Carrega a classe
             mp = new MediaPlayer();
 
            //No emulador adicionei a mp3 no sdcard pelo eclipse android explorer
            String path = "/sdcard/musicas/led1.mp3";
 
            //limpando o tocador, como se tivesse criado agora
            mp.reset();
 
            //colocando o caminho onde esta a mp3
            mp.setDataSource(path);
 
            //Se tudo ocorrer bem, muda o estado do objeto para preparado
            mp.prepare();
 
            // inicia a musica
            mp.start();
 
            //Quando a musica terminar invoca esse metodo
            mp.setOnCompletionListener(new OnCompletionListener() {
 
                public void onCompletion(MediaPlayer arg0) {
                    System.out.println("Acabou a musica");
 
                }
            });
 
        } catch (Exception e) {
            // TODO: handle exception
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void sendMessage(View view) {
		
        mp.pause();
		
		/*
		 * busca a lista de arquivos de mp3 do cartão
		 */
		ArrayList<String> lista = new ListaDeMusicas().buscarMusicas().getList();
		
        Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message + "\n" + lista);
		startActivity(intent);
 		
	}

}
