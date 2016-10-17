package com.example.abymani.image_comm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Selector extends AppCompatActivity {

    private static final int REQUEST_PATH = 1;
    String curFileName;
    String curFilePath;
    EditText edittext;
    final Hashtable bitTocolor = new Hashtable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        edittext = (EditText)findViewById(R.id.editText);
    }
    //handling click event of browser button
    //calling filechooser activity to choose a file for sending
    public void getfile(View view){
        Intent intent1 = new Intent(this, FileChooser.class);

        startActivityForResult(intent1,REQUEST_PATH);
    }
    // Listen for results.
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
        if (requestCode == REQUEST_PATH){
            if (resultCode == RESULT_OK) {
                curFileName = data.getStringExtra("GetFileName");
                curFilePath= data.getStringExtra("GetPath");
                edittext.setText(curFileName);
            }
        }
    }

    public void sending(View view) {

        File wiki_path = new File(curFilePath + "/" + curFileName);

        if(!wiki_path.exists())
        {
            Toast.makeText(getApplicationContext(),"Please select a File first",Toast.LENGTH_SHORT).show();}
        else {
            if(wiki_path.length()==0)
            {toast(String.valueOf(wiki_path.length()));}
            else {
                if(wiki_path.length()<10000) {

                    //read file in binary
                    char[] hex_file;

                    hex_file = readbinary(wiki_path);


                    //encode file in colors
                    String[] colors;
                    colors = encode_hex_into_colors(hex_file);

                    //Display colors
                    Intent display = new Intent(this, Display.class);
                    display.putExtra("color", colors);
                    startActivity(display);
                }
                else{
                    toast("File Size is out of range! Please select a file under 1mb");
                }
            }
        }

    }
    //Encoding hex digits into colors
    private String[] encode_hex_into_colors(char[] hex_file) {
        String[] colors_in = new String[hex_file.length];
        HashTablefunc();
        for (int i=0;i<hex_file.length;i++) {
            colors_in[i]= String.valueOf(bitTocolor.get(String.valueOf(hex_file[i])));
         //   toast(colors_in[i]);

        }
       // Toast.makeText(getApplicationContext(),"vvhjvjhbjh", Toast.LENGTH_SHORT).show();
        return colors_in;

    }
    //Reading file in bytes and converting it into hex digits
    public char[] readbinary(File wiki_path){

        char[] charArray_file = new char[0];
        try {
            byte[] data = FileUtils.readFileToByteArray(wiki_path);

            charArray_file = Hex.encodeHex(data);
           // Toast.makeText(getApplicationContext(),String.valueOf(charArray_file.length),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charArray_file;
     }
    //Creating a hashtable for encoding purpose
    private void HashTablefunc() {
        String[] Kelly_colors_hex = {
//                "#FFB300", // Vivid Yellow
//                "#803E75", //Strong Purple
//                "#FF6800", //Vivid Orange
//                "#A6BDD7", //Very Light Blue
//                "#C10020", //Vivid Red
//                "#CEA262", //Grayish Yellow
//                "#817066", //Medium Gray
//
//                //The following don't work well for people with defective color vision
//                "#007D34", //Vivid Green
//                "#F6768E", //Strong Purplish Pink
//                "#00538A", //Strong Blue
//                "#FF7A5C", //Strong Yellowish Pink
//                "#53377A", //Strong Violet
//                "#FF8E00", //Vivid Orange Yellow
//                "#B32851", //Strong Purplish Red
//                "#F4C800", //Vivid Greenish Yellow
//                "#7F180D", //Strong Reddish Brown
//                "#93AA00", //Vivid Yellowish Green
//                "#593315", //Deep Yellowish Brown
//                "#F13A13", //Vivid Reddish Orange
//                "#232C16", //Dark Olive Green

                "#000000",//black     0
                "#ff0000",//Red       1
                "#00ff00",//green     2
                "#0000ff",//blue        3
                "#ffffff",//white       4
                "#ff00ff",//pink        5
                "#ffff00",//yellow      6
                "#00ffff",//cyan        7
                "#646464",//gray shade rgb(100, 100, 100)   8
                "#000064",//dark purple rgb(0, 0, 100)      9
                "#960096",//dark pink rgb(150, 0, 150)      A
                "#960000",//redish brown rgb(150, 0, 0)     B
                "#000096",// dark blue rgb(0, 0, 150)       C
                "#ff9600",//yellowish brown rgb(255, 150, 0)D
                "#0096ff",//Light blue rgb(0, 150, 255)     E
                "#9600ff"//purple rgb(150, 0, 255)          F



        };



        for (int i = 0; i < 16; i++) {
            if (i < 10) {
                // char x = ((char) i);
                bitTocolor.put(String.valueOf(i), Kelly_colors_hex[i]);}
            else if (i == 10)
                bitTocolor.put("a", Kelly_colors_hex[i]);
            else if (i == 11)
                bitTocolor.put("b", Kelly_colors_hex[i]);
            else if (i == 12)
                bitTocolor.put("c", Kelly_colors_hex[i]);
            else if (i == 13)
                bitTocolor.put("d", Kelly_colors_hex[i]);
            else if (i == 14)
                bitTocolor.put("e", Kelly_colors_hex[i]);
            else if (i == 15)
                bitTocolor.put("f", Kelly_colors_hex[i]);
        }



    }
    //temporary func to debug
    public void toast(String text) {

        Toast t1 = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        t1.show();

    }


}
