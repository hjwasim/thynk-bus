package com.thynkbus.masterservice.services;

import com.thynkbus.masterservice.dtos.bus.BusCreateRequest;
import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.bus.BusUpdateRequest;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutResponse;
import com.thynkbus.masterservice.entities.Bus;
import com.thynkbus.masterservice.entities.SeatLayout;
import com.thynkbus.masterservice.repositories.BusRepository;
import com.thynkbus.masterservice.repositories.SeatLayoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BusService {

    protected final BusRepository busRepository;
    protected final ModelMapper modelMapper;
    private final SeatLayoutRepository seatLayoutRepository;

    @Autowired
    public BusService(BusRepository busRepository, ModelMapper modelMapper, SeatLayoutRepository seatLayoutRepository) {
        this.busRepository = busRepository;
        this.modelMapper = modelMapper;
        this.seatLayoutRepository = seatLayoutRepository;
    }

    public Collection<BusResponse> findAllBus() {
        Collection<Bus> buses = busRepository.findAll();
        return buses.stream().map(bus -> modelMapper.map(bus, BusResponse.class)).toList();
    }

    public BusResponse findBusById(String id) {
        Optional<Bus> station = busRepository.findById(id);
        if (station.isEmpty()) {
            throw new RuntimeException("Bus not found with id " + id);
        }
        return modelMapper.map(station.get(), BusResponse.class);
    }

    public BusResponse addBus(BusCreateRequest createRequest) {
        // can have duplicate buses
        // different people can have same bus spec and also provide same amenities
        Bus bus = modelMapper.map(createRequest, Bus.class);
        bus.setActive(true);
        bus = busRepository.save(bus);
        return modelMapper.map(bus, BusResponse.class);
    }

    public BusResponse updateBus(String id, BusUpdateRequest updateRequest) {
        Optional<Bus> station = busRepository.findById(id);
        if (station.isEmpty()) {
            throw new RuntimeException("Station not found with id " + id);
        }
        modelMapper.map(updateRequest, station.get());
        Bus updatedStation = busRepository.save(station.get());
        return modelMapper.map(updatedStation, BusResponse.class);
    }

    public boolean deleteBusById(String id) {
        Optional<Bus> station = busRepository.findById(id);
        if (station.isEmpty()) {
            return false;
        }
        busRepository.delete(station.get());
        return true;
    }

    public BusResponse assignSeatLayoutToBus(String busId, String layoutId) {
        Optional<Bus> busOptional = busRepository.findById(busId);
        if (busOptional.isEmpty()) {
            throw new RuntimeException("Bus not found with id " + busId);
        }
        Optional<SeatLayout> seatLayout = seatLayoutRepository.findById(layoutId);
        if (seatLayout.isEmpty()) {
            throw new RuntimeException("Seat layout not found with id " + layoutId);
        }
        busOptional.get().setSeatLayoutId(seatLayout.get().getId());
        // TODO validation on seats with total seats
        Bus bus = busRepository.save(busOptional.get());
        BusResponse response = modelMapper.map(bus, BusResponse.class);
        response.setSeatLayout(modelMapper.map(seatLayout.get(), SeatLayoutResponse.class));
        return response;
    }

    public BusResponse unAssignSeatLayoutToBus(String busId, String layoutId) {
        Optional<Bus> busOptional = busRepository.findById(busId);
        if (busOptional.isEmpty()) {
            throw new RuntimeException("Bus not found with id " + busId);
        }
        busOptional.get().setSeatLayoutId(null);
        Bus bus = busRepository.save(busOptional.get());
        return modelMapper.map(bus, BusResponse.class);
    }
}
