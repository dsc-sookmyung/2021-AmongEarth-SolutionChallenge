package com.example.amongearth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth.env.ImageUtils;
import com.example.amongearth.env.Logger;
import com.example.amongearth.env.Utils;
import com.example.amongearth.methods.BrokenBottleActivity;
import com.example.amongearth.methods.CoolPackActivity;
import com.example.amongearth.methods.EyeglassesActivity;
import com.example.amongearth.methods.FruitPackagingActivity;
import com.example.amongearth.methods.GlassActivity;
import com.example.amongearth.methods.IcepackActivity;
import com.example.amongearth.methods.MatActivity;
import com.example.amongearth.methods.MetalActivity;
import com.example.amongearth.methods.NoteActivity;
import com.example.amongearth.methods.NothingActivity;
import com.example.amongearth.methods.PaperActivity;
import com.example.amongearth.methods.PlasticActivity;
import com.example.amongearth.methods.PringlesActivity;
import com.example.amongearth.methods.ScissorsActivity;
import com.example.amongearth.methods.WasteActivity;
import com.example.amongearth.methods.WineglassActivity;
import com.example.amongearth.tflite.Classifier;
import com.example.amongearth.tflite.YoloV4Classifier;
import com.example.amongearth.tracking.MultiBoxTracker;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class YoloActivity extends AppCompatActivity {

    public static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yolo);

        detectButton = findViewById(R.id.detectButton);
        imageView = findViewById(R.id.imageView);

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectButton.setEnabled(false);
                final Handler handler = new Handler();
                Glide.with(YoloActivity.this).load(R.raw.recycle).into(imageView);
                final Comparator<Classifier.Recognition> cmpAsc = new Comparator<Classifier.Recognition>() {
                    @Override
                    public int compare(Classifier.Recognition rhs, Classifier.Recognition lhs) {
                        return Float.compare(rhs.getConfidence(), lhs.getConfidence());
                    }
                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Classifier.Recognition> results = detector.recognizeImage(cropBitmap);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Collections.sort(results, cmpAsc);
                                Collections.reverse(results);
                                String label = "";
                                try {
                                    label = results.get(0).getTitle();
                                }catch(Exception e){
                                    label = "nothing";
                                }
                                switch (label) {
                                    case "paper":
                                        Intent intent0 = new Intent(getApplicationContext(), PaperActivity.class);
                                        startActivity(intent0);
                                        break;
                                    case "metal":
                                        Intent intent1 = new Intent(getApplicationContext(), MetalActivity.class);
                                        startActivity(intent1);
                                        break;
                                    case "glass":
                                        Intent intent2 = new Intent(getApplicationContext(), GlassActivity.class);
                                        startActivity(intent2);
                                        break;
                                    case "plastic":
                                        Intent intent3 = new Intent(getApplicationContext(), PlasticActivity.class);
                                        startActivity(intent3);
                                        break;
                                    case "waste":
                                        Intent intent4 = new Intent(getApplicationContext(), WasteActivity.class);
                                        startActivity(intent4);
                                        break;
                                    case "eyeglasses":
                                        Intent intent5 = new Intent(getApplicationContext(), EyeglassesActivity.class);
                                        startActivity(intent5);
                                        break;
                                    case "pringles":
                                        Intent intent6 = new Intent(getApplicationContext(), PringlesActivity.class);
                                        startActivity(intent6);
                                        break;
                                    case "scissors":
                                        Intent intent7 = new Intent(getApplicationContext(), ScissorsActivity.class);
                                        startActivity(intent7);
                                        break;
                                    case "fruit packaging":
                                        Intent intent8 = new Intent(getApplicationContext(), FruitPackagingActivity.class);
                                        startActivity(intent8);
                                        break;
                                    case "cool pack":
                                        Intent intent9 = new Intent(getApplicationContext(), CoolPackActivity.class);
                                        startActivity(intent9);
                                        break;
                                    case "broken bottle":
                                        Intent intent10 = new Intent(getApplicationContext(), BrokenBottleActivity.class);
                                        startActivity(intent10);
                                        break;
                                    case "spring note":
                                        Intent intent11 = new Intent(getApplicationContext(), NoteActivity.class);
                                        startActivity(intent11);
                                        break;
                                    case "mat":
                                        Intent intent12 = new Intent(getApplicationContext(), MatActivity.class);
                                        startActivity(intent12);
                                        break;
                                    case "wine glass":
                                        Intent intent13 = new Intent(getApplicationContext(), WineglassActivity.class);
                                        startActivity(intent13);
                                        break;
                                    case "icepack":
                                        Intent intent14 = new Intent(getApplicationContext(), IcepackActivity.class);
                                        startActivity(intent14);
                                        break;
                                    case "nothing":
                                        Intent intent15 = new Intent(getApplicationContext(), NothingActivity.class);
                                        startActivity(intent15);
                                        break;
                                }
                            }
                        });
                    }
                }).start();

            }
        });

        Intent intent = getIntent();
        String imgPath = intent.getStringExtra("imgPath");

        this.sourceBitmap = BitmapFactory.decodeFile(imgPath);
        this.cropBitmap = Utils.processBitmap(sourceBitmap, TF_OD_API_INPUT_SIZE);

        initBox();
    }

    private static final Logger LOGGER = new Logger();

    public static final int TF_OD_API_INPUT_SIZE = 416;

    private static final boolean TF_OD_API_IS_QUANTIZED = false;

    private static final String TF_OD_API_MODEL_FILE = "yolov4-custom-30000.tflite";

    private static final String TF_OD_API_LABELS_FILE = "file:///android_asset/custom.txt";

    private static final boolean MAINTAIN_ASPECT = false;
    private Integer sensorOrientation = 90;

    private Classifier detector;

    private Matrix frameToCropTransform;
    private Matrix cropToFrameTransform;
    private MultiBoxTracker tracker;

    protected int previewWidth = 0;
    protected int previewHeight = 0;

    private Bitmap sourceBitmap;
    private Bitmap cropBitmap;

    private Button detectButton;
    private ImageView imageView;

    private void initBox() {
        previewHeight = TF_OD_API_INPUT_SIZE;
        previewWidth = TF_OD_API_INPUT_SIZE;
        frameToCropTransform =
                ImageUtils.getTransformationMatrix(
                        previewWidth, previewHeight,
                        TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE,
                        sensorOrientation, MAINTAIN_ASPECT);

        cropToFrameTransform = new Matrix();
        frameToCropTransform.invert(cropToFrameTransform);

        tracker = new MultiBoxTracker(this);
        tracker.setFrameConfiguration(TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE, sensorOrientation);

        try {
            detector =
                    YoloV4Classifier.create(
                            getAssets(),
                            TF_OD_API_MODEL_FILE,
                            TF_OD_API_LABELS_FILE,
                            TF_OD_API_IS_QUANTIZED);
        } catch (final IOException e) {
            e.printStackTrace();
            LOGGER.e(e, "Exception initializing classifier!");
            Toast toast =
                    Toast.makeText(
                            getApplicationContext(), "Classifier could not be initialized", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }
}
