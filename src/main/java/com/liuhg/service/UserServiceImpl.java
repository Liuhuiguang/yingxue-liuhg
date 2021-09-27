package com.liuhg.service;

import com.liuhg.annotation.AddLog;
import com.liuhg.dao.UserMapper;
import com.liuhg.entity.ChinaData;
import com.liuhg.entity.City;
import com.liuhg.entity.User;
import com.liuhg.entity.UserExample;
import com.liuhg.util.AliyunOSSUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userDao;
    @Autowired
    HttpServletRequest request;

    @Override
    public String poiGetUserData() {
        String message = null;
        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFDataFormat dataFormat = sheets.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle = sheets.createCellStyle();
        cellStyle.setDataFormat(format);
        HSSFSheet sheet = sheets.createSheet("用户信息");
        sheet.setColumnWidth(7, 5 * 256);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(3);
        cell.setCellValue("用户信息展示");
        HSSFRow row1 = sheet.createRow(1);
        String[] title = {"ID", "手机号", "用户头像", "姓名", "签名", "微信号", "状态", "创建时间"};
        for (int i = 0; i < title.length; i++) {
            row1.createCell(i).setCellValue(title[i]);
        }
        List<User> users = userDao.selectAll();
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row2 = sheet.createRow(i + 2);
            row2.createCell(0).setCellValue(users.get(i).getId());
            row2.createCell(1).setCellValue(users.get(i).getPhoto());
            row2.createCell(2).setCellValue(users.get(i).getHeadImg());
            row2.createCell(3).setCellValue(users.get(i).getName());
            row2.createCell(4).setCellValue(users.get(i).getSign());
            row2.createCell(5).setCellValue(users.get(i).getWechat());
            row2.createCell(6).setCellValue(users.get(i).getStatus());
            Cell cell1 = row2.createCell(7);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(users.get(i).getRegistTime());
        }

        try {
            sheets.write(new File("D://用户数据.xls"));
            sheets.close();
            message = "导出成功";
        } catch (IOException e) {
            e.printStackTrace();
            message = "导出失败";
        }
        return message;
    }

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //当前页
        map.put("page", page);
        UserExample userExample = new UserExample();
        int records = userDao.selectCountByExample(userExample);
        //总条数
        map.put("records", records);
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        //总页数
        map.put("total", total);

        List<User> users = userDao.selectByExampleAndRowBounds(userExample, new RowBounds((page - 1) * rows, rows));
        //展示数据
        map.put("rows", users);
        return map;
    }

    @AddLog(value = "修改用户状态")
    @Override
    public void updateStatus(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @AddLog(value = "操作用户信息")
    @Override
    public String edit(User user, String oper) {


        if (oper.equals("add")) {
            user.setId(UUID.randomUUID().toString());
            user.setStatus("1");
            user.setRegistTime(new Date());
            userDao.insert(user);
        }
        if (oper.equals("del")) {
            User user1 = userDao.selectOne(user);
            String headImg = user1.getHeadImg();
            userDao.delete(user);
            String realPath = request.getSession().getServletContext().getRealPath("/user/upload");
            File file = new File(realPath, headImg);
            if (file.exists() && file.isFile()) {

                file.delete();
            }

        }
        if (oper.equals("edit")) {
            userDao.updateByPrimaryKeySelective(user);
        }
        return user.getId();
    }

    @AddLog(value = "上传用户头像")
    @Override
    public void fileUpload(MultipartFile file, String id) {
        String originalFilename = file.getOriginalFilename();
        String newFileName = new Date().getTime() + "-" + originalFilename;
        String ObjectName = "img/" + newFileName;
        String bucketName = "yingxue-liu";

        try {
            AliyunOSSUtil.localUpload(bucketName, ObjectName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(id);
        user.setHeadImg("http://yingxue-liu.oss-cn-beijing.aliyuncs.com/" + ObjectName);
        userDao.updateByPrimaryKeySelective(user);
       /* String filename = null;
       try {
            filename = file.getOriginalFilename();

        String newName=new Date().getTime()+"-"+filename;
        String realPath = request.getSession().getServletContext().getRealPath("/user/upload");
        File files = new File(realPath);
        if(!files.exists()){
            files.mkdirs();
        }
        file.transferTo(new File(realPath,newName));
            User user = new User();
            user.setId(id);
            user.setHeadImg(newName);
            userDao.updateByPrimaryKeySelective(user);
    }catch (Exception e) {
        e.printStackTrace();
    }*/
    }

    @Override
    public String importXls(MultipartFile file, String fileName, Integer begin) {
        System.out.println(file.getName());
        String message = null;
        try {
            HSSFWorkbook sheets = new HSSFWorkbook(new FileInputStream(new File(file.getName())));
            HSSFSheet sheet = sheets.getSheet(fileName);
            int end = sheet.getLastRowNum();
            for (int i = begin; i < end; i++) {
                HSSFRow row = sheet.getRow(begin);
                String id = row.getCell(0).getStringCellValue();
                String photo = row.getCell(1).getStringCellValue();
                String headImg = row.getCell(2).getStringCellValue();
                String name = row.getCell(3).getStringCellValue();
                String sign = row.getCell(4).getStringCellValue();
                String wechat = row.getCell(5).getStringCellValue();
                String status = row.getCell(6).getStringCellValue();
                Date date = row.getCell(7).getDateCellValue();
                User user = new User(id, photo, headImg, name, sign, wechat, status, date);
                userDao.insert(user);
                message = "导入成功";
            }
        } catch (IOException e) {
            e.printStackTrace();
            message = "导入失败";
        }
        return message;
    }

    @Override
    public List<ChinaData> queryCity() {
        List<City> boyCity = userDao.queryCity("男");
        List<City> girlCity = userDao.queryCity("女");
        ArrayList<ChinaData> chinaData = new ArrayList<>();
        chinaData.add(new ChinaData("小男孩", boyCity));
        chinaData.add(new ChinaData("小女孩", girlCity));
        return chinaData;
    }

}
