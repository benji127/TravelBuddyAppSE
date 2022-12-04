package com.example.travelbuddyapp.utility;

import com.example.travelbuddyapp.entities.Confirmation;
import com.example.travelbuddyapp.repositories.ConfirmationRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public class CommonMethods {

    public static void createConfirmation(ConfirmationRepository repo, MultipartFile[] file, Integer userId, Integer tripId,
                                          int docType) {
        try {
            for(MultipartFile f: file) {
                Confirmation confirmation = new Confirmation();
                if(userId != null){
                    confirmation.setUserId(userId);
                }
                if (tripId != null) {
                    confirmation.setTripId(tripId);
                }
                confirmation.setName(f.getOriginalFilename());
                confirmation.setContent(f.getBytes());
                confirmation.setConfirmationType(docType);
                confirmation.setCreatedBy(userId);
                confirmation.setCreatedOn(new Date());
                confirmation.setLastUpdatedBy(userId);
                confirmation.setLastUpdatedOn(new Date());
                repo.save(confirmation);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
