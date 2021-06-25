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
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;
    private final String pathFileOnServer = "s3://exadel-image.s3.eu-west-3.amazonaws.com/imageDiscount/";
    private final MapperConverter mapper;

    public ImageDto getImage(Long id) throws IOException {
        FileSystemManager fsManager = VFS.getManager();
        Image img = imageDAO.findById(id).orElseThrow(NoSuchElementException::new);
        FileObject src = fsManager.resolveFile(img.getImageURL());
        ImageDto image = mapper.map(img, ImageDto.class);
        image.setContent(src.getContent().getByteArray());
        return image;
    }

    public ImageDto save(ImageDto image, String fileName) throws IOException {
        String pathFile = pathFileOnServer + fileName;
        FileSystemManager fsManager = VFS.getManager();
        FileObject dest = fsManager.resolveFile(pathFile);
        FileObject src = fsManager.resolveFile(pathFile);
        src.getContent().getOutputStream().write(image.getContent());
        dest.copyFrom(src, Selectors.SELECT_SELF);
        Image img = mapper.map(image, Image.class);
        img.setImageURL(pathFile);
        return mapper.map(imageDAO.saveAndFlush(img), ImageDto.class);
    }
}
