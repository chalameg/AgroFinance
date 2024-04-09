package com.Dx_Valley.AgroFinance.ServiceImp;
import com.Dx_Valley.AgroFinance.DTO.ResponseMessage;
import com.Dx_Valley.AgroFinance.Models.OtherAsset;
import com.Dx_Valley.AgroFinance.Repository.OtherRepository;
import com.Dx_Valley.AgroFinance.Service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OtherServiceImp implements OtherService {
    @Autowired
    private OtherRepository otherRepository;
    @Override
    public ResponseEntity<?> registerOthers(OtherAsset otherAsset) {
        OtherAsset others = otherRepository.findByName(otherAsset.getName());
        ResponseMessage responseMessage;
        if (others == null) {
            otherRepository.save(others);
            responseMessage = new ResponseMessage("success", "Others created successfully");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage("fail", "Others already exist");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public OtherAsset editOthers(OtherAsset others) {
        return this.otherRepository.save(others);
    }

    @Override
    public List<OtherAsset> fetchOthers() {
        List<OtherAsset> others = new ArrayList<>();
        others.addAll(otherRepository.findAll());
        return others;
    }
}
