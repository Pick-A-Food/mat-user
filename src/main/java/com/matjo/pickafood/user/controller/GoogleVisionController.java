package com.matjo.pickafood.user.controller;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.matjo.pickafood.user.dto.Base64DTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/vision")
@Log4j2
public class GoogleVisionController {

  private static final String BASE_PATH = "/Users/suhong/Downloads/";

  @RequestMapping(value = "/analysis")
  @ResponseBody
  public boolean analysisImageToString (
          @RequestParam(value = "fileName") String fileName) throws Exception {

    // Instantiates a client
    try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

      // The path to the image file to annotate
      if ( fileName == null) {
        fileName = "homerun.jpeg";
      }

      // Reads the image file into memory
      Path path = Paths.get(BASE_PATH + fileName);
      byte[] data = Files.readAllBytes(path);
      ByteString imgBytes = ByteString.copyFrom(data);

      // Builds the image annotation request
      List<AnnotateImageRequest> requests = new ArrayList<>();
      Image img = Image.newBuilder().setContent(imgBytes).build();
      Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
      AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
              .addFeatures(feat)
              .setImage(img)
              .build();
      requests.add(request);

      // Performs label detection on the image file
      BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
      List<AnnotateImageResponse> responses = response.getResponsesList();

      for (AnnotateImageResponse res : responses) {
        if (res.hasError()) {
          System.out.printf("Error: %s\n", res.getError().getMessage());
          return false;
        }

        for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
          annotation.getAllFields().forEach((k, v)->
                  System.out.printf("%s : %s\n", k, v.toString()));
        }
      }
    }

    return true;
  }

  @GetMapping("/ui")
  public void ui() {

  }

  @GetMapping(value = "/detectText")
  @ResponseBody
  public boolean detectText (MultipartFile[] files) throws Exception {
    MultipartFile file = files[0];
    log.info("=============");
    log.info(file.getOriginalFilename());
    log.info("=============");

    // Instantiates a client
    try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

      byte[] imageBytes = file.getBytes();

      // Reads the image file into memory
      ByteString imgBytes = ByteString.copyFrom(imageBytes);

      // Builds the image annotation request
      List<AnnotateImageRequest> requests = new ArrayList<>();
      Image img = Image.newBuilder().setContent(imgBytes).build();
      Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
      AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
              .addFeatures(feat)
              .setImage(img)
              .build();
      requests.add(request);

      // Performs label detection on the image file
      BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
      List<AnnotateImageResponse> responses = response.getResponsesList();

      for (AnnotateImageResponse res : responses) {
        if (res.hasError()) {
          System.out.printf("Error: %s\n", res.getError().getMessage());
          return false;
        }

        for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
          annotation.getAllFields().forEach((k, v)->
                  System.out.printf("%s : %s\n", k, v.toString()));
        }
      }
    }

    return true;
  }
}
