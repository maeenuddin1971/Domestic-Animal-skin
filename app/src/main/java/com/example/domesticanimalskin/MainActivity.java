package com.example.domesticanimalskin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domesticanimalskin.ml.Cow;
import com.example.domesticanimalskin.ml.Duck;
import com.example.domesticanimalskin.ml.Goat;
import com.example.domesticanimalskin.ml.Hen;


import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private Button b,c;
    private ImageView imageView;
    private TextView tf,tf2,tf3;
    private Bitmap img;
    public String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        value=intent.getStringExtra("animal");
        Log.d("maeen",value);
        b=findViewById(R.id.button);
        c=findViewById(R.id.button2);
        tf=findViewById(R.id.textView);
        tf2=findViewById(R.id.textView2);
        tf3=findViewById(R.id.textView5);
        tf3.setText("please select a "+value+" photo");
        imageView=findViewById(R.id.profile_image);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img = Bitmap.createScaledBitmap(img, 224, 224, true);
                if (value.equals("cow")) {

                    try {
                        Cow model = Cow.newInstance(getApplicationContext());

                        // Creates inputs for reference.
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tg = new TensorImage(DataType.FLOAT32);
                        tg.load(img);

                        ByteBuffer byteBuffer = tg.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);

                        // Runs model inference and gets result.
                        Cow.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                        // Releases model resources if no longer used.
                        model.close();

                        tf2.setText(outputFeature0.getFloatArray()[0] + "\n" + outputFeature0.getFloatArray()[1] + "\n" + outputFeature0.getFloatArray()[2] + "\n" + outputFeature0.getFloatArray()[3]);

                        if (outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Dermatophilosis");
                        } else if (outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Healthy");
                        } else if (outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Pepillomatosis");
                        } else {
                            tf.setText("Ringworm");
                        }


                    } catch (IOException e) {
                        // TODO Handle the exception
                    }
                }
                else if(value.equals("hen"))
                {
                    try {
                        Hen model = Hen.newInstance(getApplicationContext());

                        // Creates inputs for reference.
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tg = new TensorImage(DataType.FLOAT32);
                        tg.load(img);
                        ByteBuffer byteBuffer = tg.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        //inputFeature0.loadBuffer(byteBuffer);

                        // Runs model inference and gets result.
                        Hen.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        tf2.setText(outputFeature0.getFloatArray()[0] + "\n" + outputFeature0.getFloatArray()[1] + "\n" + outputFeature0.getFloatArray()[2] + "\n" + outputFeature0.getFloatArray()[3]);

                        if (outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Fleas");
                        } else if (outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Healthy");
                        } else if (outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Poultry Tumor");
                        } else {
                            tf.setText("Scaly leg mites");
                        }

                        // Releases model resources if no longer used.
                        model.close();
                    } catch (IOException e) {
                        // TODO Handle the exception
                    }
                }
                else if(value.equals("duck"))
                {
                    try {
                        Duck model = Duck.newInstance(getApplicationContext());

                        // Creates inputs for reference.
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tg = new TensorImage(DataType.FLOAT32);
                        tg.load(img);
                        ByteBuffer byteBuffer = tg.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        //inputFeature0.loadBuffer(byteBuffer);

                        // Runs model inference and gets result.
                        Duck.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        tf2.setText(outputFeature0.getFloatArray()[0] + "\n" + outputFeature0.getFloatArray()[1] + "\n" + outputFeature0.getFloatArray()[2]);
                        if (outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Bumblefoot");
                        } else if (outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Healthy");
                        } else if (outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Sticky_eye");
                        }
                    }
                    catch(Exception jh)
                    {

                    }
                }
                else
                {
                    try {
                        Goat model = Goat.newInstance(getApplicationContext());

                        // Creates inputs for reference.
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tg = new TensorImage(DataType.FLOAT32);
                        tg.load(img);
                        ByteBuffer byteBuffer = tg.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        //inputFeature0.loadBuffer(byteBuffer);

                        // Runs model inference and gets result.
                        Goat.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        tf2.setText(outputFeature0.getFloatArray()[0] + "\n" + outputFeature0.getFloatArray()[1] + "\n" + outputFeature0.getFloatArray()[2]+"\n"+outputFeature0.getFloatArray()[2]);
                        if (outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[0] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Ecthyma");
                        } else if (outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[2] & outputFeature0.getFloatArray()[1] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Healthy");
                        } else if (outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[0] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[1] & outputFeature0.getFloatArray()[2] > outputFeature0.getFloatArray()[3]) {
                            tf.setText("Lymphadenitis");
                        } else {
                            tf.setText("Ringworm");
                        }

                    }
                    catch (Exception nh)
                    {

                    }
                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10)
        {

            Uri uri=data.getData();
            try {
                img= MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                imageView.setImageBitmap(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}