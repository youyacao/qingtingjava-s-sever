package com.yyc.songshu.manager.threads;

import com.yyc.songshu.manager.dao.CollectDAO;
import com.yyc.songshu.manager.dao.FollowDAO;
import com.yyc.songshu.manager.dao.LikeDAO;
import com.yyc.songshu.manager.pojo.Video;
import com.yyc.songshu.manager.util.VideoUTtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class VideoListThread implements Callable<List<Video>> {
    private List<Video> videos;
    private Integer id;
    private CollectDAO collectDAO;
    private LikeDAO likeDAO;
    private FollowDAO followDAO;

    public VideoListThread(List<Video> videos, Integer id, CollectDAO collectDAO, LikeDAO likeDAO,FollowDAO followDAO) {
        this.videos = videos;
        this.id = id;
        this.collectDAO = collectDAO;
        this.likeDAO = likeDAO;
        this.followDAO = followDAO;
    }

    @Override
    public List<Video> call() throws Exception {
        System.out.println(id+":"+videos);
        for (Video video:videos){
            int second = (((int) Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)) - video.getCreatedAt())) ;
            int min = ((((int) Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)) - video.getCreatedAt())) / 60) ;
            int hour = (min / 60) ;
            if (id != null) {
                Integer is = collectDAO.selectIsCollect(video.getId(), id);
                Integer isC = followDAO.selectIsFollow(id);
                Integer isL = likeDAO.selectIsLike(video.getId(), id);

                if (is == null) {
                    video.setIsCollect("false");
                } else {
                    video.setIsCollect("true");
                }
                if (isC == null) {
                    video.setIsFocus("false");
                } else {
                    video.setIsFocus("true");
                }
                if (isL == null) {
                    video.setIsLike("false");
                } else {
                    video.setIsLike("true");
                }
            }else {
                video.setIsLike("");
                video.setIsCollect("");
            }
            if (video.getUsers().getAvatar()==null){
                video.getUsers().setAvatar("null");
            }
            if (video.getUsers().getNickname()==null){
                video.getUsers().setNickname("null");
            }
            if (video.getUsers().getEmail()==null){
                video.getUsers().setEmail("null");
            }
            System.out.println(System.currentTimeMillis()+":***");
            if (video.getCommentNum()>10000){
                double cS = video.getCommentNum()/10000;
                video.setCommentNumStr(String.valueOf(cS)+"W");
            }else {
                video.setCommentNumStr(String.valueOf(video.getCommentNum()));
            }
            if (video.getViewNum()>10000){
                double vS = video.getViewNum()/10000;
                video.setViewNumStr(String.valueOf(vS)+"W");
            }else {
                video.setViewNumStr(String.valueOf(video.getViewNum()));
            }
            if (video.getLikeNum()>10000){
                double lS = video.getLikeNum()/10000;
                video.setLikeNumStr(String.valueOf(lS)+"W");
            }else {
                video.setLikeNumStr(String.valueOf(video.getLikeNum()));
            }
            if (video.getCollectNum()>10000){
                double lS = video.getLikeNum()/10000;
                video.setCollectNumStr(String.valueOf(lS)+"W");
            }else {
                video.setCollectNumStr(String.valueOf(video.getLikeNum()));
            }
            if (video.getShareNum()>10000){
                double SS = video.getShareNum()/10000;
                video.setShareNumStr(String.valueOf(SS)+"W");
            }else {
                video.setShareNumStr(String.valueOf(video.getShareNum()));
            }
            System.out.println(System.currentTimeMillis()+":---");
            /*if (video.getVideoUrl()!=null){
                String mtime = VideoUTtils.readVideoTime(video.getVideoUrl());
                video.setDuration(mtime);
                System.out.println(video.getDuration());
            }else {
                video.setTime("null");
            }*/
            System.out.println(System.currentTimeMillis()+":+++");
            if ( 0<second && second< 60){
                video.setTime(second+"秒前");
            }else if ( 0<min && min< 60){
                video.setTime(min+"分前");
            }else if (0 < hour && hour <= 24 ){
                video.setTime(hour+"小时前");
            }else {
                video.setTime(hour/24+"天前");
            }
            System.out.println(System.currentTimeMillis()+":===");
        }
        System.out.println(videos);
        return videos;
    }

    /*private synchronized List<Video> videosList (List<Video> videos, int id, CollectDAO collectDAO) {
        long newDate = System.currentTimeMillis();
        for (Video video:videos){
            int second = (((int) newDate - video.getCreatedAt())/1000) ;
            int min = ((((int) newDate - video.getCreatedAt())/1000) / 60) ;
            int hour = (min / 60) ;
            Integer is = collectDAO.selectIsCollect(video.getId(),id);
            if (is==null){
                video.setIsCollect("false");
            }else {
                video.setIsCollect("true");
            }
            System.out.println(System.currentTimeMillis()+":***");
            if (video.getCommentNum()>10000){
                double cS = video.getCommentNum()/10000;
                video.setCommentNumStr(String.valueOf(cS)+"W");
            }else {
                video.setCommentNumStr(String.valueOf(video.getCommentNum()));
            }
            if (video.getViewNum()>10000){
                double vS = video.getViewNum()/10000;
                video.setViewNumStr(String.valueOf(vS)+"W");
            }else {
                video.setViewNumStr(String.valueOf(video.getViewNum()));
            }
            if (video.getLikeNum()>10000){
                double lS = video.getLikeNum()/10000;
                video.setLikeNumStr(String.valueOf(lS)+"W");
            }else {
                video.setLikeNumStr(String.valueOf(video.getLikeNum()));
            }
            if (video.getCollectNum()>10000){
                double lS = video.getLikeNum()/10000;
                video.setCollectNumStr(String.valueOf(lS)+"W");
            }else {
                video.setCollectNumStr(String.valueOf(video.getLikeNum()));
            }
            if (video.getShareNum()>10000){
                double SS = video.getShareNum()/10000;
                video.setShareNumStr(String.valueOf(SS)+"W");
            }else {
                video.setShareNumStr(String.valueOf(video.getShareNum()));
            }
            System.out.println(System.currentTimeMillis()+":---");
            if (video.getVideoUrl()!=null){
                String mtime = VideoUTtils.readVideoTime(video.getVideoUrl());
                video.setDuration(mtime);
            }else {
                video.setTime("null");
            }
            System.out.println(System.currentTimeMillis()+":+++");
            if ( 0<second && second< 60){
                video.setTime(second+"秒前");
            }else if ( 0<min && min< 60){
                video.setTime(min+"分前");
            }else if (0 < hour && hour <= 24 ){
                video.setTime(hour+"小时前");
            }else {
                video.setTime(hour/24+"天前");
            }
            System.out.println(System.currentTimeMillis()+":===");
        }
        System.out.println(videos);
        return videos;
    }*/
}
