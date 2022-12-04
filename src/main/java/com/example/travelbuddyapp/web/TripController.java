package com.example.travelbuddyapp.web;

        import com.example.travelbuddyapp.entities.*;
        import com.example.travelbuddyapp.repositories.ConfirmationRepository;
        import com.example.travelbuddyapp.repositories.TripRepository;
        import com.example.travelbuddyapp.services.TripServiceImpl;
        import com.example.travelbuddyapp.utility.CommonMethods;
        import lombok.AllArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.ui.ModelMap;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;

        import javax.servlet.http.HttpSession;
        import javax.validation.Valid;
        import java.util.List;

@Controller
@AllArgsConstructor
public class TripController {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ConfirmationRepository confirmationRepository;

    @GetMapping(path = "/index")
    public String trips(Model model) {

        List<Trip> trip;
        trip = tripRepository.findAll();

        model.addAttribute("listTrips", trip);
        return "index";
    }
    @GetMapping(path = "/byLocation")
    public String location(Model model, @RequestParam(name = "location", defaultValue = "")
    String keyword){
        List<Trip> trip;

        if (keyword.isEmpty()) {
            trip = tripRepository.findAll();
        } else {
            trip = tripRepository.findTripByLocation(keyword);
        }

        model.addAttribute("location",trip);
        return "byLocation";
    }
    @GetMapping(path = "/byHobbies")
    public String byHobbies(Model model, @RequestParam(name = "hobbies", defaultValue = "")
    String keyword){
        List<Trip> trips;

        if (keyword.isEmpty()) {
            trips = tripRepository.findAll();
        } else {
            trips = tripRepository.findByHobbies(keyword);
        }
        model.addAttribute("hobbies",trips);
        return "byHobbies";
    }

    @GetMapping(path = "/byLanguage")
    public String byLanguage(Model model, @RequestParam(name = "language", defaultValue = "")
    String keyword){
        List<Trip> trips;

        if (keyword.isEmpty()) {
            trips = tripRepository.findAll();
        } else {
            trips = tripRepository.findByLanguge(keyword);
        }
        model.addAttribute("language",trips);
        return "byLanguage";
    }
    @GetMapping(path = "/details")
    public String details(Model model){

        return "details";
    }

    @GetMapping(path = "/listTrip")
    public String listTrip(Model model){

        return "listTrip";
    }

    @GetMapping("/deleteTrip")
    public String deleteTrip(Long id){
        tripRepository.deleteById(id);

        return "redirect:/index";
    }

    @GetMapping(path = "/search")
    public String search(Model model, @RequestParam(name = "keyword", defaultValue = "")
    String keyword) {

        List<Trip> trips;
        if (keyword.isEmpty()) {
            trips = tripRepository.findAll();
            return "generalSearch";
        } else {
            String key = keyword;
            trips = tripRepository.findByModelContaining(key);
        }
        model.addAttribute("list_trips", trips);
        return "generalSearch";
    }

    @PostMapping(path = "/addTrip")
    public String addTrip(Model model, @Valid Trip trip, Integer id, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        id = 7;
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "redirect:listTrip";
        } else {
            Trip t  = TripServiceImpl.addTrip(trip, tripRepository, id);
            if(t != null && trip.getPics() != null){
                CommonMethods.createConfirmation(confirmationRepository, trip.getPics(), id, t.getId(),
                        ConfirmationType.USER_PHOTO.getId());
            }
            if(t != null && trip.getDocs() != null){
                CommonMethods.createConfirmation(confirmationRepository, trip.getDocs(), id, t.getId(),
                        ConfirmationType.INSURANCE_NUMBER.getId());
            }
            return "redirect:LoggedIn";
        }

    }

}

