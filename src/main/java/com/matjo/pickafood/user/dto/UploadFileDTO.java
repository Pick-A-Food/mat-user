package com.matjo.pickafood.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileDTO {

  private List<MultipartFile> files;

}
