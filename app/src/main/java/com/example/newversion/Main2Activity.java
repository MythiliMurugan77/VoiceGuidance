package com.example.newversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.ArrayList;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    TextToSpeech t1;
    private TextView voiceInput,tv4,tv5;
    private TextView speakButton;
    public Button b1,b2;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public String[] features={"BMI input","alarm off","view diet chart","add food details"};
    public static String send="";
    public String[] answer={"height in centimeter and weight in kilograms","click on water remainder menu then  switch off ",
            "click on calorie menu and view food details","click on calorie button then enter particular food details" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    t1.setSpeechRate(0.8f);
                }
            }
        });
        voiceInput = (TextView) findViewById(R.id.voiceInput);
       speakButton = (TextView) findViewById(R.id.btnSpeak);
        b1=findViewById(R.id.hear);
        b2=findViewById(R.id.hear1);
        tv5=findViewById(R.id.tv5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseApp.initializeApp(this);

                // Create an English-Tamil translator:

                FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                        .setTargetLanguage(FirebaseTranslateLanguage.TA)
                        .build();
                final FirebaseTranslator englishTamilTranslator =
                        FirebaseNaturalLanguage.getInstance().getTranslator(options);
                //download model
                FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                        .requireWifi()
                        .build();
                englishTamilTranslator.downloadModelIfNeeded(conditions)
                        .addOnSuccessListener(
                                new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void v) {
                                        // Model downloaded successfully. Okay to start translating.
                                        // (Set a flag, unhide the translation UI, etc.)
        //                                Toast.makeText(getApplicationContext(),"download",Toast.LENGTH_LONG).show();
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Model couldn’t be downloaded or other internal error.
                                        // ...
                                    }
                                });
                //calling translate
                String text=send;
                englishTamilTranslator.translate(text)
                        .addOnSuccessListener(
                                new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(@NonNull String translatedText) {
                                        // Translation successful.
          //                              Toast.makeText(getApplicationContext(),"translated"+translatedText,Toast.LENGTH_LONG).show();
                                        tv5.setText(translatedText);

                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Error.
                                        // ...
                                    }
                                });

            }
        });
        tv4=findViewById(R.id.tv4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.speak(send, TextToSpeech.QUEUE_FLUSH, null);
                tv4.setText(send);
            }
        });
        speakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                voiceInput.setText("");
                tv4.setText("");
                tv5.setText("");askSpeechInput();
            }
        });

    }

    // Showing google speech input dialog

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Provide your query");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(result.get(0));
                    String msg=result.get(0);
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                    int flag = 0;
                    int n=features.length;
                    for(int i=0;i<n;i++)
                    {
                        if(features[i].compareTo(msg)==0)
                        {
                            send=answer[i];
                            flag=1;
                            break;
                        }
                        else
                        {
                            flag=0;
                        }
                    }
                    if(flag==0)
                    {
                        send="sorry no answer for your query";
                    }
            //        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }
                break;
            }

        }
    }
}
