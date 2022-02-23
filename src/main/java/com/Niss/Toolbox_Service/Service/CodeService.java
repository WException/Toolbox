package com.Niss.Toolbox_Service.Service;

import com.Niss.Toolbox_Service.Mapper.CodeMapper;
import com.Niss.Toolbox_Service.pojo.Code;
import com.Niss.Toolbox_Service.utils.StaticUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
public class CodeService {
    @Resource
    private CodeMapper codeMapper;

    /**
     * 代码粘贴板生成代码链接
     *
     * @param userName 用户名
     * @param code     代码内容
     * @param codeType 代码类型(Python,Html,JAVA..)
     * @return 生成的访问链接
     */
    public String insertCode(String userName, String code, String codeType) {
        // 1、拼凑代码
        String data = StaticUtils.HTML1 + codeType + StaticUtils.HTML2 + code + StaticUtils.HTML3;
        // 2、写入文件
        FileWriter writer;
        String filename = userName + "/" + System.currentTimeMillis();
        try {
            writer = new FileWriter(StaticUtils.IMGPATH + filename + ".html");
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3、存入数据库
        Code codeObj = new Code();
//        codeObj.setFileData(code);
        codeObj.setFileType(codeType);
        codeObj.setUserName(userName);
        int i = codeMapper.insertCode(codeObj);
        System.out.println(userName + "生成了" + i + "条代码链接，在" + new Date());
        // 4、返回链接
        return StaticUtils.RETURNIMGPATH + filename + ".html";
    }
}
