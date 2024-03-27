package org.godvoyage.godvoyage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")
    String uploadPath;     //-->파일경로. application.properties에 지정해둔것과 일치 file:///c:/shop/

    //로컬컴퓨터에 업로드한 파일 찾을 위치를 설정 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //웹브라우저에 입력하는 url에 /images로 시작하는경우
        //uploadPath 프로퍼티 값을 읽어서 설정한 폴더를 기준으로 읽어오도록 설정
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
}
