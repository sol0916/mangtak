package com.delivery.springbootproject.prodDelivery.service;

import com.delivery.springbootproject.command.ProductImgVO;
import com.delivery.springbootproject.command.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@Service("proDeliveryService")
public class ProdDeliveryServiceImpl implements ProdDeliveryservice {

    @Autowired
    private ProdDeliveryMapper prodDeliveryMapper;

    @Value("${project.upload.path}")
    private String uploadPath;

    // 폴더 생성함수
    private String makeFolder() {
        String path = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        File file = new File(uploadPath + "/" + path);

        if (file.exists() == false) { // 존재하면 true, 존재하지 않으면 false
            file.mkdir();
        }
        return path; // 폴더명 반환
    }

    @Override
    public ArrayList<ProductVO> getList(String id) {
        return prodDeliveryMapper.getList(id);
    }

    @Override
    public ArrayList<ProductVO> clearList(String id) {
        return prodDeliveryMapper.clearList(id);
    }

    @Override
    public int statusModify(String del_no) {
        return prodDeliveryMapper.statusModify(del_no);
    }

    @Override
    public int imgReg(String del_no, MultipartFile file) {


        //파일 이름 받기
        String originName = file.getOriginalFilename();
        //브라우저 별로 파일의 경로가 다를 수 있으므로 /기준으로 파일명만 잘라서 다시 저장
        String filename = originName.substring(originName.lastIndexOf("/") + 1);
        //파일사이즈
        long size = file.getSize();
        //동일한 파일 재업로드시 기존파일 덮어버리기 때문에, 난수 이름으로 파일명을 바꿔서 올림
        String uuid = UUID.randomUUID().toString();
        //날짜별로 폴더생성
        String filepath = makeFolder();
        //세이브할 경로
        String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;

//        System.out.println(originName);
//        System.out.println(size);
//        데이터페이스 추후 저장
        System.out.println("실제파일명" + filename);
        System.out.println("난수값" + uuid);
        System.out.println("날짜폴더경로:" + filepath);
        System.out.println("세이브할 경로: " + savepath);
        System.out.println("----------------------------------------------------");

        try {
            File saveFile = new File(savepath);
            file.transferTo(saveFile);
        } catch (IOException e) {
            System.out.println("파일업로드 중 error 발생");
            e.printStackTrace();
            return 0;
        }
        //productUpload 테이블에 파일 경로 insert
        //prod_id는 insert 전에

        int result =  prodDeliveryMapper.imgReg(ProductImgVO.builder()
                .img_name(filename)
                .img_path(filepath)
                .img_uuid(uuid)
                .img_type("배송완료")
                .img_del_no(del_no)
                .build());
// prod_name, prod_no 대신 prod_del_no로 변경 -> 테이블도 변경
        return result;
    }

    @Override
    public ProductVO getDetail(String del_no) {
        return prodDeliveryMapper.getDetail(del_no);
    }

    @Override
    public int defectiveModify(String del_no) {
        return prodDeliveryMapper.defectiveModify(del_no);
    }

    @Override
    public void defectiveModifyContent(String del_no, String d_content) {
        prodDeliveryMapper.defectiveModifyContent(del_no, d_content);
    }

    @Override
    public int defectiveImg(String del_no, MultipartFile file) {
        //파일 이름 받기
        String originName = file.getOriginalFilename();
        //브라우저 별로 파일의 경로가 다를 수 있으므로 /기준으로 파일명만 잘라서 다시 저장
        String filename = originName.substring(originName.lastIndexOf("/") + 1);
        //파일사이즈
        long size = file.getSize();
        //동일한 파일 재업로드시 기존파일 덮어버리기 때문에, 난수 이름으로 파일명을 바꿔서 올림
        String uuid = UUID.randomUUID().toString();
        //날짜별로 폴더생성
        String filepath = makeFolder();
        //세이브할 경로
        String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;

//        System.out.println(originName);
//        System.out.println(size);
//        데이터페이스 추후 저장
        System.out.println("실제파일명" + filename);
        System.out.println("난수값" + uuid);
        System.out.println("날짜폴더경로:" + filepath);
        System.out.println("세이브할 경로: " + savepath);
        System.out.println("----------------------------------------------------");

        try {
            File saveFile = new File(savepath);
            file.transferTo(saveFile);
        } catch (IOException e) {
            System.out.println("파일업로드 중 error 발생");
            e.printStackTrace();
            return 0;
        }
        //productUpload 테이블에 파일 경로 insert
        //prod_id는 insert 전에

        int result =  prodDeliveryMapper.imgReg(ProductImgVO.builder()
                .img_name(filename)
                .img_path(filepath)
                .img_uuid(uuid)
                .img_type("사고물품")
                .img_del_no(del_no)
                .build());
// prod_name, prod_no 대신 prod_del_no로 변경 -> 테이블도 변경
        return result;
    }

    @Override
    public int cancelForm(String prod_del_no) {
        return prodDeliveryMapper.cancelForm(prod_del_no);
    }

    @Override
    public void deleteImg(String prod_del_no) {
        prodDeliveryMapper.deleteImg(prod_del_no);
    }

    @Override
    public ArrayList<ProductVO> defectiveList(String id) {
        return prodDeliveryMapper.defectiveList(id);
    }

    @Override
    public void defectiveDeleteImg(String prod_del_no) {
        prodDeliveryMapper.defectiveDeleteImg(prod_del_no);
    }

    @Override
    public int defectiveForm(String prod_del_no) {
        return prodDeliveryMapper.defectiveForm(prod_del_no);
    }
}
