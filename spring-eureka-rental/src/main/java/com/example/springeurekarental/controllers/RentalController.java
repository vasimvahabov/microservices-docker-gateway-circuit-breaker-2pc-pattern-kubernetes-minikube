package com.example.springeurekarental.controllers;

import com.example.springeurekarental.dtos.RentalDTO;
import com.example.springeurekarental.models.RentalModel;
import com.example.springeurekarental.services.RentalService;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental")
public class RentalController {
  
  @Autowired
  private RentalService _rentalService;
  
  @GetMapping("/list/all")
  public ResponseEntity<List<RentalModel>> getAllRentals(){
      Iterator<RentalDTO> iterator=this._rentalService.getAllRentals().iterator();
      List<RentalModel> models=StreamUtils.createStreamFromIterator(iterator)
                                          .map(dto-> RentalModel.builder()
                                        		                   .id(dto.id)
                                        		                   .pickUpDate(dto.pickUpDate)
                                        		                   .dropOffDate(dto.dropOffDate)
                                        		                   .userId(dto.userId)
                                        		                   .bookId(dto.bookId)
                                        		                .build()
                                          ).collect(Collectors.toList());
     return ResponseEntity.ok(models);
  }
  
  @PostMapping("/add")
  public ResponseEntity<RentalModel> addRental(@RequestBody @JsonProperty RentalModel model){
     RentalDTO dto=new RentalDTO(null,null,null,model.userId,model.bookId);
     dto=this._rentalService.addRental(dto);
     model.id=dto.id;
     model.pickUpDate=dto.pickUpDate;
     model.dropOffDate=dto.dropOffDate;
     return ResponseEntity.ok(model);
  }
  
//  @PutMapping("/update")
//  public ResponseEntity<RentalModel> updateRental(@RequestBody RentalModel model){
//    RentalDTO dto=new RentalDTO(model.id,null,null,model.userId,model.bookId);
//    dto=this._rentalService.updateRental(dto);
//    model.pickUpDate=dto.pickUpDate;
//    model.dropOffDate=dto.dropOffDate;
//    return ResponseEntity.ok(model);
//  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<RentalModel> deleteRental(@PathVariable int id){ 
    RentalDTO dto=this._rentalService.deleteRental(id);
    RentalModel model=RentalModel.builder()
                                     .id(dto.id)    
                                     .pickUpDate(dto.pickUpDate)
                                     .dropOffDate(dto.dropOffDate)
                                     .userId(dto.userId)
                                     .bookId(dto.bookId)
                                 .build();
    return ResponseEntity.ok(model);
  }
}