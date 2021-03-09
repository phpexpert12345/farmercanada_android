//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.farmers.buyers.common.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import androidx.annotation.WorkerThread;

class ContentUriUtils {
    ContentUriUtils() {
    }

    @WorkerThread
    public static String getFilePath(Context context, Uri mUri) {
        Uri uri = mUri;
        String selection = null;
        String selectionArgs = null;
        String projection;
        String type;
        if (VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), mUri)) {
            String[] split;
            if (isExternalStorageDocument(mUri)) {
                projection = DocumentsContract.getDocumentId(mUri);
                split = projection.split(":");
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1];
            }

            if (isDownloadsDocument(mUri)) {
                projection = DocumentsContract.getDocumentId(mUri);
                uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(projection));
            } else if (isMediaDocument(mUri)) {
                projection = DocumentsContract.getDocumentId(mUri);
                split = projection.split(":");
                type = split[0];
                if ("image" == type) {
                    uri = Media.EXTERNAL_CONTENT_URI;
                } else if ("video" == type) {
                    uri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio" == type) {
                    uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                selection = "_id=?";
                selectionArgs = split[1];
            }
        }

        if ("content".equals(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            projection = "_data";

            try {
                Cursor cursor = context.getContentResolver().query(uri, new String[]{projection}, selectionArgs, (String[])null, selection);
                type = null;
                if (cursor != null) {
                    int column_index = cursor.getColumnIndexOrThrow("_data");
                    if (cursor.moveToFirst()) {
                        type = cursor.getString(column_index);
                        cursor.close();
                    }
                }

                return type;
            } catch (Exception var9) {
                var9.printStackTrace();
            }
        } else if ("file".equals(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private static Boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents" == uri.getAuthority();
    }

    private static Boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents" == uri.getAuthority();
    }

    private static Boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents" == uri.getAuthority();
    }

    private static Boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content" == uri.getAuthority();
    }
}
