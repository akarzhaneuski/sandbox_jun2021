package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ImageDAO;
import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.vfs2.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;
    private final String pathFileOnServer = "s3://exadel-image.s3.eu-west-3.amazonaws.com/imageDiscount/";
    private final DiscountServiceImpl service;
    private final MapperConverter mapper;

    public byte[] getImage(Long id) throws IOException {
        FileSystemManager fsManager = VFS.getManager();

        return null;
    }

    public ImageDto save(ImageDto image, String fileName) throws IOException {
        String pathFile = pathFileOnServer + fileName;
        FileSystemManager fsManager = VFS.getManager();
        FileObject dest = fsManager.resolveFile(pathFile);
        FileObject src = fsManager.resolveFile(pathFile);
        src.getContent().getOutputStream().write(image.getContent());
        dest.copyFrom(src, Selectors.SELECT_SELF);
        Image img = mapper.map(image, Image.class);
        return mapper.map(imageDAO.saveAndFlush(img), ImageDto.class);
    }
}
