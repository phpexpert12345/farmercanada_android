package com.farmers.buyers.common.utils

import android.app.Activity
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.annotation.WorkerThread
import androidx.exifinterface.media.ExifInterface
import java.io.*
import java.net.URISyntaxException
import kotlin.math.roundToInt


/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
class CameraProvider(private val activity: Activity, private val code: Int, private val galleryCode: Int) {

    private var imageUri: Uri? = null

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
//        if(fragment != null) {
//            fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), galleryCode)
//        }else {
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), galleryCode)
//        }

    }

    fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
        imageUri = activity.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//        if(fragment != null) {
//            fragment.startActivityForResult(intent, code)
//        }else {
        activity.startActivityForResult(intent, code)
//        }

    }

    // get File , and file path
    fun processOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?, callBack: (File?, String?)->Unit) {
        if(requestCode == code) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    val realPath = getRealPathFromURI(imageUri)
                    if(realPath != null) {
                        val file = File(realPath)
                        handleSamplingAndRotationBitmap(Uri.fromFile(file), file.name)?.run {
                            callBack(file, realPath)
                        }

                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }else if(requestCode == galleryCode) {
            val selectedImage: Uri? = data?.data
            try {
                val realPath = ContentUriUtils.getFilePath(activity, selectedImage!!)
                if(realPath != null) {
                    val file = File(realPath)
//                    handleSamplingAndRotationBitmap(Uri.fromFile(file), file.name)?.run {
                    callBack(file, realPath)
//                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
//            val path =
//            callBack(File(path), path)
        }
    }

    private fun getRealPathFromURI(contentUri: Uri?): String? {
        val media = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = activity.managedQuery(contentUri, media, null, null, null)
        val index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(index)
    }

    /**
     * This method is responsible for solving the rotation issue if exist. Also scale the images to
     * 1024x1024 resolution
     *
     * @param selectedImage The Image URI
     * @return Bitmap image results
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun handleSamplingAndRotationBitmap(selectedImage: Uri, fileNameToSave: String): File? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var imageStream: InputStream? = activity.contentResolver.openInputStream(selectedImage)
        BitmapFactory.decodeStream(imageStream, null, options)
        imageStream?.close()
        options.inSampleSize = calculateInSampleSize(options)
        options.inJustDecodeBounds = false
        imageStream = activity.contentResolver.openInputStream(selectedImage)
        var img = BitmapFactory.decodeStream(imageStream, null, options)
        if(img != null) {
            img = rotateImageIfRequired(img, selectedImage)
            return  bitmapToFile(img!!, fileNameToSave)
        }

        return null
    }

    /**
     * Calculate an inSampleSize for use in a [BitmapFactory.Options] object when decoding
     * bitmaps using the decode* methods from [BitmapFactory]. This implementation calculates
     * the closest inSampleSize that will result in the final decoded bitmap having a width and
     * height equal to or larger than the requested width and height. This implementation does not
     * ensure a power of 2 is returned for inSampleSize which can be faster when decoding but
     * results in a larger bitmap which isn't as useful for caching purposes.
     *
     * @param options   An options object with out* params already populated (run through a decode*
     * method with inJustDecodeBounds==true
     * @param reqWidth  The requested width of the resulting bitmap
     * @param reqHeight The requested height of the resulting bitmap
     * @return The value to be used for inSampleSize
     */
    private fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int = 524, reqHeight: Int = 524): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val heightRatio =
                    (height.toFloat() / reqHeight.toFloat()).roundToInt()
            val widthRatio =
                    (width.toFloat() / reqWidth.toFloat()).roundToInt()
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            val totalPixels = width * height.toFloat()
            val totalReqPixelsCap = reqWidth * reqHeight * 2.toFloat()
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++
            }
        }
        return inSampleSize
    }

    /**
     * Rotate an image if required.
     *
     * @param img           The image bitmap
     * @param selectedImage Image URI
     * @return The resulted Bitmap after manipulation
     */
    @Throws(IOException::class)
    private fun rotateImageIfRequired(
            img: Bitmap,
            selectedImage: Uri
    ): Bitmap? {
        val input: InputStream? = activity.contentResolver.openInputStream(selectedImage)
        if(input != null) {
            val ei: ExifInterface = if (Build.VERSION.SDK_INT > 23) ExifInterface(input) else ExifInterface(selectedImage.path!!)
            return when (ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(img, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(img, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(img, 270)
                else -> img
            }
        }
        return null
    }

    private fun rotateImage(img: Bitmap, degree: Int): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedImg =
                Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
        img.recycle()
        return rotatedImg
    }

    private fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? {
        var file: File? = null
        return try {
            file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, bos)
            val bitmapData = bos.toByteArray()

            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file
        }
    }
}
