package com.example.common;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * 生成验证码
 * @author Yongqiang.Li
 */
@RestController
public class Picverifyaction {
//    private final static Logger logger = LoggerFactory.getLogger(Picverifyaction.class);

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            //输出验证码图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
//            logger.error("获取验证码失败>>>>   ", e);
        }
    }

    /**
     * 校验验证码
//     */
////    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST,headers = "Accept=application/json")
//    public boolean checkVerify(@RequestParam String verifyInput, HttpSession session) {
//        try{
//            //从session中获取随机数
//            String inputStr = verifyInput;
//            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
//            if (random == null) {
//                return false;
//            }
//            if (random.equals(inputStr)) {
//                return true;
//            } else {
//                return false;
//            }
//        }catch (Exception e){
////            logger.error("验证码校验失败", e);
//            return false;
//        }
//    }


}
