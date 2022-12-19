package com.globale.book.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;
import com.globale.book.entity.Auther;
import com.globale.book.error.FileStorageException;


@Service
public class FileUploadService {

//
////	@Value("${file.upload.base-path}")
//	//private final String basePath = "D:\\Global\\book\\";
//
////	@Value("${google.storage.bucket-name}")
//	private String googleBucketName = "";
//
////	@Value("${google.storage.project-id}")
//	private String projectId = "";
//
////	@Value("${google.storage.credentials.path}")
//	private String credentialPath = "";
//
////	endpointUrl: https://s3.us-east-2.amazonaws.com
//
////	@Value("${aws.s3.bucket}")
//	private String awsBucketName;
//
//	@Autowired
//	private  AmazonS3 amazonS3;

   
	
	
	private Path fileStorageLocation;
	@Autowired
	private AutherService autherService;
	

//	@Value("${file.upload.base-path}")
	private  String basePath = "C:\\Users\\Admin\\Desktop\\New Folder\\book\\";

	Logger log = LoggerFactory.getLogger(FileUploadService.class);

	public String storeFile(File file, Long id, String pathType) {

		// create uploaded path
		this.fileStorageLocation = Paths.get(basePath + pathType).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}

		String fileName = StringUtils.cleanPath(id + "-" + file.getName());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			InputStream targetStream = new FileInputStream(file);
			Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

			updateImagePath(id, pathType, pathType + "/" + fileName);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	/**
	 * 
	 * @param multipartFile
	 * @return
	 */
	public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			log.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

	/**
	 * 
	 * @param id
	 * @param pathType
	 * @param imagePath
	 */
	private void updateImagePath(Long id, String pathType, String imagePath) {

		if (pathType.contains("authors")) {
			// update author image path
			Auther auther = autherService.findById(id);
			auther.setImagePath(imagePath);
			autherService.update(auther);

		}

	
	}
//	public String cloudUploadFile(MultipartFile file, Long id, String pathType) {
//
//		String fileName = null;
//
//		if (file.getContentType().contains("image")) {
//			fileName = id + "_" + UUID.randomUUID() + ".jpg";
//		} else {
//			fileName = id + file.getOriginalFilename();
//		}
//		String uniqueFileName = pathType + fileName;
//		try {
//
//			awsUploadObject(uniqueFileName, file);
//
//			updateImagePath(id, pathType, pathType + "/" + fileName);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FileStorageException("Error converting the multi-part file to file= ", e);
//		}
//
//		return fileName;
//	}
//
//	/**
//	 * 
//	 * @param uniqueFileName
//	 * @param multipartFile
//	 */
//	public void awsUploadObject(final String uniqueFileName, final MultipartFile multipartFile) {
//
//		log.info("Uploading file with name= " + uniqueFileName);
//
//		try {
//
//			ObjectMetadata meta = new ObjectMetadata();
//			meta.setContentLength(IOUtils.toByteArray(multipartFile.getInputStream()).length);
//
//			final PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketName, uniqueFileName,
//					multipartFile.getInputStream(), meta).withCannedAcl(CannedAccessControlList.PublicRead);
//
//			PutObjectResult result = amazonS3.putObject(putObjectRequest);
//			log.info("File uploaded successfully result" + result.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void awsDeleteObject(String fileUrl) {
//		final DeleteObjectRequest req = new DeleteObjectRequest(awsBucketName, fileUrl);
//		amazonS3.deleteObject(req);
//		log.info("File deleted from bucket " + awsBucketName + " as " + fileUrl);
//	}

}
