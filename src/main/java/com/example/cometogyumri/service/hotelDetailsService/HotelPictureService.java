package com.example.cometogyumri.service.hotelDetailsService;

import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelPicture;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HotelPictureService {

    private final HotelPictureRepository hotelPictureRepository;

    @Value("${hotelImagePath.upload.path}")
    private String hotelImagePath;

    public void saveHotelPictures(MultipartFile[] uploadedFiles, Hotel hotel) throws IOException {
        for (MultipartFile uploadedFile : uploadedFiles) {
            if (!uploadedFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
                File newFile = new File(hotelImagePath + fileName);
                uploadedFile.transferTo(newFile);
                HotelPicture hotelPicture = HotelPicture.builder()
                        .hotel(hotel)
                        .picUrl(fileName)
                        .build();
                hotelPictureRepository.save(hotelPicture);
            }
        }
    }
}
