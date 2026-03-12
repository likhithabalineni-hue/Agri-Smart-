import java.util.*;

/**
 * AGRISMART: Integrated Data Structures Project
 * Aligned with CO1 - CO6
 */
public class AgriSmartProject {

    // Global Collections for Data Management (CO4)
    private static List<FarmerProfile> savedProfiles = new ArrayList<>();
    private static Map<String, Double> marketRates = new HashMap<>();

    // ==========================================
    // CO2: ABSTRACT DATA TYPES (ADTs)
    // ==========================================
    static class FarmerProfile {
        String name;
        String soilType;
        double landSize;
        String timestamp;

        public FarmerProfile(String name, String soilType, double landSize) {
            this.name = name;
            this.soilType = soilType;
            this.landSize = landSize;
            this.timestamp = new java.text.SimpleDateFormat("HH:mm:ss").format(new Date());
        }

        @Override
        public String toString() {
            return String.format("[%s] Name: %s | Soil: %s | Land: %.1f Acres", timestamp, name, soilType, landSize);
        }
    }

    static class Crop {
        String name;
        double profit;

        public Crop(String name, double profit) {
            this.name = name;
            this.profit = profit;
        }
    }

    // ==========================================
    // CO1: SEARCHING & SORTING ALGORITHMS
    // ==========================================
    static class AlgorithmEngine {
        // QuickSort for efficiency: O(n log n) - CO1
        public static void quickSort(List<Crop> crops, int low, int high) {
            if (low < high) {
                int pi = partition(crops, low, high);
                quickSort(crops, low, pi - 1);
                quickSort(crops, pi + 1, high);
            }
        }

        private static int partition(List<Crop> crops, int low, int high) {
            double pivot = crops.get(high).profit;
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (crops.get(j).profit > pivot) { // Descending order
                    i++;
                    Collections.swap(crops, i, j);
                }
            }
            Collections.swap(crops, i + 1, high);
            return i + 1;
        }
    }

    // ==========================================
    // CORE PAGE NAVIGATION LOGIC
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeData();

        while (true) {
            System.out.println("\n--- AGRISMART NAVIGATION ---");
            System.out.println("1. 🏠 HOME (Registration)");
            System.out.println("2. 💾 SAVED (Profiles Database)");
            System.out.println("3. 🌦️ ADVISORY (Priority Alerts)");
            System.out.println("4. 💰 MARKET (Fast Lookup)");
            System.out.println("5. ❌ EXIT");
            System.out.print("Select Page: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: showHomePage(sc); break;
                case 2: showSavedPage(); break;
                case 3: showAdvisoryPage(); break;
                case 4: showMarketPage(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid selection.");
            }
        }
    }

    // --- HOME PAGE (CO2/CO5) ---
    private static void showHomePage(Scanner sc) {
        System.out.println("\n[HOME] Farmer Registration");
        System.out.print("Enter Name: "); String name = sc.nextLine();
        System.out.print("Soil Type: "); String soil = sc.nextLine();
        System.out.print("Land Size (Acres): "); double size = sc.nextDouble();

        FarmerProfile profile = new FarmerProfile(name, soil, size);
        savedProfiles.add(profile);
        System.out.println("✅ Profile Saved to DS List!");
    }

    // --- SAVED PAGE (CO2/CO6) ---
    private static void showSavedPage() {
        System.out.println("\n[SAVED] Stored Profiles (Linked List Traversal)");
        if (savedProfiles.isEmpty()) {
            System.out.println("No records found.");
        } else {
            // CO2: Traversing the list
            savedProfiles.forEach(System.out::println);
        }
    }

    // --- ADVISORY PAGE (CO3: Priority Queues) ---
    private static void showAdvisoryPage() {
        // CO3: Implement Priority Queue (Heap) for urgent alerts
        PriorityQueue<String> alerts = new PriorityQueue<>();
        alerts.add("1-CRITICAL: Cyclone Warning in coastal regions.");
        alerts.add("2-HIGH: Pest attack reported in neighboring farms.");
        alerts.add("3-LOW: Optimal time for fertilizer application.");

        System.out.println("\n[ADVISORY] Prioritized Weather/Crop Alerts:");
                while (!alerts.isEmpty()) {
            System.out.println("⚠️ " + alerts.poll());
        }
    }

    // --- MARKET PAGE (CO1 & CO4) ---
    private static void showMarketPage() {
        System.out.println("\n[MARKET] Live Prices (O(1) Hash Lookup)");
        // CO4: Fast lookup using HashMap
        marketRates.forEach((crop, price) -> System.out.println(crop + ": ₹" + price + "/q"));

        // CO1: Sorting crops by profit for recommendation
        List<Crop> list = new ArrayList<>();
        list.add(new Crop("Cotton", 6500));
        list.add(new Crop("Rice", 2200));
        list.add(new Crop("Turmeric", 11000));

        AlgorithmEngine.quickSort(list, 0, list.size() - 1);
        System.out.println("\nRecommended Crops (Sorted by Profitability):");
                for(Crop c : list) System.out.println(c.name + " - ₹" + c.profit);
    }

    private static void initializeData() {
        marketRates.put("Rice", 2180.0);
        marketRates.put("Cotton", 7020.0);
        marketRates.put("Turmeric", 12500.0);
    }
}
