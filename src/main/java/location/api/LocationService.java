package location.api;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    Location post(Location input) {
        return locationRepository.save(input);
    }

    List<Location> findInArea(double x1, double y1, double x2, double y2) {
        return locationRepository.findAll().stream()
                .filter(location -> isInArea(x1, y1, x2, y2, location))
                .sorted(Comparator.comparing(Location::getType))
                .collect(Collectors.toList());
    }

    List<Location> findByType(LocationType type) {
        return locationRepository.findByType(type);
    }

    List<Location> findTypeInArea(double x1, double y1, double x2, double y2, LocationType type) {
        return locationRepository.findByType(type).stream()
                .filter(location -> isInArea(x1, y1, x2, y2, location))
                .collect(Collectors.toList());
    }

    private boolean isInArea(double x1, double y1, double x2, double y2, Location location) {
        return location.getX() >= Math.min(x1, x2)
                && location.getX() <= Math.max(x1, x2)
                && location.getY() >= Math.min(y1, y2)
                && location.getY() <= Math.max(y1, y2);
    }
}
