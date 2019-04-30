package just.cse.mahfuz.smrjust;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {

    ImageView img;
    EditText name,dept,roll,session,reg,dob,bg,gender,phone,email,hName,hStatus,room,enrollment,fName,mName,address,emergency;
    String myname,mydept,myroll,mysession,myreg, mydob,mybg,mygender,myphone,myemail,myhName,myhStatus,myroom,myenrollment,myfName,mymName,myaddress,myemergency;
    Button add;

    private Uri filePath;

    private StorageReference storageReference;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        img=findViewById(R.id.img);

        name=findViewById(R.id.name);
        dept=findViewById(R.id.dept);
        roll=findViewById(R.id.roll);
        session=findViewById(R.id.session);
        reg=findViewById(R.id.reg);
        dob=findViewById(R.id.dob);
        bg=findViewById(R.id.bg);
        gender=findViewById(R.id.gender);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);

        hName=findViewById(R.id.hName);
        hStatus=findViewById(R.id.hStatus);
        room=findViewById(R.id.room);
        enrollment=findViewById(R.id.enrollment);

        fName=findViewById(R.id.fName);
        mName=findViewById(R.id.mName);
        address=findViewById(R.id.address);
        emergency=findViewById(R.id.emergancy);


        add=findViewById(R.id.add);

        FirebaseApp.initializeApp(AddStudentActivity.this);
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select a Picture"),111);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myname=name.getText().toString().trim();
                mydept=dept.getText().toString().trim();
                myroll=roll.getText().toString().trim();
                mysession=session.getText().toString().trim();
                myreg=reg.getText().toString().trim();
                mydob =dob.getText().toString().trim();
                mybg=bg.getText().toString().trim();
                mygender=gender.getText().toString().trim();
                myphone=phone.getText().toString().trim();
                myemail=email.getText().toString().trim();

                myhName=hName.getText().toString().trim();
                myhStatus=hStatus.getText().toString().trim();
                myroom=room.getText().toString().trim();
                myenrollment=enrollment.getText().toString().trim();

                myfName=fName.getText().toString().trim();
                mymName=mName.getText().toString().trim();
                myaddress=address.getText().toString().trim();
                myemergency=emergency.getText().toString().trim();

                if (TextUtils.isEmpty(myname) ||
                        TextUtils.isEmpty(myname) ||
                        TextUtils.isEmpty(mydept) ||
                        TextUtils.isEmpty(myroll) ||
                        TextUtils.isEmpty(mysession) ||
                        TextUtils.isEmpty(myreg) ||
                        TextUtils.isEmpty(mydob) ||
                        TextUtils.isEmpty(mybg) ||
                        TextUtils.isEmpty(mygender) ||
                        TextUtils.isEmpty(myphone) ||
                        TextUtils.isEmpty(myemail) ||
                        TextUtils.isEmpty(myhName) ||
                        TextUtils.isEmpty(myhStatus) ||
                        TextUtils.isEmpty(myroom) ||
                        TextUtils.isEmpty(myenrollment) ||
                        TextUtils.isEmpty(myfName) ||
                        TextUtils.isEmpty(mymName) ||
                        TextUtils.isEmpty(myaddress) ||
                        TextUtils.isEmpty(myemergency)


                ) {
                    Toast.makeText(AddStudentActivity.this,"Please fill up all the required field",Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadFile();
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    private void uploadFile() {
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.setMessage("Please wait..");
            progressDialog.show();

            //getting the storage reference
            final StorageReference sRef = storageReference.child("studentImage/" + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "Information uploaded succesfully ", Toast.LENGTH_LONG).show();

                            /**
                             //creating the upload object to store uploaded image details
                             String upload =taskSnapshot.getDownloadUrl().toString();
                             Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                             //adding an upload to firebase database
                             String uploadId = mDatabase.push().getKey();
                             mDatabase.child(uploadId).setValue(upload);

                             */
                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Log.d("Error", "onSuccess: uri= "+ uri.toString());

                                    Map<String,Object> setData= new HashMap<>();
                                    setData.put("url",uri.toString());
                                    setData.put("name",myname);
                                    setData.put("dept",mydept);
                                    setData.put("roll",myroll);
                                    setData.put("session",mysession);
                                    setData.put("reg",myreg);
                                    setData.put("dob",mydob);
                                    setData.put("bg",mybg);
                                    setData.put("gender",mygender);
                                    setData.put("phone",myphone);
                                    setData.put("email",myemail);

                                    setData.put("hName",myhName);
                                    setData.put("hStatus",myhStatus);
                                    setData.put("room",myroom);
                                    setData.put("enrollment",myenrollment);

                                    setData.put("fName",myfName);
                                    setData.put("mName",mymName);
                                    setData.put("address",myaddress);
                                    setData.put("emergency",myenrollment);


                                    firebaseFirestore.collection("students").document(myroll).set(setData);

                                    finish();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
