package me.xihuxiaolong.generalcomponent.common.image;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.xihuxiaolong.generalcomponent.common.MyApplication;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/9/2.
 */
public class ImageUploadTask {

    private OSS oss;

    // 运行sample前需要配置以下字段为有效的值
    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String imgEndpoint = "http://generalcomponent.img-cn-shanghai.aliyuncs.com/";
    private static final String accessKeyId = "LTAIYdx13eOQwC7A";
    private static final String accessKeySecret = "CQvK4GaZ4hFmUJCLhEs8XZw4coCMa7";

    private static final String testBucket = "generalcomponent";
    private static final String uploadObject = "sampleObject";
    private static final String downloadObject = "sampleObject";

    public ImageUploadTask(){
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(MyApplication.getInstance(), endpoint, credentialProvider, conf);
    }

    public OSSAsyncTask asyncPutObjectFromLocalFile(String uploadFilePath, final IMageUploadListener uploadListener) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
        final String imgName = "user-upload-" + timeStamp;
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(testBucket, imgName, uploadFilePath);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                if(uploadListener != null)
                    uploadListener.onProgress(currentSize, totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");

                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                if(uploadListener != null)
                    uploadListener.onSuccess(imgEndpoint + imgName);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
                if(uploadListener != null)
                    uploadListener.onFailure(clientExcepion, serviceException);
            }
        });
        return task;
    }

    public interface IMageUploadListener{
        void onProgress(long currentSize, long totalSize);
        void onSuccess(String resultUrl);
        void onFailure(ClientException clientExcepion, ServiceException serviceException);
    }
}
