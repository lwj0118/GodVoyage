package org.godvoyage.godvoyage.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileService {
    //파일등록하기
    public String uploadFile(String uploadPath, String originalFile, byte[] fileData) throws Exception {
        //uuid생성(중복되지 않게 파일명 랜덤으로 만들어줌 )
        UUID uuid= UUID.randomUUID();
        //확장자 dog.jpg----->   .jpg
        String extension = originalFile.substring(originalFile.lastIndexOf("."));
        //uuid+확장자 --> 새로운 파일명  ex) sfsdfsdf.jpg
        String saveFileName = uuid.toString()+extension;
        //경로+파일명  ex) //shop/item/ssfsdf.jpg.
        String fileUploadFullUrl = uploadPath+"/"+saveFileName;
        //내보낼 스트림 생성.  FileOutputStream ---> 예외처리 지정
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();
        return saveFileName;
    }
    //파일삭제하기
    public void deleteFile(String filePath) {
        //파일 객체 생성
        File deleteFile = new File(filePath);
        //해당 파일이 존재하면 삭제
        if(deleteFile.exists()) {
            deleteFile.delete();
        }
    }
}
