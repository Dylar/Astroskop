//package de.bitb.astroskop.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import de.bitb.astroskop.R;
//import de.bitb.astroskop.injection.IBind;
//import de.bitb.astroskop.injection.IInjection;
//import de.bitb.astroskop.injection.components.AppComponent;
//import de.bitb.astroskop.tasks.CompressImage;
//import de.bitb.astroskop.ui.base.BaseActivity;
//import de.bitb.astroskop.ui.base.BaseFragment;
//import de.bitb.astroskop.utils.CameraUtils;
//import de.bitb.astroskop.utils.FileUtils;
//import de.bitb.astroskop.utils.UiUtils;
//import lombok.Getter;
//
//
//public class TakePictureActivity extends BaseActivity implements IInjection, IBind {
//
//    public static final int TAKE_PICTURE_CODE = 9;
//    public static final String KEY_IMAGE_PATH = "keyImagePath";
//    private static final String KEY_PICTURE_FILENAME = "pictureFilename";
//
//    @BindView(R.id.fragment_picture_saving_indicator)
//    protected View indicator;
//
//    @Getter
//    @Inject
//    protected CameraUtils cameraUtils;
//
//    @Getter
//    @Inject
//    protected FileUtils fileUtils;
//
//    @Inject
//    protected UiUtils uiUtils;
//
//    private CompressImage task;
//
//    private String fileName;
//
//    public static void startActivity(BaseFragment baseFragment, String fileName) {
//        Intent intent = new Intent(baseFragment.getContext(), TakePictureActivity.class);
//        intent.putExtra(KEY_PICTURE_FILENAME, fileName);
//        baseFragment.startActivityForResult(intent, TAKE_PICTURE_CODE);
//    }
//
//    @Override
//    public void inject(AppComponent appComponent) {
//        appComponent.inject(this);
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        fileName = getIntent().getStringExtra(KEY_PICTURE_FILENAME);
//        if (savedInstanceState == null && cameraUtils.checkPermission(this)) {
//            cameraUtils.takePicture();
//        }
//        uiUtils.startRotation(indicator);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_picture;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        cameraUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (cameraUtils.isPermissionGranted()) {
//            cameraUtils.takePicture();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            CameraUtils.ImageInfo imageInfo = new CameraUtils.ImageInfo();
//            imageInfo.setImageName(fileName);
//            imageInfo.setImageDirectory(FILE_PATH_IMAGES);
//            imageInfo.setIntent(data);
//            imageInfo.setRequestCode(requestCode);
//            imageInfo.setResultCode(resultCode);
//
//            task = new CompressImage(new TaskCallback() {
//
//                @Override
//                public void postProgress(int progress) {
//                    //nothing
//                }
//
//                @Override
//                public void onTaskCompleted(boolean success) {
//                    if (success) {
//                        Intent result = new Intent();
//                        result.putExtra(KEY_IMAGE_PATH, task.getPath());
//                        setResult(RESULT_OK, result);
//                    }
//                    finish();
//                }
//            }, cameraUtils, fileUtils, imageInfo);
//            task.start();
//        } else {
//            finish();
//        }
//    }
//}
