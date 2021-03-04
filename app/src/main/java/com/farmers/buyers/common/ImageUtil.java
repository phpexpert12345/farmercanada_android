package com.farmers.buyers.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.farmers.buyers.common.model.FileBitmapModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {

    public static void takePhotoFromCamera(Activity activity, int REQUEST_CODE) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void choosePhotoFromGallery(Activity activity, int GALLERY_CODE) {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent,GALLERY_CODE);
    }

    public static FileBitmapModel phototakenFromGallery(Intent data, Activity activity) {
        String pathName="";
        FileBitmapModel fileBitmapModel=new FileBitmapModel();

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(activity.getApplicationContext().getContentResolver(), data.getData());
                Uri uri=data.getData();
                if(bm!=null) {
                    pathName = getRealPathFromURI(uri, activity);
                    Bitmap bm2 = modifyOrientation(bm, pathName);
                    Uri uri2 = getImageUri(activity.getBaseContext(), bm2);
                    fileBitmapModel.setFile(new File(getRealPathFromURI(uri2, activity)));
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(activity,"Please select valid image",Toast.LENGTH_SHORT).show();
            }
        }
        if(bm==null)
            Toast.makeText(activity,"Please select valid image",Toast.LENGTH_SHORT).show();
        else {
            try {
                fileBitmapModel.setBitmap(modifyOrientation(bm, pathName));
                return fileBitmapModel;
            }
            catch (Exception e)
            {

            }
        }
        return null;
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static String getRealPathFromURI(Uri uri, Activity activity) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        ExifInterface ei = new ExifInterface(image_absolute_path);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);

            default:
                return bitmap;
        }
    }


    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static FileBitmapModel photoTakenFromCamera(Intent data, Activity activity) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        Uri uri= getImageUri(activity.getApplicationContext(),thumbnail);

        String path=getRealPathFromURI(uri,activity);
        FileBitmapModel fileBitmapModel=
                new FileBitmapModel(new File(path),thumbnail);
        return fileBitmapModel;
    }


    public static Bitmap ScaleDownBitmap(Bitmap originalImage, float maxImageSize, boolean filter)
    {
        float ratio = Math.min((float)maxImageSize / originalImage.getWidth(),
                (float)maxImageSize / originalImage.getHeight());
        int width = (int)Math.round(ratio * (float)originalImage.getWidth());
        int height =(int) Math.round(ratio * (float)originalImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(originalImage, width, height, filter);
        return newBitmap;
    }

    public static Bitmap ScaleBitmap(Bitmap originalImage, int wantedWidth, int wantedHeight)
    {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float)wantedWidth / originalImage.getWidth(), (float)wantedHeight / originalImage.getHeight());
        canvas.drawBitmap(originalImage, m, new Paint());

        return output;
    }

    public static FileBitmapModel getResizedFileBitmapModel(FileBitmapModel fileBitmapModelOrig) {
        // we'll start with the original picture already open to a file
        File imgFileOrig =  fileBitmapModelOrig.getFile(); //change "getPic()" for whatever you need to open the image file.
        FileBitmapModel fileBitmapModel=new FileBitmapModel();
        Bitmap b = BitmapFactory.decodeFile(imgFileOrig.getAbsolutePath());
        // original measurements
        int origWidth = b.getWidth();
        int origHeight = b.getHeight();


        final int destWidth = 600;//or the width you need
        final int destHeight = 800;

        if(origWidth<=destWidth||origHeight<=destHeight) {
            fileBitmapModel.setFile(imgFileOrig);
            fileBitmapModel.setBitmap(b);
            return  fileBitmapModelOrig;
        }

        if (origWidth > destWidth) {
            // picture is wider than we want it, we calculate its target height
            //int destHeight = origHeight / (origWidth / destWidth);
            // we create an scaled bitmap so it reduces the image, not just trim it
            Bitmap b2 = Bitmap.createScaledBitmap(b, destWidth, destHeight, false);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            // compress to the format you want, JPEG, PNG...
            // 70 is the 0-100 quality percentage
            b2.compress(Bitmap.CompressFormat.JPEG, 50, outStream);
            // we save the file, at least until we have made use of it
            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "test.jpg");
            Bitmap resizedBitmap=null;
            try {
                f.createNewFile();
                //write the bytes in file
                FileOutputStream fo = new FileOutputStream(f);
                byte[] byteArray=outStream.toByteArray();

                fo.write(outStream.toByteArray());
                resizedBitmap = BitmapFactory.
                        decodeByteArray(byteArray, 0, byteArray.length);
                // remember close de FileOutput
                fo.close();
            }
            catch (IOException e)
            {

            }
            fileBitmapModel.setBitmap(resizedBitmap);
            fileBitmapModel.setFile(f);

        } return fileBitmapModel;

    }

}
