package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public Deque<String> phrases;
    public String engPhrase;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    Button clickhere;
    Button clickhere2;
    TextView engText;


    public String getFullPath(String filename) {
        String path = System.getProperty("user.dir");
        String whole_path = path + "/src/file/" + filename;
//        System.out.println(whole_path);
//        System.out.println("/home/cosc/student/ejd83/IdeaProjects/Kiri_subtitles/src/file" + "/pop phrases.txt");
        return whole_path;
    }


    public Deque<String> getNewPhrases() {
        Deque<String> deque
                = new LinkedList<String>();
        try {
            String path = System.getProperty("user.dir");
            String whole_path = getFullPath("pop phrases.txt");
            File myObj = new File(whole_path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                    System.out.println(data);
                if (!data.equals("")) {
                    deque.add(data);
                }
            }
            //                System.out.println(deque);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return deque;
    }

    public String getPhrase(Deque<String> phrases) {
        return phrases.pop();
    }

    public void writePhraseToCorrect(View view) {
        try {
            EditText editText = (EditText) findViewById(R.id.editText);
            String kirPhrase = editText.getText().toString();
            String filepath = getFullPath("new_phrases.txt");

            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(engPhrase + " : " + kirPhrase + "\n\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

    public void writePhraseToIgnore(View view) {
        try {
            EditText editText = (EditText) findViewById(R.id.editText);
            String kirPhrase = editText.getText().toString();
            String filepath = getFullPath("bl_phrases.txt");
//            if (newPhrase) filepath = getFullPath("new_phrases.txt");

            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(engPhrase + " : " + kirPhrase + "\n\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        phrases = getNewPhrases();
//        engPhrase = getPhrase(phrases);
        engPhrase = "hi";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickhere = (Button) findViewById(R.id.button);
        clickhere2 = (Button) findViewById(R.id.button2);

        clickhere.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

             engPhrase = getPhrase(phrases);
             engText.setText(engPhrase);
         }
        });

        clickhere2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                engPhrase = getPhrase(phrases);
                engText.setText(engPhrase);
            }
        });
    }

    /** Called when the user taps the Send button */
//    public void sendMessage(View view) {
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//    }

}
