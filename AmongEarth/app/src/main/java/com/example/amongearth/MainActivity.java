package com.example.amongearth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
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


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.amongearth.community.Zerowaste_Community;
import com.example.amongearth.mypage.Badge;
import com.example.amongearth.mypage.BadgeAdapter;
import com.example.amongearth.mypage.MyBadgeActivity;
import com.example.amongearth.mypage.MyPostsActivity;
import com.example.amongearth.mypage.MyRecordActivity;
import com.example.amongearth.mypage.MyRecordActivity2;
import com.example.amongearth.mypage.MyStatsActivity;
import com.example.amongearth.mypage.WasteRecord;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import static android.os.Environment.DIRECTORY_PICTURES;

public class MainActivity extends AppCompatActivity {
    LinearLayout Btn1, Btn2, Btn3;
    ImageView profile;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View navHeader;
    ImageView close_nav;
    ImageView user_profile;
    TextView username;
    TextView view_all;
    TextView sign_out;
    ImageView badge1;
    ImageView badge2;
    ImageView badge3;
    ImageView badge4;
    ImageView badge5;
    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private static final int REQUEST_IMAGE_CAPTURE2 = 680;
    private String imageFilePath;
    private Uri photoUri;

    /* Firebase Community 부분 */
    private DatabaseReference mDatabase; // 21.02.25 Firebase 내용 생성
    String userId; // 21.02.25 Firebase 내용 생성
    HashMap<Integer, ArrayList<ArrayList<String>>> hashMap = new HashMap<>();
    HashMap<String, Integer> userinfo ;



    private MediaScanner mMediaScanner; // 사진 저장 시 갤러리 폴더에 바로 반영사항을 업데이트 시켜주려면 이 것이 필요하다(미디어 스캐닝)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userinfo = new HashMap<>();


        // 사진 저장 후 미디어 스캐닝을 돌려줘야 갤러리에 반영됨.
        mMediaScanner = MediaScanner.getInstance(getApplicationContext());

