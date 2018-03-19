package de.bitb.astroskop.utils


class CameraUtils//
//    public static final String TEMP_PATH = "temp";
//    private static final Integer ANY_INTEGER_0_TO_4000_FOR_QUALITY = 720;
//    private PermissionGranted permission;
//    private MagicalCamera magicalCamera;
//
//    public boolean checkPermission(Activity activity) {
//        permission = new PermissionGranted(activity);
//        magicalCamera = new MagicalCamera(activity, ANY_INTEGER_0_TO_4000_FOR_QUALITY, permission);
//        permission.checkWriteExternalPermission();
//        permission.checkCameraPermission();
//        permission.checkReadExternalPermission();
//
//        return isPermissionGranted();
//    }
//
//    public boolean isPermissionGranted() {
//        return android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M
//                || permission.getPermissionGrantedObject().isCameraPermission()
//                && permission.getPermissionGrantedObject().isWriteExternalStoragePermission()
//                && permission.getPermissionGrantedObject().isReadExternalStoragePermission();
//    }
//
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        magicalCamera.permissionGrant(requestCode, permissions, grantResults);
//    }
//
//    public void takePicture() {
//        magicalCamera.takePhoto();
//    }
//
//    public String getPicturePath(ImageInfo imageInfo) {
//        magicalCamera.resultPhoto(imageInfo.getRequestCode(), imageInfo.getResultCode(), imageInfo.getIntent());
//        return magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), imageInfo.getImageName(), TEMP_PATH, imageInfo.getCompressFormat(), false);
//    }
//
//    @Getter
//    @Setter
//    public static class ImageInfo {
//        private int requestCode;
//        private int resultCode;
//        private Intent intent;
//        private String imageName;
//        private String imageDirectory;
//        private Bitmap.CompressFormat compressFormat = MagicalCamera.JPEG;
//    }