package com.matjo.pickafood.user.controller;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/vision")
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
}
