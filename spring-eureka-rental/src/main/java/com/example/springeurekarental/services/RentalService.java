package com.example.springeurekarental.services;

import com.example.springeurekarental.dtos.RentalDTO;
import com.example.springeurekarental.entities.Rental;
import com.example.springeurekarental.feign.clients.BookClient;
import com.example.springeurekarental.feign.clients.PaymentClient;
import com.example.springeurekarental.repositories.RentalRepository;
import jakarta.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

@Service
public class RentalService{ 
	
  private final RentalRepository _rentalRepository;
  private final BookClient _bookClient;
//  private final PaymentClient _paymentClient;
  
  public RentalService(RentalRepository rentalRepository,
		               BookClient bookClient,
		               PaymentClient paymentClient){
    this._bookClient=bookClient;
    this._rentalRepository=rentalRepository;
//    this._paymentClient=paymentClient;
  }
  
  public List<RentalDTO> getAllRentals(){
    Iterator<Rental> iterator=this._rentalRepository.findAll().iterator();
    List<RentalDTO> dtos=StreamUtils.createStreamFromIterator(iterator)
                                    .map(rental-> new RentalDTO(rental.getId(),
                                    		                    rental.getPickUpDate(),
                                    		                    rental.getDropOffDate(),
                                    		                    rental.getUserId(),
                                    		                    rental.getBookId()
                                     )).collect(Collectors.toList());
    return dtos;
  }
  
  @Transactional
  public RentalDTO addRental(RentalDTO dto){
    Rental rental=new Rental(null,
                             null,
                             null,
                             dto.userId,
                             dto.bookId);
    rental=this._rentalRepository.save(rental);  
    
    this._bookClient.changeBookStatus(dto.bookId);
//    this._paymentClient.doPayment();
    
    dto.id=rental.getId();
    dto.pickUpDate=rental.getPickUpDate();
    dto.dropOffDate=rental.getDropOffDate();
    return dto;
  } 
  
//  public RentalDTO updateRental(RentalDTO dto){
//	Rental rental=this._rentalRepository.findById(dto.id).orElse(null);
//	rental.setBookId(dto.id);
//	rental.setUserId(dto.userId);
//    this._rentalRepository.save(rental); 
//    
//    dto.dropOffDate=rental.getDropOffDate();
//    dto.pickUpDate=rental.getPickUpDate();
//    return dto;
//  } 
  
  public RentalDTO deleteRental(int id){
    Rental rental=this._rentalRepository.findById(id).orElse(null);
    RentalDTO dto=new RentalDTO(rental.getId(),
    		                    rental.getPickUpDate(),
    		                    rental.getDropOffDate(),
    		                    rental.getUserId(),
    		                    rental.getBookId());
    this._rentalRepository.deleteById(id); 
    return dto;
  }
} 
  
