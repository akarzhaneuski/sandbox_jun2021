package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.TagDto;

import java.io.ByteArrayInputStream;

public interface TagService extends CRUDService<TagDto> {

    ByteArrayInputStream getStatisticCSVFileOrdersByTag();
}
