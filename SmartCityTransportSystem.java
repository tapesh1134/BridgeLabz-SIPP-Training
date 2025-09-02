
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

interface EmergencyService {
}

@FunctionalInterface
interface FareCalculator {

    double calculateFare(double distance, boolean isPeakHour);
}

interface TransportService {

    void scheduleService(String route, LocalDateTime time);

    double getFare(String route);

    default void printServiceDetails() {
        System.out.println("Service Type: " + getClass().getSimpleName());
    }

    static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }
}

class BusService implements TransportService {

    private Map<String, LocalDateTime> schedules = new HashMap<>();
    private Map<String, Double> fares = new HashMap<>();

    @Override
    public void scheduleService(String route, LocalDateTime time) {
        schedules.put(route, time);
    }

    @Override
    public double getFare(String route) {
        return fares.getOrDefault(route, 0.0);
    }
}

class MetroService implements TransportService {

    private Map<String, LocalDateTime> schedules = new HashMap<>();
    private Map<String, Double> fares = new HashMap<>();

    @Override
    public void scheduleService(String route, LocalDateTime time) {
        schedules.put(route, time);
    }

    @Override
    public double getFare(String route) {
        return fares.getOrDefault(route, 0.0);
    }
}

class TaxiService implements TransportService {

    private Map<String, LocalDateTime> schedules = new HashMap<>();
    private Map<String, Double> fares = new HashMap<>();

    @Override
    public void scheduleService(String route, LocalDateTime time) {
        schedules.put(route, time);
    }

    @Override
    public double getFare(String route) {
        return fares.getOrDefault(route, 0.0);
    }
}

class Trip {

    private String route;
    private LocalDateTime time;
    private double fare;
    private boolean isPeakHour;

    public Trip(String route, LocalDateTime time, double fare, boolean isPeakHour) {
        this.route = route;
        this.time = time;
        this.fare = fare;
        this.isPeakHour = isPeakHour;
    }

    public String getRoute() {
        return route;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getFare() {
        return fare;
    }

    public boolean isPeakHour() {
        return isPeakHour;
    }
}

public class SmartCityTransportSystem {

    private List<TransportService> services = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    public void addService(TransportService service) {
        services.add(service);
    }

    public void displayServiceSchedules() {
        services.forEach(TransportService::printServiceDetails);
    }

    public void recordTrip(Trip trip) {
        trips.add(trip);
    }

    public Map<String, Double> getRouteRevenue() {
        return trips.stream()
                .collect(Collectors.groupingBy(
                        Trip::getRoute,
                        Collectors.summingDouble(Trip::getFare)
                ));
    }

    public Map<Boolean, List<Trip>> getPeakHourTrips() {
        return trips.stream()
                .collect(Collectors.partitioningBy(Trip::isPeakHour));
    }

    public DoubleSummaryStatistics getFareStatistics() {
        return trips.stream()
                .collect(Collectors.summarizingDouble(Trip::getFare));
    }

    public static void main(String[] args) {
        SmartCityTransportSystem system = new SmartCityTransportSystem();

        system.addService(new BusService());
        system.addService(new MetroService());
        system.addService(new TaxiService());
        FareCalculator fareCalc = (distance, isPeakHour)
                -> distance * (isPeakHour ? 1.5 : 1.0);

        LocalDateTime now = LocalDateTime.now();
        system.recordTrip(new Trip("Route1", now, fareCalc.calculateFare(10, true), true));
        system.recordTrip(new Trip("Route2", now.plusHours(1), fareCalc.calculateFare(15, false), false));

        system.displayServiceSchedules();
        System.out.println("Revenue by route: " + system.getRouteRevenue());
        System.out.println("Fare statistics: " + system.getFareStatistics());
    }
}
