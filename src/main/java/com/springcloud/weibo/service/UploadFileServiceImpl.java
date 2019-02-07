package com.springcloud.weibo.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.springcloud.weibo.constant.UserPhotoAlbumNameConstant;
import com.springcloud.weibo.mapper.PhotoAlbumMapper;
import com.springcloud.weibo.mapper.PictureMapper;
import com.springcloud.weibo.mapper.UserFilePathMapper;
import com.springcloud.weibo.pojo.PhotoAlbum;
import com.springcloud.weibo.pojo.PhotoAlbumExample;
import com.springcloud.weibo.pojo.Picture;
import com.springcloud.weibo.pojo.User;
import com.springcloud.weibo.pojo.UserFilePath;
import com.springcloud.weibo.util.UserFileUrlUtil;
import com.springcloud.weibo.util.UserLocalFilePathUtil;

@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService {

    private final String TAG = UploadFileServiceImpl.class.getName();

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private PhotoAlbumMapper photoAlbumMapper;

    @Autowired
    private UserFilePathMapper userFilePathMapper;

    @Override
    public Map<String, Object> batchUploadFile(Map<String, Object> map, CommonsMultipartFile[] files) {
        System.out.println(TAG + "::batchUploadFile >>");

        String uid = (String) map.get("uid");
        Integer msgid = (Integer) map.get("msgid");

        PhotoAlbumExample photoAlbumExample = new PhotoAlbumExample();
        PhotoAlbumExample.Criteria criteria = photoAlbumExample.createCriteria();
        criteria.andNameEqualTo(UserPhotoAlbumNameConstant.PHOTO_ALBUM);
        criteria.andUidEqualTo(uid);
        List<PhotoAlbum> photoAlbums = photoAlbumMapper.selectByExample(photoAlbumExample);
        PhotoAlbum photoAlbum = photoAlbums.size() == 0 ? null : photoAlbums.get(0);

        UserFilePath userFilePath = userFilePathMapper.selectByUid(uid);
        String pathUUID = userFilePath.getUuid();

        for (CommonsMultipartFile file : files) {
            String filename = file.getOriginalFilename();
            Picture picture = new Picture();
            picture.setName(filename);
            picture.setAlbumId(photoAlbum.getId());
            picture.setMsgId(msgid);
            picture.setUid(uid);

            String pictureName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
            String picturePath = UserLocalFilePathUtil.getLocalPicturePath(pathUUID, pictureName);
            String pictureURLName = UserFileUrlUtil.getPictureUrl(pathUUID, pictureName);
            picture.setUrl(pictureURLName);
            int count = pictureMapper.insertSelective(picture);
            if (count > 0) {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                byte[] buffer = new byte[1024];
                int read = 0;
                try {
                    inputStream = file.getInputStream();
                    outputStream = new FileOutputStream(picturePath);
                    while ((read = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, read);
                    }
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    public Map<String, Object> batchUploadFile(User user, CommonsMultipartFile[] files) {
        PhotoAlbumExample photoAlbumExample = new PhotoAlbumExample();
        PhotoAlbumExample.Criteria criteria = photoAlbumExample.createCriteria();
        criteria.andNameEqualTo(UserPhotoAlbumNameConstant.PHOTO_ALBUM);
        criteria.andUidEqualTo(user.getUid());
        List<PhotoAlbum> photoAlbums = photoAlbumMapper.selectByExample(photoAlbumExample);
        PhotoAlbum photoAlbum = photoAlbums.size() == 0 ? null : photoAlbums.get(0);

        UserFilePath userFilePath = userFilePathMapper.selectByUid(user.getUid());
        String pathUUID = userFilePath.getUuid();
        Map<String, Object> data = new HashMap<>();
        for (CommonsMultipartFile file : files) {
            String filename = file.getOriginalFilename();
            if (filename.length() > 0) {
                Picture picture = new Picture();
                picture.setName(filename);
                picture.setAlbumId(photoAlbum.getId());
                picture.setUid(user.getUid());

                String pictureName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
                String picturePath = UserLocalFilePathUtil.getLocalPicturePath(pathUUID, pictureName);
                String pictureURLName = UserFileUrlUtil.getPictureUrl(pathUUID, pictureName);
                picture.setUrl(pictureURLName);

                int count = pictureMapper.insertSelective(picture);
                if (count > 0) {
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    byte[] buffer = new byte[1024];
                    int read = 0;
                    try {
                        inputStream = file.getInputStream();
                        outputStream = new FileOutputStream(picturePath);
                        while ((read = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, read);
                        }
                        outputStream.close();
                        inputStream.close();
                        data.put("code", 0);
                        data.put("msg", "上传成功");
                        data.put("data", "");
                        data.put("error", "");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        data.put("code", -1);
                        data.put("msg", "");
                        data.put("data", "");
                        data.put("error", "发生未知异常");
                    }
                }
            } else {
                data.put("code", 1);
                data.put("msg", "上传文件为空");
                data.put("data", "");
                data.put("error", "");
            }
        }
        return data;
    }
}
