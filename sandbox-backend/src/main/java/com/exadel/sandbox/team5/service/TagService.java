package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.TagDto;

import java.io.IOException;

public interface TagService extends CRUDService<TagDto> {

    void createFile(String excelFilePath) throws IOException;
}
