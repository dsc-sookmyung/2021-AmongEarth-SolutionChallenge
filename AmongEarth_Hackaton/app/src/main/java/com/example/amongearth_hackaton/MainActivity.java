package com.example.amongearth_hackaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amongearth_hackaton.Community_Page.Community_MainActivity;
import com.example.amongearth_hackaton.mypage.MyBadgeActivity;
import com.example.amongearth_hackaton.mypage.MyPostsActivity;
import com.example.amongearth_hackaton.mypage.MyRecordActivity;
import com.example.amongearth_hackaton.mypage.MyStatsActivity;
import com.google.android.material.navigation.NavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import static android.os.Environment.DIRECTORY_PICTURES;

public class MainActivity extends AppCompatActivity {
    LinearLayout Btn1, Btn2, Btn3;
    ImageView profile;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View navHeader;
    ImageView close_nav;
    TextView view_all;

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private static final int REQUEST_IMAGE_CAPTURE2 = 680;
    private String imageFilePath;
    private Uri photoUri;

    private MediaScanner mMediaScanner; // 사진 저장 시 갤러리 폴더에 바로 반영사항을 업데이트 시켜주려면 이 것이 필요하다(미디어 스캐닝)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 사진 저장 후 미디어 스캐닝을 돌려줘야 갤러리에 반영됨.
        mMediaScanner = MediaScanner.getInstance(getApplicationContext());

        // 권한 체크
        TedPermission.with(getApplicationContext()).setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("거부하셨습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

        navigationView = findViewById(R.id.navigationView);
        profile = findViewById(R.id.profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        close_nav = findViewById(R.id.close_nav);
        profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        navHeader = navigationView.getHeaderView(0);
        view_all = (TextView) navHeader.findViewById(R.id.view_all);
        view_all.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myBadge = new Intent(getApplicationContext(), MyBadgeActivity.class);
                startActivity(myBadge);
            }
        });
        close_nav = (ImageView) navHeader.findViewById(R.id.close_nav);
        close_nav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.my_posts:
                        Intent myPosts = new Intent(getApplicationContext(), MyPostsActivity.class);
                        startActivity(myPosts);
                        break;

                    case R.id.my_waste_record:
                        Intent myRecord = new Intent(getApplicationContext(), MyRecordActivity.class);
                        startActivity(myRecord);
                        break;

                    case R.id.my_waste_graph:
                        Intent myGraph = new Intent(getApplicationContext(), MyStatsActivity.class);
                        startActivity(myGraph);
                        break;
                }
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return false;

            }
        });

        //////////// Ways to Recycle //////////////
        Btn1 = findViewById(R.id.btn_recycle);
        Btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {

                    }
                    /* 여기서부터 안적어도 될것같음 */
                    // $ 오잉?!!!!!
                    if (photoFile != null) {
                        photoUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

        //////////// Emissions Record //////////////
        Btn2 = findViewById(R.id.btn_record);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {

                    }
                    /* 여기서부터 안적어도 될것같음 */
                    if (photoFile != null) {
                        photoUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE2);
                    }
                }
            }
        });


        Btn3 = findViewById(R.id.btn_community);
        Btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent community = new Intent(getApplicationContext(), Community_MainActivity.class);
                startActivity(community);
            }
        });

    }
    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_"+ timeStamp+"_";
        File StorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ",jpg",
                StorageDir
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // 넘어가는 화면
            Intent intent2 = new Intent(this, LoadingActivity.class);
            startActivity(intent2);

            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            /* 21.02.05 1:40 a.m 수정 */
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            Log.d("bitmap", bitmap.toString());
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegress(exifOrientation);
            } else {
                exifDegree = 0;
            }

            // $ 사진 두 번 저장
            String result = "";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss", Locale.getDefault() );
            Date curDate   = new Date(System.currentTimeMillis());
            String filename  = formatter.format(curDate);

            //String strFolderName = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES) + File.separator + "AmongEarth" + File.separator;
            String strFolderName = getExternalFilesDir(DIRECTORY_PICTURES) + File.separator + "AmongEarth" + File.separator;
            File file = new File(strFolderName);
            if( !file.exists() )
                file.mkdirs();

            //File f = new File(strFolderName + "/" + filename + ".png");// jpg
            File f = new File(strFolderName + "/" + filename + ".jpg"); /* */
            result = f.getPath();

            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result = "Save Error fOut";
            }

            // 비트맵 사진 폴더 경로에 저장
            //rotate(bitmap,exifDegree).compress(Bitmap.CompressFormat.PNG, 70, fOut);
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, fOut);

            try {
                fOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fOut.close();
                // 방금 저장된 사진을 갤러리 폴더 반영 및 최신화
                mMediaScanner.mediaScanning(strFolderName + "/" + filename + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
                result = "File close Error";
            }

            // 이미지 뷰에 비트맵을 set하여 이미지 표현
            // ((ImageView) findViewById(R.id.image_result)).setImageBitmap(rotate(bitmap,exifDegree));

            Intent intent = new Intent(this, YoloActivity.class);
            intent.putExtra("imgPath", result);
            //intent.putExtra("imgPath", "teddy_bear2.JPG");
            startActivity(intent);
        }
        else if (requestCode == REQUEST_IMAGE_CAPTURE2 && resultCode == RESULT_OK) {
            // 넘어가는 화면
            Intent intent2 = new Intent(this, com.example.amongearth_hackaton.record.LoadingActivity.class);
            startActivity(intent2);

            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);

            /* 21.02.05 1:40 a.m 수정 */
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            Log.d("bitmap", bitmap.toString());

            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegress(exifOrientation);
            } else {
                exifDegree = 0;
            }

            String result = "";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss", Locale.getDefault() );
            Date curDate   = new Date(System.currentTimeMillis());
            String filename  = formatter.format(curDate);

            //String strFolderName = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES) + File.separator + "AmongEarth" + File.separator;
            String strFolderName = getExternalFilesDir(DIRECTORY_PICTURES) + File.separator + "AmongEarth" + File.separator;  // 사진 또 저장
            File file = new File(strFolderName);
            if( !file.exists() )
                file.mkdirs();

            //File f = new File(strFolderName + "/" + filename + ".png");// jpg
            File f = new File(strFolderName + "/" + filename + ".jpg"); /* */
            result = f.getPath();

            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result = "Save Error fOut";
            }

            // 비트맵 사진 폴더 경로에 저장
            //rotate(bitmap,exifDegree).compress(Bitmap.CompressFormat.PNG, 70, fOut);
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, fOut);

            try {
                fOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fOut.close();
                // 방금 저장된 사진을 갤러리 폴더 반영 및 최신화
                mMediaScanner.mediaScanning(strFolderName + "/" + filename + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
                result = "File close Error";
            }

            // 이미지 뷰에 비트맵을 set하여 이미지 표현
            // ((ImageView) findViewById(R.id.image_result)).setImageBitmap(rotate(bitmap,exifDegree));

            Intent intent = new Intent(this, com.example.amongearth_hackaton.record.YoloActivity.class);
            intent.putExtra("imgPath", result);
            //intent.putExtra("imgPath", "teddy_bear2.JPG");
            startActivity(intent);
        }
    }

    private int exifOrientationToDegress(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

//    private Bitmap rotate(Bitmap bitmap, float degree) {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degree);
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(),"권한이 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(),"권한이 거부됨", Toast.LENGTH_SHORT).show();
        }
    };

}
