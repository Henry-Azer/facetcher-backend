package com.henry.facetcher.processor;

import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.enums.Gender;
import com.henry.facetcher.service.ImageService;
import com.henry.facetcher.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.henry.facetcher.constants.FacetcherConstants.*;

/**
 * @author Henry Azer
 * @since 12/02/2023
 */
@Slf4j
@Component
@AllArgsConstructor
public class FDLProcessorImpl implements FDLProcessor {
    private final ImageService imageService;

    @Override
    public void process(UserTrialDto userTrialDto) {
        log.info("FDLProcessor: process() called");
        userTrialDto.setInputImage(imageService.create(imageService.constructImageDto(userTrialDto.getInputImageFile())));
        File inputImageFile = writeImageToFDL(userTrialDto.getInputImage().getImage(), userTrialDto.getInputImage().getName());
        userTrialDto.setProcessProperties(generateFDLImageProperties(userTrialDto.getInputImage().getName(), userTrialDto.getGender()));
        userTrialDto.setImageProperties(userTrialDto.getProcessProperties().toString());
        processImageFDL(userTrialDto);
        inputImageFile.delete();
        if (userTrialDto.getExceptionOccurred()) return;
        MultipartFile outputImageMultipartFile = extractImageMultipartFileFromFDL(userTrialDto.getInputImage().getName());
        userTrialDto.setOutputImage(imageService.create(imageService.constructImageDto(outputImageMultipartFile, userTrialDto.getInputImage().getName())));
        File outputImageFile = extractImageFileFromFDL(userTrialDto.getInputImage().getName());
        outputImageFile.delete();
        log.info("FDLProcessor: process() ended");
    }

    private void processImageFDL(UserTrialDto userTrialDto) {
        log.info("FDLProcessor: processImageFDL() called");
        try {
            ProcessBuilder processBuilder = buildFDLProcessBuilder(userTrialDto.getProcessProperties());
            processBuilder.directory(new File(System.getProperties().get("user.dir") + FDL_DIRECTORY_PATH));
            Process process = processBuilder.start();

            BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String processOutputLine;
            boolean exceptionStored = false;
            userTrialDto.setExceptionOccurred(false);
            while ((processOutputLine = processOutputReader.readLine()) != null) {
                if (userTrialDto.getExceptionOccurred()) {
                    if (!exceptionStored) {
                        userTrialDto.setExceptionOccurred(true);
                        userTrialDto.setExceptionMessage(processOutputLine);
                        exceptionStored = true;
                    }
                }
                if (processOutputLine.equals(FDL_EXCEPTION)) userTrialDto.setExceptionOccurred(true);
                System.out.println("FDL Process Output: " + processOutputLine);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) throw new EntityNotFoundException("Invalid FDL processor image input, Try again!");
            log.info("FDL Process Output: Exit Code: " + exitCode);
        } catch (Exception exception) {
            throw new EntityNotFoundException("Invalid FDL processor image input, Try again!");
        }
    }

    private ProcessBuilder buildFDLProcessBuilder(List<String> properties) {
        log.info("FDLProcessor: buildFDLProcessBuilder() called");
        return new ProcessBuilder("python3", System.getProperties().get("user.dir") + FDL_PATH, String.join(" ", properties));
    }

    private List<String> generateFDLImageProperties(String inputImageName, Gender gender) {
        log.info("FDLProcessor: generateFDLImageProperties() called");
        List<String> properties = new ArrayList<>();
        properties.add(inputImageName);
        properties.addAll(generateFDLParams());
        properties.add(gender.getValue().toString());
        return properties;
    }

    private List<String> generateFDLParams() {
        log.info("FDLProcessor: generateFDLParams() called");
        List<String> params = new ArrayList<>();
        for (int i = 0; i < 5; i++) params.add(String.format("%.2f", NumberUtil.positiveRandomNumberUnderOne()));
        return params;
    }

    private File extractImageFileFromFDL(String imageName) {
        log.info("FDLProcessor: extractImageFileFromFDL() called");
        return new File(OUTPUT_IMAGE_PATH + imageName);
    }

    private MultipartFile extractImageMultipartFileFromFDL(String imageName) {
        log.info("FDLProcessor: extractImageMultipartFileFromFDL() called");
        String imageFileName = OUTPUT_IMAGE_PATH + imageName;
        FileInputStream input;
        MockMultipartFile multipartFile;
        try {
            input = new FileInputStream(imageFileName);
            multipartFile = new MockMultipartFile("fileItem", imageFileName, "image/jpg", IOUtils.toByteArray(input));
        } catch (IOException exception) {
            throw new EntityNotFoundException("Invalid FDL processor image input, Try again!");
        }
        return multipartFile;
    }

    private File writeImageToFDL(byte[] imageBytes, String imageName) {
        log.info("FDLProcessor: writeImageToFDL() called");
        return writeImageFile(imageBytes, imageName);
    }

    private File writeImageFile(byte[] imageBytes, String imageName) {
        log.info("FDLProcessor: writeImageFile() called");
        File imageFile = new File(INPUT_IMAGE_PATH + imageName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(imageBytes);
            fileOutputStream.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return imageFile;
    }
}
