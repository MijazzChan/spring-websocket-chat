package com.zstu.mijazz.controller;

import com.zstu.mijazz.model.ResponseModel;
import com.zstu.mijazz.model.UploadFileResponseModel;
import com.zstu.mijazz.storage.FileStorage;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 14-Jun-20.
 */
@Api(description = "文件接口")
@RestController
@CrossOrigin
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorage fileStorage;

    @ApiOperation(value = "文件上传", notes = "表单形式post文件, 最大100MB, 再开发可修改", produces = "application/json")
    @ApiImplicitParam(name = "file", required = true)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功且+文件UUID后下载位置"),
            @ApiResponse(code = 0, message = "文件有非法或服务器储存权限出错")
    })
    @PostMapping(value = "/uploadfile", produces = "application/json")
    public ResponseModel<UploadFileResponseModel> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorage.storeFile(file);
        if (StringUtils.isEmpty(fileName)) {
            // Include.NONNULL
            return new ResponseModel<>(0, null);
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadfile/")
                .path(fileName)
                .toUriString();

        return new ResponseModel<>(1, new UploadFileResponseModel(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()));
    }

    @ApiOperation(value = "多文件上传", notes = "表单形式加多文件", produces = "application/json")
    @ApiResponse(code = 1, message = "包含所有文件的文件信息, Iterable转json形式, 不成功的文件会给code0")
    @PostMapping(value = "/uploadmultiplefiles", produces = "application/json")
    public Iterable<ResponseModel<UploadFileResponseModel>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

        return Arrays.asList(files)
                .stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "回取文件", notes = "拿upload的fileDownloadUri回取文件, 传入pathVariable")
    @GetMapping("/downloadfile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorage.loadFileAsResource(fileName);

        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
