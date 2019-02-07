package com.springcloud.weibo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.springcloud.weibo.mapper.MessageCollectionMapper;
import com.springcloud.weibo.mapper.MessageForwardingMapper;
import com.springcloud.weibo.mapper.MessageLikeMapper;
import com.springcloud.weibo.mapper.MessageMapper;
import com.springcloud.weibo.pojo.Message;
import com.springcloud.weibo.pojo.MessageCollection;
import com.springcloud.weibo.pojo.MessageCollectionExample;
import com.springcloud.weibo.pojo.MessageForwarding;
import com.springcloud.weibo.pojo.MessageForwardingExample;
import com.springcloud.weibo.pojo.MessageLike;
import com.springcloud.weibo.pojo.MessageLikeExample;
import com.springcloud.weibo.pojo.User;

@Service
public class MessageServiceImpl implements MessageService {

    private final String TAG = MessageServiceImpl.class.getName();

    @Autowired
    private MessageLikeMapper messageLikeMapper;

    @Autowired
    private MessageForwardingMapper messageForwardingMapper;

    @Autowired
    private MessageCollectionMapper messageCollectionMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public Map<String, Object> saveMessage(Message message, CommonsMultipartFile[] images, HttpSession session) {
        System.out.println(TAG + "::saveMessage >>");
        Map<String, Object> data = new HashMap<>();
        if (message.getContent() != null) {
            if (message.getContent().length() > 0) {
                User user = (User) session.getAttribute("user");
                message.setUid(user.getUid());
                int count = messageMapper.insertSelective(message);
                if (count > 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("uid", user.getUid());
                    map.put("msgid", message.getId());
                    // 上传文件
                    if (images.length > 0) {
                        if (images[0].getOriginalFilename().length() > 0) {
                            uploadFileService.batchUploadFile(map, images);
                        }
                    }
                    data.put("code", 0);
                    data.put("msg", "发布成功");
                    data.put("data", "");
                    data.put("error", "");
                } else {
                    data.put("code", -1);
                    data.put("msg", "error");
                    data.put("data", "");
                    data.put("error", "发生未知异常");
                }
            } else {
                data.put("code", 1);
                data.put("msg", "发布内容不能为空");
                data.put("data", "");
                data.put("error", "");
            }
        }
        return data;
    }

    @Override
    public Map<String, Object> collectMessage(String msgid, HttpSession session) {
        System.out.println(TAG + "::collectMessage >>");
        Map<String, Object> data = new HashMap<>();
        User user = (User) session.getAttribute("user");
        MessageCollectionExample messageCollectionExample = new MessageCollectionExample();
        MessageCollectionExample.Criteria criteria = messageCollectionExample.createCriteria();
        criteria.andMsgIdEqualTo(Integer.parseInt(msgid));
        criteria.andUidEqualTo(user.getUid());
        List<MessageCollection> messageCollections = messageCollectionMapper.selectByExample(messageCollectionExample);
        // 取消收藏
        if (messageCollections.size() > 0) {
            for (MessageCollection messageCollection : messageCollections) {
                criteria.andMsgIdEqualTo(messageCollection.getMsgId());
                criteria.andUidEqualTo(messageCollection.getUid());
                int count = messageCollectionMapper.deleteByExample(messageCollectionExample);
                if (count > 0) {
                    data.put("code", 0);
                    data.put("msg", "取消收藏成功");
                    data.put("data", "");
                    data.put("error", "");
                } else {
                    data.put("code", -1);
                    data.put("msg", "取消收藏失败");
                    data.put("data", "");
                    data.put("error", "");
                }
            }
        } else {
            // 添加收藏
            MessageCollection messageCollection = new MessageCollection();
            messageCollection.setMsgId(Integer.parseInt(msgid));
            messageCollection.setUid(user.getUid());
            int count = messageCollectionMapper.insertSelective(messageCollection);
            if (count > 0) {
                data.put("code", 0);
                data.put("msg", "收藏成功");
                data.put("data", "");
                data.put("error", "");
            } else {
                data.put("code", -1);
                data.put("msg", "error");
                data.put("data", "");
                data.put("error", "发生未知异常");
            }
        }
        return data;
    }

    @Override
    public Map<String, Object> forwardingMessage(String msgid, HttpSession session) {
        System.out.println(TAG + "::forwardingMessage >>");
        Map<String, Object> data = new HashMap<>();
        User user = (User) session.getAttribute("user");
        MessageForwardingExample messageForwardingExample = new MessageForwardingExample();
        MessageForwardingExample.Criteria criteria = messageForwardingExample.createCriteria();
        criteria.andMsgIdEqualTo(Integer.parseInt(msgid));
        criteria.andUidEqualTo(user.getUid());
        List<MessageForwarding> messageForwardings = messageForwardingMapper.selectByExample(messageForwardingExample);
        if (messageForwardings.size() == 0) {
            MessageForwarding messageForwarding = new MessageForwarding();
            messageForwarding.setMsgId(Integer.parseInt(msgid));
            messageForwarding.setUid(user.getUid());
            int count = messageForwardingMapper.insertSelective(messageForwarding);
            if (count > 0) {
                data.put("code", 0);
                data.put("msg", "OK");
                data.put("data", "");
                data.put("error", "");
            } else {
                data.put("code", -1);
                data.put("msg", "error");
                data.put("data", "");
                data.put("error", "发生未知异常");
            }
        } else {
            data.put("code", 1);
            data.put("msg", "不能重复转发");
            data.put("data", "");
            data.put("error", "");
        }
        return data;
    }

    @Override
    public Map<String, Object> likeMessage(String msgid, HttpSession session) {
        System.out.println(TAG + "::likeMessage >>");
        Map<String, Object> data = new HashMap<>();
        User user = (User) session.getAttribute("user");
        MessageLikeExample messageLikeExample = new MessageLikeExample();
        MessageLikeExample.Criteria criteria = messageLikeExample.createCriteria();
        criteria.andMsgIdEqualTo(Integer.parseInt(msgid));
        criteria.andUidEqualTo(user.getUid());
        List<MessageLike> messageLikes = messageLikeMapper.selectByExample(messageLikeExample);
        if (messageLikes.size() > 0) {
            for (MessageLike messageLike : messageLikes) {
                criteria.andMsgIdEqualTo(messageLike.getMsgId());
                criteria.andUidEqualTo(messageLike.getUid());
                int count = messageLikeMapper.deleteByExample(messageLikeExample);
                if (count > 0) {
                    data.put("code", 0);
                    data.put("msg", "取消赞成功");
                    data.put("data", "");
                    data.put("error", "");
                } else {
                    data.put("code", -1);
                    data.put("msg", "取消赞失败");
                    data.put("data", "");
                    data.put("error", "");
                }
            }
        } else {
            MessageLike messageLike = new MessageLike();
            messageLike.setMsgId(Integer.parseInt(msgid));
            messageLike.setUid(user.getUid());
            int count = messageLikeMapper.insertSelective(messageLike);
            if (count > 0) {
                data.put("code", 0);
                data.put("msg", "点赞成功");
                data.put("data", "");
                data.put("error", "");
            } else {
                data.put("code", -1);
                data.put("msg", "error");
                data.put("data", "");
                data.put("error", "发生未知异常");
            }
        }
        return data;
    }

}
