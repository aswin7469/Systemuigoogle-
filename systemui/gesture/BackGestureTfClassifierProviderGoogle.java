package com.google.android.systemui.gesture;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Trace;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.Interpreter;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BackGestureTfClassifierProviderGoogle {
    public static final Object sModelLoadingLock = new Object();
    public Interpreter mInterpreter = null;
    public final String mModelFile;
    public boolean mModelLoaded = false;
    public final float[][] mOutput;
    public final Map mOutputMap;
    public Map mVocab;
    public final String mVocabFile;

    public BackGestureTfClassifierProviderGoogle(String str) {
        HashMap hashMap = new HashMap();
        this.mOutputMap = hashMap;
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = 1;
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, iArr);
        this.mOutput = fArr;
        this.mModelFile = str.concat(".tflite");
        this.mVocabFile = str.concat(".vocab");
        hashMap.put(0, fArr);
    }

    public final void loadModel(AssetManager assetManager) {
        AssetFileDescriptor openFd;
        try {
            Trace.beginSection("BackGestureTfClassifierProviderGoogle#modelLoading");
            openFd = assetManager.openFd(this.mModelFile);
            this.mInterpreter = new Interpreter(openFd.createInputStream().getChannel().map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength()));
            this.mModelLoaded = true;
            openFd.close();
        } catch (Exception e) {
            Log.e("BackGestureTfClassifier", "Load TFLite file error:", e);
            this.mModelLoaded = false;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
        Trace.endSection();
        return;
        throw th;
    }

    public final Map readVocab(AssetManager assetManager) {
        BufferedReader bufferedReader;
        HashMap hashMap = new HashMap();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(this.mVocabFile)));
            int i = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                hashMap.put(readLine, Integer.valueOf(i));
                i++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            Log.e("BackGestureTfClassifier", "Load vocab file error: ", e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return hashMap;
        throw th;
    }
}
