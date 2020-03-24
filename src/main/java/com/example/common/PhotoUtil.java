package com.example.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author {李永强}
 * @Title PhotoUtil
 * @Description
 * @date 2019/12/5
 */
@RestController
public class PhotoUtil {

//    /**
//     * 图片上传
//     * @param file
//     * @param request
//     * @return
//     */
//    @RequestMapping("upload")
//    public Map upload(MultipartFile[] file, HttpServletRequest request){
//
//        String prefix="";
//        //写入数据并输出
//        OutputStream out = null;
//        //读数据
//        InputStream fileInput=null;
//        System.out.println(file);
//
//        try{
//            if(file!=null){
//                String[] originalName = new String[file.length];
//                String[] uuids = new String[file.length];
//                String[] filepath =new String[file.length];
//                String[] imgs = new String[file.length];
//                for (int i = 0; i <file.length ; i++) {
//                    originalName[i] = file[i].getOriginalFilename();
//                    prefix=originalName[i].substring(originalName[i].lastIndexOf(".")+1);
//                    //生成一个唯一标识符给图片命名，避免图片名重复，覆盖原有图片
//                    uuids[i] = UUID.randomUUID()+"";
//                    imgs[i] = uuids[i]+"." + prefix;
//                    System.out.println("保存到数据库的名称是：：："+imgs[i]);
//                    //图片上传到本地路径
//                    filepath[i] = "E:\\lyq\\image\\"+uuids[i]+"." + prefix;
//                    File files=new File(filepath[i]);
//                    System.out.println(filepath[i]);
//                    if(!files.getParentFile().exists()){
//                        files.getParentFile().mkdirs();
//                    }
//                    //transferTo()方法将上传的文件写到服务器指定的文件
//                    file[i].transferTo(files);
//                }
//                //List转String
//                //String[] strs1=testList.toArray(new String[testList.size()]);
//
//                //string转换成list
//                List<String> list = Arrays.asList(imgs);
//                System.out.println("list是："+list);
//
//                Map<String,Object> map2=new HashMap<>();
//                Map<String,Object> map=new HashMap<>();
//                map.put("code",0);
//                map.put("msg","");
//                map.put("data",list);
//                //数据库保存的路径
////                map2.put("src",list);
//                return map;
//            }
//
//        }catch (Exception e){
//        }finally{
//            try {
//                if(out!=null){
//                    out.close();
//                }
//                if(fileInput!=null){
//                    fileInput.close();
//                }
//            } catch (IOException e) {
//            }
//        }
//        Map<String,Object> map=new HashMap<>();
//        map.put("code",1);
//        map.put("msg","");
//        return map;
//    }

    @RequestMapping("upload")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        //写入数据并输出
        OutputStream out = null;
        //读数据
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                //生成一个唯一标识符给图片命名，避免图片名重复，覆盖原有图片
                String uuid = UUID.randomUUID()+"";
                //图片上传到本地路径
                String filepath = "E:\\lyq\\image\\"+uuid+"." + prefix;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                //transferTo()方法将上传的文件写到服务器指定的文件
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                //数据库保存的路径
                map2.put("src",uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }


    /**
     * 图片的读取
     */
    @GetMapping(value = "/ioReadImage/{imgName}")
    public String IoReadImage(@PathVariable String imgName, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = "E:\\lyq\\image\\" + imgName;
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("image/jpg");
            out = response.getOutputStream();
            //读取文件流
            int len = ips.available();
            byte[] buffer = new byte[len];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }

}
