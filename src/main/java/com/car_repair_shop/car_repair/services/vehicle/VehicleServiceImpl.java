package com.car_repair_shop.car_repair.services.vehicle;

import com.car_repair_shop.car_repair.domain.Member;
import com.car_repair_shop.car_repair.domain.Vehicle;
import com.car_repair_shop.car_repair.exceptions.VehicleNotFoundException;
import com.car_repair_shop.car_repair.repositories.MemberRepository;
import com.car_repair_shop.car_repair.repositories.VehicleRepository;
import com.car_repair_shop.car_repair.services.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public Vehicle insertVehicle(Vehicle vehicle) throws Exception {
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        if (newVehicle == null) {
            throw new Exception("vehicle already exists");
        }

        return newVehicle;
    }

    @Override
    public Vehicle getByPlate(String plate) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findByPlate(plate);

        if (vehicle == null) {
            throw new VehicleNotFoundException("vehicle with " + plate + " not found");
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> getVehiclesByMemberVat(String vat) throws VehicleNotFoundException {
        Member member = memberRepository.findByVat(vat);

        if (member == null) {
            throw new VehicleNotFoundException("member not found with vat :" + vat);
        }

        List<Vehicle> vehicleList = member.getVehicles();

        if (vehicleList.isEmpty()) {
            throw new VehicleNotFoundException("member with vat :" + vat + " hasn't vehicles! " );
        }

        return vehicleList;
    }

    @Override
    public List<Vehicle> getVehiclesByMemberPlate(String plate) throws VehicleNotFoundException{
        Vehicle vehicle = vehicleRepository.findByPlate(plate);

        if (vehicle == null ) {
            throw new VehicleNotFoundException("vehicle not exist with palte :" + plate);
        }

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);

        return vehicles;
    }

    @Override
    public void deleteVehicle(Long id) { vehicleRepository.delete(id); }
}
