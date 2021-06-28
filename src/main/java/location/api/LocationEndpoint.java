package location.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
public class LocationEndpoint {
    private final LocationService locationService;

    public LocationEndpoint(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    Location post(@RequestBody Location input) {
        return locationService.post(input);
    }

    @GetMapping("/{x1}/{y1}/{x2}/{y2}")
    List<Location> getInArea(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2) {
        return locationService.findInArea(x1, y1, x2, y2);
    }

    @GetMapping("/{type}")
    List<Location> getByType(@PathVariable LocationType type) {
        return locationService.findByType(type);
    }

    @GetMapping("/{x1}/{y1}/{x2}/{y2}/{type}")
    List<Location> getByTypeInArea(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2, @PathVariable LocationType type) {
        return locationService.findTypeInArea(x1, y1, x2, y2, type);
    }

    @GetMapping("/{x1}/{y1}/{x2}/{y2}/limit/{limit}")
    List<Location> getInAreaLimit(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2, @PathVariable int limit) {
        return locationService.findInArea(x1, y1, x2, y2)
                .stream().limit(limit).collect(Collectors.toList());
    }

    @GetMapping("/{type}/limit/{limit}")
    List<Location> getByTypeLimit(@PathVariable LocationType type, @PathVariable int limit) {
        return locationService.findByType(type)
                .stream().limit(limit).collect(Collectors.toList());
    }

    @GetMapping("/{x1}/{y1}/{x2}/{y2}/{type}/limit/{limit}")
    List<Location> getByTypeInAreaLimit(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2, @PathVariable LocationType type, @PathVariable int limit) {
        return locationService.findTypeInArea(x1, y1, x2, y2, type)
                .stream().limit(limit).collect(Collectors.toList());
    }
}
