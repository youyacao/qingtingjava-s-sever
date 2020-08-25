package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的作品
     */
    @RequestMapping(method = RequestMethod.POST,value = "/myProduction")
    public String myProduction(@RequestBody String data){
        return videoService.selectMyProduction(data);
    }

    /**
     * @param page 页码
     * @param limit 页数
     * @return 最新视频
     */
    @RequestMapping(method = RequestMethod.POST,value = "/newestVideo")
    public String selectNewest(@RequestBody String data){
        return videoService.getNewsetVideo(data);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/getVideoByCid")
    public String selectVideoCid(@RequestBody String data){
        return videoService.getVideoByCategoryId(data);
    }
    /**
     * @param searchData 搜索数据
     * @return 结果数据并分页
     */
    @RequestMapping(method = RequestMethod.POST,value = "/searchList")
    public String selectVideoData(@RequestBody String searchData){
        return videoService.searchVideo(searchData);
    }

    /**
     * @param videoData video数据
     * @return 成功
     */
    @RequestMapping(method = RequestMethod.POST,value = "/addVideo")
    public String insertVideo(@RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(value = "data",required = false)String videoData){
        return videoService.addVideo(file,videoData);
    }

    /**
     * @param videoData video数据
     * @return 视频详情
     */
    @RequestMapping(method = RequestMethod.POST,value = "/videoInfo")
    public String findVideoInfo(@RequestBody String videoData){
        return videoService.getVideoInfo(videoData);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/videoInfoRandom")
    public String findVideoInfoRandom(){
        return videoService.getVideoInfoRandom();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/videoAll")
    public String findVideoAll(@RequestParam(value = "limit")int limit,@RequestParam(value = "page")int page){
        return videoService.getVideoAll(limit,page);
    }

}
