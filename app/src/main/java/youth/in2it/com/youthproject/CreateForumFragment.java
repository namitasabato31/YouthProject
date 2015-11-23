package youth.in2it.com.youthproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;

/**
 * Created by IN2IT on 11/17/2015.
 */
public class CreateForumFragment extends Fragment {

    RelativeLayout relImage,relVideo,relFiles;
    Spinner spinnerFileType,spinnerCategory;
    ImageView spinnerFilehelper,spinnerCategoryHelper;
    ImageView img1,img2,img3,img4;
    String imgvalue="",videovalue="";
    Button fileCapture,chooseUserBtn;
EditText editText;

    ImageView video1,video2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.create_forum_fragment, container, false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Create Forum");
        chooseUserBtn = (Button) rootView.findViewById(R.id.btn_create_forum);
        chooseUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChooseUserFragment();
                FragmentActivity activity =((FragmentActivity)getActivity());
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();
            }
        });
        relImage = (RelativeLayout) rootView.findViewById(R.id.image_layout);
        relVideo = (RelativeLayout) rootView.findViewById(R.id.rel_add_video);
editText = (EditText) rootView.findViewById(R.id.edt_file_location);
        relFiles = (RelativeLayout) rootView.findViewById(R.id.rel_add_files);
        fileCapture = (Button) rootView.findViewById(R.id.button_browse);
        fileCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 1234);
            }
        });
        spinnerCategory = (Spinner) rootView.findViewById(R.id.spinner_catagory);
        video1 = (ImageView) rootView.findViewById(R.id.video_1);
        video2 = (ImageView) rootView.findViewById(R.id.video_2);

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videovalue="video1";
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
               // Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("video/*");

                startActivityForResult(photoPickerIntent, 123);
            }
        });

        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videovalue="video2";
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                // Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("video/*");

                startActivityForResult(photoPickerIntent, 123);
            }
        });


        img1 = (ImageView) rootView.findViewById(R.id.img_1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvalue = "img1";

                Crop.pickImage(getActivity());
            }
        });

        img2 = (ImageView) rootView.findViewById(R.id.img_2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvalue = "img2";

                Crop.pickImage(getActivity());
            }
        });

        img3 = (ImageView) rootView.findViewById(R.id.img_3);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvalue = "img3";

                Crop.pickImage(getActivity());
            }
        });

        img4 = (ImageView) rootView.findViewById(R.id.img_4);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvalue = "img4";

                Crop.pickImage(getActivity());
            }
        });
        spinnerCategoryHelper = (ImageView) rootView.findViewById(R.id.spinner_catagory_helper);

        spinnerCategoryHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerCategory.performClick();
            }
        });
        addItemToCategorySpinner();
        spinnerFileType = (Spinner) rootView.findViewById(R.id.spinner_att);
        spinnerFilehelper = (ImageView) rootView.findViewById(R.id.spinner_att_helper);
        spinnerFilehelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerFileType.performClick();
            }
        });
        addItemToFileTypeSpinner();
        spinnerFileType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.v("value=", "" + value);
                if(value.equals("Images")){
                    relImage.setVisibility(View.VISIBLE);
                    relVideo.setVisibility(View.GONE);
                    relFiles.setVisibility(View.GONE);
                }else if(value.equals("Video")){
                    relImage.setVisibility(View.GONE);
                    relVideo.setVisibility(View.VISIBLE);
                    relFiles.setVisibility(View.GONE);
                }else if(value.equals("Files")){
                    relImage.setVisibility(View.GONE);
                    relVideo.setVisibility(View.GONE);
                    relFiles.setVisibility(View.VISIBLE);
                }else{
                    relImage.setVisibility(View.GONE);
                    relVideo.setVisibility(View.GONE);
                    relFiles.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return rootView;



    }
    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getActivity());
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == getActivity().RESULT_OK) {
            img1.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        Log.v("video","video");
        Log.v("video","video"+requestCode);

        if(requestCode == 1234 || requestCode == 66770){
            if(result != null) {
                Uri filelocation = result.getData();
                Log.v("filelocation", "====" + filelocation);
                editText.setText("" + filelocation);
            }
        }else if(requestCode == 65659 || requestCode == 123){
            if(videovalue.equals("video1")){

                if(result != null){
                    video1.setImageDrawable(null);
                    Uri selectedVideoLocation = result.getData();
                    Log.v("video", "video====" + selectedVideoLocation);
                    String path = getRealPathFromURI(selectedVideoLocation);
                    Log.v("video", "path====" + path);
                    Bitmap thumb = ThumbnailUtils.createVideoThumbnail(path,
                            MediaStore.Images.Thumbnails.MINI_KIND);
                    video1.setImageDrawable(null);
                    Log.v("video", "thumb====" + thumb);
                    video1.setImageBitmap(thumb);
                }

            }else if(videovalue.equals("video2")) {
                if (result != null) {
                    video2.setImageDrawable(null);
                    Uri selectedVideoLocation = result.getData();
                    Log.v("video", "video====" + selectedVideoLocation);
                    String path = getRealPathFromURI(selectedVideoLocation);
                    Log.v("video", "path====" + path);
                    Bitmap thumb1 = ThumbnailUtils.createVideoThumbnail(path,
                            MediaStore.Images.Thumbnails.MINI_KIND);

                    Log.v("video", "thumb1====" + thumb1);
                    video2.setImageBitmap(thumb1);
                }
            }

        }else {



        if (imgvalue.equals("img1")) {

        if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            if (resultCode == getActivity().RESULT_OK) {
                img1.setImageDrawable(null);
                img1.setImageURI(Crop.getOutput(result));
            } else if (resultCode == Crop.RESULT_ERROR) {
                Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }else if(imgvalue.equals("img2")){
            if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
                beginCrop(result.getData());
            } else if (requestCode == Crop.REQUEST_CROP) {
                if (resultCode == getActivity().RESULT_OK) {
                    img2.setImageDrawable(null);
                    img2.setImageURI(Crop.getOutput(result));
                } else if (resultCode == Crop.RESULT_ERROR) {
                    Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        }else if(imgvalue.equals("img3")){
            if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
                beginCrop(result.getData());
            } else if (requestCode == Crop.REQUEST_CROP) {
                if (resultCode == getActivity().RESULT_OK) {
                    img3.setImageDrawable(null);
                    img3.setImageURI(Crop.getOutput(result));
                } else if (resultCode == Crop.RESULT_ERROR) {
                    Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        }else if(imgvalue.equals("img4")) {
            if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
                beginCrop(result.getData());
            } else if (requestCode == Crop.REQUEST_CROP) {
                if (resultCode == getActivity().RESULT_OK) {
                    img4.setImageDrawable(null);
                    img4.setImageURI(Crop.getOutput(result));
                } else if (resultCode == Crop.RESULT_ERROR) {
                    Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void addItemToFileTypeSpinner() {
        String[] stringYearArray = new String[]{"Select", "Images", "Video","Files"};


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, stringYearArray);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFileType.setAdapter(dataAdapter);

    }

    private void addItemToCategorySpinner() {
        String[] stringYearArray = new String[]{"None", "Business", "Computers & Internet","Country & Politics","Education & career","Environment",
        "Gaming","Sports","Global event","Holidays & Events"};


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, stringYearArray);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapter);

    }
}
