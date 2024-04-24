package com.company.tour.app;

import com.company.tour.entity.City;
import org.locationtech.jts.geom.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TourExplorerUtils {

    private static final Map<String, String> districtFillColors = new HashMap<>();
    private static final Map<String, String> districtStrokeColors = new HashMap<>();

    public static final String BIG_GARDEN_TOUR = "Big Garden Tour";
    public static final List<Coordinate> BIG_GARDEN_TOUR_COORDINATES = List.of(
            new Coordinate(4549524.651984713, 3273414.42731149),
            new Coordinate(4549662.785637062, 3273289.2675380902),
            new Coordinate(4549803.208797462, 3273181.66065974),
            new Coordinate(4549720.023338312, 3273065.658918541),
            new Coordinate(4549723.460490986, 3272890.14153043),
            new Coordinate(4549613.564104586, 3272678.743620481),
            new Coordinate(4549332.681442388, 3272639.6039352445),
            new Coordinate(4548944.991412589, 3272583.8925726945),
            new Coordinate(4548608.773937264, 3272533.523395594),
            new Coordinate(4548272.979423266, 3272478.5752023943),
            new Coordinate(4548376.007285512, 3272272.519477894),
            new Coordinate(4548437.060833509, 3271974.2998313042),
            new Coordinate(4548491.245857359, 3271677.4269541553),
            new Coordinate(4548487.4300106075, 3271479.766092505),
            new Coordinate(4548610.3002759535, 3271146.261086555),
            new Coordinate(4548662.958961103, 3270911.204926756),
            new Coordinate(4548305.032535954, 3270951.652902306),
            new Coordinate(4548057.002497201, 3271025.6803292558),
            new Coordinate(4547841.788740502, 3271100.4709255556),
            new Coordinate(4547518.204936101, 3271287.447416305),
            new Coordinate(4547134.330753053, 3271356.8958271546),
            new Coordinate(4547377.01860635, 3271421.7652219054),
            new Coordinate(4547678.470499599, 3271423.2915606056),
            new Coordinate(4548006.633320098, 3271559.898874255),
            new Coordinate(4548244.742157298, 3271665.2162445537),
            new Coordinate(4548340.138326047, 3271851.429565953),
            new Coordinate(4548653.800928898, 3271957.5101056043),
            new Coordinate(4548863.49293089, 3271996.431742456),
            new Coordinate(4549165.707993489, 3272004.063435956),
            new Coordinate(4549304.326045404, 3271885.4492536606),
            new Coordinate(4549533.788124904, 3271773.2622899543),
            new Coordinate(4549716.185599553, 3271871.7111361036),
            new Coordinate(4549904.454622969, 3271955.4259585696),
            new Coordinate(4550191.4062985685, 3271958.4786359696),
            new Coordinate(4550176.906080918, 3272188.192610319),
            new Coordinate(4550195.222145321, 3272488.1181648676),
            new Coordinate(4550171.592026379, 3272733.6511906204),
            new Coordinate(4550060.169301278, 3272932.0752216196),
            new Coordinate(4549730.480142079, 3272980.9180600196),
            new Coordinate(4549787.7178433295, 3273098.446139919),
            new Coordinate(4549863.271608979, 3273254.1326873186),
            new Coordinate(4550026.589849878, 3273370.1344285184),
            new Coordinate(4549937.491956537, 3273528.404121406),
            new Coordinate(4549652.8297889875, 3273513.9039037563));

    public static final String BERLIN_CENTER_TOUR = "Berlin center tour";
    public static final List<Coordinate> BERLIN_CENTER_TOUR_COORDINATES = List.of(
            new Coordinate(4550532.170561988, 3272837.3136903225),
            new Coordinate(4550390.20456434, 3272907.7001933577),
            new Coordinate(4550199.3259120425, 3272951.8408817016),
            new Coordinate(4550181.431038389, 3272592.7504170653),
            new Coordinate(4550169.501122618, 3272363.6960343085),
            new Coordinate(4550156.378215273, 3272123.9047273584),
            new Coordinate(4550195.870478335, 3271909.3322032564),
            new Coordinate(4550331.871518098, 3271744.699365649),
            new Coordinate(4550566.890858741, 3271774.5241550705),
            new Coordinate(4550800.717207805, 3271807.927919223),
            new Coordinate(4551097.772110445, 3271905.7532285256),
            new Coordinate(4551070.333304177, 3272104.982821862),
            new Coordinate(4551170.544596634, 3272264.843693162),
            new Coordinate(4551280.960997761, 3272477.0955800973),
            new Coordinate(4551165.240814805, 3272759.8345838143),
            new Coordinate(4551088.889353886, 3272844.5369857727),
            new Coordinate(4550898.010701588, 3272899.414598308),
            new Coordinate(4550579.481950565, 3272849.30895208));

    public static final String FERNSEHTURM_TOUR = "Fernsehturm tour";
    public static final List<Coordinate> FERNSEHTURM_TOUR_COORDINATES = List.of(
            new Coordinate(4552332.209023247, 3273572.9821219314),
            new Coordinate(4552237.803171511, 3273484.212440449),
            new Coordinate(4552112.398383382, 3273372.898077954),
            new Coordinate(4551990.516201664, 3273255.2430239255),
            new Coordinate(4551899.632956336, 3273158.0190870636),
            new Coordinate(4551737.201539302, 3273051.088351766),
            new Coordinate(4551558.253133773, 3272985.5678725755),
            new Coordinate(4551379.304728244, 3272942.592074397),
            new Coordinate(4551396.213239003, 3272831.982233185),
            new Coordinate(4551391.281590032, 3272697.4186683972),
            new Coordinate(4551260.94515293, 3272606.53542307),
            new Coordinate(4551289.126004194, 3272422.6553685702),
            new Coordinate(4551308.376834321, 3272288.3171882574),
            new Coordinate(4551417.215828128, 3272210.315909362),
            new Coordinate(4551607.079406214, 3272239.9443021207),
            new Coordinate(4551735.872215549, 3272255.060829037),
            new Coordinate(4551939.038337323, 3272375.9930443782),
            new Coordinate(4552076.901062813, 3272536.832890783),
            new Coordinate(4552164.5769189345, 3272665.0210390445),
            new Coordinate(4552268.108832603, 3272822.3677885407),
            new Coordinate(4552372.715198875, 3272976.5563630993),
            new Coordinate(4552439.227917314, 3273134.3729041195),
            new Coordinate(4552563.183438037, 3273230.5140153165),
            new Coordinate(4552681.092347994, 3273321.817837899),
            new Coordinate(4552763.930915505, 3273445.168697547),
            new Coordinate(4552754.256338277, 3273579.403456576),
            new Coordinate(4552624.858867862, 3273668.288634852));

    static {
        districtFillColors.put("Berlin Lichtenberg", "rgba(214, 96, 204, 0.1)");
        districtStrokeColors.put("Berlin Lichtenberg", "rgba(173, 0, 159, 0.2)");

        districtFillColors.put("Berlin Marzahn-Hellersdorf", "rgba(104, 156, 210, 0.1)");
        districtStrokeColors.put("Berlin Marzahn-Hellersdorf", "rgba(13, 88, 166, 0.2)");

        districtFillColors.put("Berlin Treptow-Köpenick", "rgba(83, 78, 217, 0.1)");
        districtStrokeColors.put("Berlin Treptow-Köpenick", "rgba(31, 26, 178, 0.2)");

        districtFillColors.put("Berlin Neukölln", "rgba(255, 210, 115, 0.1)");
        districtStrokeColors.put("Berlin Neukölln", "rgba(255, 173, 0, 0.2)");

        districtFillColors.put("Berlin Tempelhof-Schöneberg", "rgba(143, 162, 0, 0.1)");
        districtStrokeColors.put("Berlin Tempelhof-Schöneberg", "rgba(220, 249, 0, 0.2)");

        districtFillColors.put("Berlin Steglitz-Zehlendorf", "rgba(19, 137, 0, 0.1)");
        districtStrokeColors.put("Berlin Steglitz-Zehlendorf", "rgba(29, 211, 0, 0.2)");

        districtFillColors.put("Berlin Charlottenburg-Wilmersdor", "rgba(201, 54, 211, 0.1)");
        districtStrokeColors.put("Berlin Charlottenburg-Wilmersdor", "rgba(156, 2, 167, 0.2)");

        districtFillColors.put("Berlin Spandau", "rgba(129, 0, 81, 0.1)");
        districtStrokeColors.put("Berlin Spandau", "rgba(199, 0, 125, 0.2)");

        districtFillColors.put("Berlin Reinickendorf", "rgba(117, 238, 59, 0.1)");
        districtStrokeColors.put("Berlin Reinickendorf", "rgba(72, 221, 0, 0.2)");

        districtFillColors.put("Berlin Pankow", "rgba(70, 113, 213, 0.1)");
        districtStrokeColors.put("Berlin Pankow", "rgba(18, 64, 171, 0.2)");

        districtFillColors.put("Berlin Mitte", "rgba(191, 118, 48, 0.1)");
        districtStrokeColors.put("Berlin Mitte", "rgba(166, 81, 0, 0.2)");

        districtFillColors.put("Berlin Friedrichshain-Kreuzberg", "rgba(255, 69, 64, 0.1)");
        districtStrokeColors.put("Berlin Friedrichshain-Kreuzberg", "rgba(255, 7, 0, 0.2)");
    }

    public static String getDistrictFillColor(String name) {
        return districtFillColors.get(name);
    }

    public static String getDistrictStrokeColor(String name) {
        return districtStrokeColors.get(name);
    }

    public static City getBerlinCityFrom(List<City> cities) {
        return cities.stream().filter(c -> c.getName().equals("Berlin"))
                .findAny().orElseThrow(() -> new IllegalStateException("No Berlin in collection"));
    }

    private TourExplorerUtils() {
    }


}
