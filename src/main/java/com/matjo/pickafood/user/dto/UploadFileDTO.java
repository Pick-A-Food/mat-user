package com.matjo.pickafood.user.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileDTO {

  private MultipartFile file;
}
