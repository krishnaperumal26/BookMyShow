package com.myproject.bookmyshow.service;

import com.myproject.bookmyshow.models.Show;
import com.myproject.bookmyshow.models.ShowSeat;
import com.myproject.bookmyshow.models.ShowSeatType;
import com.myproject.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {

    ShowSeatTypeRepository showSeatTypeRepository;
    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats)
    {

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        int amount = 0;
        for(ShowSeat showSeat : showSeats)
        {
            for(ShowSeatType showSeatType : showSeatTypes)
            {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType()))
                {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }



}
