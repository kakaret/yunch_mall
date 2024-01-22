package com.zrrd.yunchmall.product.client;

import com.zrrd.yunchmall.content.entity.PrefrenceAreaProductRelation;
import com.zrrd.yunchmall.content.entity.SubjectProductRelation;
import com.zrrd.yunchmall.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("shop-content")
public interface ContentService {

    @RequestMapping("/subjectProductRelation/create")
    public ResponseResult createSPR(@RequestBody List<SubjectProductRelation> list);

    @RequestMapping("/subjectProductRelation/list/{id}")
    public ResponseResult listSPR(@PathVariable("id") long id);

    @RequestMapping("/subjectProductRelation/delete/{id}")
    public ResponseResult deleteSPR(@PathVariable("id") long id);


    @RequestMapping("/prefrenceAreaProductRelation/create")
    public ResponseResult createAPR(@RequestBody List<PrefrenceAreaProductRelation> list);


    @RequestMapping("/prefrenceAreaProductRelation/list/{id}")
    public ResponseResult listAPR(@PathVariable("id") long id);

    @RequestMapping("/prefrenceAreaProductRelation/delete/{id}")
    public ResponseResult deleteAPR(@PathVariable("id") long id);

}
