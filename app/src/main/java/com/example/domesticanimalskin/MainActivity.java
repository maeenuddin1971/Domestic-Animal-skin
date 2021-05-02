package com.example.domesticanimalskin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domesticanimalskin.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private Button b,c;
    private ImageView imageView;
    private TextView tf,tf2;
    private Bitmap img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=findViewById(R.id.button);
        c=findViewById(R.id.button2);
        tf=findViewById(R.id.textView);
        tf2=findViewById(R.id.textView2);
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
                 img=Bitmap.createScaledBitmap(img,224,224,true);
                try {
                    ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                    TensorImage tg=new TensorImage(DataType.FLOAT32);
                    tg.load(img);

                    ByteBuffer byteBuffer=tg.getBuffer();
                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();
                    
                    tf2.setText(outputFeature0.getFloatArray()[0]+"\n"+outputFeature0.getFloatArray()[1]+"\n"+outputFeature0.getFloatArray()[2]+"\n"+outputFeature0.getFloatArray()[3]);

                     if(outputFeature0.getFloatArray()[0]>outputFeature0.getFloatArray()[1]&outputFeature0.getFloatArray()[0]>outputFeature0.getFloatArray()[2]&outputFeature0.getFloatArray()[0]>outputFeature0.getFloatArray()[3])
                     {
                         tf.setText("Dermatophilosis");
                     }
                     else if(outputFeature0.getFloatArray()[1]>outputFeature0.getFloatArray()[0]&outputFeature0.getFloatArray()[1]>outputFeature0.getFloatArray()[2]&outputFeature0.getFloatArray()[1]>outputFeature0.getFloatArray()[3])
                     {
                           tf.setText("Healthy");
                     }
                     else if(outputFeature0.getFloatArray()[2]>outputFeature0.getFloatArray()[0]&outputFeature0.getFloatArray()[2]>outputFeature0.getFloatArray()[1]&outputFeature0.getFloatArray()[2]>outputFeature0.getFloatArray()[3])
                     {
                            tf.setText("Pepillomatosis");
                     }
                     else
                     {
                         tf.setText("Ringworm");
                     }


                } catch (IOException e) {
                    // TODO Handle the exception
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