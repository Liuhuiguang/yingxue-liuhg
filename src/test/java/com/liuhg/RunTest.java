package com.liuhg;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;
import com.liuhg.dao.CategoryMapper;
import com.liuhg.dao.LogMapper;
import com.liuhg.dao.UserMapper;
import com.liuhg.entity.Category;
import com.liuhg.entity.City;
import com.liuhg.entity.Log;
import com.liuhg.entity.User;
import com.liuhg.util.SeondPhoneCodeUtil;
import javafx.concurrent.Worker;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class RunTest {
    @Autowired
    CategoryMapper categoryDao;
    @Autowired
    LogMapper logDao;
    @Autowired
    UserMapper userDao;

    @Test
    public void test1() throws IOException {
        Workbook worker = new HSSFWorkbook();
        DataFormat dataFormat = worker.createDataFormat();
        CellStyle cellStyle = worker.createCellStyle();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
        Sheet sheet = worker.createSheet("用户数据");
        sheet.setColumnWidth(7, 5 * 256);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(3);
        cell.setCellValue("用户数据展示");

        String[] title = {"ID", "手机号", "用户头像", "姓名", "签名", "微信号", "状态", "创建时间"};
        Row row2 = sheet.createRow(1);
        for (int i = 0; i < title.length; i++) {
            String s = title[i];
            Cell cell2 = row2.createCell(i);
            cell2.setCellValue(title[i]);
        }
        List<User> users = userDao.selectAll();
        for (int i = 0; i < users.size(); i++) {
            Row row3 = sheet.createRow(2 + i);
            row3.createCell(0).setCellValue(users.get(i).getId());
            row3.createCell(1).setCellValue(users.get(i).getPhoto());
            row3.createCell(2).setCellValue(users.get(i).getHeadImg());
            row3.createCell(3).setCellValue(users.get(i).getName());
            row3.createCell(4).setCellValue(users.get(i).getSign());
            row3.createCell(5).setCellValue(users.get(i).getWechat());
            row3.createCell(6).setCellValue(users.get(i).getStatus());
            Cell cell1 = row3.createCell(7);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(users.get(i).getRegistTime());


        }
        worker.write(new FileOutputStream(new File("D://用户数据.xls")));
        worker.close();
    }

    @Test
    public void test4() throws IOException {
        ExportParams exportParams = new ExportParams("日志信息", "第一页");
        List<Log> logs = logDao.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Log.class, logs);
        try {
            workbook.write(new FileOutputStream(new File("D://日志.xls")));
        } catch (IOException e) {
            e.printStackTrace();

        }
        workbook.close();
    }

    @Test
    public void test5() {
        ExportParams exportParams = new ExportParams("类别信息", "第一页");
        List<Category> categors = categoryDao.queryCategoryAll();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Category.class, categors);
        try {
            workbook.write(new FileOutputStream(new File("D://类别信息.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test6() {
        List<Category> categories = categoryDao.queryCategoryAll();
        System.out.println(categories);
    }

    @Test
    public void test7() {
        List<City> cities = userDao.queryCity("男");
        for (City city : cities) {

            System.out.println(city);
        }
    }
}
