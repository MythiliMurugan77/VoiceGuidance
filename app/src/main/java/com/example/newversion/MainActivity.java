package com.example.newversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateRemoteModel;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.Set;

public class MainActivity extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
           // Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
            switch (item.getItemId()) {
                case R.id.search_item:
             //       Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.upload_item:
                    // do your code
                    return true;
                case R.id.copy_item:
                    // do your code
                    return true;
                case R.id.print_item:
                    // do your code
                    return true;
                case R.id.share_item:
                    Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(i);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }


