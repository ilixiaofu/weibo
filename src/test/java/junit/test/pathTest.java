package junit.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.springcloud.weibo.util.UserFileUrlUtil;
import com.springcloud.weibo.util.UserLocalFilePathUtil;

public class pathTest {

    @Test
    public void sfdf() throws IOException {
//		String uuid = UUID.randomUUID().toString();
//		String path = UserLocalFilePathUtil.getLocalPicturePath(uuid, "");
//		File file = new File(path);
//		System.out.println(file.mkdirs());

        //String userDefaultPictureUUID = UUID.randomUUID().toString();
        String userDefaultPicture = UserLocalFilePathUtil.getLocalPicturePath("1148e125-3e47-4ab2-8007-f098cfd2e590", "default.JPG");
        String sysDefaultPicture = UserLocalFilePathUtil.USER_DEFAULT_HEAD_PICTURE;
        File sysDefaultPFile = new File(sysDefaultPicture);
        if (sysDefaultPFile.exists()) {
            File userDefaultPictureFile = new File(userDefaultPicture);
            if (!userDefaultPictureFile.exists()) {
                //InputStream inputStream = new FileInputStream(sysDefaultPFile);
                //OutputStream outputStream = new FileOutputStream(userDefaultPictureFile);
                //System.out.println(Files.copy(sysDefaultPFile.toPath(), outputStream));
                System.out.println(sysDefaultPFile.renameTo(userDefaultPictureFile));
            }

        }
    }

    @Test
    public void dfghdhg() throws IOException {
//		String sysDefaultPicturePath = UserLocalFilePathUtil.USER_DEFAULT_HEAD_PICTURE;
//		String defaultPictureName = UUID.randomUUID().toString() + ".jpg";
//		String userDefaultPicturePath = UserLocalFilePathUtil.getLocalPicturePath("1148e125-3e47-4ab2-8007-f098cfd2e590", defaultPictureName);
//		
//		File sourcePictureFile = new File(sysDefaultPicturePath);
//		File desPictureFile = new File(userDefaultPicturePath);
//		System.out.println(sourcePictureFile.exists());
//		if (sourcePictureFile.exists()) {
//			if (!desPictureFile.exists()) {
//				OutputStream outputStream = new FileOutputStream(desPictureFile);
//				System.out.println(Files.copy(sourcePictureFile.toPath(), outputStream));
//			}
//		}

        String pictureURLName = "dtgrfg.jpg";
        System.out.println(UserFileUrlUtil.getPictureUrl("sddfdgfgf", pictureURLName));
    }

}
