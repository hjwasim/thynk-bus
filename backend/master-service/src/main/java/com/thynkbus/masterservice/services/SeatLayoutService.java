package com.thynkbus.masterservice.services;

import com.thynkbus.masterservice.dtos.layout.SeatLayoutCreateRequest;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutCreatedResponse;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutResponse;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutUpdateRequest;
import com.thynkbus.masterservice.entities.SeatLayout;
import com.thynkbus.masterservice.repositories.SeatLayoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatLayoutService {

    protected final SeatLayoutRepository seatLayoutRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SeatLayoutService(SeatLayoutRepository seatLayoutRepository, ModelMapper modelMapper) {
        this.seatLayoutRepository = seatLayoutRepository;
        this.modelMapper = modelMapper;
    }

    public SeatLayoutCreatedResponse addSeatLayout(SeatLayoutCreateRequest createRequest) {
        // TODO - VALIDATION
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder
                .append(createRequest.getRowMax())
                .append("R")
                .append(createRequest.getColMax())
                .append("C")
                .append("_LAYOUT");
        String name = nameBuilder.toString();

        SeatLayout layout = modelMapper.map(createRequest, SeatLayout.class);
        layout.setName(name);
        seatLayoutRepository.save(layout);

        SeatLayoutCreatedResponse resp = new SeatLayoutCreatedResponse();
        resp.setName(name);
        resp.setId(layout.getId());
        return resp;
    }

    public boolean deleteLayoutById(String id) {
        Optional<SeatLayout> layout = seatLayoutRepository.findById(id);
        if (layout.isEmpty()) {
            return false;
        }
        seatLayoutRepository.delete(layout.get());
        return true;
    }

    public SeatLayoutCreatedResponse updateSeatLayout(String id, SeatLayoutUpdateRequest updateRequest) {
        Optional<SeatLayout> seatLayout = seatLayoutRepository.findById(id);
        if (seatLayout.isEmpty()) {
            throw new RuntimeException("Station not found with id " + id);
        }
        modelMapper.map(updateRequest, seatLayout.get());
        SeatLayout updatedLayout = seatLayoutRepository.save(seatLayout.get());
        SeatLayoutCreatedResponse resp = new SeatLayoutCreatedResponse();
        resp.setId(updatedLayout.getId());
        resp.setName(updatedLayout.getName());
        return resp;
    }

    public SeatLayoutResponse findSeatLayoutById(String id) {
        Optional<SeatLayout> seatLayout = seatLayoutRepository.findById(id);
        if (seatLayout.isEmpty()) {
            throw new RuntimeException("Bus not found with id " + id);
        }
        return modelMapper.map(seatLayout.get(), SeatLayoutResponse.class);
    }
}
