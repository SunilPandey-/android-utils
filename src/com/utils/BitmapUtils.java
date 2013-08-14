package com.utils;

import java.io.FileDescriptor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;


public class BitmapUtils{

	public static Bitmap getImageThumbnail(Bitmap bmp, int width , int height){
		return ThumbnailUtils.extractThumbnail(bmp, width, height);
	}

	public static Bitmap getImageThumbnail(Bitmap bmp, int dimension){
		return getImageThumbnail(bmp, dimension, dimension);
	}

	public static Bitmap processBitmap(Context ctx, int resId, int width, int height) {
		return decodeSampledBitmapFromResource(ctx, resId, width,height);
	}

	public static Bitmap processBitmap(Object data) {
		return processBitmap(Integer.parseInt(String.valueOf(data)));
	}

	public static Bitmap decodeSampledBitmapFromResource(Context ctx, int resId, int reqWidth, int reqHeight) {
		Resources res = ctx.getResources();
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static Bitmap decodeSampledBitmapFromFile(String filename,int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filename, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filename, options);
	}

	public static Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fd, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFileDescriptor(fd, null, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFileDescriptor(fd, null, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
		int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
			final float totalPixels = width * height;
			final float totalReqPixelsCap = reqWidth * reqHeight * 2;
			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		}
		return inSampleSize;
	}
}