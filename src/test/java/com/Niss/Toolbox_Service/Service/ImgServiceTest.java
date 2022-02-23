package com.Niss.Toolbox_Service.Service;

import org.junit.jupiter.api.Test;

public class ImgServiceTest {
    ImgService imgService = new ImgService();

    @Test
    public void findUserImg() {
        imgService.findUserImg("admin");
    }
}