        // 권한 체크
        TedPermission.with(getApplicationContext()).setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("거부하셨습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

        navigationView = findViewById(R.id.navigationView);
        navHeader = navigationView.getHeaderView(0);
        profile = findViewById(R.id.profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        close_nav = findViewById(R.id.close_nav);
        sign_out = findViewById(R.id.sign_out);
        user_profile = findViewById(R.id.user_profile);
        username = findViewById(R.id.username);
        //badge preview 선언
        profile.bringToFront();
        profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
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

        sign_out = (TextView) navHeader.findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String uid = user.getUid();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.getReference("user").child(uid).addValueEventListener(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        if(dataSnapshot.getKey().equals("nickname")) {
                            String nickname = dataSnapshot.getValue().toString();
                            username = (TextView) navHeader.findViewById(R.id.username);
                            username.setText(nickname);
                        }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("MainActivity:닉네임변경실패",String.valueOf(error.toException()));
                }
            });
            //badge preview setting
            DatabaseReference badgeData = firebaseDatabase.getReference("user").child(uid).child("my_badge");
            firebaseDatabase.getReference("user").child(uid).child("my_badge").addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if(dataSnapshot.getKey().equals("Welcome")){
                            badge1 = (ImageView) navHeader.findViewById(R.id.badge1);
                            badge1.setImageResource(R.drawable.first_join);
                        }
                        if(dataSnapshot.getKey().equals("StartChallenge")){
                            badge2 = (ImageView) navHeader.findViewById(R.id.badge2);
                            badge2.setImageResource(R.drawable.startchallenge);
                        }
                        if(dataSnapshot.getKey().equals("Challenge15")){
                            badge3 = (ImageView) navHeader.findViewById(R.id.badge3);
                            badge3.setImageResource(R.drawable.challenge15);
                        }
                        if(dataSnapshot.getKey().equals("Challenge30")){
                            badge4 = (ImageView) navHeader.findViewById(R.id.badge4);
                            badge4.setImageResource(R.drawable.challenge30);
                        }
                        if(dataSnapshot.getKey().equals("Challenge45")){
                            badge5 = (ImageView) navHeader.findViewById(R.id.badge5);
                            badge5.setImageResource(R.drawable.challenge45);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("MainActivity preview 실패",String.valueOf(error.toException()));
                }
            });
        }


        ArrayList<WasteRecord> wasteRecords = new ArrayList<>(7);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Query myStatsQuery = databaseReference.child("waste_record").child(user.getUid()).limitToLast(7);
        myStatsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WasteRecord wasteRecord = dataSnapshot.getValue(WasteRecord.class);
                    wasteRecord.date = dataSnapshot.getKey();
                    wasteRecords.add(wasteRecord);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //네비게이션 메뉴 버튼 클릭 이벤트
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
                        Log.d("{{ 아아아아아아아아아제발 }} : ", wasteRecords+"");
                        Intent myGraph = new Intent(getApplicationContext(), MyStatsActivity.class);
                        myGraph.putExtra("wasteRecords", wasteRecords);
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
                        // startActivityForResult(intent, REQUEST_TAKE_PHOTO);
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




        mDatabase = FirebaseDatabase.getInstance().getReference(); // 21.02.25 Firebase 내용 생성
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("UserID느느느느느는",userId);

        mDatabase.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userinfo.put(dataSnapshot.getKey()+"", Integer.parseInt(dataSnapshot.child("profile").getValue()+""));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("challenge_board").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /* Date는 어차피 숫자니까 date 객체가 아닌 int로 저장하기로 함 */
                /* 내가 하트를 눌렀는지 여부를 여기서 판단해야함 */ // 내가 이미 누른거라면 : 1 내가 기존에 안누른거라면 0

                hashMap.clear();
                int count=0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                    while (child.hasNext()) {
                        int heartflag=1;
                        DataSnapshot data = child.next();
                        // 하트를 내가 눌렀는지 판별하는 코드
                        HashMap<String, Object> td = (HashMap<String, Object>) data.child("likes").getValue();
                        if ((dataSnapshot.getKey()!=userId)&&(!td.containsKey(userId))){
                            heartflag=0;
                        }

                        if (Integer.parseInt(data.child("visibility").getValue()+"")==1){
                            ArrayList<String> arr = new ArrayList<>(Arrays.asList(data.child("nickname").getValue() + "",data.getKey(),
                                    data.child("content").getValue() + "", (data.child("likes").getChildrenCount()-1) + "",
                                    data.child("upload_file").getValue()+"", userinfo.get(dataSnapshot.getKey()+"")+"", dataSnapshot.getKey(), Integer.toString(heartflag))); // 모두 String으로 받아옴
                            if (!hashMap.containsKey(Integer.parseInt(data.getKey()))){
                                hashMap.put(Integer.parseInt(data.getKey()), new ArrayList<>());
                            }

                            hashMap.get(Integer.parseInt(data.getKey())).add(arr);
                        }
                        count++;
                    }

                }
                Log.d("{{ Hash Map }} : ", hashMap+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        Btn3 = findViewById(R.id.btn_community);
        Btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent community = new Intent(getApplicationContext(), Zerowaste_Community.class);
                community.putExtra("hashmap", hashMap);
                startActivity(community);
            }
        });

    }
    public void DBSearch(){
        mDatabase.child("challenge_board").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /* Date는 어차피 숫자니까 date 객체가 아닌 int로 저장하기로 함 */
                /* 내가 하트를 눌렀는지 여부를 여기서 판단해야함 */ // 내가 이미 누른거라면 : 1 내가 기존에 안누른거라면 0
                int count=0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                    while (child.hasNext()) {
                        int heartflag=1;
                        DataSnapshot data = child.next();
                        // 하트를 내가 눌렀는지 판별하는 코드
                        HashMap<String, Object> td = (HashMap<String, Object>) data.child("likes").getValue();
                        if ((dataSnapshot.getKey()!=userId)&&(!td.containsKey(userId))){
                            heartflag=0;
                        }

                        if (Integer.parseInt(data.child("visibility").getValue()+"")==1){
                            ArrayList<String> arr = new ArrayList<>(Arrays.asList(data.child("nickname").getValue() + "",data.getKey(),
                                    data.child("content").getValue() + "", (data.child("likes").getChildrenCount()-1) + "",
                                    data.child("upload_file").getValue()+"", userinfo.get(dataSnapshot.getKey()+"")+"", dataSnapshot.getKey(), Integer.toString(heartflag))); // 모두 String으로 받아옴
                            if (!hashMap.containsKey(Integer.parseInt(data.getKey()))){
                                hashMap.put(Integer.parseInt(data.getKey()), new ArrayList<>());
                            }
                            count++;

                            hashMap.get(Integer.parseInt(data.getKey())).add(arr);
                        }

                    }

                }
                Log.d("{{ Hash Map }} : ", count+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_"+ timeStamp+"_";
        File StorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
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
            Intent intent2 = new Intent(this, com.example.amongearth.record.LoadingActivity.class);
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

            Intent intent = new Intent(this, com.example.amongearth.record.YoloActivity.class);
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
