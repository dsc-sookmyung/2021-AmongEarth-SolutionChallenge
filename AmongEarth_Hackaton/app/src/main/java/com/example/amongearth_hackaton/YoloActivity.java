package com.example.amongearth_hackaton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth_hackaton.customview.OverlayView;
import com.example.amongearth_hackaton.env.ImageUtils;
import com.example.amongearth_hackaton.env.Logger;
import com.example.amongearth_hackaton.env.Utils;
import com.example.amongearth_hackaton.methods.BrokenBottleActivity;
import com.example.amongearth_hackaton.methods.CoolPackActivity;
import com.example.amongearth_hackaton.methods.EyeglassesActivity;
import com.example.amongearth_hackaton.methods.FruitPackagingActivity;
import com.example.amongearth_hackaton.methods.GlassActivity;
import com.example.amongearth_hackaton.methods.IcepackActivity;
import com.example.amongearth_hackaton.methods.MatActivity;
import com.example.amongearth_hackaton.methods.MetalActivity;
import com.example.amongearth_hackaton.methods.NoteActivity;
import com.example.amongearth_hackaton.methods.NothingActivity;
import com.example.amongearth_hackaton.methods.PaperActivity;
import com.example.amongearth_hackaton.methods.PlasticActivity;
import com.example.amongearth_hackaton.methods.PringlesActivity;
import com.example.amongearth_hackaton.methods.ScissorsActivity;
import com.example.amongearth_hackaton.methods.WasteActivity;
import com.example.amongearth_hackaton.methods.WineglassActivity;
import com.example.amongearth_hackaton.tflite.Classifier;
import com.example.amongearth_hackaton.tflite.YoloV4Classifier;
import com.example.amongearth_hackaton.tracking.MultiBoxTracker;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class YoloActivity extends AppCompatActivity {

    public static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yolo);

        // cameraButton = findViewById(R.id.cameraButton);
        detectButton = findViewById(R.id.detectButton);
        imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.raw.recycle).into(imageView);

        // textView = findViewById(R.id.textView);
        // cameraButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DetectorActivity.class)));

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler();

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
                                // handleResult(cropBitmap, results);
                                Log.d("ResultLabel1", results.toString());
                                Collections.sort(results, cmpAsc); // Confidence 값 정렬
                                Collections.reverse(results); // 역순! Confidence 가장 큰 값이 가장 앞에 오도록!
                                // Log.d("ResultLabel2", results.get(0).getTitle()); // Label!!   // 인식된게 하나도 없을 때 오류가 난다,,
                                // 인식된게 하나도 없을 때 nothing을 출력하도록 예외처리
                                String label = "";
                                try {
                                    //textView.setText(results.get(0).getTitle());
                                    label = results.get(0).getTitle();
                                    // throw new Exception();
                                }catch(Exception e){
                                    //textView.setText("nothing");
                                    label = "nothing";
                                }
                                // textView.setText(results.get(0).getTitle());
                                // String label = results.get(0).getTitle();
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

//        Bundle b = intent.getExtras();
//        this.sourceBitmap = (Bitmap)b.get("imgBitmap");

        //Log.d("imgPath", imgPath);
        // String imgPath="";

        //this.sourceBitmap = Utils.getBitmapFromAsset(YoloActivity.this, imgPath);

        this.sourceBitmap = BitmapFactory.decodeFile(imgPath);

        this.cropBitmap = Utils.processBitmap(sourceBitmap, TF_OD_API_INPUT_SIZE);

        /*this.imageView.setImageBitmap(cropBitmap); // 이미지 출력!*/

        initBox();
    }

    private static final Logger LOGGER = new Logger();

    public static final int TF_OD_API_INPUT_SIZE = 416;

    private static final boolean TF_OD_API_IS_QUANTIZED = false;

    private static final String TF_OD_API_MODEL_FILE = "yolov4-custom.tflite";

    private static final String TF_OD_API_LABELS_FILE = "file:///android_asset/custom.txt";

    // Minimum detection confidence to track a detection.
    private static final boolean MAINTAIN_ASPECT = false;
    private Integer sensorOrientation = 90;

    private Classifier detector;

    private Matrix frameToCropTransform;
    private Matrix cropToFrameTransform;
    private MultiBoxTracker tracker;
    private OverlayView trackingOverlay;

    protected int previewWidth = 0;
    protected int previewHeight = 0;

    private Bitmap sourceBitmap;
    private Bitmap cropBitmap;

    private Button detectButton;
    private ImageView imageView;
    //private TextView textView;

    private void initBox() {
        previewHeight = TF_OD_API_INPUT_SIZE; // 416
        previewWidth = TF_OD_API_INPUT_SIZE; // 416
        frameToCropTransform =
                ImageUtils.getTransformationMatrix(
                        previewWidth, previewHeight,
                        TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE,
                        sensorOrientation, MAINTAIN_ASPECT);

        cropToFrameTransform = new Matrix();
        frameToCropTransform.invert(cropToFrameTransform);

        tracker = new MultiBoxTracker(this);
        trackingOverlay = findViewById(R.id.tracking_overlay);
        trackingOverlay.addCallback(
                new OverlayView.DrawCallback() {
                    @Override
                    public void drawCallback(Canvas canvas) {
                        tracker.draw(canvas);
                    }
                });

        tracker.setFrameConfiguration(TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE, sensorOrientation);

        // YoloV4 Classifier init
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

    private void handleResult(Bitmap bitmap, List<Classifier.Recognition> results) {  // 이미지 빨간색 바운딩 박스!
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);

        final List<Classifier.Recognition> mappedRecognitions =
                new LinkedList<Classifier.Recognition>();

        for (final Classifier.Recognition result : results) {
            final RectF location = result.getLocation();
            if (location != null && result.getConfidence() >= MINIMUM_CONFIDENCE_TF_OD_API) {
                canvas.drawRect(location, paint);
//                cropToFrameTransform.mapRect(location);
//
//                result.setLocation(location);
//                mappedRecognitions.add(result);
            }
        }
//        tracker.trackResults(mappedRecognitions, new Random().nextInt());
//        trackingOverlay.postInvalidate();
        imageView.setImageBitmap(bitmap);
    }
}