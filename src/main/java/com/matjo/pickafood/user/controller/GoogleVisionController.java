package com.matjo.pickafood.user.controller;

import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;
import com.matjo.pickafood.user.dto.ListDTO;
import com.matjo.pickafood.user.dto.UploadFileDTO;
import com.matjo.pickafood.user.dto.WordRectangleDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping(value = "/vision")
@Log4j2
public class GoogleVisionController {

    private static final String BASE_PATH = "/Users/suhong/Downloads/";

    @RequestMapping(value = "/analysis")
    public boolean analysisImageToString(
            @RequestParam(value = "fileName") String fileName) throws Exception {

        // Instantiates a client
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

            // The path to the image file to annotate
            if (fileName == null) {
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
                    annotation.getAllFields().forEach((k, v) ->
                            System.out.printf("%s : %s\n", k, v.toString()));
                }
            }
        }

        return true;
    }

    @GetMapping("/ui")
    public void ui() {
        log.info("/vision/ui");
    }

    @PostMapping(value = "/detectText", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListDTO<String> detectText(UploadFileDTO uploadFileDTO) throws Exception {
        List<String> wordsInDscOrder = new ArrayList<>();
        ListDTO<String> listDTO = new ListDTO<>(wordsInDscOrder);
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

            // Reads the image file into memory
            byte[] data = uploadFileDTO.getFiles().get(0).getBytes();
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
                System.out.println("-------------------------------------------------------");
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return listDTO;
                }

                List<EntityAnnotation> entityAnnotations = res.getTextAnnotationsList();
                List<WordRectangleDTO> wordRectangles = new ArrayList<>();
                for (int i = 1; i < entityAnnotations.size(); i++) {

                    EntityAnnotation entity = entityAnnotations.get(i);
                    System.out.println("============================================");
                    System.out.println(i);
//                    entity.getAllFields().forEach((k, v) ->
//                            System.out.printf("%s : %s\n", k, v.toString()));

                    WordRectangleDTO wordRectangle = new WordRectangleDTO();

                    entity.getAllFields().forEach((annotation, content) -> {
                        if (annotation.toString().equals("google.cloud.vision.v1.EntityAnnotation.description")) {
                            wordRectangle.setWord(content.toString());
                            System.out.println("word = " + content.toString());
                        } else {
                            String[] results = content.toString().split("\n");
                            List<Integer> x = new ArrayList<>();
                            List<Integer> y = new ArrayList<>();
                            System.out.println("============= results ============");
                            for (String result : results) {
                                result = result.trim();

                                if (result.contains("x")) {
                                    x.add(Integer.parseInt(result.split(" ")[1]));
                                    System.out.println(result);
                                } else if (result.contains("y")) {
                                    y.add(Integer.parseInt(result.split(" ")[1]));
                                    System.out.println(result);
                                }
                            }
                            for (int j = 0; j < x.size(); j++) {
                                Point point = new Point(x.get(j), y.get(j));
                                wordRectangle.getPoints().add(point);
                            }
                        }
                    });
                    wordRectangles.add(wordRectangle);

                }
                Collections.sort(wordRectangles, (a,b) -> (int) (b.getSize() - a.getSize()));
                for (WordRectangleDTO word : wordRectangles) {
                    wordsInDscOrder.add(word.getWord());
                }
            }
        }
        return listDTO;
    }
//    @ApiOperation(value = "Upload POST", notes = "POST 방식으로 파일 등록")
//    @PostMapping(value = "/detectText", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String[] detectText (UploadFileDTO uploadFileDTO) throws Exception {
//        log.info("=============");
//        log.info(uploadFileDTO);
//        log.info("=============");
//
//        Map<String, String> result = new HashMap<>();
//        // Instantiates a client
//        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
//
//            byte[] imageBytes = uploadFileDTO.getFiles().get(0).getBytes();
//
//            // Reads the image file into memory
//            ByteString imgBytes = ByteString.copyFrom(imageBytes);
//
//            // Builds the image annotation request
//            List<AnnotateImageRequest> requests = new ArrayList<>();
//            Image img = Image.newBuilder().setContent(imgBytes).build();
//            Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
//            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
//                    .addFeatures(feat)
//                    .setImage(img)
//                    .build();
//            requests.add(request);
//
//            // Performs label detection on the image file
//            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
//            List<AnnotateImageResponse> responses = response.getResponsesList();
//
//            for (int i = 0; i < responses.size(); i++) {
//                AnnotateImageResponse res = responses.get(i);
//                if (res.hasError()) {
//                    System.out.printf("Error: %s\n", res.getError().getMessage());
//                    return new String[]{"Error"};
//                }
//                if (i == 0) {
//                    List<EntityAnnotation> annotations = res.getTextAnnotationsList();
//                    for (int j = 0; j < annotations.size(); j++) {
//                        EntityAnnotation annotation = annotations.get(j);
//                        if (j == 0) {
//                            annotation.getAllFields().
//                                    forEach((k, v) ->
//                                            {
//                                                System.out.printf("%s : %s ......\n", k, v.toString());
//                                                result.put(k.toString(), v.toString());
//
//                                            }
//                                    );
//                        }
//                    }
//                }
//            }
//        }
//
//        return result.get("google.cloud.vision.v1.EntityAnnotation.description").split("\n");
//    }
}
