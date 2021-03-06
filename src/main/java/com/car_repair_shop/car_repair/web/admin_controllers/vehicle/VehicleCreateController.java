package com.car_repair_shop.car_repair.web.admin_controllers.vehicle;

import com.car_repair_shop.car_repair.converters.VehicleConverter;
import com.car_repair_shop.car_repair.domain.Member;
import com.car_repair_shop.car_repair.domain.Vehicle;
import com.car_repair_shop.car_repair.forms.vehicle.VehicleForm;
import com.car_repair_shop.car_repair.services.member.MemberService;
import com.car_repair_shop.car_repair.services.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.car_repair_shop.car_repair.properties.Constants.ERROR_MESSAGE;

@Controller
public class VehicleCreateController {

    private static final String VEHICLE_FORM = "vehicleForm";

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "admin/create-vehicle", method = RequestMethod.GET)
    public String getCreateVehicleView(Model model, RedirectAttributes redirectAttributes) {

        if (!model.containsAttribute(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
        }

        return "/admin/vehicle/create-vehicle-view";
    }

    @RequestMapping(value = "/admin/create-vehicle", method = RequestMethod.POST)
    public String createVehicle(@Valid @ModelAttribute(name = VEHICLE_FORM) VehicleForm vehicleForm,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vehicleForm", bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);

            return "redirect:/admin/create-vehicle";
        }

        String memberVat = vehicleForm.getVat();
        Vehicle vehicle;

        try {
            vehicle = VehicleConverter.buildVehicleObjecr(vehicleForm);
            Member member = memberService.getMemberByVat(memberVat);
            vehicle.setMember(member);
            vehicle = vehicleService.insertVehicle(vehicle);

        } catch (Exception e) {
            String message = "There is not user with VAT: " + memberVat + " or vehicle already exists";

            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, message);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);

            return "redirect:/admin/create-vehicle";
        }

        String message = "vehicle with plate: " + vehicle.getPlate().toUpperCase() +
                         " is created for user with VAT: " + memberVat;

        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/admin/create-vehicle";
    }

}
